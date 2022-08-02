package de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.flowers_straight;

import de.geheimagentnr1.manyideas_core.elements.blocks.BlockRenderTypeInterface;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.TallFlowerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;


@SuppressWarnings( "AbstractClassExtendsConcreteClass" )
public abstract class TallFlower extends TallFlowerBlock implements FlowerBlockItemInterface, BlockRenderTypeInterface {
	
	
	protected TallFlower( BlockBehaviour.Properties _properties ) {
		
		super( _properties );
	}
	
	@Override
	public RenderType getRenderType() {
		
		return RenderType.cutout();
	}
}
