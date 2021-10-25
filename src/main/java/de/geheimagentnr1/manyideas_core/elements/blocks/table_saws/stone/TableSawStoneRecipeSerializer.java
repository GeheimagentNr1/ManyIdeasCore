package de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.stone;

import de.geheimagentnr1.manyideas_core.elements.recipes.single_item_recipes.SingleItemRecipeSerializer;


public class TableSawStoneRecipeSerializer extends SingleItemRecipeSerializer<TableSawStoneRecipe> {
	
	
	public TableSawStoneRecipeSerializer() {
		
		super( TableSawStoneRecipe::new, TableSawStoneRecipe.registry_name );
	}
}
