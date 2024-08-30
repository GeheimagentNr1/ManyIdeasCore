package de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.doors;

import de.geheimagentnr1.manyideas_core.elements.block_state_properties.ModBlockStateProperties;
import de.geheimagentnr1.manyideas_core.elements.block_state_properties.OpenedBy;
import de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.multi_block.MultiBlock;
import de.geheimagentnr1.manyideas_core.elements.items.ModItemsRegisterFactory;
import de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.interfaces.RedstoneKeyable;
import de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.models.Option;
import de.geheimagentnr1.manyideas_core.util.doors.BigDoorsHelper;
import de.geheimagentnr1.manyideas_core.util.doors.BlockData;
import de.geheimagentnr1.manyideas_core.util.doors.DoorsHelper;
import de.geheimagentnr1.manyideas_core.util.doors.OpenedByHelper;
import de.geheimagentnr1.manyideas_core.util.voxel_shapes.VoxelShapeMemory;
import de.geheimagentnr1.manyideas_core.util.voxel_shapes.VoxelShapeVector;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.List;


@SuppressWarnings( { "unused", "AbstractClassNeverImplemented" } )
public abstract class BigDoor extends MultiBlock implements RedstoneKeyable {
	
	
	@NotNull
	private static final VoxelShapeMemory DOORS_SHAPES = VoxelShapeMemory.createHorizontalVoxelShapes(
		Direction.SOUTH,
		VoxelShapeVector.create( 0, 0, 0, 16, 16, 3 )
	);
	
	@NotNull
	private final BlockSetType type;
	
	private boolean[][][] hasBlockstateAtPos;
	
	private final boolean doubleDoorActive;
	
	protected BigDoor(
		@NotNull BlockBehaviour.Properties _properties,
		@NotNull BlockSetType _type,
		@NotNull OpenedBy openedBy,
		boolean _doubleDoorActive ) {
		
		super( _properties.noOcclusion().isViewBlocking( ( state, level, pos ) -> false ) );
		registerDefaultState(
			defaultBlockState().setValue( BlockStateProperties.OPEN, false )
				.setValue( BlockStateProperties.POWERED, false )
				.setValue( ModBlockStateProperties.OPENED_BY, openedBy )
		);
		type = _type;
		doubleDoorActive = _doubleDoorActive;
	}
	
	@SuppressWarnings( "deprecation" )
	@NotNull
	@Override
	public VoxelShape getShape(
		@NotNull BlockState state,
		@NotNull BlockGetter level,
		@NotNull BlockPos pos,
		@NotNull CollisionContext context ) {
		
		Direction facing = state.getValue( BlockStateProperties.HORIZONTAL_FACING );
		if( state.getValue( BlockStateProperties.OPEN ) ) {
			int z = state.getValue( Z_SIZE );
			if( state.getValue( BlockStateProperties.DOOR_HINGE ) == DoorHingeSide.LEFT ) {
				if( z == 0 ) {
					return DOORS_SHAPES.getShapeFromHorizontalFacing( facing.getClockWise() );
				}
			} else {
				if( z == getZSize() - 1 ) {
					return DOORS_SHAPES.getShapeFromHorizontalFacing( facing.getCounterClockWise() );
				}
			}
		} else {
			if( state.getValue( X_SIZE ) == 0 ) {
				return DOORS_SHAPES.getShapeFromHorizontalFacing( facing );
			}
		}
		return Shapes.empty();
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
	
	@NotNull
	@Override
	protected BlockState getDefaultState( boolean left_sided ) {
		
		return defaultBlockState().setValue(
			BlockStateProperties.DOOR_HINGE,
			left_sided ? DoorHingeSide.LEFT : DoorHingeSide.RIGHT
		);
	}
	
	@NotNull
	protected BlockPos getZeroPos( @NotNull BlockData blockData ) {
		
		return getZeroPos( blockData.getState(), blockData.getPos() );
	}
	
	@NotNull
	@Override
	protected ItemInteractionResult useItemOn(
		@NotNull ItemStack pStack,
		@NotNull BlockState pState,
		@NotNull Level pLevel,
		@NotNull BlockPos pPos,
		@NotNull Player pPlayer,
		@NotNull InteractionHand pHand,
		@NotNull BlockHitResult pHitResult ) {
		
		if( pStack.getItem() != ModItemsRegisterFactory.RESTONE_KEY &&
			OpenedByHelper.canBeOpened( pState, true ) ) {
			boolean open = !pState.getValue( BlockStateProperties.OPEN );
			Direction facing = pState.getValue( BlockStateProperties.HORIZONTAL_FACING );
			BlockPos zeroPos = getZeroPos( pState, pPos );
			runForBlocks(
				pLevel,
				zeroPos,
				facing,
				( x, y, z, blockPos ) -> pLevel.setBlock(
					blockPos,
					pLevel.getBlockState( blockPos ).setValue( BlockStateProperties.OPEN, open ),
					3
				),
				true
			);
			DoorsHelper.playDoorSound( pLevel, pPos, type, pPlayer, open );
			if( doubleDoorActive ) {
				BlockData neighbor = BigDoorsHelper.getNeighborBlock(
					pLevel,
					zeroPos,
					pState,
					getZSize(),
					this::getZeroPos
				);
				if( BigDoorsHelper.isNeighbor( pState, neighbor ) ) {
					runForBlocks(
						pLevel,
						neighbor.getZeroPos(),
						facing,
						( x, y, z, blockPos ) -> pLevel.setBlock(
							blockPos,
							pLevel.getBlockState( blockPos ).setValue( BlockStateProperties.OPEN, open ),
							3
						),
						true
					);
				}
			}
			return ItemInteractionResult.SUCCESS;
		}
		return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
	}
	
	@SuppressWarnings( "deprecation" )
	@Override
	public void neighborChanged(
		@NotNull BlockState state,
		@NotNull Level level,
		@NotNull BlockPos pos,
		@NotNull Block block,
		@NotNull BlockPos fromPos,
		boolean isMoving ) {
		
		if( block != this && OpenedByHelper.canBeOpened( state, false ) ) {
			BlockPos zeroPos = getZeroPos( state, pos );
			Direction facing = state.getValue( BlockStateProperties.HORIZONTAL_FACING );
			BlockData neighbor = BigDoorsHelper.getNeighborBlock(
				level,
				zeroPos,
				state,
				getZSize(),
				this::getZeroPos
			);
			boolean isNeighbor = BigDoorsHelper.isNeighbor( state, neighbor );
			boolean isDoorPowered = isPowered( level, zeroPos, facing ) ||
				isNeighbor && isPowered( level, neighbor.getZeroPos(), facing );
			
			if( isDoorPowered != state.getValue( BlockStateProperties.POWERED ) ) {
				if( state.getValue( BlockStateProperties.OPEN ) != isDoorPowered ) {
					DoorsHelper.playDoorSound( level, pos, type, null, isDoorPowered );
				}
				runForBlocks(
					level,
					zeroPos,
					facing,
					( x, y, z, blockPos ) -> level.setBlock(
						blockPos,
						level.getBlockState( blockPos )
							.setValue( BlockStateProperties.POWERED, isDoorPowered )
							.setValue( BlockStateProperties.OPEN, isDoorPowered ),
						3
					),
					true
				);
				if( doubleDoorActive && isNeighbor ) {
					runForBlocks(
						level,
						neighbor.getZeroPos(),
						facing,
						( x, y, z, blockPos ) -> level.setBlock(
							blockPos,
							level.getBlockState( blockPos )
								.setValue( BlockStateProperties.POWERED, isDoorPowered )
								.setValue( BlockStateProperties.OPEN, isDoorPowered ),
							3
						),
						true
					);
				}
			}
		}
	}
	
	@Override
	protected boolean isPathfindable( BlockState pState, PathComputationType pPathComputationType ) {
		
		return switch( pPathComputationType ) {
			case LAND, AIR -> pState.getValue( BlockStateProperties.OPEN );
			case WATER -> false;
		};
	}
	
	@Override
	protected void createBlockStateDefinition( StateDefinition.Builder<Block, BlockState> builder ) {
		
		super.createBlockStateDefinition( builder );
		builder.add(
			BlockStateProperties.DOOR_HINGE,
			BlockStateProperties.OPEN,
			BlockStateProperties.POWERED,
			ModBlockStateProperties.OPENED_BY
		);
	}
	
	@Override
	public Component getTitle() {
		
		return OpenedByHelper.OPEN_BY_CONTAINER_TITLE;
	}
	
	@Override
	public ResourceLocation getIconTextures() {
		
		return OpenedByHelper.ICON_TEXTURES;
	}
	
	@Override
	public List<Option> getOptions() {
		
		return OpenedByHelper.buildOptions();
	}
	
	@Override
	public int getStateIndex( BlockState state ) {
		
		return OpenedByHelper.getStateIndex( state );
	}
	
	@Override
	public void setBlockStateValue(
		Level level,
		BlockState state,
		BlockPos pos,
		int stateIndex,
		Player player ) {
		
		OpenedBy[] openedByValues = OpenedBy.values();
		if( stateIndex >= 0 && stateIndex < openedByValues.length ) {
			OpenedBy openedBy = openedByValues[stateIndex];
			
			BlockPos zeroPos = getZeroPos( state, pos );
			Direction facing = state.getValue( BlockStateProperties.HORIZONTAL_FACING );
			runForBlocks(
				level,
				zeroPos,
				facing,
				( x, y, z, blockPos ) -> level.setBlock(
					blockPos,
					level.getBlockState( blockPos ).setValue( ModBlockStateProperties.OPENED_BY, openedBy ),
					3
				),
				true
			);
			
			BlockData neighbor = BigDoorsHelper.getNeighborBlock(
				level,
				zeroPos,
				state,
				getZSize(),
				this::getZeroPos
			);
			if( doubleDoorActive && BigDoorsHelper.isNeighbor( state, neighbor ) ) {
				runForBlocks(
					level,
					neighbor.getZeroPos(),
					facing,
					( x, y, z, blockPos ) -> level.setBlock(
						blockPos,
						level.getBlockState( blockPos ).setValue( ModBlockStateProperties.OPENED_BY, openedBy ),
						3
					),
					true
				);
			}
		}
	}
}
