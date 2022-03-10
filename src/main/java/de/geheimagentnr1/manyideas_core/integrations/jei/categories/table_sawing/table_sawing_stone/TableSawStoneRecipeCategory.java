package de.geheimagentnr1.manyideas_core.integrations.jei.categories.table_sawing.table_sawing_stone;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.stone.TableSawStoneRecipe;
import de.geheimagentnr1.manyideas_core.integrations.jei.categories.template.single_item_recipe.JeiSingleItemRecipeCategory;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;


public class TableSawStoneRecipeCategory extends JeiSingleItemRecipeCategory<JeiTableSawStoneRecipe> {
	
	
	public static final RecipeType<JeiTableSawStoneRecipe> TABLE_SAWING_STONE = RecipeType.create(
		ManyIdeasCore.MODID,
		TableSawStoneRecipe.registry_name,
		JeiTableSawStoneRecipe.class
	);
	
	public TableSawStoneRecipeCategory( IGuiHelper guiHelper ) {
		
		super( guiHelper, TABLE_SAWING_STONE, ModBlocks.TABLE_SAW_STONE );
	}
}
