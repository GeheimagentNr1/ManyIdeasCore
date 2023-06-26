package de.geheimagentnr1.manyideas_core.integrations.jei.categories.table_sawing.table_sawing_iron;

import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.iron.TableSawIronRecipe;
import de.geheimagentnr1.manyideas_core.elements.recipes.ModRecipeTypesRegisterFactory;
import de.geheimagentnr1.manyideas_core.integrations.jei.categories.table_sawing.JeiTableSawRecipe;
import de.geheimagentnr1.manyideas_core.integrations.jei.categories.template.single_item_recipe.JeiSingleItemRecipe;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class JeiTableSawIronRecipe extends JeiTableSawRecipe<TableSawIronRecipe> {
	
	
	private JeiTableSawIronRecipe( @NotNull TableSawIronRecipe recipe ) {
		
		super( recipe );
	}
	
	@NotNull
	public static List<JeiTableSawIronRecipe> getRecipes() {
		
		return JeiSingleItemRecipe.getRecipes(
			ModRecipeTypesRegisterFactory.TABLE_SAWING_IRON,
			JeiTableSawIronRecipe::new
		);
	}
}
