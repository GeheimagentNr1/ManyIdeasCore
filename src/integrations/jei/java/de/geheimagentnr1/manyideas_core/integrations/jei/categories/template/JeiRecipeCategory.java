package de.geheimagentnr1.manyideas_core.integrations.jei.categories.template;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;


public abstract class JeiRecipeCategory<R> implements IRecipeCategory<R> {
	
	
	private final RecipeType<R> recipeType;
	
	private final Block block;
	
	private final IDrawable icon;
	
	private final IDrawable background;
	
	protected JeiRecipeCategory(
		IGuiHelper guiHelper,
		RecipeType<R> _recipeType,
		Block _block,
		IDrawable _background ) {
		
		recipeType = _recipeType;
		block = _block;
		icon = guiHelper.createDrawableIngredient( VanillaTypes.ITEM, new ItemStack( block ) );
		background = _background;
	}
	
	@NotNull
	@Override
	public RecipeType<R> getRecipeType() {
		
		return recipeType;
	}
	
	@Nonnull
	@Override
	public ResourceLocation getUid() {
		
		return getRecipeType().getUid();
	}
	
	@Nonnull
	@Override
	public Class<? extends R> getRecipeClass() {
		
		return getRecipeType().getRecipeClass();
	}
	
	@Nonnull
	@Override
	public Component getTitle() {
		
		return block.getName();
	}
	
	@Nonnull
	@Override
	public IDrawable getIcon() {
		
		return icon;
	}
	
	@Nonnull
	@Override
	public IDrawable getBackground() {
		
		return background;
	}
}
