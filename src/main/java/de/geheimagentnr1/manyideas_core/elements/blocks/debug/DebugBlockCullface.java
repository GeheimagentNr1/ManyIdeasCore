package de.geheimagentnr1.manyideas_core.elements.blocks.debug;

import de.geheimagentnr1.manyideas_core.elements.blocks.BlockItemInterface;
import de.geheimagentnr1.manyideas_core.util.voxel_shapes.VoxelShapeMemory;
import de.geheimagentnr1.manyideas_core.util.voxel_shapes.VoxelShapeVector;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;


public class DebugBlockCullface extends Block implements BlockItemInterface {
	
	
	private static final String registry_name = "debug_block_cullface";
	
	private static final VoxelShapeMemory SHAPES = VoxelShapeMemory.createVoxelShapes(
		Direction.NORTH,
		VoxelShapeVector.create( 0, 0, 0, 16, 16, 0.1 )
	);
	
	public DebugBlockCullface() {
		
		super( Properties.of( Material.DIRT ) );
		setRegistryName( registry_name );
	}
	
	@SuppressWarnings( "deprecation" )
	@Nonnull
	@Override
	public VoxelShape getShape(
		BlockState state,
		@Nonnull IBlockReader worldIn,
		@Nonnull BlockPos pos,
		@Nonnull ISelectionContext context ) {
		
		return SHAPES.getShapeFromFacing( state.getValue( BlockStateProperties.FACING ) );
	}
	
	@Nullable
	@Override
	public BlockState getStateForPlacement( BlockItemUseContext context ) {
		
		return defaultBlockState().setValue( BlockStateProperties.FACING, context.getClickedFace().getOpposite() );
	}
	
	
	@Override
	protected void createBlockStateDefinition( StateContainer.Builder<Block, BlockState> builder ) {
		
		builder.add( BlockStateProperties.FACING );
	}
	
	@Override
	public Item getBlockItem( Item.Properties properties ) {
		
		return createBlockItem( this, properties, registry_name );
	}
}
