package de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.flowers_straight;


import de.geheimagentnr1.manyideas_core.elements.blocks.BlockRenderTypeInterface;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.FlowerBlock;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.potion.Effect;

import javax.annotation.Nonnull;


@SuppressWarnings( "AbstractClassExtendsConcreteClass" )
public abstract class Flower extends FlowerBlock implements FlowerBlockItemInterface, BlockRenderTypeInterface {
	
	
	protected Flower( Effect effect, int duration, AbstractBlock.Properties _properties, String registry_name ) {
		
		super( effect, duration, _properties );
		setRegistryName( registry_name );
	}
	
	@Override
	public RenderType getRenderType() {
		
		return RenderType.cutout();
	}
	
	@Nonnull
	@Override
	public AbstractBlock.OffsetType getOffsetType() {
		
		return AbstractBlock.OffsetType.NONE;
	}
}
