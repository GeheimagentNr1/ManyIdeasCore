package de.geheimagentnr1.manyideas_core.util.doors;

import de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.doors.BigDoor;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoorHingeSide;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.function.Function;


public class BigDoorsHelper {
	
	
	public static BlockData getNeighborBlock(
		World world,
		BlockPos zeroPos,
		BlockState state,
		int zSize,
		Function<BlockData, BlockPos> zeroPosCalculator ) {
		
		BlockPos neighborPos = zeroPos.relative( getDirectionToNeighborDoor( state ), zSize );
		BlockState neighborState = world.getBlockState( neighborPos );
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
		
		Direction facing = state.getValue( DoorBlock.FACING );
		return state.getValue( BlockStateProperties.DOOR_HINGE ) == DoorHingeSide.LEFT
			? facing.getClockWise()
			: facing.getCounterClockWise();
	}
}
