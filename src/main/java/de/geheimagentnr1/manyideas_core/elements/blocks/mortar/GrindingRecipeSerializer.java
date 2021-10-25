package de.geheimagentnr1.manyideas_core.elements.blocks.mortar;

import de.geheimagentnr1.manyideas_core.elements.recipes.single_item_recipes.SingleItemRecipeSerializer;


public class GrindingRecipeSerializer extends SingleItemRecipeSerializer<GrindingRecipe> {
	
	
	public GrindingRecipeSerializer() {
		
		super( GrindingRecipe::new, GrindingRecipe.registry_name );
	}
}
