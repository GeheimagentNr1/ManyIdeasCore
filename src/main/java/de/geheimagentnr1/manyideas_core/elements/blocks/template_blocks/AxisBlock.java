package de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;

import javax.annotation.Nullable;


@SuppressWarnings( "AbstractClassExtendsConcreteClass" )
public abstract class AxisBlock extends Block {
	
	
	protected AxisBlock( Block.Properties properties ) {
		
		super( properties );
	}
	
	@Nullable
	@Override
	public BlockState getStateForPlacement( BlockItemUseContext context ) {
		
		return getDefaultState().with( BlockStateProperties.AXIS, context.getFace().getAxis() );
	}
	
	@Override
	protected void fillStateContainer( StateContainer.Builder<Block, BlockState> builder ) {
		
		builder.add( BlockStateProperties.AXIS );
	}
}
