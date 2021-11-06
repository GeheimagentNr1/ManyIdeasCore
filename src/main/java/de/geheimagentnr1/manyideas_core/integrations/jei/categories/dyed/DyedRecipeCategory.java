package de.geheimagentnr1.manyideas_core.integrations.jei.categories.dyed;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes.DyedRecipe;
import de.geheimagentnr1.manyideas_core.integrations.jei.categories.template.JeiRecipeCategory;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;


public class DyedRecipeCategory extends JeiRecipeCategory<JeiDyedRecipe> {
	
	
	public static final ResourceLocation registry_key = new ResourceLocation(
		ManyIdeasCore.MODID,
		DyedRecipe.registry_name
	);
	
	private static final ResourceLocation texture = new ResourceLocation( "jei", "textures/gui/gui_vanilla.png" );
	
	private final IDrawable background;
	
	public DyedRecipeCategory( IGuiHelper guiHelper ) {
		
		super( guiHelper, ModBlocks.DYE_CRAFTING_TABLE );
		background = guiHelper.createDrawable( texture, 0, 60, 116, 54 );
	}
	
	@Nonnull
	@Override
	public ResourceLocation getUid() {
		
		return registry_key;
	}
	
	@Nonnull
	@Override
	public Class<? extends JeiDyedRecipe> getRecipeClass() {
		
		return JeiDyedRecipe.class;
	}
	
	@Nonnull
	@Override
	public IDrawable getBackground() {
		
		return background;
	}
	
	@Override
	public void setIngredients( @Nonnull JeiDyedRecipe recipe, @Nonnull IIngredients ingredients ) {
		
		ingredients.setInputLists( VanillaTypes.ITEM, recipe.getInputs() );
		ingredients.setOutput( VanillaTypes.ITEM, recipe.getResult() );
	}
	
	@Override
	public void setRecipe(
		IRecipeLayout recipeLayout,
		@Nonnull JeiDyedRecipe recipe,
		@Nonnull IIngredients ingredients ) {
		
		IGuiItemStackGroup itemStacks = recipeLayout.getItemStacks();
		
		for( int i = 0; i < 3; i++ ) {
			for( int j = 0; j < 3; j++ ) {
				itemStacks.init( j + i * 3, true, j * 18, i * 18 );
			}
		}
		itemStacks.init( 10, false, 94, 18 );
		itemStacks.set( ingredients );
	}
}
