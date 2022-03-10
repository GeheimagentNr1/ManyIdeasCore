package de.geheimagentnr1.manyideas_core.integrations.jei.categories.table_sawing.table_sawing_iron;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.iron.TableSawIronRecipe;
import de.geheimagentnr1.manyideas_core.integrations.jei.categories.template.single_item_recipe.JeiSingleItemRecipeCategory;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;


public class TableSawIronRecipeCategory extends JeiSingleItemRecipeCategory<JeiTableSawIronRecipe> {
	
	
	public static final RecipeType<JeiTableSawIronRecipe> TABLE_SAWING_IRON = RecipeType.create(
		ManyIdeasCore.MODID,
		TableSawIronRecipe.registry_name,
		JeiTableSawIronRecipe.class
	);
	
	public TableSawIronRecipeCategory( IGuiHelper guiHelper ) {
		
		super( guiHelper, TABLE_SAWING_IRON, ModBlocks.TABLE_SAW_IRON );
	}
}
