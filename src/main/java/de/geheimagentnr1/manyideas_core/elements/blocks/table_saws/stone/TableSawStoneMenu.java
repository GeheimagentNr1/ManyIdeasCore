package de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.stone;

import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocksRegisterFactory;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.TableSawMenu;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.TableSawRecipe;
import de.geheimagentnr1.manyideas_core.elements.recipes.ModRecipeTypesRegisterFactory;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TableSawStoneMenu extends TableSawMenu {
	
	
	public TableSawStoneMenu( int windowId, @NotNull Inventory inventory ) {
		
		super( ModBlocksRegisterFactory.TABLE_SAW_STONE_MENU, windowId, inventory );
	}
	
	//package-private
	TableSawStoneMenu(
		int windowId,
		@NotNull Inventory inventory,
		@NotNull ContainerLevelAccess _containerLevelAccess ) {
		
		super( ModBlocksRegisterFactory.TABLE_SAW_STONE_MENU, windowId, inventory, _containerLevelAccess );
	}
	
	@NotNull
	@Override
	public List<RecipeType<?>> getAcceptedRecipeTypes() {
		
		return Collections.singletonList( ModRecipeTypesRegisterFactory.TABLE_SAWING_STONE );
	}
	
	@NotNull
	@Override
	public Block getCanInteractBlock() {
		
		return ModBlocksRegisterFactory.TABLE_SAW_STONE;
	}
	
	@NotNull
	@Override
	public List<TableSawRecipe> getAvaiableRecipes( @NotNull Container container, @NotNull Level _level ) {
		
		return new ArrayList<>(
			_level.getRecipeManager()
				.getRecipesFor( ModRecipeTypesRegisterFactory.TABLE_SAWING_STONE, container, _level )
		);
	}
}
