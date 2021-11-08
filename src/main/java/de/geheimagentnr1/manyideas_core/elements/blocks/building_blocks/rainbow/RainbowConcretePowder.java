package de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.rainbow;

import de.geheimagentnr1.manyideas_core.elements.blocks.BlockItemInterface;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ConcretePowderBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;


public class RainbowConcretePowder extends ConcretePowderBlock implements BlockItemInterface {
	
	
	public static final String registry_name = "rainbow_concrete_powder";
	
	//package-private
	static Block CONCRETE_BLOCK;
	
	public RainbowConcretePowder() {
		
		super(
			CONCRETE_BLOCK,
			BlockBehaviour.Properties.of( Material.SAND ).strength( 0.5F ).sound( SoundType.SAND )
		);
		setRegistryName( registry_name );
	}
	
	@Override
	public Item getBlockItem( Item.Properties _properties ) {
		
		return createBlockItem( ModBlocks.RAINBOW_CONCRETE_POWDER, _properties, registry_name );
	}
}
