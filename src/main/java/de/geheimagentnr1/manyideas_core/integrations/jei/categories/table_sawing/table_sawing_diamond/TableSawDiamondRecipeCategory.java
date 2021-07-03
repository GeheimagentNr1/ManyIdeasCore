package de.geheimagentnr1.manyideas_core.integrations.jei.categories.table_sawing.table_sawing_diamond;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.diamond.TableSawDiamondRecipe;
import de.geheimagentnr1.manyideas_core.integrations.jei.categories.template.single_item_recipe.JeiSingleItemRecipeCategory;
import mezz.jei.api.helpers.IGuiHelper;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;


public class TableSawDiamondRecipeCategory extends JeiSingleItemRecipeCategory<JeiTableSawDiamondRecipe> {
	
	
	public static final ResourceLocation registry_key = new ResourceLocation(
		ManyIdeasCore.MODID,
		TableSawDiamondRecipe.registry_name
	);
	
	public TableSawDiamondRecipeCategory( IGuiHelper guiHelper ) {
		
		super( guiHelper, ModBlocks.TABLE_SAW_DIAMOND );
	}
	
	@Nonnull
	@Override
	public ResourceLocation getUid() {
		
		return registry_key;
	}
	
	@Nonnull
	@Override
	public Class<? extends JeiTableSawDiamondRecipe> getRecipeClass() {
		
		return JeiTableSawDiamondRecipe.class;
	}
}
