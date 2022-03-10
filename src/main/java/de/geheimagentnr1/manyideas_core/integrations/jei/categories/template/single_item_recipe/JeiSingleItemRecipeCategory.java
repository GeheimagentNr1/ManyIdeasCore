package de.geheimagentnr1.manyideas_core.integrations.jei.categories.template.single_item_recipe;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.recipes.single_item_recipes.SingleItemRecipe;
import de.geheimagentnr1.manyideas_core.integrations.jei.categories.template.JeiRecipeCategory;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;


public abstract class JeiSingleItemRecipeCategory<R extends JeiSingleItemRecipe<? extends SingleItemRecipe>>
	extends JeiRecipeCategory<R> {
	
	
	private static final ResourceLocation texture = new ResourceLocation(
		ManyIdeasCore.MODID,
		"textures/jei/gui/single_item_recipe_gui.png"
	);
	
	protected JeiSingleItemRecipeCategory( IGuiHelper guiHelper, RecipeType<R> _recipeType, Block _block ) {
		
		super( guiHelper, _recipeType, _block, guiHelper.createDrawable( texture, 0, 0, 116, 54 ) );
	}
	
	@Override
	public void setRecipe( @NotNull IRecipeLayoutBuilder builder, @NotNull R recipe, @NotNull IFocusGroup focuses ) {
		
		builder.addSlot( RecipeIngredientRole.INPUT, 19, 18 )
			.addItemStacks( recipe.getInputs().get( 0 ) );
		
		builder.addSlot( RecipeIngredientRole.OUTPUT, 79, 18 )
			.addItemStack( recipe.getResult() );
	}
}
