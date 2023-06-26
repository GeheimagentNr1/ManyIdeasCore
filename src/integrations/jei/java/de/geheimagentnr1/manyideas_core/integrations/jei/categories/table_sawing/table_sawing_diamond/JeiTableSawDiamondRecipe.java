package de.geheimagentnr1.manyideas_core.integrations.jei.categories.table_sawing.table_sawing_diamond;

import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.diamond.TableSawDiamondRecipe;
import de.geheimagentnr1.manyideas_core.elements.recipes.ModRecipeTypesRegisterFactory;
import de.geheimagentnr1.manyideas_core.integrations.jei.categories.table_sawing.JeiTableSawRecipe;
import de.geheimagentnr1.manyideas_core.integrations.jei.categories.template.single_item_recipe.JeiSingleItemRecipe;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class JeiTableSawDiamondRecipe extends JeiTableSawRecipe<TableSawDiamondRecipe> {
	
	
	private JeiTableSawDiamondRecipe( @NotNull TableSawDiamondRecipe recipe ) {
		
		super( recipe );
	}
	
	@NotNull
	public static List<JeiTableSawDiamondRecipe> getRecipes() {
		
		return JeiSingleItemRecipe.getRecipes(
			ModRecipeTypesRegisterFactory.TABLE_SAWING_DIAMOND,
			JeiTableSawDiamondRecipe::new
		);
	}
}
