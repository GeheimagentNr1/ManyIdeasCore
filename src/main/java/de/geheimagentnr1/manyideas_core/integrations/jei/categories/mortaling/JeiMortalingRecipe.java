package de.geheimagentnr1.manyideas_core.integrations.jei.categories.mortaling;

import de.geheimagentnr1.manyideas_core.elements.blocks.mortal.MortalingRecipe;
import de.geheimagentnr1.manyideas_core.elements.recipes.RecipeTypes;
import de.geheimagentnr1.manyideas_core.integrations.jei.categories.template.single_item_recipe.JeiSingleItemRecipe;

import java.util.List;


public class JeiMortalingRecipe extends JeiSingleItemRecipe<MortalingRecipe> {
	
	
	private JeiMortalingRecipe( MortalingRecipe recipe ) {
		
		super( recipe );
	}
	
	public static List<JeiMortalingRecipe> getRecipes() {
		
		return JeiSingleItemRecipe.getRecipes( RecipeTypes.MORTALING, JeiMortalingRecipe::new );
	}
}
