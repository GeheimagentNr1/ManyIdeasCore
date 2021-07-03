package de.geheimagentnr1.manyideas_core.util;

import de.geheimagentnr1.manyideas_core.elements.blocks.BlockItemInterface;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;


public class BlockRegistrationHelper {
	
	
	public static void registerBlockItems(
		RegistryEvent.Register<Item> itemRegistryEvent,
		Block[] blocks,
		Item.Properties properties ) {
		
		for( Block block : blocks ) {
			if( block instanceof BlockItemInterface ) {
				BlockItemInterface blockItem = (BlockItemInterface)block;
				itemRegistryEvent.getRegistry().register( blockItem.getBlockItem( properties ) );
			}
		}
	}
}
