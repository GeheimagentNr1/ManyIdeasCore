package de.geheimagentnr1.manyideascore.elements.blocks.building_blocks.woods.woods_stripped_smooth;

import de.geheimagentnr1.manyideascore.elements.blocks.BlockItemInterface;
import de.geheimagentnr1.manyideascore.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideascore.elements.blocks.building_blocks.woods.Wood;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;


public class WoodStrippedSmoothAcacia extends Wood implements BlockItemInterface {
	
	
	public final static String registry_name = "wood_stripped_smooth_acacia";
	
	public WoodStrippedSmoothAcacia() {
		
		setRegistryName( registry_name );
	}
	
	@Override
	public Item getBlockItem( Item.Properties properties ) {
		
		return new BlockItem( ModBlocks.WOOD_STRIPPED_SMOOTH_ACACIA, properties ).setRegistryName( registry_name );
	}
}