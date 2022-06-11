package de.geheimagentnr1.manyideas_core.util;

import de.geheimagentnr1.manyideas_core.elements.RegistryEntry;
import de.geheimagentnr1.manyideas_core.elements.blocks.BlockItemInterface;
import de.geheimagentnr1.manyideas_core.elements.blocks.BlockRenderTypeInterface;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

import java.util.List;


public class BlockRegistrationHelper {
	
	
	public static void registerBlockRenderTypes( List<RegistryEntry<? extends Block>> blocks ) {
		
		blocks.forEach( registryEntry -> {
			Block block = registryEntry.getValue();
			if( block instanceof BlockRenderTypeInterface blockRenderType ) {
				ItemBlockRenderTypes.setRenderLayer( block, blockRenderType.getRenderType() );
			}
		} );
	}
	
	public static void registerBlockItems(
		RegisterEvent event,
		List<RegistryEntry<? extends Block>> blocks,
		Item.Properties properties ) {
		
		if( event.getRegistryKey().equals( ForgeRegistries.Keys.ITEMS ) ) {
			event.register(
				ForgeRegistries.Keys.ITEMS,
				registerHelper -> blocks.forEach( registryEntry -> {
					if( registryEntry.getValue() instanceof BlockItemInterface blockItem ) {
						registerHelper.register(
							registryEntry.getRegistryName(),
							blockItem.getBlockItem( properties )
						);
					}
				} )
			);
		}
	}
}
