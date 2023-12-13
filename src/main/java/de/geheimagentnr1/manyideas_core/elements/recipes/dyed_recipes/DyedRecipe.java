package de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import de.geheimagentnr1.manyideas_core.elements.block_state_properties.Color;
import de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.dyed.DyeBlockItem;
import de.geheimagentnr1.manyideas_core.util.DyeBlockHelper;
import lombok.Getter;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;


public abstract class DyedRecipe implements Recipe<CraftingContainer> {
	
	
	@NotNull
	public static final String registry_name = "dyed";
	
	static final Codec<Item> DYE_BLOCK_ITEM_CODEC = ExtraCodecs.validate(
		BuiltInRegistries.ITEM.byNameCodec(),
		( item ) -> item instanceof DyeBlockItem
			? DataResult.success( item )
			: DataResult.error( () -> "Non DyeBlockItem result not allowed here" )
	);
	
	static final Codec<ItemStack> RESULT_CODEC = RecordCodecBuilder.create( builder -> builder.group(
		DYE_BLOCK_ITEM_CODEC.fieldOf( "item" ).forGetter( ItemStack::getItem ),
		ExtraCodecs.strictOptionalField( ExtraCodecs.POSITIVE_INT, "count", 1 ).forGetter( ItemStack::getCount )
	).apply( builder, ItemStack::new ) );
	
	@NotNull
	final NonNullList<Ingredient> ingredients;
	
	@NotNull
	@Getter
	final ItemStack result;
	
	DyedRecipe(
		@NotNull NonNullList<Ingredient> _ingredients,
		@NotNull ItemStack _result ) {
		
		ingredients = _ingredients;
		result = _result;
	}
	
	@NotNull
	Optional<Color> findMatchingColor( @NotNull CraftingContainer inv ) {
		
		Color color = null;
		for( Ingredient ingredient : ingredients ) {
			if( ingredient instanceof ColorIngredient ) {
				for( int j = 0; j < inv.getContainerSize(); j++ ) {
					if( ingredient.test( inv.getItem( j ) ) ) {
						ColorIngredient<?> colorIngredient = (ColorIngredient<?>)ingredient;
						Color newColor = colorIngredient.getColor( inv.getItem( j ) );
						if( newColor == null ) {
							return Optional.empty();
						}
						if( color == null ) {
							color = newColor;
						} else {
							if( color != newColor ) {
								return Optional.empty();
							}
						}
						break;
					}
				}
			}
		}
		return color == null ? Optional.empty() : Optional.of( color );
	}
	
	@NotNull
	@Override
	public ItemStack assemble( @NotNull CraftingContainer inv, @NotNull RegistryAccess registryAccess ) {
		
		Optional<Color> color = findMatchingColor( inv );
		return color.map( value -> DyeBlockHelper.setColorToItemStack( result.copy(), value ) )
			.orElse( ItemStack.EMPTY );
	}
	
	/**
	 * Get the result of this recipe, usually for display purposes (e.g. recipe book). If your recipe has more than one
	 * possible result (e.g. it's dynamic and depends on its inputs), then return an empty stack.
	 */
	@NotNull
	@Override
	public ItemStack getResultItem( @NotNull RegistryAccess registryAccess ) {
		
		return ItemStack.EMPTY;
	}
}
