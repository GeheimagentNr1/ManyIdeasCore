package de.geheimagentnr1.manyideascore.elements.item_groups;


import de.geheimagentnr1.manyideascore.ManyIdeasCore;
import de.geheimagentnr1.manyideascore.elements.items.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;


public class CoreItemGroup extends ItemGroup {
	
	
	public CoreItemGroup() {
		
		super( ManyIdeasCore.MODID );
	}
	
	@Nonnull
	@Override
	public ItemStack createIcon() {
		
		return new ItemStack( ModItems.PLATE_IRON );
	}
}
