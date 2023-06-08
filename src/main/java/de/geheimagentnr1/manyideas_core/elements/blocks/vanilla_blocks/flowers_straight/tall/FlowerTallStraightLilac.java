package de.geheimagentnr1.manyideas_core.elements.blocks.vanilla_blocks.flowers_straight.tall;

import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.flowers_straight.TallFlower;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;


public class FlowerTallStraightLilac extends TallFlower {
	
	
	public static final String registry_name = "flower_tall_straight_lilac";
	
	public FlowerTallStraightLilac() {
		
		super(
			BlockBehaviour.Properties.of()
				.mapColor( MapColor.PLANT )
				.noCollission()
				.instabreak()
				.sound( SoundType.GRASS )
		);
	}
	
	@Override
	public Item getBlockItem( Item.Properties _properties ) {
		
		return createBlockItem( ModBlocks.FLOWER_TALL_STRAIGHT_LILAC, _properties, registry_name );
	}
}
