package de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.stone;

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
import java.util.Collections;
import java.util.List;


public class TableSawStoneMenu extends TableSawMenu {
	
	
	public TableSawStoneMenu( int menuId, Inventory inventory ) {
		
		super( ModBlocks.TABLE_SAW_STONE_MENU, menuId, inventory );
	}
	
	//package-private
	TableSawStoneMenu( int menuId, Inventory inventory, ContainerLevelAccess containerLevelAccess ) {
		
		super( ModBlocks.TABLE_SAW_STONE_MENU, menuId, inventory, containerLevelAccess );
	}
	
	@SuppressWarnings( "rawtypes" )
	@Override
	public List<RecipeType> getAcceptedRecipeTypes() {
		
		return Collections.singletonList( RecipeTypes.TABLE_SAWING_STONE );
	}
	
	@Override
	public Block getCanInteractBlock() {
		
		return ModBlocks.TABLE_SAW_STONE;
	}
	
	@Override
	public List<TableSawRecipe> getAvaiableRecipes( Container container, Level _level ) {
		
		return new ArrayList<>(
			_level.getRecipeManager().getRecipesFor( RecipeTypes.TABLE_SAWING_STONE, container, _level )
		);
	}
}
