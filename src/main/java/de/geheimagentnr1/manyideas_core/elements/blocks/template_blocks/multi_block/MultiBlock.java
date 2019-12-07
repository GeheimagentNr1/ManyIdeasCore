package de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.multi_block;

import de.geheimagentnr1.manyideas_core.elements.blocks.BlockItemInterface;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;


@SuppressWarnings( { "AbstractClassExtendsConcreteClass", "AbstractClassNeverImplemented", "unused", "WeakerAccess" } )
public abstract class MultiBlock extends Block implements BlockItemInterface {
	
	
	protected IntegerProperty X_SIZE;
	
	protected IntegerProperty Y_SIZE;
	
	protected IntegerProperty Z_SIZE;
	
	protected MultiBlock( Block.Properties properties, String registry_name ) {
		
		super( properties );
		setRegistryName( registry_name );
	}
	
	@SuppressWarnings( "deprecation" )
	@Nonnull
	@Override
	public PushReaction getPushReaction( BlockState state ) {
		
		return PushReaction.BLOCK;
	}
	
	protected abstract int getXSize();
	
	protected abstract int getYSize();
	
	protected abstract int getZSize();
	
	protected abstract boolean[][][] hasBlockStatesAtPos();
	
	protected BlockState getDefaultState( boolean left_sided ) {
		
		return getDefaultState();
	}
	
	@Nullable
	@Override
	public BlockState getStateForPlacement( BlockItemUseContext context ) {
		
		BlockPos pos = context.getPos();
		Direction facing = context.getPlacementHorizontalFacing();
		PlayerEntity player = context.getPlayer();
		int z_index = 0;
		boolean left_sided = false;
		if( player != null ) {
			left_sided = shouldSideBeLeft( facing, context.getPlayer() );
			if( left_sided ) {
				z_index = getZSize() - 1;
				pos = pos.offset( facing.rotateYCCW(), z_index );
			}
		}
		if( isPlaceFree( context.getWorld(), pos, facing, context ) ) {
			return getDefaultState( left_sided ).with( BlockStateProperties.HORIZONTAL_FACING, facing )
				.with( X_SIZE, 0 ).with( Y_SIZE, 0 ).with( Z_SIZE, z_index );
		} else {
			return null;
		}
	}
	
	private static boolean shouldSideBeLeft( Direction direction, PlayerEntity player ) {
		
		float player_yaw_degree = player.getYaw( 1.0F ) * (float)( Math.PI / 180.0 );
		boolean shouldTurnedLeft = true;
		
		switch( direction ) {
			case NORTH:
				shouldTurnedLeft = MathHelper.sin( player_yaw_degree ) > 0.0F;
				break;
			case SOUTH:
				shouldTurnedLeft = MathHelper.sin( player_yaw_degree ) < 0.0F;
				break;
			case EAST:
				shouldTurnedLeft = MathHelper.cos( player_yaw_degree ) < 0.0F;
				break;
			case WEST:
				shouldTurnedLeft = MathHelper.cos( player_yaw_degree ) > 0.0F;
				break;
		}
		return shouldTurnedLeft;
	}
	
	private boolean isPlaceFree( World world, BlockPos pos, Direction facing, BlockItemUseContext context ) {
		
		return calculateForBlocks( pos, facing, new MultiBlockCalculater<Boolean>() {
			
			@Override
			public Optional<Boolean> calculate( int x, int y, int z, BlockPos blockPos ) {
				
				if( !world.getBlockState( blockPos ).isReplaceable( context ) ) {
					return Optional.of( false );
				}
				return Optional.empty();
			}
			
			@Override
			public Boolean getAlternative() {
				
				return true;
			}
		} );
	}
	
	@Override
	public void onBlockPlacedBy( World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer,
		ItemStack stack ) {
		
		int z_index = state.get( Z_SIZE );
		if( z_index != 0 ) {
			pos = pos.offset( state.get( BlockStateProperties.HORIZONTAL_FACING ).rotateYCCW(), z_index );
		}
		runForBlocks( pos, state.get( BlockStateProperties.HORIZONTAL_FACING ),
			( x, y, z, blockPos ) -> worldIn.setBlockState( blockPos,
				state.with( X_SIZE, x ).with( Y_SIZE, y ).with( Z_SIZE, z ), 3 ) );
	}
	
	@Override
	public void onBlockHarvested( World worldIn, @Nonnull BlockPos pos, BlockState state,
		@Nonnull PlayerEntity player ) {
		
		runForBlocks( getZeroPos( state, pos ), state.get( BlockStateProperties.HORIZONTAL_FACING ),
			( x, y, z, blockPos ) -> {
				BlockState blockState = worldIn.getBlockState( blockPos );
				if( blockState.getBlock() == this ) {
					worldIn.removeBlock( blockPos, true );
					super.onBlockHarvested( worldIn, blockPos, blockState, player );
				}
			} );
		super.onBlockHarvested( worldIn, pos, state, player );
	}
	
	@SuppressWarnings( "deprecation" )
	@Override
	public void onReplaced( BlockState state, @Nonnull World worldIn, @Nonnull BlockPos pos, BlockState newState,
		boolean isMoving ) {
		
		if( newState.getBlock() != this ) {
			runForBlocks( getZeroPos( state, pos ), state.get( BlockStateProperties.HORIZONTAL_FACING ),
				( x, y, z, blockPos ) -> {
					BlockState blockState = worldIn.getBlockState( blockPos );
					if( blockState.getBlock() == this ) {
						super.onReplaced( blockState, worldIn, blockPos, Blocks.AIR.getDefaultState(), isMoving );
						worldIn.setBlockState( blockPos, Blocks.AIR.getDefaultState(), 3 );
					}
				} );
		}
	}
	
	protected BlockPos getZeroPos( BlockState state, BlockPos pos ) {
		
		Direction facing = state.get( BlockStateProperties.HORIZONTAL_FACING );
		for( int x = 0; x < state.get( X_SIZE ); x++ ) {
			pos = pos.offset( facing.getOpposite() );
		}
		for( int y = 0; y < state.get( Y_SIZE ); y++ ) {
			pos = pos.down();
		}
		for( int z = 0; z < state.get( Z_SIZE ); z++ ) {
			pos = pos.offset( facing.rotateYCCW() );
		}
		return pos;
	}
	
	protected void runForBlocks( BlockPos pos, Direction facing, MultiBlockRunner runner ) {
		
		boolean[][][] hasStatesAtPos = hasBlockStatesAtPos();
		BlockPos x_pos = pos;
		Direction z_facing = facing.rotateY();
		for( int x = 0; x < getXSize(); x++, x_pos = x_pos.offset( facing ) ) {
			@SuppressWarnings( "SuspiciousNameCombination" )
			BlockPos y_pos = x_pos;
			for( int y = 0; y < getYSize(); y++, y_pos = y_pos.up() ) {
				BlockPos z_pos = y_pos;
				for( int z = 0; z < getZSize(); z++, z_pos = z_pos.offset( z_facing ) ) {
					if( hasStatesAtPos[x][y][z] ) {
						runner.run( x, y, z, z_pos );
					}
				}
			}
		}
	}
	
	private <T> T calculateForBlocks( BlockPos pos, Direction facing, MultiBlockCalculater<T>
		calculater ) {
		
		boolean[][][] hasStatesAtPos = hasBlockStatesAtPos();
		Direction z_facing = facing.rotateY();
		BlockPos x_pos = pos;
		for( int x = 0; x < getXSize(); x++, x_pos = x_pos.offset( facing ) ) {
			@SuppressWarnings( "SuspiciousNameCombination" )
			BlockPos y_pos = x_pos;
			for( int y = 0; y < getYSize(); y++, y_pos = y_pos.up() ) {
				BlockPos z_pos = y_pos;
				for( int z = 0; z < getZSize(); z++, z_pos = z_pos.offset( z_facing ) ) {
					if( hasStatesAtPos[x][y][z] ) {
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
	
	protected boolean isPowered( World worldIn, BlockPos zeroPos, Direction facing ) {
		
		return calculateForBlocks( zeroPos, facing,
			new MultiBlockCalculater<Boolean>() {
				
				@Override
				public Optional<Boolean> calculate( int x, int y, int z, BlockPos blockPos ) {
					
					if( worldIn.isBlockPowered( blockPos ) ) {
						return Optional.of( true );
					} else {
						return Optional.empty();
					}
				}
				
				@Override
				public Boolean getAlternative() {
					
					return false;
				}
			} );
	}
	
	@Override
	protected void fillStateContainer( StateContainer.Builder<Block, BlockState> builder ) {
		
		X_SIZE = IntegerProperty.create( "x", 0, getXSize() - 1 );
		Y_SIZE = IntegerProperty.create( "y", 0, getYSize() - 1 );
		Z_SIZE = IntegerProperty.create( "z", 0, getZSize() - 1 );
		builder.add( BlockStateProperties.HORIZONTAL_FACING, X_SIZE, Y_SIZE, Z_SIZE );
	}
}
