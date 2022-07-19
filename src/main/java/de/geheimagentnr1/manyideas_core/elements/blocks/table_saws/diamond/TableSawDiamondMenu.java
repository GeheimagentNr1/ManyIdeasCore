package de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.diamond;

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


public class TableSawDiamondMenu extends TableSawMenu {
	
	
	public TableSawDiamondMenu( int menuId, Inventory inventory ) {
		
		super( ModBlocks.TABLE_SAW_DIAMOND_MENU, menuId, inventory );
	}
	
	//package-private
	TableSawDiamondMenu(
		int menuId,
		Inventory inventory,
		ContainerLevelAccess containerLevelAccess ) {
		
		super( ModBlocks.TABLE_SAW_DIAMOND_MENU, menuId, inventory, containerLevelAccess );
	}
	
	@SuppressWarnings( "rawtypes" )
	@Override
	public List<RecipeType> getAcceptedRecipeTypes() {
		
		return Arrays.asList(
			RecipeTypes.TABLE_SAWING_STONE,
			RecipeTypes.TABLE_SAWING_IRON,
			RecipeTypes.TABLE_SAWING_DIAMOND
		);
	}
	
	@Override
	public Block getCanInteractBlock() {
		
		return ModBlocks.TABLE_SAW_DIAMOND;
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
		recipes.addAll(
			_level.getRecipeManager().getRecipesFor( RecipeTypes.TABLE_SAWING_DIAMOND, container, _level )
		);
		return recipes;
	}
}
