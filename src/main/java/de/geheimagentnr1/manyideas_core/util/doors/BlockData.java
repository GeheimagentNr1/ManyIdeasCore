package de.geheimagentnr1.manyideas_core.util.doors;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;


@RequiredArgsConstructor
@Data
public class BlockData {
	
	
	@NotNull
	private final BlockPos pos;
	
	private final BlockPos zeroPos;
	
	@NotNull
	private final BlockState state;
	
	public BlockData( BlockPos _pos, BlockState _state ) {
		
		pos = _pos;
		zeroPos = null;
		state = _state;
	}
}
