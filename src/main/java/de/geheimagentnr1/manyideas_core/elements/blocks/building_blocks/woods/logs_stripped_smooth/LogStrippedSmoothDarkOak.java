package de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.woods.logs_stripped_smooth;

import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.woods.Wood;
import net.minecraft.item.Item;


public class LogStrippedSmoothDarkOak extends Wood {
	
	
	public static final String registry_name = "log_stripped_smooth_dark_oak";
	
	public LogStrippedSmoothDarkOak() {
		
		super( registry_name );
	}
	
	@Override
	public Item getBlockItem( Item.Properties properties ) {
		
		return createBlockItem( ModBlocks.LOG_STRIPPED_SMOOTH_DARK_OAK, properties, registry_name );
	}
}