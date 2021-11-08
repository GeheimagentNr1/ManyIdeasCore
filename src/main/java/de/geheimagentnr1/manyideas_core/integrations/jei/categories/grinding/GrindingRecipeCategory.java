package de.geheimagentnr1.manyideas_core.integrations.jei.categories.grinding;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideas_core.elements.blocks.mortar.GrindingRecipe;
import de.geheimagentnr1.manyideas_core.integrations.jei.categories.template.single_item_recipe.JeiSingleItemRecipeCategory;
import mezz.jei.api.helpers.IGuiHelper;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;


public class GrindingRecipeCategory extends JeiSingleItemRecipeCategory<JeiGrindingRecipe> {
	
	
	public static final ResourceLocation registry_key = new ResourceLocation(
		ManyIdeasCore.MODID,
		GrindingRecipe.registry_name
	);
	
	public GrindingRecipeCategory( IGuiHelper guiHelper ) {
		
		super( guiHelper, ModBlocks.MORTAR );
	}
	
	@Nonnull
	@Override
	public ResourceLocation getUid() {
		
		return registry_key;
	}
	
	@Nonnull
	@Override
	public Class<? extends JeiGrindingRecipe> getRecipeClass() {
		
		return JeiGrindingRecipe.class;
	}
}
