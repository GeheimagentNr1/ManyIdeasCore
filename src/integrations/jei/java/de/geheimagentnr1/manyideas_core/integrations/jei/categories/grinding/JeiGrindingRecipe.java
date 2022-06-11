package de.geheimagentnr1.manyideas_core.integrations.jei.categories.grinding;

import de.geheimagentnr1.manyideas_core.elements.blocks.mortar.GrindingRecipe;
import de.geheimagentnr1.manyideas_core.elements.recipes.RecipeTypes;
import de.geheimagentnr1.manyideas_core.integrations.jei.categories.template.single_item_recipe.JeiSingleItemRecipe;

import java.util.List;


public class JeiGrindingRecipe extends JeiSingleItemRecipe<GrindingRecipe> {
	
	
	private JeiGrindingRecipe( GrindingRecipe recipe ) {
		
		super( recipe );
	}
	
	public static List<JeiGrindingRecipe> getRecipes() {
		
		return JeiSingleItemRecipe.getRecipes( RecipeTypes.GRINDING, JeiGrindingRecipe::new );
	}
}
