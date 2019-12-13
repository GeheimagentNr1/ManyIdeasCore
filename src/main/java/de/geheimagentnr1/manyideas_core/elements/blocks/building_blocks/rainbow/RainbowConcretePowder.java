package de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.rainbow;

import de.geheimagentnr1.manyideas_core.elements.blocks.BlockItemInterface;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.ConcretePowderBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;


public class RainbowConcretePowder extends ConcretePowderBlock implements BlockItemInterface {
	
	
	public final static String registry_name = "rainbow_concrete_powder";
	
	//package-private
	static Block CONCRETE_BLOCK;
	
	public RainbowConcretePowder() {
		
		super( CONCRETE_BLOCK, Block.Properties.create( Material.SAND ).hardnessAndResistance( 0.5F )
			.sound( SoundType.SAND ) );
		setRegistryName( registry_name );
	}
	
	@Override
	public Item getBlockItem( Item.Properties properties ) {
		
		return createBlockItem( ModBlocks.RAINBOW_CONCRETE_POWDER, properties, registry_name );
	}
}
