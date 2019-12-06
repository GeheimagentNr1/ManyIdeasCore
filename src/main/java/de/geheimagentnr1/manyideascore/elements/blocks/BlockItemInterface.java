package de.geheimagentnr1.manyideascore.elements.blocks;

import net.minecraft.item.Item;


@FunctionalInterface
public interface BlockItemInterface {
	
	
	Item getBlockItem( Item.Properties properties );
}
