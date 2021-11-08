package de.geheimagentnr1.manyideas_core.integrations.jei.categories.template;

import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

import javax.annotation.Nonnull;


public abstract class JeiRecipeCategory<R> implements IRecipeCategory<R> {
	
	
	private final Block block;
	
	private final IDrawable icon;
	
	protected JeiRecipeCategory( IGuiHelper guiHelper, Block _block ) {
		
		block = _block;
		icon = guiHelper.createDrawableIngredient( new ItemStack( block ) );
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
}
