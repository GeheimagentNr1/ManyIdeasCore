package de.geheimagentnr1.manyideas_core.elements.blocks.vanilla_blocks.flowers_straight.normal;

import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.flowers_straight.Flower;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.potion.Effects;


public class FlowerStraightAllium extends Flower {
	
	
	public static final String registry_name = "flower_straight_allium";
	
	public FlowerStraightAllium() {
		
		super(
			Effects.FIRE_RESISTANCE,
			4,
			AbstractBlock.Properties.of( Material.PLANT ).noCollission().instabreak().sound( SoundType.GRASS ),
			registry_name
		);
	}
	
	@Override
	public Item getBlockItem( Item.Properties _properties ) {
		
		return createBlockItem( ModBlocks.FLOWER_STRAIGHT_ALLIUM, _properties, registry_name );
	}
}
