package de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.flowers_straight;


import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.NotNull;


@SuppressWarnings( "AbstractClassExtendsConcreteClass" )
public abstract class Flower extends FlowerBlock implements FlowerBlockItemInterface {
	
	
	protected Flower( @NotNull MobEffect effect, int duration, @NotNull BlockBehaviour.Properties _properties ) {
		
		super( () -> effect, duration, _properties );
	}
}
