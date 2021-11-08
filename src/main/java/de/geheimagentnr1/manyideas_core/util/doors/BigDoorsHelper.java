package de.geheimagentnr1.manyideas_core.util.doors;

import de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.doors.BigDoor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;

import java.util.function.Function;


public class BigDoorsHelper {
	
	
	public static BlockData getNeighborBlock(
		Level level,
		BlockPos zeroPos,
		BlockState state,
		int zSize,
		Function<BlockData, BlockPos> zeroPosCalculator ) {
		
		BlockPos neighborPos = zeroPos.relative( getDirectionToNeighborDoor( state ), zSize );
		BlockState neighborState = level.getBlockState( neighborPos );
		BlockPos neighborZeroPos = neighborState.getBlock() instanceof BigDoor
			? zeroPosCalculator.apply( new BlockData( neighborPos, neighborState ) )
			: null;
		return new BlockData( neighborPos, neighborZeroPos, neighborState );
	}
	
	public static boolean isNeighbor( BlockState state, BlockData neighborBlock ) {
		
		BlockState neighborState = neighborBlock.getState();
		return neighborBlock.getZeroPos() != null &&
			neighborBlock.getPos().equals( neighborBlock.getZeroPos() ) &&
			state.getValue( BlockStateProperties.HORIZONTAL_FACING ) ==
				neighborState.getValue( BlockStateProperties.HORIZONTAL_FACING ) &&
			state.getValue( BlockStateProperties.DOOR_HINGE ) !=
				neighborState.getValue( BlockStateProperties.DOOR_HINGE );
	}
	
	private static Direction getDirectionToNeighborDoor( BlockState state ) {
		
		Direction facing = state.getValue( BlockStateProperties.HORIZONTAL_FACING );
		return state.getValue( BlockStateProperties.DOOR_HINGE ) == DoorHingeSide.LEFT
			? facing.getClockWise()
			: facing.getCounterClockWise();
	}
}
