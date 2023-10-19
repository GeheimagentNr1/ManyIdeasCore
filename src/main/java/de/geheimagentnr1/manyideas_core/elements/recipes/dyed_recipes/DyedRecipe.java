package de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes;

import de.geheimagentnr1.manyideas_core.elements.block_state_properties.Color;
import de.geheimagentnr1.manyideas_core.elements.recipes.ModRecipeSerializersRegisterFactory;
import de.geheimagentnr1.manyideas_core.elements.recipes.ModRecipeTypesRegisterFactory;
import de.geheimagentnr1.manyideas_core.util.DyeBlockHelper;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class DyedRecipe implements Recipe<CraftingContainer> {
	
	
	@NotNull
	public static final String registry_name = "dyed";
	
	private final boolean shaped;
	
	@NotNull
	private final NonNullList<Ingredient> ingredients;
	
	@NotNull
	private final ItemStack result;
	
	private final int recipeWidth;
	
	private final int recipeHeight;
	
	//package-private
	DyedRecipe(
		boolean _shaped,
		@NotNull NonNullList<Ingredient> _ingredients,
		@NotNull ItemStack _result,
		int _recipeWidth,
		int _recipeHeight ) {
		
		shaped = _shaped;
		ingredients = _ingredients;
		result = _result;
		recipeWidth = _recipeWidth;
		recipeHeight = _recipeHeight;
	}
	
	@Override
	public boolean matches( @NotNull CraftingContainer inv, @NotNull Level level ) {
		
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
	
	@NotNull
	private Optional<Color> findMatchingColor( @NotNull CraftingContainer inv ) {
		
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
	
	@NotNull
	@Override
	public ItemStack assemble( @NotNull CraftingContainer inv, @NotNull RegistryAccess registryAccess ) {
		
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
	@NotNull
	@Override
	public ItemStack getResultItem( @NotNull RegistryAccess registryAccess ) {
		
		return ItemStack.EMPTY;
	}
	
	@NotNull
	@Override
	public RecipeType<?> getType() {
		
		return ModRecipeTypesRegisterFactory.DYED;
	}
	
	@NotNull
	@Override
	public RecipeSerializer<?> getSerializer() {
		
		return ModRecipeSerializersRegisterFactory.DYED;
	}
	
	//package-private
	boolean isShaped() {
		
		return shaped;
	}
	
	@NotNull
	@Override
	public NonNullList<Ingredient> getIngredients() {
		
		return ingredients;
	}
	
	@NotNull
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
	
	static String[] shrink( List<String> p_299210_ ) {
		
		int i = Integer.MAX_VALUE;
		int j = 0;
		int k = 0;
		int l = 0;
		
		for( int i1 = 0; i1 < p_299210_.size(); ++i1 ) {
			String s = p_299210_.get( i1 );
			i = Math.min( i, firstNonSpace( s ) );
			int j1 = lastNonSpace( s );
			j = Math.max( j, j1 );
			if( j1 < 0 ) {
				if( k == i1 ) {
					++k;
				}
				
				++l;
			} else {
				l = 0;
			}
		}
		
		if( p_299210_.size() == l ) {
			return new String[0];
		} else {
			String[] astring = new String[p_299210_.size() - l - k];
			
			for( int k1 = 0; k1 < astring.length; ++k1 ) {
				astring[k1] = p_299210_.get( k1 + k ).substring( i, j + 1 );
			}
			
			return astring;
		}
	}
	
	private static int firstNonSpace( String p_44185_ ) {
		
		int i;
		for( i = 0; i < p_44185_.length() && p_44185_.charAt( i ) == ' '; ++i ) {
		}
		
		return i;
	}
	
	private static int lastNonSpace( String p_44201_ ) {
		
		int i;
		for( i = p_44201_.length() - 1; i >= 0 && p_44201_.charAt( i ) == ' '; --i ) {
		}
		
		return i;
	}
}
