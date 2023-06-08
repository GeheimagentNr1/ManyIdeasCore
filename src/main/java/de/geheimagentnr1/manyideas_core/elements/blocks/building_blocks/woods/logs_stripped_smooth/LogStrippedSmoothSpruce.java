package de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.woods.logs_stripped_smooth;

import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.woods.Wood;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;


public class LogStrippedSmoothSpruce extends Wood {
	
	
	public static final String registry_name = "log_stripped_smooth_spruce";
	
	public LogStrippedSmoothSpruce() {
		
		super( BlockBehaviour.Properties.of().mapColor( MapColor.PODZOL ) );
	}
	
	@Override
	public Item getBlockItem( Item.Properties _properties ) {
		
		return createBlockItem( ModBlocks.LOG_STRIPPED_SMOOTH_SPRUCE, _properties, registry_name );
	}
}