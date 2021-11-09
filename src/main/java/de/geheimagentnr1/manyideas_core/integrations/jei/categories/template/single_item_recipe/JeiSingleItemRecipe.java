package de.geheimagentnr1.manyideas_core.integrations.jei.categories.template.single_item_recipe;

import de.geheimagentnr1.manyideas_core.elements.recipes.single_item_recipes.SingleItemRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;

import java.util.*;
import java.util.function.Function;


@SuppressWarnings( "AbstractClassWithoutAbstractMethods" )
public abstract class JeiSingleItemRecipe<T extends SingleItemRecipe> {
	
	
	private final List<ItemStack> input;
	
	private final ItemStack result;
	
	protected JeiSingleItemRecipe( T recipe ) {
		
		input = Arrays.asList( recipe.getIngredient().getMatchingStacks() );
		result = recipe.getRecipeOutput();
	}
	
	@SuppressWarnings( "unchecked" )
	protected static <R extends SingleItemRecipe, J extends JeiSingleItemRecipe<R>> List<J> getRecipes(
		IRecipeType<R> recipeType,
		Function<R, J> jeiRecipeBuilder ) {
		
		ClientWorld world = Minecraft.getInstance().world;
		ArrayList<J> jeiRecipes = new ArrayList<>();
		Objects.requireNonNull( world )
			.getRecipeManager()
			.getRecipes()
			.stream()
			.filter( iRecipe -> iRecipe.getType() == recipeType )
			.forEach( iRecipe -> jeiRecipes.add( jeiRecipeBuilder.apply( (R)iRecipe ) ) );
		return jeiRecipes;
	}
	
	//package-private
	List<List<ItemStack>> getInputs() {
		
		return Collections.singletonList( input );
	}
	
	public ItemStack getResult() {
		
		return result;
	}
}
