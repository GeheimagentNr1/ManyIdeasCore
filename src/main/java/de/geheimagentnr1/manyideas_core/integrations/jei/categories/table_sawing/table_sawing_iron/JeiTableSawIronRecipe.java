package de.geheimagentnr1.manyideas_core.integrations.jei.categories.table_sawing.table_sawing_iron;

import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.iron.TableSawIronRecipe;
import de.geheimagentnr1.manyideas_core.elements.recipes.RecipeTypes;
import de.geheimagentnr1.manyideas_core.integrations.jei.categories.table_sawing.JeiTableSawRecipe;
import de.geheimagentnr1.manyideas_core.integrations.jei.categories.template.single_item_recipe.JeiSingleItemRecipe;

import java.util.List;


public class JeiTableSawIronRecipe extends JeiTableSawRecipe<TableSawIronRecipe> {
	
	
	private JeiTableSawIronRecipe( TableSawIronRecipe recipe ) {
		
		super( recipe );
	}
	
	public static List<JeiTableSawIronRecipe> getRecipes() {
		
		return JeiSingleItemRecipe.getRecipes( RecipeTypes.TABLE_SAWING_IRON, JeiTableSawIronRecipe::new );
	}
}
