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
import net.minecraft.world.IWorld;
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
	public PushReaction getPushReaction( @Nonnull BlockState state ) {
		
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
			return withSize( withSize( withSize( getDefaultState( left_sided ).with(
				BlockStateProperties.HORIZONTAL_FACING,
				facing
			), X_SIZE, 0 ), Y_SIZE, 0 ), Z_SIZE, z_index );
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
		
		return calculateForBlocks(
			world,
			pos,
			facing,
			new MultiBlockCalculater<Boolean>() {
				
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
			},
			false
		);
	}
	
	@Override
	public void onBlockPlacedBy(
		@Nonnull World worldIn,
		@Nonnull BlockPos pos,
		@Nonnull BlockState state,
		@Nullable LivingEntity placer,
		@Nonnull ItemStack stack ) {
		
		int z_index = getSize( state, Z_SIZE );
		if( z_index != 0 ) {
			pos = pos.offset( state.get( BlockStateProperties.HORIZONTAL_FACING ).rotateYCCW(), z_index );
		}
		runForBlocks(
			worldIn,
			pos,
			state.get( BlockStateProperties.HORIZONTAL_FACING ),
			( x, y, z, blockPos ) -> worldIn.setBlockState(
				blockPos,
				withSize( withSize( withSize( state, X_SIZE, x ), Y_SIZE, y ), Z_SIZE, z ),
				3
			),
			false
		);
	}
	
	@Override
	public void onBlockHarvested(
		@Nonnull World worldIn,
		@Nonnull BlockPos pos,
		@Nonnull BlockState state,
		@Nonnull PlayerEntity player ) {
		
		runForBlocks(
			worldIn,
			getZeroPos( state, pos ),
			state.get( BlockStateProperties.HORIZONTAL_FACING ),
			( x, y, z, blockPos ) -> {
				BlockState blockState = worldIn.getBlockState( blockPos );
				worldIn.removeBlock( blockPos, true );
				super.onBlockHarvested( worldIn, blockPos, blockState, player );
				if( !worldIn.isRemote && !player.isCreative() ) {
					Block.spawnDrops(
						blockState,
						worldIn,
						blockPos,
						worldIn.getTileEntity( blockPos ),
						player,
						player.getHeldItemMainhand()
					);
				}
			},
			true
		);
		super.onBlockHarvested( worldIn, pos, state, player );
	}
	
	@SuppressWarnings( "deprecation" )
	@Override
	public void onReplaced(
		@Nonnull BlockState state,
		@Nonnull World worldIn,
		@Nonnull BlockPos pos,
		BlockState newState,
		boolean isMoving ) {
		
		if( newState.getBlock() != this ) {
			runForBlocks(
				worldIn,
				getZeroPos( state, pos ),
				state.get( BlockStateProperties.HORIZONTAL_FACING ),
				( x, y, z, blockPos ) -> {
					BlockState blockState = worldIn.getBlockState( blockPos );
					super.onReplaced( blockState, worldIn, blockPos, Blocks.AIR.getDefaultState(), isMoving );
					worldIn.setBlockState( blockPos, Blocks.AIR.getDefaultState(), 3 );
				},
				true
			);
		}
	}
	
	protected BlockPos getZeroPos( BlockState state, BlockPos pos ) {
		
		Direction facing = state.get( BlockStateProperties.HORIZONTAL_FACING );
		pos = pos.offset( facing.getOpposite(), getSize( state, X_SIZE ) );
		pos = pos.down( getSize( state, Y_SIZE ) );
		pos = pos.offset( facing.rotateYCCW(), getSize( state, Z_SIZE ) );
		return pos;
	}
	
	protected boolean checkBlockAtPos( IWorld world, BlockPos pos, boolean checkIsBlockAtPos ) {
		
		return !checkIsBlockAtPos || world.getBlockState( pos ).getBlock() == this;
	}
	
	protected void runForBlocks(
		IWorld world,
		BlockPos pos,
		Direction facing,
		MultiBlockRunner runner,
		boolean checkIsBlockAtPos ) {
		
		boolean[][][] hasStatesAtPos = hasBlockStatesAtPos();
		BlockPos x_pos = pos;
		Direction z_facing = facing.rotateY();
		for( int x = 0; x < getXSize(); x++, x_pos = x_pos.offset( facing ) ) {
			@SuppressWarnings( "SuspiciousNameCombination" )
			BlockPos y_pos = x_pos;
			for( int y = 0; y < getYSize(); y++, y_pos = y_pos.up() ) {
				BlockPos z_pos = y_pos;
				for( int z = 0; z < getZSize(); z++, z_pos = z_pos.offset( z_facing ) ) {
					if( hasStatesAtPos[x][y][z] && checkBlockAtPos( world, z_pos, checkIsBlockAtPos ) ) {
						runner.run( x, y, z, z_pos );
					}
				}
			}
		}
	}
	
	private <T> T calculateForBlocks(
		IWorld world,
		BlockPos pos,
		Direction facing,
		MultiBlockCalculater<T> calculater,
		boolean checkIsBlockAtPos ) {
		
		boolean[][][] hasStatesAtPos = hasBlockStatesAtPos();
		Direction z_facing = facing.rotateY();
		BlockPos x_pos = pos;
		for( int x = 0; x < getXSize(); x++, x_pos = x_pos.offset( facing ) ) {
			@SuppressWarnings( "SuspiciousNameCombination" )
			BlockPos y_pos = x_pos;
			for( int y = 0; y < getYSize(); y++, y_pos = y_pos.up() ) {
				BlockPos z_pos = y_pos;
				for( int z = 0; z < getZSize(); z++, z_pos = z_pos.offset( z_facing ) ) {
					if( hasStatesAtPos[x][y][z] && checkBlockAtPos( world, z_pos, checkIsBlockAtPos ) ) {
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
		
		return calculateForBlocks(
			worldIn,
			zeroPos,
			facing,
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
			},
			true
		);
	}
	
	private int getSize( BlockState state, IntegerProperty property ) {
		
		if( state.has( property ) ) {
			return state.get( property );
		}
		return 0;
	}
	
	private BlockState withSize( BlockState state, IntegerProperty property, int value ) {
		
		if( state.has( property ) ) {
			return state.with( property, value );
		}
		return state;
	}
	
	@Override
	protected void fillStateContainer( StateContainer.Builder<Block, BlockState> builder ) {
		
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
