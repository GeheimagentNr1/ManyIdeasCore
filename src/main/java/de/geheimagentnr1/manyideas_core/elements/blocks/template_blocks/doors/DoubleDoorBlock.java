package de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.doors;

import de.geheimagentnr1.manyideas_core.elements.block_state_properties.CanBeOpenedByBlockState;
import de.geheimagentnr1.manyideas_core.elements.block_state_properties.ModBlockStateProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.DoorHingeSide;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;


@SuppressWarnings( { "AbstractClassExtendsConcreteClass", "unused", "AbstractClassNeverImplemented" } )
public abstract class DoubleDoorBlock extends DoorBlock {
	
	
	protected DoubleDoorBlock( Block.Properties properties, String registry_name ) {
		
		super( properties );
		setRegistryName( registry_name );
		initDoubleDoorBlock( material != Material.IRON );
	}
	
	protected DoubleDoorBlock( Block.Properties properties, String registry_name,
		boolean canBeOpenedOnBlockActivated ) {
		
		super( properties );
		setRegistryName( registry_name );
		initDoubleDoorBlock( canBeOpenedOnBlockActivated );
	}
	
	private void initDoubleDoorBlock( boolean canBeOpenedOnBlockActivated ) {
		
		setDefaultState( getDefaultState().with( ModBlockStateProperties.OPENED_BY, material != Material.IRON ||
			canBeOpenedOnBlockActivated ? CanBeOpenedByBlockState.BOTH : CanBeOpenedByBlockState.REDSTONE ) );
	}
	
	@Override
	public boolean onBlockActivated( @Nonnull BlockState state, @Nonnull World worldIn, @Nonnull BlockPos pos,
		PlayerEntity player, @Nonnull Hand handIn, @Nonnull BlockRayTraceResult hit ) {
		
		if( player.getHeldItem( handIn ).getItem() == Items.REDSTONE_TORCH ) {
			state = state.cycle( ModBlockStateProperties.OPENED_BY );
			worldIn.setBlockState( pos, state, 3 );
			BlockPos other_pos = state.get( HALF ) == DoubleBlockHalf.LOWER ? pos.up() : pos.down();
			BlockState other_state = worldIn.getBlockState( other_pos ).cycle( ModBlockStateProperties.OPENED_BY );
			worldIn.setBlockState( other_pos, other_state, 3 );
			if( !worldIn.isRemote ) {
				player.sendMessage( new StringTextComponent( "Door reacts on: " +
					state.get( ModBlockStateProperties.OPENED_BY ).getName() ) );
			}
			return true;
		}
		if( canBeOpened( state, true ) ) {
			state = state.cycle( OPEN );
			worldIn.setBlockState( pos, state, 10 );
			playDoorSound( player, worldIn, pos, state.get( OPEN ) );
			BlockPos neighborPos = pos.offset( getDirectionToOtherDoor( state ) );
			BlockState neighborState = worldIn.getBlockState( neighborPos );
			if( neighborState.getBlock() instanceof DoubleDoorBlock &&
				state.get( FACING ) == neighborState.get( FACING ) &&
				state.get( HINGE ) != neighborState.get( HINGE ) ) {
				worldIn.setBlockState( neighborPos, neighborState.with( OPEN, state.get( OPEN ) ), 2 );
			}
			return true;
		}
		return false;
	}
	
	@Override
	public void neighborChanged( @Nonnull BlockState state, @Nonnull World worldIn, @Nonnull BlockPos pos,
		@Nonnull Block blockIn, @Nonnull BlockPos fromPos, boolean isMoving ) {
		
		boolean isDoorPowered = isDoorBlockPowerd( pos, worldIn, state );
		BlockPos neighborPos = pos.offset( getDirectionToOtherDoor( state ) );
		BlockState neighborState = worldIn.getBlockState( neighborPos );
		boolean isNeighborDoubleDoor = neighborState.getBlock() instanceof DoubleDoorBlock &&
			state.get( FACING ) == neighborState.get( FACING ) && state.get( HINGE ) != neighborState.get( HINGE );
		
		if( isNeighborDoubleDoor ) {
			isDoorPowered |= isDoorBlockPowerd( neighborPos, worldIn, worldIn.getBlockState( neighborPos ) );
		}
		if( canBeOpened( state, false ) && blockIn != this && isDoorPowered != state.get( POWERED ) ) {
			playDoorSound( null, worldIn, pos, isDoorPowered );
			worldIn.setBlockState( pos, state.with( POWERED, isDoorPowered ).with( OPEN, isDoorPowered ), 2 );
			worldIn.setBlockState( neighborPos, neighborState.with( POWERED, isDoorPowered )
				.with( OPEN, isDoorPowered ), 2 );
		}
	}
	
	@Override
	protected void fillStateContainer( @Nonnull StateContainer.Builder<Block, BlockState> builder ) {
		
		super.fillStateContainer( builder );
		builder.add( ModBlockStateProperties.OPENED_BY );
	}
	
	private boolean canBeOpened( BlockState state, boolean onActivated ) {
		
		switch( state.get( ModBlockStateProperties.OPENED_BY ) ) {
			case NOTHING:
				return false;
			case HAND:
				return onActivated;
			case REDSTONE:
				return !onActivated;
			case BOTH:
				return true;
			default:
				throw new IllegalStateException( "Illegal \"can_be_opened_by\" State" );
		}
	}
	
	private boolean isDoorBlockPowerd( BlockPos pos, World world, BlockState state ) {
		
		return world.isBlockPowered( pos ) || world.isBlockPowered( pos.offset( state.get( HALF ) ==
				DoubleBlockHalf.LOWER ? Direction.UP : Direction.DOWN ) );
	}
	
	private Direction getDirectionToOtherDoor( BlockState state ) {
		
		Direction direction = null;
		
		switch( state.get( FACING ) ) {
			case EAST:
				if( state.get( HINGE ) == DoorHingeSide.LEFT ) {
					direction = Direction.SOUTH;
				} else {
					direction = Direction.NORTH;
				}
				break;
			case WEST:
				if( state.get( HINGE ) == DoorHingeSide.LEFT ) {
					direction = Direction.NORTH;
				} else {
					direction = Direction.SOUTH;
				}
				break;
			case SOUTH:
				if( state.get( HINGE ) == DoorHingeSide.LEFT ) {
					direction = Direction.WEST;
				} else {
					direction = Direction.EAST;
				}
				break;
			case NORTH:
				if( state.get( HINGE ) == DoorHingeSide.LEFT ) {
					direction = Direction.EAST;
				} else {
					direction = Direction.WEST;
				}
				break;
		}
		return direction;
	}
	
	private void playDoorSound( PlayerEntity player, World world, BlockPos pos, boolean open ) {
		
		world.playSound( player, pos, open ? getOpenDoorSound() : getCloseDoorSound(), SoundCategory.BLOCKS, 1.0F,
			1.0F );
	}
	
	private SoundEvent getCloseDoorSound() {
		
		return material == Material.IRON ? SoundEvents.BLOCK_IRON_DOOR_OPEN : SoundEvents.BLOCK_WOODEN_DOOR_OPEN;
	}
	
	private SoundEvent getOpenDoorSound() {
		
		return material == Material.IRON ? SoundEvents.BLOCK_IRON_DOOR_CLOSE : SoundEvents.BLOCK_WOODEN_DOOR_CLOSE;
	}
}
