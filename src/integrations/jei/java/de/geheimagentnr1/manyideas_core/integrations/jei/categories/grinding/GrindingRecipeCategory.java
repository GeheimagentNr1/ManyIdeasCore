package de.geheimagentnr1.manyideas_core.integrations.jei.categories.grinding;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocksRegisterFactory;
import de.geheimagentnr1.manyideas_core.elements.blocks.mortar.GrindingRecipe;
import de.geheimagentnr1.manyideas_core.integrations.jei.categories.template.single_item_recipe.JeiSingleItemRecipeCategory;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import org.jetbrains.annotations.NotNull;


public class GrindingRecipeCategory extends JeiSingleItemRecipeCategory<JeiGrindingRecipe> {
	
	
	@NotNull
	public static final RecipeType<JeiGrindingRecipe> GRINDING = RecipeType.create(
		ManyIdeasCore.MODID,
		GrindingRecipe.registry_name,
		JeiGrindingRecipe.class
	);
	
	public GrindingRecipeCategory( @NotNull IGuiHelper guiHelper ) {
		
		super( guiHelper, GRINDING, ModBlocksRegisterFactory.MORTAR );
	}
}
