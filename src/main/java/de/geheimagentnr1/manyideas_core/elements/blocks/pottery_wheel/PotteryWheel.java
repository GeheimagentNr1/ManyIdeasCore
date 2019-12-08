package de.geheimagentnr1.manyideas_core.elements.blocks.pottery_wheel;

import de.geheimagentnr1.manyideas_core.elements.blocks.BlockItemInterface;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideas_core.util.voxel_shapes.VoxelShapeMemory;
import de.geheimagentnr1.manyideas_core.util.voxel_shapes.VoxelShapeVector;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;


public class PotteryWheel extends Block implements BlockItemInterface {
	
	
	public final static String registry_name = "pottery_wheel";
	
	private final static VoxelShapeMemory SHAPES = VoxelShapeMemory.createHorizontalVoxelShapes( Direction.WEST,
		VoxelShapeVector.create( 2, 0, 0, 16, 3, 16 ), VoxelShapeVector.create( 0, 0, 3, 1, 15, 5 ),
		VoxelShapeVector.create( 0, 0, 11, 1, 15, 13 ), VoxelShapeVector.create( 0, 9, 5, 1, 11, 11 ),
		VoxelShapeVector.create( 0, 15, 0, 3, 16, 16 ), VoxelShapeVector.create( 2, 3, 0, 3, 15, 1 ),
		VoxelShapeVector.create( 2, 3, 15, 3, 15, 16 ), VoxelShapeVector.create( 0, 11, 7, 16, 12, 9 ),
		VoxelShapeVector.create( 8, 3, 7, 10, 13, 9 ), VoxelShapeVector.create( 4, 13, 3, 14, 14, 13 ),
		VoxelShapeVector.create( 14.9, 3, 0.2, 15.9, 4, 15.8 ), VoxelShapeVector.create( 14.9, 4, 1.2, 15.9, 5, 14.8 ),
		VoxelShapeVector.create( 14.9, 5, 2.2, 15.9, 6, 13.8 ), VoxelShapeVector.create( 14.9, 6, 3.2, 15.9, 7, 12.8 ),
		VoxelShapeVector.create( 14.9, 7, 4.2, 15.9, 8, 11.8 ), VoxelShapeVector.create( 14.9, 8, 5.2, 15.9, 9, 10.8 ),
		VoxelShapeVector.create( 14.9, 9, 5.8, 15.9, 12.3, 10.2 ) );
	
	public PotteryWheel() {
		
		super( Block.Properties.create( Material.WOOD ).hardnessAndResistance( 2.5F ).sound( SoundType.WOOD ) );
		setRegistryName( registry_name );
	}
	
	@Nonnull
	@Override
	public BlockRenderLayer getRenderLayer() {
		
		return BlockRenderLayer.CUTOUT;
	}
	
	@SuppressWarnings( "deprecation" )
	@Nonnull
	@Override
	public VoxelShape getShape( BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context ) {
		
		return SHAPES.getShapeFromHorizontalFacing( state.get( BlockStateProperties.HORIZONTAL_FACING ) );
	}
	
	@Nullable
	@Override
	public BlockState getStateForPlacement( BlockItemUseContext context ) {
		
		return getDefaultState().with( BlockStateProperties.HORIZONTAL_FACING,
			context.getPlacementHorizontalFacing() );
	}
	
	@Override
	protected void fillStateContainer( StateContainer.Builder<Block, BlockState> builder ) {
		
		builder.add( BlockStateProperties.HORIZONTAL_FACING );
	}
	
	@Override
	public Item getBlockItem( Item.Properties properties ) {
		
		return createBlockItem( ModBlocks.POTTERY_WHEEL, properties, registry_name );
	}
}
