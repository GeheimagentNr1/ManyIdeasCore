package de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.flowers_straight;

import net.minecraft.world.level.block.TallFlowerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.NotNull;


@SuppressWarnings( "AbstractClassExtendsConcreteClass" )
public abstract class TallFlower extends TallFlowerBlock implements FlowerBlockItemInterface {
	
	
	protected TallFlower( @NotNull BlockBehaviour.Properties _properties ) {
		
		super( _properties );
	}
}
