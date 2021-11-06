package de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes;

import de.geheimagentnr1.manyideas_core.elements.block_state_properties.Color;
import de.geheimagentnr1.manyideas_core.elements.recipes.RecipeTypes;
import de.geheimagentnr1.manyideas_core.util.DyeBlockHelper;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Optional;


public class DyedRecipe implements IRecipe<CraftingInventory> {
	
	
	public static final String registry_name = "dyed";
	
	private final ResourceLocation id;
	
	private final IRecipeSerializer<?> serializer;
	
	private final boolean shaped;
	
	private final NonNullList<Ingredient> ingredients;
	
	private final ItemStack result;
	
	private final int recipeWidth;
	
	private final int recipeHeight;
	
	//package-private
	DyedRecipe(
		ResourceLocation _id,
		IRecipeSerializer<?> _serializer,
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
	public boolean matches( @Nonnull CraftingInventory inv, @Nonnull World level ) {
		
		if( !findMatchingColor( inv ).isPresent() ) {
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
	
	private Optional<Color> findMatchingColor( CraftingInventory inv ) {
		
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
	public ItemStack assemble( @Nonnull CraftingInventory inv ) {
		
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
	public ItemStack getResultItem() {
		
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
