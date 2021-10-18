package de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.flowers_straight;


import de.geheimagentnr1.manyideas_core.elements.blocks.BlockRenderTypeInterface;
import net.minecraft.block.Block;
import net.minecraft.block.FlowerBlock;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.potion.Effect;

import javax.annotation.Nonnull;


@SuppressWarnings( "AbstractClassExtendsConcreteClass" )
public abstract class Flower extends FlowerBlock implements FlowerBlockItemInterface, BlockRenderTypeInterface {
	
	
	protected Flower( Effect effect, int duration, Block.Properties properties, String registry_name ) {
		
		super( effect, duration, properties );
		setRegistryName( registry_name );
	}
	
	@Override
	public RenderType getRenderType() {
		
		return RenderType.getCutout();
	}
	
	@Nonnull
	@Override
	public Block.OffsetType getOffsetType() {
		
		return Block.OffsetType.NONE;
	}
}
