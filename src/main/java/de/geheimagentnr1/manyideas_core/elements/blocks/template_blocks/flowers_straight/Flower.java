package de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.flowers_straight;


import de.geheimagentnr1.manyideas_core.elements.blocks.BlockItemInterface;
import net.minecraft.block.Block;
import net.minecraft.block.FlowerBlock;
import net.minecraft.potion.Effect;

import javax.annotation.Nonnull;


@SuppressWarnings( "AbstractClassExtendsConcreteClass" )
public abstract class Flower extends FlowerBlock implements BlockItemInterface {
	
	
	protected Flower( Effect effect, int duration, Block.Properties properties, String registry_name ) {
		
		super( effect, duration, properties );
		setRegistryName( registry_name );
	}
	
	@Nonnull
	@Override
	public Block.OffsetType getOffsetType() {
		
		return Block.OffsetType.NONE;
	}
}
