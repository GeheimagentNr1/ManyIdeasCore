package de.geheimagentnr1.manyideas_core.integrations.jei.categories.dyed;

import de.geheimagentnr1.manyideas_core.elements.block_state_properties.Color;
import de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.dyed.DyeBlockItem;
import de.geheimagentnr1.manyideas_core.elements.recipes.RecipeTypes;
import de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes.ColorIngredient;
import de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes.DyedRecipe;
import de.geheimagentnr1.manyideas_core.util.DyeBlockHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Objects;


public class JeiDyedRecipe {
	
	
	private final List<List<ItemStack>> inputs;
	
	private final ItemStack result;
	
	private JeiDyedRecipe( List<List<ItemStack>> _inputs, ItemStack _result ) {
		
		inputs = _inputs;
		result = _result;
	}
	
	private static ArrayList<JeiDyedRecipe> create( DyedRecipe recipe ) {
		
		ArrayList<JeiDyedRecipe> recipes = new ArrayList<>();
		
		ItemStack result = recipe.getResult();
		if( !result.isEmpty() ) {
			for( Color color : Color.values() ) {
				ArrayList<List<ItemStack>> inputs = new ArrayList<>();
				for( Ingredient ingredient : recipe.getIngredients() ) {
					ArrayList<ItemStack> itemStacks = new ArrayList<>();
					if( ingredient instanceof ColorIngredient ) {
						ItemStack colorStack = ( (ColorIngredient)ingredient ).getStack( color );
						if( colorStack != null ) {
							itemStacks.add( colorStack );
						}
					} else {
						ItemStack[] matchingStacks = ingredient.getItems();
						if( matchingStacks.length == 1 && matchingStacks[0].getItem() instanceof DyeBlockItem ) {
							for( Color matchingStackColor : Color.values() ) {
								itemStacks.add( DyeBlockHelper.setColorToItemStack(
									matchingStacks[0].copy(),
									matchingStackColor
								) );
							}
						} else {
							itemStacks.addAll( Arrays.asList( ingredient.getItems() ) );
						}
					}
					inputs.add( itemStacks );
				}
				recipes.add( new JeiDyedRecipe( inputs, DyeBlockHelper.setColorToItemStack( result.copy(), color ) ) );
			}
		}
		return recipes;
	}
	
	public static List<JeiDyedRecipe> getRecipes() {
		
		return Optional.ofNullable( Minecraft.getInstance().level )
			.map( clientWorld -> clientWorld.getRecipeManager().getAllRecipesFor( RecipeTypes.DYED ) )
			.orElseGet( ArrayList::new )
			.stream()
			.map( JeiDyedRecipe::create )
			.flatMap( List::stream )
			.collect( Collectors.toList() );
	}
	
	//package-private
	List<List<ItemStack>> getInputs() {
		
		return inputs;
	}
	
	public ItemStack getResult() {
		
		return result;
	}
}
