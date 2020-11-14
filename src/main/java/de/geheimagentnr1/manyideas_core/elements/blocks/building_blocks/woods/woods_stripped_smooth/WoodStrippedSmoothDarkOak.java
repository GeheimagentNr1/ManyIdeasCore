package de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.woods.woods_stripped_smooth;

import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.woods.Wood;
import net.minecraft.item.Item;


public class WoodStrippedSmoothDarkOak extends Wood {
	
	
	public static final String registry_name = "wood_stripped_smooth_dark_oak";
	
	public WoodStrippedSmoothDarkOak() {
		
		super( registry_name );
	}
	
	@Override
	public Item getBlockItem( Item.Properties properties ) {
		
		return createBlockItem( ModBlocks.SMOOTH_STRIPPED_DARK_OAK_WOOD, properties, registry_name );
	}
}