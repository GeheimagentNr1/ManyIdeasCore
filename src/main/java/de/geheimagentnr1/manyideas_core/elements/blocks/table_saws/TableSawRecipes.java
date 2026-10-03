package de.geheimagentnr1.manyideas_core.elements.blocks.table_saws;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;


//Since Minecraft 1.21.2 recipes are no longer synced to the client, but the table saw menus need them on both sides.
//The server sends them with TableSawRecipesSyncMsg, the client keeps them here.
public class TableSawRecipes {
	
	
	@NotNull
	private static List<TableSawRecipe> clientRecipes = List.of();
	
	@NotNull
	public static List<TableSawRecipe> getRecipes( @NotNull Level level ) {
		
		if( level instanceof ServerLevel serverLevel ) {
			return getServerRecipes( serverLevel );
		}
		return clientRecipes;
	}
	
	@NotNull
	public static List<TableSawRecipe> getServerRecipes( @NotNull ServerLevel level ) {
		
		return level.recipeAccess().getRecipes().stream()
			.map( RecipeHolder::value )
			.filter( TableSawRecipe.class::isInstance )
			.map( TableSawRecipe.class::cast )
			.toList();
	}
	
	public static void setClientRecipes( @NotNull List<TableSawRecipe> recipes ) {
		
		clientRecipes = List.copyOf( recipes );
	}
}
