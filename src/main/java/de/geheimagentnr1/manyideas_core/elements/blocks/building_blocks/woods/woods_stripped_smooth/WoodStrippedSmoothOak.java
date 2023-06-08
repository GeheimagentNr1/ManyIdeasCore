package de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.woods.woods_stripped_smooth;

import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.woods.Wood;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;


public class WoodStrippedSmoothOak extends Wood {
	
	
	public static final String registry_name = "wood_stripped_smooth_oak";
	
	public WoodStrippedSmoothOak() {
		
		super( BlockBehaviour.Properties.of().mapColor( MapColor.WOOD ) );
	}
	
	@Override
	public Item getBlockItem( Item.Properties _properties ) {
		
		return createBlockItem( ModBlocks.WOOD_STRIPPED_SMOOTH_OAK, _properties, registry_name );
	}
}