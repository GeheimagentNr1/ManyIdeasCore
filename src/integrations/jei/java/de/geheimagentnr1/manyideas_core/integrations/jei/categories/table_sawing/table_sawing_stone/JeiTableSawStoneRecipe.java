package de.geheimagentnr1.manyideas_core.integrations.jei.categories.table_sawing.table_sawing_stone;

import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.stone.TableSawStoneRecipe;
import de.geheimagentnr1.manyideas_core.elements.recipes.ModRecipeTypesRegisterFactory;
import de.geheimagentnr1.manyideas_core.integrations.jei.categories.table_sawing.JeiTableSawRecipe;
import de.geheimagentnr1.manyideas_core.integrations.jei.categories.template.single_item_recipe.JeiSingleItemRecipe;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class JeiTableSawStoneRecipe extends JeiTableSawRecipe<TableSawStoneRecipe> {
	
	
	private JeiTableSawStoneRecipe( @NotNull TableSawStoneRecipe recipe ) {
		
		super( recipe );
	}
	
	@NotNull
	public static List<JeiTableSawStoneRecipe> getRecipes() {
		
		return JeiSingleItemRecipe.getRecipes(
			ModRecipeTypesRegisterFactory.TABLE_SAWING_STONE,
			JeiTableSawStoneRecipe::new
		);
	}
}
