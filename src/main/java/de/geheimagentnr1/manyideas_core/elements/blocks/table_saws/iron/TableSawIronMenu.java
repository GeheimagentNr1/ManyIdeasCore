package de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.iron;

import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocksRegisterFactory;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.TableSawMenu;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.TableSawRecipe;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.TableSawRecipes;
import de.geheimagentnr1.manyideas_core.elements.recipes.ModRecipeTypesRegisterFactory;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TableSawIronMenu extends TableSawMenu {
	
	
	public TableSawIronMenu( int windowId, @NotNull Inventory inventory ) {
		
		super( ModBlocksRegisterFactory.TABLE_SAW_IRON_MENU, windowId, inventory );
	}
	
	//package-private
	TableSawIronMenu(
		int windowId, @NotNull Inventory inventory,
		@NotNull ContainerLevelAccess _containerLevelAccess ) {
		
		super( ModBlocksRegisterFactory.TABLE_SAW_IRON_MENU, windowId, inventory, _containerLevelAccess );
	}
	
	@NotNull
	@Override
	public List<RecipeType<?>> getAcceptedRecipeTypes() {
		
		return Arrays.asList(
			ModRecipeTypesRegisterFactory.TABLE_SAWING_STONE,
			ModRecipeTypesRegisterFactory.TABLE_SAWING_IRON
		);
	}
	
	@NotNull
	@Override
	public Block getCanInteractBlock() {
		
		return ModBlocksRegisterFactory.TABLE_SAW_IRON;
	}
	
	@NotNull
	@Override
	public List<TableSawRecipe> getAvaiableRecipes( @NotNull SingleRecipeInput container, @NotNull Level _level ) {

		return TableSawRecipes.getRecipes( _level ).stream()
			.filter( recipe ->
				recipe.getType() == ModRecipeTypesRegisterFactory.TABLE_SAWING_STONE
					|| recipe.getType() == ModRecipeTypesRegisterFactory.TABLE_SAWING_IRON )
			.filter( recipe -> recipe.matches( container, _level ) )
			.toList();
	}
}
