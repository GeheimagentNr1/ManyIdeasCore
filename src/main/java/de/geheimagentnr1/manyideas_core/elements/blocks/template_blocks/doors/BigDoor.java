package de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.doors;

import de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.multi_block.MultiBlock;
import de.geheimagentnr1.manyideas_core.util.voxel_shapes.VoxelShapeMemory;
import de.geheimagentnr1.manyideas_core.util.voxel_shapes.VoxelShapeVector;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoorHingeSide;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nonnull;


@SuppressWarnings( { "unused", "AbstractClassNeverImplemented" } )
public abstract class BigDoor extends MultiBlock {
	
	
	private static final VoxelShapeMemory DOORS_SHAPES = VoxelShapeMemory.createHorizontalVoxelShapes(
		Direction.SOUTH,
		VoxelShapeVector.create( 0, 0, 0, 16, 16, 3 )
	);
	
	private boolean[][][] hasBlockstateAtPos;
	
	private final boolean doubleDoorActive;
	
	protected BigDoor( Block.Properties properties, boolean _doubleDoorActive, String registry_name ) {
		
		super( properties, registry_name );
		setDefaultState( getDefaultState().with( BlockStateProperties.OPEN, false )
			.with( BlockStateProperties.POWERED, false ) );
		doubleDoorActive = _doubleDoorActive;
	}
	
	/**
	 * Gets the render layer this block will render on. SOLID for solid blocks, CUTOUT or CUTOUT_MIPPED for on-off
	 * transparency (glass, reeds), TRANSLUCENT for fully blended transparency (stained glass)
	 */
	@Nonnull
	@Override
	public BlockRenderLayer getRenderLayer() {
		
		return BlockRenderLayer.CUTOUT;
	}
	
	@SuppressWarnings( "deprecation" )
	@Nonnull
	@Override
	public VoxelShape getShape(
		BlockState state,
		@Nonnull IBlockReader worldIn,
		@Nonnull BlockPos pos,
		@Nonnull ISelectionContext context ) {
		
		Direction facing = state.get( BlockStateProperties.HORIZONTAL_FACING );
		if( state.get( BlockStateProperties.OPEN ) ) {
			int z = state.get( Z_SIZE );
			if( state.get( BlockStateProperties.DOOR_HINGE ) == DoorHingeSide.LEFT ) {
				if( z == 0 ) {
					return DOORS_SHAPES.getShapeFromHorizontalFacing( facing.rotateY() );
				}
			} else {
				if( z == getZSize() - 1 ) {
					return DOORS_SHAPES.getShapeFromHorizontalFacing( facing.rotateYCCW() );
				}
			}
		} else {
			if( state.get( X_SIZE ) == 0 ) {
				return DOORS_SHAPES.getShapeFromHorizontalFacing( facing );
			}
		}
		return VoxelShapes.empty();
	}
	
	@Override
	protected boolean[][][] hasBlockStatesAtPos() {
		
		if( hasBlockstateAtPos == null ) {
			hasBlockstateAtPos = new boolean[getXSize()][getYSize()][getZSize()];
			for( int x = 0; x < getXSize(); x++ ) {
				for( int y = 0; y < getYSize(); y++ ) {
					for( int z = 0; z < getZSize(); z++ ) {
						hasBlockstateAtPos[x][y][z] = true;
					}
				}
			}
		}
		return hasBlockstateAtPos;
	}
	
	@Override
	protected BlockState getDefaultState( boolean left_sided ) {
		
		return getDefaultState().with(
			BlockStateProperties.DOOR_HINGE,
			left_sided ? DoorHingeSide.LEFT : DoorHingeSide.RIGHT
		);
	}
	
	@SuppressWarnings( "deprecation" )
	@Override
	public boolean onBlockActivated(
		@Nonnull BlockState state,
		@Nonnull World worldIn,
		@Nonnull BlockPos pos,
		@Nonnull PlayerEntity player,
		@Nonnull Hand handIn,
		@Nonnull BlockRayTraceResult hit ) {
		
		boolean open = !state.get( BlockStateProperties.OPEN );
		Direction facing = state.get( BlockStateProperties.HORIZONTAL_FACING );
		BlockPos zeroPos = getZeroPos( state, pos );
		runForBlocks(
			zeroPos,
			facing,
			( x, y, z, blockPos ) -> worldIn.setBlockState( blockPos,
				worldIn.getBlockState( blockPos ).with( BlockStateProperties.OPEN, open ),
				3
			)
		);
		playDoorSound( player, worldIn, pos, open );
		if( doubleDoorActive ) {
			Direction direction = state.get( BlockStateProperties.DOOR_HINGE ) == DoorHingeSide.LEFT
				? facing.rotateY()
				: facing.rotateYCCW();
			BlockPos neighborPos = zeroPos.offset( direction, getZSize() );
			BlockState neighborState = worldIn.getBlockState( neighborPos );
			if( neighborState.getBlock() == this ) {
				BlockPos neighborZeroPos = getZeroPos( neighborState, neighborPos );
				if( neighborPos.equals( neighborZeroPos ) && state.get( BlockStateProperties.HORIZONTAL_FACING ) ==
					neighborState.get( BlockStateProperties.HORIZONTAL_FACING ) &&
					state.get( BlockStateProperties.DOOR_HINGE ) !=
						neighborState.get( BlockStateProperties.DOOR_HINGE ) ) {
					runForBlocks(
						neighborZeroPos,
						facing,
						( x, y, z, blockPos ) -> worldIn.setBlockState( blockPos,
							worldIn.getBlockState( blockPos ).with( BlockStateProperties.OPEN, open ),
							3
						)
					);
				}
			}
		}
		return true;
	}
	
	@SuppressWarnings( "deprecation" )
	@Override
	public void neighborChanged(
		@Nonnull BlockState state,
		@Nonnull World worldIn,
		@Nonnull BlockPos pos,
		@Nonnull Block blockIn,
		@Nonnull BlockPos fromPos,
		boolean isMoving ) {
		
		if( blockIn == this ) {
			return;
		}
		BlockPos zeroPos = getZeroPos( state, pos );
		DoorHingeSide doorHingeSide = state.get( BlockStateProperties.DOOR_HINGE );
		Direction facing = state.get( BlockStateProperties.HORIZONTAL_FACING );
		boolean isPowered = isPowered( worldIn, zeroPos, facing );
		Direction direction = doorHingeSide == DoorHingeSide.LEFT ? facing.rotateY() : facing.rotateYCCW();
		BlockPos neighborPos = zeroPos.offset( direction, getZSize() );
		BlockState neighborState = worldIn.getBlockState( neighborPos );
		BlockPos neighborZeroPos = null;
		boolean isNeighborDoubleDoor = false;
		if( neighborState.getBlock() == this ) {
			neighborZeroPos = getZeroPos( neighborState, neighborPos );
			if( neighborPos.equals( neighborZeroPos ) &&
				facing == neighborState.get( BlockStateProperties.HORIZONTAL_FACING ) &&
				doorHingeSide != neighborState.get( BlockStateProperties.DOOR_HINGE ) ) {
				isNeighborDoubleDoor = true;
				isPowered |= isPowered( worldIn, neighborZeroPos, facing );
			}
		}
		if( isPowered != state.get( BlockStateProperties.POWERED ) ) {
			if( state.get( BlockStateProperties.OPEN ) != isPowered ) {
				playDoorSound( null, worldIn, pos, isPowered );
			}
			boolean isDoorPowered = isPowered;
			runForBlocks(
				zeroPos,
				facing,
				( x, y, z, blockPos ) -> worldIn.setBlockState( blockPos,
					worldIn.getBlockState( blockPos )
						.with( BlockStateProperties.POWERED, isDoorPowered )
						.with( BlockStateProperties.OPEN, isDoorPowered ),
					3
				)
			);
			if( doubleDoorActive && isNeighborDoubleDoor ) {
				runForBlocks(
					neighborZeroPos,
					facing,
					( x, y, z, blockPos ) -> worldIn.setBlockState( blockPos,
						worldIn.getBlockState( blockPos )
							.with( BlockStateProperties.POWERED, isDoorPowered )
							.with( BlockStateProperties.OPEN, isDoorPowered ),
						3
					)
				);
			}
		}
	}
	
	@Override
	protected void fillStateContainer( StateContainer.Builder<Block, BlockState> builder ) {
		
		super.fillStateContainer( builder );
		builder.add( BlockStateProperties.DOOR_HINGE, BlockStateProperties.OPEN, BlockStateProperties.POWERED );
	}
	
	private void playDoorSound( PlayerEntity player, World world, BlockPos pos, boolean open ) {
		
		world.playSound(
			player,
			pos,
			open ? getOpenDoorSound() : getCloseDoorSound(),
			SoundCategory.BLOCKS,
			1.0F,
			1.0F
		);
	}
	
	private SoundEvent getCloseDoorSound() {
		
		return material == Material.IRON ? SoundEvents.BLOCK_IRON_DOOR_OPEN : SoundEvents.BLOCK_WOODEN_DOOR_OPEN;
	}
	
	private SoundEvent getOpenDoorSound() {
		
		return material == Material.IRON ? SoundEvents.BLOCK_IRON_DOOR_CLOSE : SoundEvents.BLOCK_WOODEN_DOOR_CLOSE;
	}
}
