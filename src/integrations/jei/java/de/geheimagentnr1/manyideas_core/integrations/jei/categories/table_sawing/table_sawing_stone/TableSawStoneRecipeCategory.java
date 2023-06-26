package de.geheimagentnr1.manyideas_core.integrations.jei.categories.table_sawing.table_sawing_stone;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocksRegisterFactory;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.stone.TableSawStoneRecipe;
import de.geheimagentnr1.manyideas_core.integrations.jei.categories.template.single_item_recipe.JeiSingleItemRecipeCategory;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import org.jetbrains.annotations.NotNull;


public class TableSawStoneRecipeCategory extends JeiSingleItemRecipeCategory<JeiTableSawStoneRecipe> {
	
	
	@NotNull
	public static final RecipeType<JeiTableSawStoneRecipe> TABLE_SAWING_STONE = RecipeType.create(
		ManyIdeasCore.MODID,
		TableSawStoneRecipe.registry_name,
		JeiTableSawStoneRecipe.class
	);
	
	public TableSawStoneRecipeCategory( @NotNull IGuiHelper guiHelper ) {
		
		super( guiHelper, TABLE_SAWING_STONE, ModBlocksRegisterFactory.TABLE_SAW_STONE );
	}
}
