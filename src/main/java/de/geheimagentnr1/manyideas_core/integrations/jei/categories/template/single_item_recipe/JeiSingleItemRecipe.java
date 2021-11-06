package de.geheimagentnr1.manyideas_core.integrations.jei.categories.template.single_item_recipe;

import de.geheimagentnr1.manyideas_core.elements.recipes.single_item_recipes.SingleItemRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


@SuppressWarnings( "AbstractClassWithoutAbstractMethods" )
public abstract class JeiSingleItemRecipe<T extends SingleItemRecipe> {
	
	
	private final List<ItemStack> input;
	
	private final ItemStack result;
	
	protected JeiSingleItemRecipe( T recipe ) {
		
		input = Arrays.asList( recipe.getIngredient().getItems() );
		result = recipe.getResultItem();
	}
	
	protected static <R extends SingleItemRecipe, J extends JeiSingleItemRecipe<R>> List<J> getRecipes(
		IRecipeType<R> recipe,
		Function<R, J> jeiRecipeBuilder ) {
		
		return Optional.ofNullable( Minecraft.getInstance().level )
			.map( level -> level.getRecipeManager().getAllRecipesFor( recipe ) )
			.orElseGet( ArrayList::new )
			.stream()
			.map( jeiRecipeBuilder )
			.collect( Collectors.toList());
	}
	
	//package-private
	List<List<ItemStack>> getInputs() {
		
		return Collections.singletonList( input );
	}
	
	public ItemStack getResult() {
		
		return result;
	}
}
