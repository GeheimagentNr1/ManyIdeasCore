package de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.iron;

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
import java.util.Arrays;
import java.util.List;


public class TableSawIronContainer extends TableSawContainer {
	
	
	public TableSawIronContainer( int menuId, PlayerInventory playerInventory ) {
		
		super( ModBlocks.TABLE_SAW_IRON_CONTAINER, menuId, playerInventory );
	}
	
	//package-private
	TableSawIronContainer( int menuId, PlayerInventory playerInventory, IWorldPosCallable worldPosCallableIn ) {
		
		super( ModBlocks.TABLE_SAW_IRON_CONTAINER, menuId, playerInventory, worldPosCallableIn );
	}
	
	@SuppressWarnings( "rawtypes" )
	@Override
	public List<IRecipeType> getAcceptedRecipeTypes() {
		
		return Arrays.asList( RecipeTypes.TABLE_SAWING_STONE, RecipeTypes.TABLE_SAWING_IRON );
	}
	
	@Override
	public Block getCanInteractBlock() {
		
		return ModBlocks.TABLE_SAW_IRON;
	}
	
	@Override
	public List<TableSawRecipe> getAvaiableRecipes( IInventory inventory, World level ) {
		
		ArrayList<TableSawRecipe> recipes = new ArrayList<>();
		
		recipes.addAll(
			level.getRecipeManager().getRecipesFor( RecipeTypes.TABLE_SAWING_STONE, inventory, level )
		);
		recipes.addAll(
			level.getRecipeManager().getRecipesFor( RecipeTypes.TABLE_SAWING_IRON, inventory, level )
		);
		return recipes;
	}
}
