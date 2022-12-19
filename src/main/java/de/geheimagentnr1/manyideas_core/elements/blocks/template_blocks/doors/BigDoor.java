package de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.doors;

import de.geheimagentnr1.manyideas_core.elements.block_state_properties.ModBlockStateProperties;
import de.geheimagentnr1.manyideas_core.elements.block_state_properties.OpenedBy;
import de.geheimagentnr1.manyideas_core.elements.blocks.BlockRenderTypeInterface;
import de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.multi_block.MultiBlock;
import de.geheimagentnr1.manyideas_core.elements.items.ModItems;
import de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.interfaces.RedstoneKeyable;
import de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.models.Option;
import de.geheimagentnr1.manyideas_core.util.doors.BigDoorsHelper;
import de.geheimagentnr1.manyideas_core.util.doors.BlockData;
import de.geheimagentnr1.manyideas_core.util.doors.DoorsHelper;
import de.geheimagentnr1.manyideas_core.util.doors.OpenedByHelper;
import de.geheimagentnr1.manyideas_core.util.voxel_shapes.VoxelShapeMemory;
import de.geheimagentnr1.manyideas_core.util.voxel_shapes.VoxelShapeVector;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nonnull;
import java.util.List;


@SuppressWarnings( { "unused", "AbstractClassNeverImplemented" } )
public abstract class BigDoor extends MultiBlock implements BlockRenderTypeInterface, RedstoneKeyable {
	
	
	private static final VoxelShapeMemory DOORS_SHAPES = VoxelShapeMemory.createHorizontalVoxelShapes(
		Direction.SOUTH,
		VoxelShapeVector.create( 0, 0, 0, 16, 16, 3 )
	);
	
	private boolean[][][] hasBlockstateAtPos;
	
	private final boolean doubleDoorActive;
	
	protected BigDoor(
		BlockBehaviour.Properties _properties,
		OpenedBy openedBy,
		boolean _doubleDoorActive ) {
		
		super( _properties.noOcclusion().isViewBlocking( ( state, level, pos ) -> false ) );
		registerDefaultState(
			defaultBlockState().setValue( BlockStateProperties.OPEN, false )
				.setValue( BlockStateProperties.POWERED, false )
				.setValue( ModBlockStateProperties.OPENED_BY, openedBy )
		);
		doubleDoorActive = _doubleDoorActive;
	}
	
	@Override
	public RenderType getRenderType() {
		
		return RenderType.cutout();
	}
	
	@SuppressWarnings( "deprecation" )
	@Nonnull
	@Override
	public VoxelShape getShape(
		@Nonnull BlockState state,
		@Nonnull BlockGetter level,
		@Nonnull BlockPos pos,
		@Nonnull CollisionContext context ) {
		
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
	
	@Override
	protected BlockState getDefaultState( boolean left_sided ) {
		
		return defaultBlockState().setValue(
			BlockStateProperties.DOOR_HINGE,
			left_sided ? DoorHingeSide.LEFT : DoorHingeSide.RIGHT
		);
	}
	
	protected BlockPos getZeroPos( BlockData blockData ) {
		
		return getZeroPos( blockData.getState(), blockData.getPos() );
	}
	
	@SuppressWarnings( "deprecation" )
	@Nonnull
	@Override
	public InteractionResult use(
		@Nonnull BlockState state,
		@Nonnull Level level,
		@Nonnull BlockPos pos,
		@Nonnull Player player,
		@Nonnull InteractionHand hand,
		@Nonnull BlockHitResult hitResult ) {
		
		if( player.getItemInHand( hand ).getItem() != ModItems.RESTONE_KEY &&
			OpenedByHelper.canBeOpened( state, true ) ) {
			boolean open = !state.getValue( BlockStateProperties.OPEN );
			Direction facing = state.getValue( BlockStateProperties.HORIZONTAL_FACING );
			BlockPos zeroPos = getZeroPos( state, pos );
			runForBlocks(
				level,
				zeroPos,
				facing,
				( x, y, z, blockPos ) -> level.setBlock(
					blockPos,
					level.getBlockState( blockPos ).setValue( BlockStateProperties.OPEN, open ),
					3
				),
				true
			);
			DoorsHelper.playDoorSound( level, pos, material, player, open );
			if( doubleDoorActive ) {
				BlockData neighbor = BigDoorsHelper.getNeighborBlock(
					level,
					zeroPos,
					state,
					getZSize(),
					this::getZeroPos
				);
				if( BigDoorsHelper.isNeighbor( state, neighbor ) ) {
					runForBlocks(
						level,
						neighbor.getZeroPos(),
						facing,
						( x, y, z, blockPos ) -> level.setBlock(
							blockPos,
							level.getBlockState( blockPos ).setValue( BlockStateProperties.OPEN, open ),
							3
						),
						true
					);
				}
			}
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.PASS;
	}
	
	@SuppressWarnings( "deprecation" )
	@Override
	public void neighborChanged(
		@Nonnull BlockState state,
		@Nonnull Level level,
		@Nonnull BlockPos pos,
		@Nonnull Block block,
		@Nonnull BlockPos fromPos,
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
					DoorsHelper.playDoorSound( level, pos, material, null, isDoorPowered );
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
	
	@SuppressWarnings( "deprecation" )
	@Deprecated
	@Override
	public boolean isPathfindable(
		@Nonnull BlockState state,
		@Nonnull BlockGetter level,
		@Nonnull BlockPos pos,
		@Nonnull PathComputationType type ) {
		
		return switch( type ) {
			case LAND, AIR -> state.getShape( level, pos ).isEmpty();
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
