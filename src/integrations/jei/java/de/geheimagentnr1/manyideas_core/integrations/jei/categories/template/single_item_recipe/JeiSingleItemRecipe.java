package de.geheimagentnr1.manyideas_core.integrations.jei.categories.template.single_item_recipe;

import de.geheimagentnr1.manyideas_core.elements.recipes.single_item_recipes.SingleItemRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


@SuppressWarnings( "AbstractClassWithoutAbstractMethods" )
public abstract class JeiSingleItemRecipe<T extends SingleItemRecipe> {
	
	
	@NotNull
	private final List<ItemStack> input;
	
	@NotNull
	private final ItemStack result;
	
	protected JeiSingleItemRecipe( @NotNull T recipe ) {
		
		input = Arrays.asList( recipe.getIngredient().getItems() );
		result = recipe.getResult();
	}
	
	@NotNull
	protected static <R extends SingleItemRecipe, J extends JeiSingleItemRecipe<R>> List<J> getRecipes(
		@NotNull RecipeType<R> recipe,
		@NotNull Function<R, J> jeiRecipeBuilder ) {
		
		return Optional.ofNullable( Minecraft.getInstance().level )
			.map( level -> level.getRecipeManager().getAllRecipesFor( recipe ) )
			.orElseGet( ArrayList::new )
			.stream()
			.map( jeiRecipeBuilder )
			.collect( Collectors.toList() );
	}
	
	//package-private
	@NotNull
	List<List<ItemStack>> getInputs() {
		
		return Collections.singletonList( input );
	}
	
	@NotNull
	public ItemStack getResult() {
		
		return result;
	}
}
