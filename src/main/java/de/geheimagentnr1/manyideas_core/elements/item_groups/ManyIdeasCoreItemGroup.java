package de.geheimagentnr1.manyideas_core.elements.item_groups;


import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideas_core.elements.items.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;


public class ManyIdeasCoreItemGroup extends ItemGroup {
	
	
	public ManyIdeasCoreItemGroup() {
		
		super( ManyIdeasCore.MODID );
	}
	
	@Nonnull
	@Override
	public ItemStack createIcon() {
		
		return new ItemStack( ModBlocks.TABLE_SAW_DIAMOND );
	}
}
