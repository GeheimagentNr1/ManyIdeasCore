package de.geheimagentnr1.manyideascore.elements.recipes.dyed_recipes;

import de.geheimagentnr1.manyideascore.elements.block_state_properties.Color;
import de.geheimagentnr1.manyideascore.elements.recipes.RecipeTypes;
import de.geheimagentnr1.manyideascore.util.DyeBlockHelper;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Optional;


public class DyedRecipe implements IRecipe<CraftingInventory> {
	
	
	public final static String registry_name = "dyed";
	
	private final ResourceLocation id;
	
	private final IRecipeSerializer<?> serializer;
	
	private final boolean shaped;
	
	private final NonNullList<Ingredient> ingredients;
	
	private final ItemStack result;
	
	private final int recipeWidth;
	
	private final int recipeHeight;
	
	public DyedRecipe( ResourceLocation _id, IRecipeSerializer<?> _serializer, boolean _shaped,
		NonNullList<Ingredient> _ingredients, ItemStack _result, int _recipeWidth, int _recipeHeight ) {
		
		id = _id;
		serializer = _serializer;
		shaped = _shaped;
		ingredients = _ingredients;
		result = _result;
		recipeWidth = _recipeWidth;
		recipeHeight = _recipeHeight;
	}
	
	@Override
	public boolean matches( @Nonnull CraftingInventory inv, @Nonnull World worldIn ) {
		
		if( !findMatchingColor( inv ).isPresent() ) {
			return false;
		}
		if( shaped ) {
			for( int i = 0; i < ingredients.size(); i++ ) {
				if( !ingredients.get( i ).test( inv.getStackInSlot( i ) ) ) {
					return false;
				}
			}
			return true;
		} else {
			ArrayList<ItemStack> inputItems = new ArrayList<>();
			for( int i = 0; i < inv.getSizeInventory(); i++ ) {
				if( !inv.getStackInSlot( i ).isEmpty() ) {
					inputItems.add( inv.getStackInSlot( i ) );
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
	
	private Optional<Color> findMatchingColor( CraftingInventory inv ) {
		
		Color color = null;
		for( Ingredient ingredient : ingredients ) {
			if( ingredient instanceof ColorIngredient ) {
				for( int j = 0; j < inv.getSizeInventory(); j++ ) {
					if( ingredient.test( inv.getStackInSlot( j ) ) ) {
						ColorIngredient colorIngredient = (ColorIngredient)ingredient;
						Color newColor = colorIngredient.getColor( inv.getStackInSlot( j ) );
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
		/*for( int i = 0; i < ingredients.size(); i++ ) {
			Ingredient ingredient = ingredients.get( i );
			if( ingredient instanceof ColorIngredient ) {
				ColorIngredient colorIngredient = (ColorIngredient)ingredient;
				Color newColor = colorIngredient.getColor( inv.getStackInSlot( i ) );
				if( newColor == null ) {
					return Optional.empty();
				} else {
					if( color == null ) {
						color = newColor;
					} else {
						if( color != newColor ) {
							return Optional.empty();
						}
					}
				}
			}
		}*/
		return color == null ? Optional.empty() : Optional.of( color );
	}
	
	@Nonnull
	@Override
	public ItemStack getCraftingResult( @Nonnull CraftingInventory inv ) {
		
		Optional<Color> color = findMatchingColor( inv );
		return color.map( value -> DyeBlockHelper.setColorToItemStack( result.copy(), value ) )
			.orElse( ItemStack.EMPTY );
	}
	
	/**
	 * Used to determine if this recipe can fit in a grid of the given width/height
	 */
	@Override
	public boolean canFit( int width, int height ) {
		
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
	public ItemStack getRecipeOutput() {
		
		return ItemStack.EMPTY;
	}
	
	@Nonnull
	@Override
	public IRecipeType<?> getType() {
		
		return RecipeTypes.DYED;
	}
	
	@Nonnull
	@Override
	public ResourceLocation getId() {
		
		return id;
	}
	
	@Nonnull
	@Override
	public IRecipeSerializer<?> getSerializer() {
		
		return serializer;
	}
	
	public boolean isShaped() {
		
		return shaped;
	}
	
	@Override
	public NonNullList<Ingredient> getIngredients() {
		
		return ingredients;
	}
	
	public ItemStack getResult() {
		
		return result;
	}
	
	public int getRecipeWidth() {
		
		return recipeWidth;
	}
	
	public int getRecipeHeight() {
		
		return recipeHeight;
	}
}
