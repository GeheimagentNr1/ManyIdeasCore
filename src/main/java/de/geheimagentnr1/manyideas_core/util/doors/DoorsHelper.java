package de.geheimagentnr1.manyideas_core.util.doors;

import de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.doors.DoubleDoorBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.properties.DoorHingeSide;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class DoorsHelper {
	
	
	public static void playDoorSound(
		World level,
		BlockPos pos,
		Material material,
		PlayerEntity player,
		boolean open ) {
		
		level.playSound(
			player,
			pos,
			open ? getOpenDoorSound( material ) : getCloseDoorSound( material ),
			SoundCategory.BLOCKS,
			1.0F,
			1.0F
		);
	}
	
	private static SoundEvent getCloseDoorSound( Material material ) {
		
		return material == Material.METAL ? SoundEvents.IRON_DOOR_OPEN : SoundEvents.WOODEN_DOOR_OPEN;
	}
	
	private static SoundEvent getOpenDoorSound( Material material ) {
		
		return material == Material.METAL ? SoundEvents.IRON_DOOR_CLOSE : SoundEvents.WOODEN_DOOR_CLOSE;
	}
	
	public static BlockData getOtherBlock( World level, BlockPos pos, BlockState state ) {
		
		BlockPos otherPos = state.getValue( DoorBlock.HALF ) == DoubleBlockHalf.LOWER ? pos.above() : pos.below();
		BlockState otherState = level.getBlockState( otherPos );
		return new BlockData( otherPos, otherState );
	}
	
	public static BlockData getNeighborBlock( World level, BlockPos pos, BlockState state ) {
		
		BlockPos neighborPos = pos.relative( getDirectionToNeighborDoor( state ) );
		BlockState neighborState = level.getBlockState( neighborPos );
		return new BlockData( neighborPos, neighborState );
	}
	
	public static boolean isNeighbor( BlockState state, BlockData neighborBlock ) {
		
		BlockState neighborState = neighborBlock.getState();
		return neighborState.getBlock() instanceof DoubleDoorBlock &&
			state.getValue( DoorBlock.FACING ) == neighborState.getValue( DoorBlock.FACING ) &&
			state.getValue( DoorBlock.HINGE ) != neighborState.getValue( DoorBlock.HINGE ) &&
			state.getValue( DoorBlock.HALF ) == neighborState.getValue( DoorBlock.HALF );
	}
	
	public static boolean isDoorPowered( World level, BlockPos pos, BlockState state ) {
		
		return level.hasNeighborSignal( pos ) || level.hasNeighborSignal( pos.relative(
			state.getValue( DoorBlock.HALF ) == DoubleBlockHalf.LOWER ? Direction.UP : Direction.DOWN ) );
	}
	
	private static Direction getDirectionToNeighborDoor( BlockState state ) {
		
		Direction facing = state.getValue( DoorBlock.FACING );
		return state.getValue( DoorBlock.HINGE ) == DoorHingeSide.LEFT
			? facing.getClockWise()
			: facing.getCounterClockWise();
	}
}
