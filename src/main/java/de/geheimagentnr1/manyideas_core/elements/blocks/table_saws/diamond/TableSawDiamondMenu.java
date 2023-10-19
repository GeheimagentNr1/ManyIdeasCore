package de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.diamond;

import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocksRegisterFactory;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.TableSawMenu;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.TableSawRecipe;
import de.geheimagentnr1.manyideas_core.elements.recipes.ModRecipeTypesRegisterFactory;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TableSawDiamondMenu extends TableSawMenu {
	
	
	public TableSawDiamondMenu( int windowId, @NotNull Inventory inventory ) {
		
		super( ModBlocksRegisterFactory.TABLE_SAW_DIAMOND_MENU, windowId, inventory );
	}
	
	//package-private
	TableSawDiamondMenu(
		int windowId,
		@NotNull Inventory inventory,
		@NotNull ContainerLevelAccess _containerLevelAccess ) {
		
		super( ModBlocksRegisterFactory.TABLE_SAW_DIAMOND_MENU, windowId, inventory, _containerLevelAccess );
	}
	
	@NotNull
	@Override
	public List<RecipeType<?>> getAcceptedRecipeTypes() {
		
		return Arrays.asList(
			ModRecipeTypesRegisterFactory.TABLE_SAWING_STONE,
			ModRecipeTypesRegisterFactory.TABLE_SAWING_IRON,
			ModRecipeTypesRegisterFactory.TABLE_SAWING_DIAMOND
		);
	}
	
	@NotNull
	@Override
	public Block getCanInteractBlock() {
		
		return ModBlocksRegisterFactory.TABLE_SAW_DIAMOND;
	}
	
	@NotNull
	@Override
	public List<TableSawRecipe> getAvaiableRecipes( @NotNull Container container, @NotNull Level _level ) {
		
		ArrayList<TableSawRecipe> recipes = new ArrayList<>();
		
		recipes.addAll(
			_level.getRecipeManager()
				.getRecipesFor( ModRecipeTypesRegisterFactory.TABLE_SAWING_STONE, container, _level )
				.stream()
				.map( RecipeHolder::value )
				.toList()
		);
		recipes.addAll(
			_level.getRecipeManager()
				.getRecipesFor( ModRecipeTypesRegisterFactory.TABLE_SAWING_IRON, container, _level )
				.stream()
				.map( RecipeHolder::value )
				.toList()
		);
		recipes.addAll(
			_level.getRecipeManager()
				.getRecipesFor( ModRecipeTypesRegisterFactory.TABLE_SAWING_DIAMOND, container, _level )
				.stream()
				.map( RecipeHolder::value )
				.toList()
		);
		return recipes;
	}
}
