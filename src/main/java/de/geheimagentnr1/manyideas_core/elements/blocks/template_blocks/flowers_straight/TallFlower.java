package de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.flowers_straight;

import de.geheimagentnr1.manyideas_core.elements.blocks.BlockRenderTypeInterface;
import net.minecraft.block.TallFlowerBlock;
import net.minecraft.client.renderer.RenderType;

import javax.annotation.Nonnull;


@SuppressWarnings( "AbstractClassExtendsConcreteClass" )
public abstract class TallFlower extends TallFlowerBlock implements FlowerBlockItemInterface, BlockRenderTypeInterface {
	
	
	protected TallFlower( Properties properties, String registry_name ) {
		
		super( properties );
		setRegistryName( registry_name );
	}
	
	@Override
	public RenderType getRenderType() {
		
		return RenderType.cutout();
	}
	
	@Nonnull
	@Override
	public OffsetType getOffsetType() {
		
		return OffsetType.NONE;
	}
}
