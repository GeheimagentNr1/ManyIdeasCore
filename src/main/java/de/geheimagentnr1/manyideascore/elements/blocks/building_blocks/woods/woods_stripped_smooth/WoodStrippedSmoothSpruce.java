package de.geheimagentnr1.manyideascore.elements.blocks.building_blocks.woods.woods_stripped_smooth;

import de.geheimagentnr1.manyideascore.elements.blocks.BlockItemInterface;
import de.geheimagentnr1.manyideascore.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideascore.elements.blocks.building_blocks.woods.Wood;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;


public class WoodStrippedSmoothSpruce extends Wood implements BlockItemInterface {
	
	
	public final static String registry_name = "wood_stripped_smooth_spruce";
	
	public WoodStrippedSmoothSpruce() {
		
		setRegistryName( registry_name );
	}
	
	@Override
	public Item getBlockItem( Item.Properties properties ) {
		
		return new BlockItem( ModBlocks.WOOD_STRIPPED_SMOOTH_SPRUCE, properties ).setRegistryName( registry_name );
	}
}