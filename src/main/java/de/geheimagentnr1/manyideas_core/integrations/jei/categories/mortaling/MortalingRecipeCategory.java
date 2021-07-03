package de.geheimagentnr1.manyideas_core.integrations.jei.categories.mortaling;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideas_core.elements.blocks.mortal.MortalingRecipe;
import de.geheimagentnr1.manyideas_core.integrations.jei.categories.template.single_item_recipe.JeiSingleItemRecipeCategory;
import mezz.jei.api.helpers.IGuiHelper;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;


public class MortalingRecipeCategory extends JeiSingleItemRecipeCategory<JeiMortalingRecipe> {
	
	
	public static final ResourceLocation registry_key = new ResourceLocation(
		ManyIdeasCore.MODID,
		MortalingRecipe.registry_name
	);
	
	public MortalingRecipeCategory( IGuiHelper guiHelper ) {
		
		super( guiHelper, ModBlocks.MORTAL );
	}
	
	@Nonnull
	@Override
	public ResourceLocation getUid() {
		
		return registry_key;
	}
	
	@Nonnull
	@Override
	public Class<? extends JeiMortalingRecipe> getRecipeClass() {
		
		return JeiMortalingRecipe.class;
	}
}
