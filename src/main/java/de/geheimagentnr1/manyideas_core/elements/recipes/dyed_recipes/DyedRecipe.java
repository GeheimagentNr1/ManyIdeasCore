package de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes;

import de.geheimagentnr1.manyideas_core.elements.block_state_properties.Color;
import de.geheimagentnr1.manyideas_core.elements.recipes.RecipeTypes;
import de.geheimagentnr1.manyideas_core.util.DyeBlockHelper;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Optional;


public class DyedRecipe implements Recipe<CraftingContainer> {
	
	
	public static final String registry_name = "dyed";
	
	private final ResourceLocation id;
	
	private final RecipeSerializer<?> serializer;
	
	private final boolean shaped;
	
	private final NonNullList<Ingredient> ingredients;
	
	private final ItemStack result;
	
	private final int recipeWidth;
	
	private final int recipeHeight;
	
	//package-private
	DyedRecipe(
		ResourceLocation _id,
		RecipeSerializer<?> _serializer,
		boolean _shaped,
		NonNullList<Ingredient> _ingredients,
		ItemStack _result,
		int _recipeWidth,
		int _recipeHeight ) {
		
		id = _id;
		serializer = _serializer;
		shaped = _shaped;
		ingredients = _ingredients;
		result = _result;
		recipeWidth = _recipeWidth;
		recipeHeight = _recipeHeight;
	}
	
	@Override
	public boolean matches( @Nonnull CraftingContainer inv, @Nonnull Level level ) {
		
		if( findMatchingColor( inv ).isEmpty() ) {
			return false;
		}
		if( shaped ) {
			for( int i = 0; i < ingredients.size(); i++ ) {
				if( !ingredients.get( i ).test( inv.getItem( i ) ) ) {
					return false;
				}
			}
			return true;
		} else {
			ArrayList<ItemStack> inputItems = new ArrayList<>();
			for( int i = 0; i < inv.getContainerSize(); i++ ) {
				if( !inv.getItem( i ).isEmpty() ) {
					inputItems.add( inv.getItem( i ) );
				}
			}
			int found = 0;
			for( Ingredient ingredient : ingredients ) {
				for( int j = 0; j < inputItems.size(); j++ ) {
					if( ingredient.test( inputItems.get( j ) ) ) {
						inputItems.remove( j );
						found++;
						break;
					}
				}
			}
			return found == ingredients.size() && inputItems.isEmpty();
		}
	}
	
	private Optional<Color> findMatchingColor( CraftingContainer inv ) {
		
		Color color = null;
		for( Ingredient ingredient : ingredients ) {
			if( ingredient instanceof ColorIngredient ) {
				for( int j = 0; j < inv.getContainerSize(); j++ ) {
					if( ingredient.test( inv.getItem( j ) ) ) {
						ColorIngredient colorIngredient = (ColorIngredient)ingredient;
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
	
	@Nonnull
	@Override
	public ItemStack assemble( @Nonnull CraftingContainer inv, @NotNull RegistryAccess registryAccess ) {
		
		Optional<Color> color = findMatchingColor( inv );
		return color.map( value -> DyeBlockHelper.setColorToItemStack( result.copy(), value ) )
			.orElse( ItemStack.EMPTY );
	}
	
	/**
	 * Used to determine if this recipe can fit in a grid of the given width/height
	 */
	@Override
	public boolean canCraftInDimensions( int width, int height ) {
		
		if( shaped ) {
			return width >= recipeWidth && height >= recipeHeight;
		} else {
			return width * height <= ingredients.size();
		}
	}
	
	/**
	 * Get the result of this recipe, usually for display purposes (e.g. recipe book). If your recipe has more than one
	 * possible result (e.g. it's dynamic and depends on its inputs), then return an empty stack.
	 */
	@Nonnull
	@Override
	public ItemStack getResultItem( @NotNull RegistryAccess registryAccess ) {
		
		return ItemStack.EMPTY;
	}
	
	@Nonnull
	@Override
	public RecipeType<?> getType() {
		
		return RecipeTypes.DYED;
	}
	
	@Nonnull
	@Override
	public ResourceLocation getId() {
		
		return id;
	}
	
	@Nonnull
	@Override
	public RecipeSerializer<?> getSerializer() {
		
		return serializer;
	}
	
	//package-private
	boolean isShaped() {
		
		return shaped;
	}
	
	@Nonnull
	@Override
	public NonNullList<Ingredient> getIngredients() {
		
		return ingredients;
	}
	
	public ItemStack getResult() {
		
		return result;
	}
	
	//package-private
	int getRecipeWidth() {
		
		return recipeWidth;
	}
	
	//package-private
	int getRecipeHeight() {
		
		return recipeHeight;
	}
}
