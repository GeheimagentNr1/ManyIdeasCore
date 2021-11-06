package de.geheimagentnr1.manyideas_core.util;

import de.geheimagentnr1.manyideas_core.elements.blocks.BlockItemInterface;
import de.geheimagentnr1.manyideas_core.elements.blocks.BlockRenderTypeInterface;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;


public class BlockRegistrationHelper {
	
	
	public static void registerBlockRenderTypes( Block[] blocks ) {
		
		for( Block block : blocks ) {
			if( block instanceof BlockRenderTypeInterface ) {
				BlockRenderTypeInterface blockRenderType = (BlockRenderTypeInterface)block;
				RenderTypeLookup.setRenderLayer( block, blockRenderType.getRenderType() );
			}
		}
	}
	
	public static void registerBlockItems(
		RegistryEvent.Register<Item> event,
		Block[] blocks,
		Item.Properties properties ) {
		
		for( Block block : blocks ) {
			if( block instanceof BlockItemInterface ) {
				BlockItemInterface blockItem = (BlockItemInterface)block;
				event.getRegistry().register( blockItem.getBlockItem( properties ) );
			}
		}
	}
}
