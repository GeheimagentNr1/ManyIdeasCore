package de.geheimagentnr1.manyideas_core.integrations.jei.categories.table_sawing.table_sawing_iron;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocksRegisterFactory;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.iron.TableSawIronRecipe;
import de.geheimagentnr1.manyideas_core.integrations.jei.categories.template.single_item_recipe.JeiSingleItemRecipeCategory;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import org.jetbrains.annotations.NotNull;


public class TableSawIronRecipeCategory extends JeiSingleItemRecipeCategory<JeiTableSawIronRecipe> {
	
	
	@NotNull
	public static final RecipeType<JeiTableSawIronRecipe> TABLE_SAWING_IRON = RecipeType.create(
		ManyIdeasCore.MODID,
		TableSawIronRecipe.registry_name,
		JeiTableSawIronRecipe.class
	);
	
	public TableSawIronRecipeCategory( @NotNull IGuiHelper guiHelper ) {
		
		super( guiHelper, TABLE_SAWING_IRON, ModBlocksRegisterFactory.TABLE_SAW_IRON );
	}
}
