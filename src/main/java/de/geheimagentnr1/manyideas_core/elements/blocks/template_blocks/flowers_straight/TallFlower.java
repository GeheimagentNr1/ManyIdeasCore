package de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.flowers_straight;

import net.minecraft.block.Block;
import net.minecraft.block.TallFlowerBlock;

import javax.annotation.Nonnull;


@SuppressWarnings( "AbstractClassExtendsConcreteClass" )
public abstract class TallFlower extends TallFlowerBlock implements FlowerBlockItemInterface {
	
	
	protected TallFlower( Block.Properties properties, String registry_name ) {
		
		super( properties );
		setRegistryName( registry_name );
	}
	
	@Nonnull
	@Override
	public Block.OffsetType getOffsetType() {
		
		return Block.OffsetType.NONE;
	}
}
