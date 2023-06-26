package de.geheimagentnr1.manyideas_core.integrations.jei.categories.grinding;

import de.geheimagentnr1.manyideas_core.elements.blocks.mortar.GrindingRecipe;
import de.geheimagentnr1.manyideas_core.elements.recipes.ModRecipeTypesRegisterFactory;
import de.geheimagentnr1.manyideas_core.integrations.jei.categories.template.single_item_recipe.JeiSingleItemRecipe;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class JeiGrindingRecipe extends JeiSingleItemRecipe<GrindingRecipe> {
	
	
	private JeiGrindingRecipe( @NotNull GrindingRecipe recipe ) {
		
		super( recipe );
	}
	
	@NotNull
	public static List<JeiGrindingRecipe> getRecipes() {
		
		return JeiSingleItemRecipe.getRecipes( ModRecipeTypesRegisterFactory.GRINDING, JeiGrindingRecipe::new );
	}
}
