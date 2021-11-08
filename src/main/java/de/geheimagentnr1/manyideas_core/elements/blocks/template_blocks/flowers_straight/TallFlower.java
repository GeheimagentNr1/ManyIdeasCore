package de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.flowers_straight;

import de.geheimagentnr1.manyideas_core.elements.blocks.BlockRenderTypeInterface;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.TallFlowerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

import javax.annotation.Nonnull;


@SuppressWarnings( "AbstractClassExtendsConcreteClass" )
public abstract class TallFlower extends TallFlowerBlock implements FlowerBlockItemInterface, BlockRenderTypeInterface {
	
	
	protected TallFlower( BlockBehaviour.Properties _properties, String registry_name ) {
		
		super( _properties );
		setRegistryName( registry_name );
	}
	
	@Override
	public RenderType getRenderType() {
		
		return RenderType.cutout();
	}
	
	@Nonnull
	@Override
	public BlockBehaviour.OffsetType getOffsetType() {
		
		return BlockBehaviour.OffsetType.NONE;
	}
}
