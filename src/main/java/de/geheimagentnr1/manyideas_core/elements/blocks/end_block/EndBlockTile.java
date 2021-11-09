package de.geheimagentnr1.manyideas_core.elements.blocks.end_block;

import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class EndBlockTile extends TileEntity {
	
	
	public EndBlockTile() {
		
		super( ModBlocks.END_BLOCK_TILE );
	}
	
	@SuppressWarnings( "unused" )
	public EndBlockTile( TileEntityType<? extends EndBlockTile> _type ) {
		
		super( _type );
	}
	
	//package-private
	boolean shouldRender( Direction direction ) {
		
		return shouldRender( world, pos, direction );
	}
	
	@SuppressWarnings( "WeakerAccess" )
	public static boolean shouldRender( World world, BlockPos pos, Direction direction ) {
		
		if( world == null ) {
			return true;
		}
		BlockPos direction_pos = pos.offset( direction );
		BlockState direction_state = world.getBlockState( direction_pos );
		if( direction_state.getRenderType() == BlockRenderType.INVISIBLE ) {
			return true;
		}
		return !Block.hasSolidSide( direction_state, world, direction_pos, direction.getOpposite() );
	}
}
