package de.geheimagentnr1.manyideas_core.integrations.jei.categories.template.single_item_recipe;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.recipes.single_item_recipes.SingleItemRecipe;
import de.geheimagentnr1.manyideas_core.integrations.jei.categories.template.JeiRecipeCategory;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;


public abstract class JeiSingleItemRecipeCategory<R extends JeiSingleItemRecipe<? extends SingleItemRecipe>>
	extends JeiRecipeCategory<R> {
	
	
	private static final ResourceLocation texture = new ResourceLocation( ManyIdeasCore.MODID,
		"textures/jei/gui/single_item_recipe_gui.png" );
	
	private final IDrawable background;
	
	protected JeiSingleItemRecipeCategory( IGuiHelper guiHelper, Block _block ) {
		
		super( guiHelper, _block );
		background = guiHelper.createDrawable( texture, 0, 0, 116, 54 );
	}
	
	@Nonnull
	@Override
	public IDrawable getBackground() {
		
		return background;
	}
	
	@Override
	public void setIngredients( R recipe, IIngredients iIngredients ) {
		
		iIngredients.setInputLists( VanillaTypes.ITEM, recipe.getInputs() );
		iIngredients.setOutput( VanillaTypes.ITEM, recipe.getResult() );
	}
	
	@Override
	public void setRecipe( IRecipeLayout iRecipeLayout, @Nonnull R r, @Nonnull IIngredients iIngredients ) {
		
		IGuiItemStackGroup itemStacks = iRecipeLayout.getItemStacks();
		
		itemStacks.init( 0, true, 19, 18 );
		itemStacks.init( 1, false, 79, 18 );
		itemStacks.set( iIngredients );
	}
}
