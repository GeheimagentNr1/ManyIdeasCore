package de.geheimagentnr1.manyideas_core.integrations.jei.categories.table_sawing.table_sawing_stone;

import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.stone.TableSawStoneRecipe;
import de.geheimagentnr1.manyideas_core.elements.recipes.RecipeTypes;
import de.geheimagentnr1.manyideas_core.integrations.jei.categories.template.single_item_recipe.JeiSingleItemRecipe;
import de.geheimagentnr1.manyideas_core.integrations.jei.categories.table_sawing.JeiTableSawRecipe;

import java.util.List;


public class JeiTableSawStoneRecipe extends JeiTableSawRecipe<TableSawStoneRecipe> {
	
	
	private JeiTableSawStoneRecipe( TableSawStoneRecipe recipe ) {
		
		super( recipe );
	}
	
	public static List<JeiTableSawStoneRecipe> getRecipes() {
		
		return JeiSingleItemRecipe.getRecipes( RecipeTypes.TABLE_SAWING_STONE, JeiTableSawStoneRecipe::new );
	}
}
