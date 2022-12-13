package de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.multi_block;

import de.geheimagentnr1.manyideas_core.elements.blocks.BlockItemInterface;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.PushReaction;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;


@SuppressWarnings( { "AbstractClassExtendsConcreteClass", "AbstractClassNeverImplemented", "unused", "WeakerAccess" } )
public abstract class MultiBlock extends Block implements BlockItemInterface {
	
	
	protected IntegerProperty X_SIZE;
	
	protected IntegerProperty Y_SIZE;
	
	protected IntegerProperty Z_SIZE;
	
	protected MultiBlock( BlockBehaviour.Properties _properties ) {
		
		super( _properties );
	}
	
	@SuppressWarnings( "deprecation" )
	@Nonnull
	@Override
	public PushReaction getPistonPushReaction( @Nonnull BlockState state ) {
		
		return PushReaction.BLOCK;
	}
	
	protected abstract int getXSize();
	
	protected abstract int getYSize();
	
	protected abstract int getZSize();
	
	protected abstract boolean[][][] hasBlockStatesAtPos();
	
	protected BlockState getDefaultState( boolean left_sided ) {
		
		return defaultBlockState();
	}
	
	@Nullable
	@Override
	public BlockState getStateForPlacement( BlockPlaceContext context ) {
		
		BlockPos pos = context.getClickedPos();
		Direction facing = context.getHorizontalDirection();
		Player player = context.getPlayer();
		int z_index = 0;
		boolean left_sided = false;
		if( player != null ) {
			left_sided = shouldSideBeLeft( facing, context.getPlayer() );
			if( left_sided ) {
				z_index = getZSize() - 1;
				pos = pos.relative( facing.getCounterClockWise(), z_index );
			}
		}
		if( isPlaceFree( context.getLevel(), pos, facing, context ) ) {
			return withSize( withSize( withSize( getDefaultState( left_sided ).setValue(
				BlockStateProperties.HORIZONTAL_FACING,
				facing
			), X_SIZE, 0 ), Y_SIZE, 0 ), Z_SIZE, z_index );
		} else {
			return null;
		}
	}
	
	private static boolean shouldSideBeLeft( Direction direction, Player player ) {
		
		float player_yaw_degree = player.getViewYRot( 1.0F ) * (float)( Math.PI / 180.0 );
		
		return switch( direction ) {
			case NORTH -> Mth.sin( player_yaw_degree ) > 0.0F;
			case SOUTH -> Mth.sin( player_yaw_degree ) < 0.0F;
			case EAST -> Mth.cos( player_yaw_degree ) < 0.0F;
			case WEST -> Mth.cos( player_yaw_degree ) > 0.0F;
			default -> true;
		};
	}
	
	private boolean isPlaceFree( Level level, BlockPos pos, Direction facing, BlockPlaceContext context ) {
		
		return calculateForBlocks(
			level,
			pos,
			facing,
			new MultiBlockCalculater<>() {
				
				@Override
				public Optional<Boolean> calculate( int x, int y, int z, BlockPos blockPos ) {
					
					if( !level.getBlockState( blockPos ).canBeReplaced( context ) ) {
						return Optional.of( false );
					}
					return Optional.empty();
				}
				
				@Override
				public Boolean getAlternative() {
					
					return true;
				}
			},
			false
		);
	}
	
	@Override
	public void setPlacedBy(
		@Nonnull Level level,
		@Nonnull BlockPos pos,
		@Nonnull BlockState state,
		@Nullable LivingEntity placer,
		@Nonnull ItemStack stack ) {
		
		int z_index = getSize( state, Z_SIZE );
		if( z_index != 0 ) {
			pos = pos.relative(
				state.getValue( BlockStateProperties.HORIZONTAL_FACING ).getCounterClockWise(),
				z_index
			);
		}
		runForBlocks(
			level,
			pos,
			state.getValue( BlockStateProperties.HORIZONTAL_FACING ),
			( x, y, z, blockPos ) -> level.setBlock(
				blockPos,
				withSize( withSize( withSize( state, X_SIZE, x ), Y_SIZE, y ), Z_SIZE, z ),
				3
			),
			false
		);
	}
	
	@Override
	public void playerWillDestroy(
		@Nonnull Level level,
		@Nonnull BlockPos pos,
		@Nonnull BlockState state,
		@Nonnull Player player ) {
		
		runForBlocks(
			level,
			getZeroPos( state, pos ),
			state.getValue( BlockStateProperties.HORIZONTAL_FACING ),
			( x, y, z, blockPos ) -> {
				BlockState blockState = level.getBlockState( blockPos );
				if( !level.isClientSide && !player.isCreative() && player.hasCorrectToolForDrops( blockState ) ) {
					Block.dropResources(
						blockState,
						level,
						blockPos,
						level.getBlockEntity( blockPos ),
						player,
						player.getMainHandItem()
					);
				}
			},
			true
		);
		runForBlocks(
			level,
			getZeroPos( state, pos ),
			state.getValue( BlockStateProperties.HORIZONTAL_FACING ),
			( x, y, z, blockPos ) -> {
				BlockState blockState = level.getBlockState( blockPos );
				level.removeBlock( blockPos, true );
				super.playerWillDestroy( level, blockPos, blockState, player );
			},
			true
		);
		super.playerWillDestroy( level, pos, state, player );
	}
	
	@Override
	public void onBlockExploded( BlockState state, Level level, BlockPos pos, Explosion explosion ) {
		
		runForBlocks(
			level,
			getZeroPos( state, pos ),
			state.getValue( BlockStateProperties.HORIZONTAL_FACING ),
			( x, y, z, blockPos ) -> {
				if( !blockPos.equals( pos ) && !level.isClientSide ) {
					BlockState blockState = level.getBlockState( blockPos );
					Block.dropResources(
						blockState,
						level,
						blockPos,
						level.getBlockEntity( blockPos )
					);
				}
			},
			true
		);
		super.onBlockExploded( state, level, pos, explosion );
	}
	
	@SuppressWarnings( "deprecation" )
	@Override
	public void onRemove(
		@Nonnull BlockState state,
		@Nonnull Level level,
		@Nonnull BlockPos pos,
		@Nonnull BlockState newState,
		boolean isMoving ) {
		
		if( newState.getBlock() != this ) {
			runForBlocks(
				level,
				getZeroPos( state, pos ),
				state.getValue( BlockStateProperties.HORIZONTAL_FACING ),
				( x, y, z, blockPos ) -> {
					BlockState blockState = level.getBlockState( blockPos );
					super.onRemove( blockState, level, blockPos, Blocks.AIR.defaultBlockState(), isMoving );
					level.setBlock( blockPos, Blocks.AIR.defaultBlockState(), 3 );
				},
				true
			);
		}
	}
	
	protected BlockPos getZeroPos( BlockState state, BlockPos pos ) {
		
		Direction facing = state.getValue( BlockStateProperties.HORIZONTAL_FACING );
		pos = pos.relative( facing.getOpposite(), getSize( state, X_SIZE ) );
		pos = pos.below( getSize( state, Y_SIZE ) );
		pos = pos.relative( facing.getCounterClockWise(), getSize( state, Z_SIZE ) );
		return pos;
	}
	
	protected boolean checkBlockAtPos( Level level, BlockPos pos, boolean checkIsBlockAtPos ) {
		
		return !checkIsBlockAtPos || level.getBlockState( pos ).getBlock() == this;
	}
	
	protected void runForBlocks(
		Level level,
		BlockPos pos,
		Direction facing,
		MultiBlockRunner runner,
		boolean checkIsBlockAtPos ) {
		
		boolean[][][] hasStatesAtPos = hasBlockStatesAtPos();
		BlockPos x_pos = pos;
		Direction z_facing = facing.getClockWise();
		for( int x = 0; x < getXSize(); x++, x_pos = x_pos.relative( facing ) ) {
			@SuppressWarnings( "SuspiciousNameCombination" )
			BlockPos y_pos = x_pos;
			for( int y = 0; y < getYSize(); y++, y_pos = y_pos.above() ) {
				BlockPos z_pos = y_pos;
				for( int z = 0; z < getZSize(); z++, z_pos = z_pos.relative( z_facing ) ) {
					if( hasStatesAtPos[x][y][z] && checkBlockAtPos( level, z_pos, checkIsBlockAtPos ) ) {
						runner.run( x, y, z, z_pos );
					}
				}
			}
		}
	}
	
	private <T> T calculateForBlocks(
		Level level,
		BlockPos pos,
		Direction facing,
		MultiBlockCalculater<T> calculater,
		boolean checkIsBlockAtPos ) {
		
		boolean[][][] hasStatesAtPos = hasBlockStatesAtPos();
		Direction z_facing = facing.getClockWise();
		BlockPos x_pos = pos;
		for( int x = 0; x < getXSize(); x++, x_pos = x_pos.relative( facing ) ) {
			@SuppressWarnings( "SuspiciousNameCombination" )
			BlockPos y_pos = x_pos;
			for( int y = 0; y < getYSize(); y++, y_pos = y_pos.above() ) {
				BlockPos z_pos = y_pos;
				for( int z = 0; z < getZSize(); z++, z_pos = z_pos.relative( z_facing ) ) {
					if( hasStatesAtPos[x][y][z] && checkBlockAtPos( level, z_pos, checkIsBlockAtPos ) ) {
						Optional<T> result = calculater.calculate( x, y, z, z_pos );
						if( result.isPresent() ) {
							return result.get();
						}
					}
				}
			}
		}
		return calculater.getAlternative();
	}
	
	protected boolean isPowered( Level level, BlockPos zeroPos, Direction facing ) {
		
		return calculateForBlocks(
			level,
			zeroPos,
			facing,
			new MultiBlockCalculater<>() {
				
				@Override
				public Optional<Boolean> calculate( int x, int y, int z, BlockPos blockPos ) {
					
					if( level.hasNeighborSignal( blockPos ) ) {
						return Optional.of( true );
					} else {
						return Optional.empty();
					}
				}
				
				@Override
				public Boolean getAlternative() {
					
					return false;
				}
			},
			true
		);
	}
	
	private int getSize( BlockState state, IntegerProperty property ) {
		
		if( state.hasProperty( property ) ) {
			return state.getValue( property );
		}
		return 0;
	}
	
	private BlockState withSize( BlockState state, IntegerProperty property, int value ) {
		
		if( state.hasProperty( property ) ) {
			return state.setValue( property, value );
		}
		return state;
	}
	
	@Override
	protected void createBlockStateDefinition( StateDefinition.Builder<Block, BlockState> builder ) {
		
		builder.add( BlockStateProperties.HORIZONTAL_FACING );
		if( getXSize() > 1 ) {
			X_SIZE = IntegerProperty.create( "x", 0, getXSize() - 1 );
			builder.add( X_SIZE );
		}
		if( getYSize() > 1 ) {
			Y_SIZE = IntegerProperty.create( "y", 0, getYSize() - 1 );
			builder.add( Y_SIZE );
		}
		if( getZSize() > 1 ) {
			Z_SIZE = IntegerProperty.create( "z", 0, getZSize() - 1 );
			builder.add( Z_SIZE );
		}
	}
}
