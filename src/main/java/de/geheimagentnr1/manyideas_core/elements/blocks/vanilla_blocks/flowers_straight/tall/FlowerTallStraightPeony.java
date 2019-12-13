package de.geheimagentnr1.manyideas_core.elements.blocks.vanilla_blocks.flowers_straight.tall;

import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.flowers_straight.TallFlower;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;


public class FlowerTallStraightPeony extends TallFlower {
	
	
	public final static String registry_name = "flower_tall_straight_peony";
	
	public FlowerTallStraightPeony() {
		
		super( Block.Properties.create( Material.TALL_PLANTS ).doesNotBlockMovement().hardnessAndResistance( 0.0F )
			.sound( SoundType.PLANT ), registry_name );
	}
	
	@Override
	public Item getBlockItem( Item.Properties properties ) {
		
		return createBlockItem( ModBlocks.FLOWER_TALL_STRAIGHT_PEONY, properties, registry_name );
	}
}
