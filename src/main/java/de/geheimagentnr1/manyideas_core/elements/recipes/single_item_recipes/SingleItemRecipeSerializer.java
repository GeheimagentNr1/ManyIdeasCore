package de.geheimagentnr1.manyideas_core.elements.recipes.single_item_recipes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class SingleItemRecipeSerializer<T extends SingleItemRecipe> implements RecipeSerializer<T> {
	
	
	private static final MapCodec<ItemStack> RESULT_CODEC = RecordCodecBuilder.mapCodec( builder -> builder.group(
		BuiltInRegistries.ITEM.byNameCodec().fieldOf( "result" ).forGetter( ItemStack::getItem ),
		ExtraCodecs.strictOptionalField( ExtraCodecs.POSITIVE_INT, "count", 1 ).forGetter( ItemStack::getCount )
	).apply( builder, ItemStack::new ) );
	
	@NotNull
	private final ISingleItemRecipeFactory<T> factory;
	
	private final Codec<T> codec;
	
	public SingleItemRecipeSerializer( @NotNull ISingleItemRecipeFactory<T> _factory ) {
		
		factory = _factory;
		codec = RecordCodecBuilder.create( ( builder ) -> builder.group(
			ExtraCodecs.strictOptionalField( Codec.STRING, "group", "" ).forGetter( SingleItemRecipe::getGroup ),
			Ingredient.CODEC_NONEMPTY.fieldOf( "ingredient" ).forGetter( SingleItemRecipe::getIngredient ),
			RESULT_CODEC.forGetter( SingleItemRecipe::getResult )
		).apply( builder, factory::create ) );
	}
	
	@Override
	public Codec<T> codec() {
		
		return codec;
	}
	
	@Nullable
	@Override
	public T fromNetwork( @NotNull FriendlyByteBuf buffer ) {
		
		String group = buffer.readUtf();
		Ingredient ingredient = Ingredient.fromNetwork( buffer );
		ItemStack result = buffer.readItem();
		return factory.create( group, ingredient, result );
	}
	
	@Override
	public void toNetwork( @NotNull FriendlyByteBuf buffer, @NotNull T recipe ) {
		
		buffer.writeUtf( recipe.getGroup() );
		recipe.ingredient.toNetwork( buffer );
		buffer.writeItem( recipe.result );
	}
	
}
