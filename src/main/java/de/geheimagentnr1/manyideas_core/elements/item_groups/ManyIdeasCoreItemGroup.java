package de.geheimagentnr1.manyideas_core.elements.item_groups;


import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;


public class ManyIdeasCoreItemGroup extends CreativeModeTab {
	
	
	public ManyIdeasCoreItemGroup() {
		
		super( ManyIdeasCore.MODID );
	}
	
	@Nonnull
	@Override
	public ItemStack makeIcon() {
		
		return new ItemStack( ModBlocks.TABLE_SAW_DIAMOND );
	}
}
