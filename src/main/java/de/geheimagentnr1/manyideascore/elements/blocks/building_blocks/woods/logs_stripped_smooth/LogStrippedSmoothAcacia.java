package de.geheimagentnr1.manyideascore.elements.blocks.building_blocks.woods.logs_stripped_smooth;

import de.geheimagentnr1.manyideascore.elements.blocks.BlockItemInterface;
import de.geheimagentnr1.manyideascore.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideascore.elements.blocks.building_blocks.woods.Wood;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;


public class LogStrippedSmoothAcacia extends Wood implements BlockItemInterface {
	
	
	public final static String registry_name = "log_stripped_smooth_acacia";
	
	public LogStrippedSmoothAcacia() {
		
		setRegistryName( registry_name );
	}
	
	@Override
	public Item getBlockItem( Item.Properties properties ) {
		
		return new BlockItem( ModBlocks.LOG_STRIPPED_SMOOTH_ACACIA, properties ).setRegistryName( registry_name );
	}
}