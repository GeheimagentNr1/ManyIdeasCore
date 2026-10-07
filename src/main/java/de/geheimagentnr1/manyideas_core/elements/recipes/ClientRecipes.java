package de.geheimagentnr1.manyideas_core.elements.recipes;

import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeMap;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.client.event.RecipesReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;


//Recipes are not sent to the client since 1.21.2, the server sends the recipe types requested in
//TableSawRecipesSyncMsg.SyncHandler, this class keeps them for recipe viewers like JEI
public class ClientRecipes {
	
	
	@NotNull
	private static RecipeMap recipeMap = RecipeMap.EMPTY;
	
	public static void handleRecipesReceivedEvent( @NotNull RecipesReceivedEvent event ) {
		
		recipeMap = event.getRecipeMap();
	}
	
	@NotNull
	public static <I extends RecipeInput, T extends Recipe<I>> Collection<RecipeHolder<T>> byType(
		@NotNull RecipeType<T> recipeType ) {
		
		return recipeMap.byType( recipeType );
	}
}
