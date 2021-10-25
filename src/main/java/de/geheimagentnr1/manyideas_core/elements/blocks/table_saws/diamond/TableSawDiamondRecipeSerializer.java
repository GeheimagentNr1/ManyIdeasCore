package de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.diamond;

import de.geheimagentnr1.manyideas_core.elements.recipes.single_item_recipes.SingleItemRecipeSerializer;


public class TableSawDiamondRecipeSerializer extends SingleItemRecipeSerializer<TableSawDiamondRecipe> {
	
	
	public TableSawDiamondRecipeSerializer() {
		
		super( TableSawDiamondRecipe::new, TableSawDiamondRecipe.registry_name );
	}
}
