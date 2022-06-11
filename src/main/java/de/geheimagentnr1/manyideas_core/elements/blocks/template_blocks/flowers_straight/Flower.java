package de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.flowers_straight;


import de.geheimagentnr1.manyideas_core.elements.blocks.BlockRenderTypeInterface;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

import javax.annotation.Nonnull;


@SuppressWarnings( "AbstractClassExtendsConcreteClass" )
public abstract class Flower extends FlowerBlock implements FlowerBlockItemInterface, BlockRenderTypeInterface {
	
	
	protected Flower( MobEffect effect, int duration, BlockBehaviour.Properties _properties ) {
		
		super( effect, duration, _properties );
	}
	
	@Override
	public RenderType getRenderType() {
		
		return RenderType.cutout();
	}
}
