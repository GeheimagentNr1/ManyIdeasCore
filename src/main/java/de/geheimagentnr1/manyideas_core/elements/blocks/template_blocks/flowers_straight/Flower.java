package de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.flowers_straight;


import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.NotNull;


@SuppressWarnings( "AbstractClassExtendsConcreteClass" )
public abstract class Flower extends FlowerBlock implements FlowerBlockItemInterface {
	
	
	protected Flower( @NotNull Holder<MobEffect> pEffect, int pSeconds, @NotNull BlockBehaviour.Properties pProperties ) {
		
		super( pEffect, pSeconds, pProperties );
	}
}
