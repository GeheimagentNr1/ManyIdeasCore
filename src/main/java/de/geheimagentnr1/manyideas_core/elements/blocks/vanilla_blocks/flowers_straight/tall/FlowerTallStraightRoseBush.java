package de.geheimagentnr1.manyideas_core.elements.blocks.vanilla_blocks.flowers_straight.tall;

import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.flowers_straight.TallFlower;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;


public class FlowerTallStraightRoseBush extends TallFlower {
	
	
	public static final String registry_name = "flower_tall_straight_rose_bush";
	
	public FlowerTallStraightRoseBush() {
		
		super(
			Properties.of( Material.REPLACEABLE_PLANT )
				.noCollission()
				.instabreak()
				.sound( SoundType.GRASS ),
			registry_name
		);
	}
	
	@Override
	public Item getBlockItem( Item.Properties properties ) {
		
		return createBlockItem( ModBlocks.FLOWER_TALL_STRAIGHT_ROSE_BUSH, properties, registry_name );
	}
}
