package de.geheimagentnr1.manyideas_core.integrations.jei.categories.grinding;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideas_core.elements.blocks.mortar.GrindingRecipe;
import de.geheimagentnr1.manyideas_core.integrations.jei.categories.template.single_item_recipe.JeiSingleItemRecipeCategory;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;


public class GrindingRecipeCategory extends JeiSingleItemRecipeCategory<JeiGrindingRecipe> {
	
	
	public static final RecipeType<JeiGrindingRecipe> GRINDING = RecipeType.create(
		ManyIdeasCore.MODID,
		GrindingRecipe.registry_name,
		JeiGrindingRecipe.class
	);
	
	public GrindingRecipeCategory( IGuiHelper guiHelper ) {
		
		super( guiHelper, GRINDING, ModBlocks.MORTAR );
	}
}
