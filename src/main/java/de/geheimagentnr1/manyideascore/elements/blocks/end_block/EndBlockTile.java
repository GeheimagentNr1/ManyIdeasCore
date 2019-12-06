package de.geheimagentnr1.manyideascore.elements.blocks.end_block;

import de.geheimagentnr1.manyideascore.elements.blocks.ModBlocks;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;


public class EndBlockTile extends TileEntity {
	
	
	public EndBlockTile() {
		
		super( ModBlocks.END_BLOCK_TILE );
	}
	
	//package-private
	boolean shouldRender( Direction direction ) {
		
		if( world == null ) {
			return true;
		}
		BlockState state = world.getBlockState( pos.offset( direction ) );
		return state.getRenderType() == BlockRenderType.INVISIBLE ||
			state.getBlock().getRenderLayer() != BlockRenderLayer.SOLID;
	}
}
