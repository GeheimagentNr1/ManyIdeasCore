package de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.woods.woods_stripped_smooth;

import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.woods.Wood;
import net.minecraft.item.Item;


public class WoodStrippedSmoothBirch extends Wood {
	
	
	public static final String registry_name = "wood_stripped_smooth_birch";
	
	public WoodStrippedSmoothBirch() {
		
		super( registry_name );
	}
	
	@Override
	public Item getBlockItem( Item.Properties properties ) {
		
		return createBlockItem( ModBlocks.WOOD_STRIPPED_SMOOTH_BIRCH, properties, registry_name );
	}
}