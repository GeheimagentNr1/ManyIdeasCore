package de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.stone;

import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.TableSawContainer;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.TableSawRecipe;
import de.geheimagentnr1.manyideas_core.elements.recipes.RecipeTypes;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TableSawStoneContainer extends TableSawContainer {
	
	
	public TableSawStoneContainer( int menuId, PlayerInventory playerInventory ) {
		
		super( ModBlocks.TABLE_SAW_STONE_CONTAINER, menuId, playerInventory );
	}
	
	//package-private
	TableSawStoneContainer( int menuId, PlayerInventory playerInventory, IWorldPosCallable worldPosCallable ) {
		
		super( ModBlocks.TABLE_SAW_STONE_CONTAINER, menuId, playerInventory, worldPosCallable );
	}
	
	@SuppressWarnings( "rawtypes" )
	@Override
	public List<IRecipeType> getAcceptedRecipeTypes() {
		
		return Collections.singletonList( RecipeTypes.TABLE_SAWING_STONE );
	}
	
	@Override
	public Block getCanInteractBlock() {
		
		return ModBlocks.TABLE_SAW_STONE;
	}
	
	@Override
	public List<TableSawRecipe> getAvaiableRecipes( IInventory inventory, World level ) {
		
		return new ArrayList<>(
			level.getRecipeManager().getRecipesFor( RecipeTypes.TABLE_SAWING_STONE, inventory, level )
		);
	}
}
