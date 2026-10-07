package de.geheimagentnr1.manyideas_core.integrations.jei.categories.dyed;

import de.geheimagentnr1.manyideas_core.elements.block_state_properties.Color;
import de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.dyed.DyeBlockItem;
import de.geheimagentnr1.manyideas_core.elements.recipes.ClientRecipes;
import de.geheimagentnr1.manyideas_core.elements.recipes.ModRecipeTypesRegisterFactory;
import de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes.ColorIngredient;
import de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes.DyedRecipe;
import de.geheimagentnr1.manyideas_core.util.DyeBlockHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jetbrains.annotations.NotNull;


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
		
		ItemStack result = recipe.getResult().create();
		if( !result.isEmpty() ) {
			for( Color color : Color.values() ) {
				ArrayList<List<ItemStack>> inputs = new ArrayList<>();
				for( Ingredient ingredient : recipe.getIngredients() ) {
					ArrayList<ItemStack> itemStacks = new ArrayList<>();
					if( ingredient.getCustomIngredient() instanceof ColorIngredient<?> colorIngredient ) {
						ItemStack colorStack = colorIngredient.getStack( color );
						if( colorStack != null ) {
							itemStacks.add( colorStack );
						}
					} else {
						List<ItemStack> matchingStacks = ingredient.items().map( ItemStack::new ).toList();
						if( matchingStacks.size() == 1 && matchingStacks.get( 0 ).getItem() instanceof DyeBlockItem ) {
							for( Color matchingStackColor : Color.values() ) {
								itemStacks.add( DyeBlockHelper.setColor(
									matchingStacks.get( 0 ).copy(),
									matchingStackColor
								) );
							}
						} else {
							ingredient.items().map( ItemStack::new ).forEach( itemStacks::add );
						}
					}
					inputs.add( itemStacks );
				}
				recipes.add( new JeiDyedRecipe( inputs, DyeBlockHelper.setColor( result.copy(), color ) ) );
			}
		}
		return recipes;
	}
	
	@NotNull
	public static List<JeiDyedRecipe> getRecipes() {
		
		return ClientRecipes.byType( ModRecipeTypesRegisterFactory.DYED )
			.stream()
			.map( RecipeHolder::value )
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
