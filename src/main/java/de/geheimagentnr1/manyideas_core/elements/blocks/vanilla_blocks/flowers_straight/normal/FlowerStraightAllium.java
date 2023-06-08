package de.geheimagentnr1.manyideas_core.elements.blocks.vanilla_blocks.flowers_straight.normal;

import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.flowers_straight.Flower;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;


public class FlowerStraightAllium extends Flower {
	
	
	public static final String registry_name = "flower_straight_allium";
	
	public FlowerStraightAllium() {
		
		super(
			MobEffects.FIRE_RESISTANCE,
			4,
			BlockBehaviour.Properties.of()
				.mapColor( MapColor.PLANT )
				.noCollission()
				.instabreak()
				.sound( SoundType.GRASS )
		);
	}
	
	@Override
	public Item getBlockItem( Item.Properties _properties ) {
		
		return createBlockItem( ModBlocks.FLOWER_STRAIGHT_ALLIUM, _properties, registry_name );
	}
}
