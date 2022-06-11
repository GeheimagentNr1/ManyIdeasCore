package de.geheimagentnr1.manyideas_core.integrations.jei.categories.table_sawing.table_sawing_diamond;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.diamond.TableSawDiamondRecipe;
import de.geheimagentnr1.manyideas_core.integrations.jei.categories.template.single_item_recipe.JeiSingleItemRecipeCategory;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;


public class TableSawDiamondRecipeCategory extends JeiSingleItemRecipeCategory<JeiTableSawDiamondRecipe> {
	
	
	public static final RecipeType<JeiTableSawDiamondRecipe> TABLE_SAWING_DIAMOND = RecipeType.create(
		ManyIdeasCore.MODID,
		TableSawDiamondRecipe.registry_name,
		JeiTableSawDiamondRecipe.class
	);
	
	public TableSawDiamondRecipeCategory( IGuiHelper guiHelper ) {
		
		super( guiHelper, TABLE_SAWING_DIAMOND, ModBlocks.TABLE_SAW_DIAMOND );
	}
}
