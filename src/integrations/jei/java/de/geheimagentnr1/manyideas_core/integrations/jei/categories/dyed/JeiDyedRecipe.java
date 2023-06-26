package de.geheimagentnr1.manyideas_core.integrations.jei.categories.dyed;

import de.geheimagentnr1.manyideas_core.elements.block_state_properties.Color;
import de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.dyed.DyeBlockItem;
import de.geheimagentnr1.manyideas_core.elements.recipes.ModRecipeTypesRegisterFactory;
import de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes.ColorIngredient;
import de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes.DyedRecipe;
import de.geheimagentnr1.manyideas_core.util.DyeBlockHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class JeiDyedRecipe {
	
	
	@NotNull
	private final List<List<ItemStack>> inputs;
	
	@NotNull
	private final ItemStack result;
	
	private JeiDyedRecipe( @NotNull List<List<ItemStack>> _inputs, @NotNull ItemStack _result ) {
		
		inputs = _inputs;
		result = _result;
	}
	
	@NotNull
	private static ArrayList<JeiDyedRecipe> create( @NotNull DyedRecipe recipe ) {
		
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
	
	@NotNull
	public static List<JeiDyedRecipe> getRecipes() {
		
		return Optional.ofNullable( Minecraft.getInstance().level )
			.map( clientWorld -> clientWorld.getRecipeManager().getAllRecipesFor( ModRecipeTypesRegisterFactory.DYED ) )
			.orElseGet( ArrayList::new )
			.stream()
			.map( JeiDyedRecipe::create )
			.flatMap( List::stream )
			.collect( Collectors.toList() );
	}
	
	//package-private
	@NotNull
	List<List<ItemStack>> getInputs() {
		
		return inputs;
	}
	
	@NotNull
	public ItemStack getResult() {
		
		return result;
	}
}
