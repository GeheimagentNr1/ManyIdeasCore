package de.geheimagentnr1.manyideas_core.integrations.jei.categories.table_sawing.table_sawing_stone;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.stone.TableSawStoneRecipe;
import de.geheimagentnr1.manyideas_core.integrations.jei.categories.template.single_item_recipe.JeiSingleItemRecipeCategory;
import mezz.jei.api.helpers.IGuiHelper;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;


public class TableSawStoneRecipeCategory extends JeiSingleItemRecipeCategory<JeiTableSawStoneRecipe> {
	
	
	public static final ResourceLocation registry_key = new ResourceLocation(
		ManyIdeasCore.MODID,
		TableSawStoneRecipe.registry_name
	);
	
	public TableSawStoneRecipeCategory( IGuiHelper guiHelper ) {
		
		super( guiHelper, ModBlocks.TABLE_SAW_STONE );
	}
	
	@Nonnull
	@Override
	public ResourceLocation getUid() {
		
		return registry_key;
	}
	
	@Nonnull
	@Override
	public Class<? extends JeiTableSawStoneRecipe> getRecipeClass() {
		
		return JeiTableSawStoneRecipe.class;
	}
}
