package de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.woods.woods_stripped_smooth;

import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.woods.Wood;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;


public class WoodStrippedSmoothAcacia extends Wood {
	
	
	public static final String registry_name = "wood_stripped_smooth_acacia";
	
	public WoodStrippedSmoothAcacia() {
		
		super( BlockBehaviour.Properties.of().mapColor( MapColor.COLOR_ORANGE ) );
	}
	
	@Override
	public Item getBlockItem( Item.Properties _properties ) {
		
		return createBlockItem( ModBlocks.WOOD_STRIPPED_SMOOTH_ACACIA, _properties, registry_name );
	}
}