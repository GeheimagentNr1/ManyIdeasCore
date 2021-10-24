package de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks;

import de.geheimagentnr1.manyideas_core.elements.blocks.BlockItemInterface;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;

import javax.annotation.Nullable;


@SuppressWarnings( "AbstractClassExtendsConcreteClass" )
public abstract class AxisBlock extends Block implements BlockItemInterface {
	
	
	protected AxisBlock( Properties properties, String registry_name ) {
		
		super( properties );
		setRegistryName( registry_name );
	}
	
	@Nullable
	@Override
	public BlockState getStateForPlacement( BlockItemUseContext context ) {
		
		return defaultBlockState().setValue( BlockStateProperties.AXIS, context.getClickedFace().getAxis() );
	}
	
	@Override
	protected void createBlockStateDefinition( StateContainer.Builder<Block, BlockState> builder ) {
		
		builder.add( BlockStateProperties.AXIS );
	}
}
