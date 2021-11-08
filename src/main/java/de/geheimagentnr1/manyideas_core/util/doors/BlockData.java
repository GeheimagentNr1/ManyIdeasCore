package de.geheimagentnr1.manyideas_core.util.doors;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;


public class BlockData {
	
	
	private final BlockPos pos;
	
	private final BlockPos zeroPos;
	
	private final BlockState state;
	
	public BlockData( BlockPos _pos, BlockPos _zeroPos, BlockState _state ) {
		
		pos = _pos;
		zeroPos = _zeroPos;
		state = _state;
	}
	
	public BlockData( BlockPos _pos, BlockState _state ) {
		
		pos = _pos;
		zeroPos = null;
		state = _state;
	}
	
	public BlockPos getPos() {
		
		return pos;
	}
	
	public BlockPos getZeroPos() {
		
		return zeroPos;
	}
	
	public BlockState getState() {
		
		return state;
	}
}
