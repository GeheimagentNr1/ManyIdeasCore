package de.geheimagentnr1.manyideas_core.util.doors;

import de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.doors.BigDoor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;


public class BigDoorsHelper {
	
	
	@NotNull
	public static BlockData getNeighborBlock(
		@NotNull Level level,
		@NotNull BlockPos zeroPos,
		@NotNull BlockState state,
		int zSize,
		@NotNull Function<BlockData, BlockPos> zeroPosCalculator ) {
		
		BlockPos neighborPos = zeroPos.relative( getDirectionToNeighborDoor( state ), zSize );
		BlockState neighborState = level.getBlockState( neighborPos );
		BlockPos neighborZeroPos = neighborState.getBlock() instanceof BigDoor
			? zeroPosCalculator.apply( new BlockData( neighborPos, neighborState ) )
			: null;
		return new BlockData( neighborPos, neighborZeroPos, neighborState );
	}
	
	public static boolean isNeighbor( @NotNull BlockState state, @NotNull BlockData neighborBlock ) {
		
		BlockState neighborState = neighborBlock.getState();
		return neighborBlock.getZeroPos() != null &&
			neighborBlock.getPos().equals( neighborBlock.getZeroPos() ) &&
			state.getValue( BlockStateProperties.HORIZONTAL_FACING ) ==
				neighborState.getValue( BlockStateProperties.HORIZONTAL_FACING ) &&
			state.getValue( BlockStateProperties.DOOR_HINGE ) !=
				neighborState.getValue( BlockStateProperties.DOOR_HINGE );
	}
	
	@NotNull
	private static Direction getDirectionToNeighborDoor( @NotNull BlockState state ) {
		
		Direction facing = state.getValue( BlockStateProperties.HORIZONTAL_FACING );
		return state.getValue( BlockStateProperties.DOOR_HINGE ) == DoorHingeSide.LEFT
			? facing.getClockWise()
			: facing.getCounterClockWise();
	}
}
