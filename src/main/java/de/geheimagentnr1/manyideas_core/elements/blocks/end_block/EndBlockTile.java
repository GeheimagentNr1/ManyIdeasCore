package de.geheimagentnr1.manyideas_core.elements.blocks.end_block;

import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;


public class EndBlockTile extends TileEntity {
	
	
	public EndBlockTile() {
		
		super( ModBlocks.END_BLOCK_TILE );
	}
	
	@SuppressWarnings( "unused" )
	public EndBlockTile( TileEntityType<? extends EndBlockTile> _type ) {
		
		super( _type );
	}
	
	//package-private
	@SuppressWarnings( "deprecation" )
	boolean shouldRender( Direction direction ) {
		
		if( world == null ) {
			return true;
		}
		BlockPos direction_pos = pos.offset( direction );
		BlockState direction_state = world.getBlockState( direction_pos );
		if( direction_state.getRenderType() == BlockRenderType.INVISIBLE ) {
			return true;
		}
		if( direction_state.getBlock() instanceof IEndBlock ) {
			return false;
		}
		if( direction_state.getBlock().getRenderLayer() != BlockRenderLayer.SOLID ) {
			return !direction_state.getBlock().isSolid( direction_state );
		} else {
			return !Block.hasSolidSide( direction_state, world, direction_pos, direction.getOpposite() );
		}
	}
}
