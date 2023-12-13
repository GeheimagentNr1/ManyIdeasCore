package de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.multi_block;

import de.geheimagentnr1.minecraft_forge_api.elements.blocks.BlockItemInterface;
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
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Optional;


@SuppressWarnings( { "AbstractClassExtendsConcreteClass", "AbstractClassNeverImplemented", "unused", "WeakerAccess" } )
public abstract class MultiBlock extends Block implements BlockItemInterface {
	
	
	protected IntegerProperty X_SIZE;
	
	protected IntegerProperty Y_SIZE;
	
	protected IntegerProperty Z_SIZE;
	
	protected MultiBlock( @NotNull BlockBehaviour.Properties _properties ) {
		
		super( _properties.pushReaction( PushReaction.BLOCK ) );
	}
	
	protected abstract int getXSize();
	
	protected abstract int getYSize();
	
	protected abstract int getZSize();
	
	protected abstract boolean[][][] hasBlockStatesAtPos();
	
	@NotNull
	protected BlockState getDefaultState( boolean left_sided ) {
		
		return defaultBlockState();
	}
	
	@Nullable
	@Override
	public BlockState getStateForPlacement( @NotNull BlockPlaceContext context ) {
		
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
	
	private static boolean shouldSideBeLeft( @NotNull Direction direction, @NotNull Player player ) {
		
		float player_yaw_degree = player.getViewYRot( 1.0F ) * (float)( Math.PI / 180.0 );
		
		return switch( direction ) {
			case NORTH -> Mth.sin( player_yaw_degree ) > 0.0F;
			case SOUTH -> Mth.sin( player_yaw_degree ) < 0.0F;
			case EAST -> Mth.cos( player_yaw_degree ) < 0.0F;
			case WEST -> Mth.cos( player_yaw_degree ) > 0.0F;
			default -> true;
		};
	}
	
	private boolean isPlaceFree(
		@NotNull Level level,
		@NotNull BlockPos pos,
		@NotNull Direction facing,
		@NotNull BlockPlaceContext context ) {
		
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
		@NotNull Level level,
		@NotNull BlockPos pos,
		@NotNull BlockState state,
		@Nullable LivingEntity placer,
		@NotNull ItemStack stack ) {
		
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
	
	@NotNull
	@Override
	public BlockState playerWillDestroy(
		@NotNull Level level,
		@NotNull BlockPos pos,
		@NotNull BlockState state,
		@NotNull Player player ) {
		
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
		return super.playerWillDestroy( level, pos, state, player );
	}
	
	@Override
	public void onBlockExploded(
		@NotNull BlockState state,
		@NotNull Level level,
		@NotNull BlockPos pos,
		@NotNull Explosion explosion ) {
		
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
		@NotNull BlockState state,
		@NotNull Level level,
		@NotNull BlockPos pos,
		@NotNull BlockState newState,
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
	
	@NotNull
	protected BlockPos getZeroPos( @NotNull BlockState state, @NotNull BlockPos pos ) {
		
		Direction facing = state.getValue( BlockStateProperties.HORIZONTAL_FACING );
		pos = pos.relative( facing.getOpposite(), getSize( state, X_SIZE ) );
		pos = pos.below( getSize( state, Y_SIZE ) );
		pos = pos.relative( facing.getCounterClockWise(), getSize( state, Z_SIZE ) );
		return pos;
	}
	
	protected boolean checkBlockAtPos( @NotNull Level level, @NotNull BlockPos pos, boolean checkIsBlockAtPos ) {
		
		return !checkIsBlockAtPos || level.getBlockState( pos ).getBlock() == this;
	}
	
	protected void runForBlocks(
		@NotNull Level level,
		@NotNull BlockPos pos,
		@NotNull Direction facing,
		@NotNull MultiBlockRunner runner,
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
		@NotNull Level level,
		@NotNull BlockPos pos,
		@NotNull Direction facing,
		@NotNull MultiBlockCalculater<T> calculater,
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
	
	protected boolean isPowered( @NotNull Level level, @NotNull BlockPos zeroPos, @NotNull Direction facing ) {
		
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
	
	private int getSize( @NotNull BlockState state, @NotNull IntegerProperty property ) {
		
		if( state.hasProperty( property ) ) {
			return state.getValue( property );
		}
		return 0;
	}
	
	@NotNull
	private BlockState withSize( @NotNull BlockState state, @NotNull IntegerProperty property, int value ) {
		
		if( state.hasProperty( property ) ) {
			return state.setValue( property, value );
		}
		return state;
	}
	
	@Override
	protected void createBlockStateDefinition( @NotNull StateDefinition.Builder<Block, BlockState> builder ) {
		
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
