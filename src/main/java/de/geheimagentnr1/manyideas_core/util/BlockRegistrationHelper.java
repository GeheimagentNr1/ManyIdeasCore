package de.geheimagentnr1.manyideas_core.util;

import de.geheimagentnr1.manyideas_core.elements.blocks.BlockItemInterface;
import de.geheimagentnr1.manyideas_core.elements.blocks.BlockRenderTypeInterface;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.RegistryEvent;


public class BlockRegistrationHelper {
	
	
	public static void registerBlockRenderTypes( Block[] blocks ) {
		
		for( Block block : blocks ) {
			if( block instanceof BlockRenderTypeInterface blockRenderType ) {
				ItemBlockRenderTypes.setRenderLayer( block, blockRenderType.getRenderType() );
			}
		}
	}
	
	public static void registerBlockItems(
		RegistryEvent.Register<Item> event,
		Block[] blocks,
		Item.Properties properties ) {
		
		for( Block block : blocks ) {
			if( block instanceof BlockItemInterface blockItem ) {
				event.getRegistry().register( blockItem.getBlockItem( properties ) );
			}
		}
	}
}
