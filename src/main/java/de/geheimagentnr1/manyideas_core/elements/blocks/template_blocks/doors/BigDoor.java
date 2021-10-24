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
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoorHingeSide;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

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
		Properties properties,
		String registry_name,
		OpenedBy openedBy,
		boolean _doubleDoorActive ) {
		
		super(
			properties.noOcclusion()
				.isViewBlocking( ( p_test_1_, p_test_2_, p_test_3_ ) -> false ),
			registry_name
		);
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
		@Nonnull IBlockReader worldIn,
		@Nonnull BlockPos pos,
		@Nonnull ISelectionContext context ) {
		
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
	public ActionResultType use(
		@Nonnull BlockState state,
		@Nonnull World worldIn,
		@Nonnull BlockPos pos,
		@Nonnull PlayerEntity player,
		@Nonnull Hand handIn,
		@Nonnull BlockRayTraceResult hit ) {
		
		if( player.getItemInHand( handIn ).getItem() != ModItems.RESTONE_KEY &&
			OpenedByHelper.canBeOpened( state, true ) ) {
			boolean open = !state.getValue( BlockStateProperties.OPEN );
			Direction facing = state.getValue( BlockStateProperties.HORIZONTAL_FACING );
			BlockPos zeroPos = getZeroPos( state, pos );
			runForBlocks(
				worldIn,
				zeroPos,
				facing,
				( x, y, z, blockPos ) -> worldIn.setBlock(
					blockPos,
					worldIn.getBlockState( blockPos ).setValue( BlockStateProperties.OPEN, open ),
					3
				),
				true
			);
			DoorsHelper.playDoorSound( worldIn, pos, material, player, open );
			if( doubleDoorActive ) {
				BlockData neighbor = BigDoorsHelper.getNeighborBlock(
					worldIn,
					zeroPos,
					state,
					getZSize(),
					this::getZeroPos
				);
				if( BigDoorsHelper.isNeighbor( state, neighbor ) ) {
					runForBlocks(
						worldIn,
						neighbor.getZeroPos(),
						facing,
						( x, y, z, blockPos ) -> worldIn.setBlock(
							blockPos,
							worldIn.getBlockState( blockPos ).setValue( BlockStateProperties.OPEN, open ),
							3
						),
						true
					);
				}
			}
			return ActionResultType.SUCCESS;
		}
		return ActionResultType.PASS;
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
		
		if( blockIn != this && OpenedByHelper.canBeOpened( state, false ) ) {
			BlockPos zeroPos = getZeroPos( state, pos );
			Direction facing = state.getValue( BlockStateProperties.HORIZONTAL_FACING );
			BlockData neighbor = BigDoorsHelper.getNeighborBlock(
				worldIn,
				zeroPos,
				state,
				getZSize(),
				this::getZeroPos
			);
			boolean isNeighbor = BigDoorsHelper.isNeighbor( state, neighbor );
			boolean isDoorPowered = isPowered( worldIn, zeroPos, facing ) ||
				isNeighbor && isPowered( worldIn, neighbor.getZeroPos(), facing );
			
			if( isDoorPowered != state.getValue( BlockStateProperties.POWERED ) ) {
				if( state.getValue( BlockStateProperties.OPEN ) != isDoorPowered ) {
					DoorsHelper.playDoorSound( worldIn, pos, material, null, isDoorPowered );
				}
				runForBlocks(
					worldIn,
					zeroPos,
					facing,
					( x, y, z, blockPos ) -> worldIn.setBlock(
						blockPos,
						worldIn.getBlockState( blockPos )
							.setValue( BlockStateProperties.POWERED, isDoorPowered )
							.setValue( BlockStateProperties.OPEN, isDoorPowered ),
						3
					),
					true
				);
				if( doubleDoorActive && isNeighbor ) {
					runForBlocks(
						worldIn,
						neighbor.getZeroPos(),
						facing,
						( x, y, z, blockPos ) -> worldIn.setBlock(
							blockPos,
							worldIn.getBlockState( blockPos )
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
	protected void createBlockStateDefinition( StateContainer.Builder<Block, BlockState> builder ) {
		
		super.createBlockStateDefinition( builder );
		builder.add(
			BlockStateProperties.DOOR_HINGE,
			BlockStateProperties.OPEN,
			BlockStateProperties.POWERED,
			ModBlockStateProperties.OPENED_BY
		);
	}
	
	@Override
	public ITextComponent getTitle() {
		
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
		World world, BlockState state, BlockPos pos, int stateIndex,
		PlayerEntity player ) {
		
		OpenedBy[] openedByValues = OpenedBy.values();
		if( stateIndex >= 0 && stateIndex < openedByValues.length ) {
			OpenedBy openedBy = openedByValues[stateIndex];
			
			BlockPos zeroPos = getZeroPos( state, pos );
			Direction facing = state.getValue( BlockStateProperties.HORIZONTAL_FACING );
			runForBlocks(
				world,
				zeroPos,
				facing,
				( x, y, z, blockPos ) -> world.setBlock(
					blockPos,
					world.getBlockState( blockPos ).setValue( ModBlockStateProperties.OPENED_BY, openedBy ),
					3
				),
				true
			);
			
			BlockData neighbor = BigDoorsHelper.getNeighborBlock(
				world,
				zeroPos,
				state,
				getZSize(),
				this::getZeroPos
			);
			if( doubleDoorActive && BigDoorsHelper.isNeighbor( state, neighbor ) ) {
				runForBlocks(
					world,
					neighbor.getZeroPos(),
					facing,
					( x, y, z, blockPos ) -> world.setBlock(
						blockPos,
						world.getBlockState( blockPos ).setValue( ModBlockStateProperties.OPENED_BY, openedBy ),
						3
					),
					true
				);
			}
		}
	}
}
