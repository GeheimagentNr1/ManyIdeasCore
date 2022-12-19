package de.geheimagentnr1.manyideas_core.elements.creative_mod_tabs;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.config.ClientConfig;
import de.geheimagentnr1.manyideas_core.elements.block_state_properties.Color;
import de.geheimagentnr1.manyideas_core.elements.blocks.BlockItemInterface;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocksDebug;
import de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.dyed.DyeBlockItem;
import de.geheimagentnr1.manyideas_core.elements.items.ModItems;
import de.geheimagentnr1.manyideas_core.util.DyeBlockHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class ManyIdeasCoreCreativeModeTabFactory implements CreativeModeTabFactory {
	
	
	@Override
	public String getModId() {
		
		return ManyIdeasCore.MODID;
	}
	
	@Override
	public String getRegistryName() {
		
		return ManyIdeasCore.MODID;
	}
	
	@Override
	public Item getDisplayItem() {
		
		return ModBlocks.TABLE_SAW_DIAMOND.asItem();
	}
	
	@Override
	public List<ItemStack> getDisplayItems() {
		
		ArrayList<ItemStack> displayItems = new ArrayList<>();
		displayItems.addAll(
			ModBlocks.BLOCKS.stream()
				.filter( registryEntry -> registryEntry.getValue() instanceof BlockItemInterface )
				.flatMap( registryEntry -> {
					
					Item item = registryEntry.getValue().asItem();
					if( item instanceof DyeBlockItem ) {
						if( ClientConfig.ALL_COLORS_IN_ITEM_GROUP.get() ) {
							List<ItemStack> items = new ArrayList<>();
							for( Color color : Color.values() ) {
								items.add( DyeBlockHelper.createItemStackOfItem(
									item,
									color
								) );
							}
							return items.stream();
						} else {
							return Stream.of( DyeBlockHelper.createItemStackOfItem(
								item,
								Color.WHITE
							) );
						}
					} else {
						return Stream.of( new ItemStack( registryEntry.getValue() ) );
					}
				} )
				.toList()
		);
		displayItems.addAll(
			ModBlocksDebug.BLOCKS.stream()
				.filter( registryEntry -> registryEntry.getValue() instanceof BlockItemInterface )
				.map( registryEntry -> new ItemStack( registryEntry.getValue() ) )
				.toList()
		);
		displayItems.addAll(
			ModItems.ITEMS.stream()
				.map( registryEntry -> new ItemStack( registryEntry.getValue() ) )
				.toList()
		);
		return displayItems;
	}
}
