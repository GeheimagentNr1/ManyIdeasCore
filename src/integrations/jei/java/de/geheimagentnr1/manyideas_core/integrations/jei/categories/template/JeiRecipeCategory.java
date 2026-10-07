package de.geheimagentnr1.manyideas_core.integrations.jei.categories.template;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;


public abstract class JeiRecipeCategory<R> implements IRecipeCategory<R> {
	
	
	@NotNull
	private final RecipeType<R> recipeType;
	
	@NotNull
	private final Block block;
	
	@NotNull
	private final IDrawable icon;
	
	@NotNull
	private final IDrawable background;
	
	protected JeiRecipeCategory(
		@NotNull IGuiHelper guiHelper,
		@NotNull RecipeType<R> _recipeType,
		@NotNull Block _block,
		@NotNull IDrawable _background ) {
		
		recipeType = _recipeType;
		block = _block;
		icon = guiHelper.createDrawableIngredient( VanillaTypes.ITEM_STACK, new ItemStack( block ) );
		background = _background;
	}
	
	@NotNull
	@Override
	public RecipeType<R> getRecipeType() {
		
		return recipeType;
	}
	
	@NotNull
	@Override
	public Component getTitle() {
		
		return block.getName();
	}
	
	@NotNull
	@Override
	public IDrawable getIcon() {
		
		return icon;
	}
	
	@Override
	public int getWidth() {
		
		return background.getWidth();
	}
	
	@Override
	public int getHeight() {
		
		return background.getHeight();
	}
	
	@Override
	public void draw(
		@NotNull R recipe,
		@NotNull IRecipeSlotsView recipeSlotsView,
		@NotNull GuiGraphicsExtractor guiGraphics,
		double mouseX,
		double mouseY ) {
		
		background.draw( guiGraphics );
	}
}
