package de.geheimagentnr1.manyideas_core.util.doors;

import de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.doors.DoubleDoorBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.properties.BlockStateProperties;
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
		World world,
		BlockPos pos,
		Material material,
		PlayerEntity player,
		boolean open ) {
		
		world.playSound(
			player,
			pos,
			open ? getOpenDoorSound( material ) : getCloseDoorSound( material ),
			SoundCategory.BLOCKS,
			1.0F,
			1.0F
		);
	}
	
	private static SoundEvent getCloseDoorSound( Material material ) {
		
		return material == Material.IRON ? SoundEvents.BLOCK_IRON_DOOR_OPEN : SoundEvents.BLOCK_WOODEN_DOOR_OPEN;
	}
	
	private static SoundEvent getOpenDoorSound( Material material ) {
		
		return material == Material.IRON ? SoundEvents.BLOCK_IRON_DOOR_CLOSE : SoundEvents.BLOCK_WOODEN_DOOR_CLOSE;
	}
	
	public static BlockData getOtherBlock( World world, BlockPos pos, BlockState state ) {
		
		BlockPos otherPos = state.get( DoorBlock.HALF ) == DoubleBlockHalf.LOWER ? pos.up() : pos.down();
		BlockState otherState = world.getBlockState( otherPos );
		return new BlockData( otherPos, otherState );
	}
	
	public static BlockData getNeighborBlock( World world, BlockPos pos, BlockState state ) {
		
		BlockPos neighborPos = pos.offset( getDirectionToNeighborDoor( state ) );
		BlockState neighborState = world.getBlockState( neighborPos );
		return new BlockData( neighborPos, neighborState );
	}
	
	public static boolean isNeighbor( BlockState state, BlockData neighborBlock ) {
		
		BlockState neighborState = neighborBlock.getState();
		return neighborState.getBlock() instanceof DoubleDoorBlock &&
			state.get( DoorBlock.FACING ) == neighborState.get( DoorBlock.FACING ) &&
			state.get( DoorBlock.HINGE ) != neighborState.get( DoorBlock.HINGE ) &&
			state.get( DoorBlock.HALF ) == neighborState.get( DoorBlock.HALF );
	}
	
	public static boolean isDoorPowered( World world, BlockPos pos, BlockState state ) {
		
		return world.isBlockPowered( pos ) || world.isBlockPowered( pos.offset(
			state.get( DoorBlock.HALF ) == DoubleBlockHalf.LOWER ? Direction.UP : Direction.DOWN ) );
	}
	
	private static Direction getDirectionToNeighborDoor( BlockState state ) {
		
		Direction facing = state.get( DoorBlock.FACING );
		return state.get( DoorBlock.HINGE ) == DoorHingeSide.LEFT ? facing.rotateY() : facing.rotateYCCW();
	}
}
