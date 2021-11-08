package de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.iron;

import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.TableSawMenu;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.TableSawRecipe;
import de.geheimagentnr1.manyideas_core.elements.recipes.RecipeTypes;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TableSawIronMenu extends TableSawMenu {
	
	
	public TableSawIronMenu( int menuId, Inventory inventory ) {
		
		super( ModBlocks.TABLE_SAW_IRON_CONTAINER, menuId, inventory );
	}
	
	//package-private
	TableSawIronMenu( int menuId, Inventory inventory, ContainerLevelAccess containerLevelAccess ) {
		
		super( ModBlocks.TABLE_SAW_IRON_CONTAINER, menuId, inventory, containerLevelAccess );
	}
	
	@SuppressWarnings( "rawtypes" )
	@Override
	public List<RecipeType> getAcceptedRecipeTypes() {
		
		return Arrays.asList( RecipeTypes.TABLE_SAWING_STONE, RecipeTypes.TABLE_SAWING_IRON );
	}
	
	@Override
	public Block getCanInteractBlock() {
		
		return ModBlocks.TABLE_SAW_IRON;
	}
	
	@Override
	public List<TableSawRecipe> getAvaiableRecipes( Container container, Level _level ) {
		
		ArrayList<TableSawRecipe> recipes = new ArrayList<>();
		
		recipes.addAll(
			_level.getRecipeManager().getRecipesFor( RecipeTypes.TABLE_SAWING_STONE, container, _level )
		);
		recipes.addAll(
			_level.getRecipeManager().getRecipesFor( RecipeTypes.TABLE_SAWING_IRON, container, _level )
		);
		return recipes;
	}
}
