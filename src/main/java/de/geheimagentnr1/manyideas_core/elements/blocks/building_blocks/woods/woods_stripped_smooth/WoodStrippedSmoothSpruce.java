package de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.woods.woods_stripped_smooth;

import de.geheimagentnr1.manyideas_core.elements.blocks.BlockItemInterface;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.woods.Wood;
import net.minecraft.item.Item;


public class WoodStrippedSmoothSpruce extends Wood implements BlockItemInterface {
	
	
	public final static String registry_name = "wood_stripped_smooth_spruce";
	
	public WoodStrippedSmoothSpruce() {
		
		super( registry_name );
	}
	
	@Override
	public Item getBlockItem( Item.Properties properties ) {
		
		return createBlockItem( ModBlocks.WOOD_STRIPPED_SMOOTH_SPRUCE, properties, registry_name );
	}
}