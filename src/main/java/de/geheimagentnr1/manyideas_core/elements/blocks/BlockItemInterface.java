package de.geheimagentnr1.manyideas_core.elements.blocks;

import net.minecraft.item.Item;


@FunctionalInterface
public interface BlockItemInterface {
	
	
	Item getBlockItem( Item.Properties properties );
}
