package de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes;

import de.geheimagentnr1.manyideas_core.elements.recipes.ModRecipeSerializersRegisterFactory;
import de.geheimagentnr1.manyideas_core.elements.recipes.ModRecipeTypesRegisterFactory;
import net.minecraft.core.NonNullList;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.RecipeMatcher;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class DyedShapelessRecipe extends DyedRecipe {
	
	
	@NotNull
	public static final String registry_name = "dyed_shapeless";
	
	//package-private
	DyedShapelessRecipe(
		@NotNull NonNullList<Ingredient> _ingredients,
		@NotNull ItemStack _result ) {
		
		super( _ingredients, _result );
	}
	
	@Override
	public boolean matches( @NotNull CraftingContainer inv, @NotNull Level level ) {
		
		if( findMatchingColor( inv ).isEmpty() ) {
			return false;
		}
		ArrayList<ItemStack> inputItems = new ArrayList<>();
		for( int i = 0; i < inv.getContainerSize(); i++ ) {
			if( !inv.getItem( i ).isEmpty() ) {
				inputItems.add( inv.getItem( i ) );
			}
		}
		/*int found = 0;
		for( Ingredient ingredient : ingredients ) {
			for( int j = 0; j < inputItems.size(); j++ ) {
				if( ingredient.test( inputItems.get( j ) ) ) {
					inputItems.remove( j );
					found++;
					break;
				}
			}
		}*/
		return inputItems.size() == ingredients.size() && RecipeMatcher.findMatches( inputItems, ingredients ) != null;
	}
	
	/**
	 * Used to determine if this recipe can fit in a grid of the given width/height
	 */
	@Override
	public boolean canCraftInDimensions( int width, int height ) {
		
		return ( width * height ) >= ingredients.size();
	}
	
	@NotNull
	@Override
	public RecipeType<?> getType() {
		
		return ModRecipeTypesRegisterFactory.DYED;
	}
	
	@NotNull
	@Override
	public RecipeSerializer<?> getSerializer() {
		
		return ModRecipeSerializersRegisterFactory.DYED_SHAPELESS;
	}
	
	@NotNull
	@Override
	public NonNullList<Ingredient> getIngredients() {
		
		return ingredients;
	}
}
