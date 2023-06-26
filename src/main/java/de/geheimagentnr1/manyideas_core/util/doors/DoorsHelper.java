package de.geheimagentnr1.manyideas_core.util.doors;

import de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.doors.DoubleDoorBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class DoorsHelper {
	
	
	public static void playDoorSound(
		@NotNull Level level,
		@NotNull BlockPos pos,
		@NotNull BlockSetType type,
		@Nullable Player player,
		boolean open ) {
		
		level.playSound(
			player,
			pos,
			open ? type.doorOpen() : type.doorClose(),
			SoundSource.BLOCKS,
			1.0F,
			1.0F
		);
	}
	
	@NotNull
	public static BlockData getOtherBlock( @NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state ) {
		
		BlockPos otherPos = state.getValue( DoorBlock.HALF ) == DoubleBlockHalf.LOWER ? pos.above() : pos.below();
		BlockState otherState = level.getBlockState( otherPos );
		return new BlockData( otherPos, otherState );
	}
	
	@NotNull
	public static BlockData getNeighborBlock(
		@NotNull Level level, @NotNull BlockPos pos,
		@NotNull BlockState state ) {
		
		BlockPos neighborPos = pos.relative( getDirectionToNeighborDoor( state ) );
		BlockState neighborState = level.getBlockState( neighborPos );
		return new BlockData( neighborPos, neighborState );
	}
	
	public static boolean isNeighbor( @NotNull BlockState state, @NotNull BlockData neighborBlock ) {
		
		BlockState neighborState = neighborBlock.getState();
		return neighborState.getBlock() instanceof DoubleDoorBlock &&
			state.getValue( DoorBlock.FACING ) == neighborState.getValue( DoorBlock.FACING ) &&
			state.getValue( DoorBlock.HINGE ) != neighborState.getValue( DoorBlock.HINGE ) &&
			state.getValue( DoorBlock.HALF ) == neighborState.getValue( DoorBlock.HALF );
	}
	
	public static boolean isDoorPowered( @NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state ) {
		
		return level.hasNeighborSignal( pos ) || level.hasNeighborSignal( pos.relative(
			state.getValue( DoorBlock.HALF ) == DoubleBlockHalf.LOWER ? Direction.UP : Direction.DOWN ) );
	}
	
	@NotNull
	private static Direction getDirectionToNeighborDoor( @NotNull BlockState state ) {
		
		Direction facing = state.getValue( DoorBlock.FACING );
		return state.getValue( DoorBlock.HINGE ) == DoorHingeSide.LEFT
			? facing.getClockWise()
			: facing.getCounterClockWise();
	}
}
