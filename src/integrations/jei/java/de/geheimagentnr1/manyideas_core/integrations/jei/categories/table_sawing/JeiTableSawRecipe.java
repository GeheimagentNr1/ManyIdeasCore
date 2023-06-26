package de.geheimagentnr1.manyideas_core.integrations.jei.categories.table_sawing;

import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.TableSawRecipe;
import de.geheimagentnr1.manyideas_core.integrations.jei.categories.template.single_item_recipe.JeiSingleItemRecipe;
import org.jetbrains.annotations.NotNull;


public class JeiTableSawRecipe<R extends TableSawRecipe> extends JeiSingleItemRecipe<R> {
	
	
	protected JeiTableSawRecipe( @NotNull R recipe ) {
		
		super( recipe );
	}
}
