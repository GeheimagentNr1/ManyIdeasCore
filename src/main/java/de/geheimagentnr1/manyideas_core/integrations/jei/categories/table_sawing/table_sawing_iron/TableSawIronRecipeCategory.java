package de.geheimagentnr1.manyideas_core.integrations.jei.categories.table_sawing.table_sawing_iron;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.iron.TableSawIronRecipe;
import de.geheimagentnr1.manyideas_core.integrations.jei.categories.template.single_item_recipe.JeiSingleItemRecipeCategory;
import mezz.jei.api.helpers.IGuiHelper;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;


public class TableSawIronRecipeCategory extends JeiSingleItemRecipeCategory<JeiTableSawIronRecipe> {
	
	
	public static final ResourceLocation registry_key = new ResourceLocation(
		ManyIdeasCore.MODID,
		TableSawIronRecipe.registry_name
	);
	
	public TableSawIronRecipeCategory( IGuiHelper guiHelper ) {
		
		super( guiHelper, ModBlocks.TABLE_SAW_IRON );
	}
	
	@Nonnull
	@Override
	public ResourceLocation getUid() {
		
		return registry_key;
	}
	
	@Nonnull
	@Override
	public Class<? extends JeiTableSawIronRecipe> getRecipeClass() {
		
		return JeiTableSawIronRecipe.class;
	}
}
