package de.geheimagentnr1.manyideas_core.elements.blocks.end_block;

import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.EndPortalTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nonnull;


public class EndBlockTile extends EndPortalTileEntity {
	
	
	public EndBlockTile() {
		
		super( ModBlocks.END_BLOCK_TILE );
	}
	
	@SuppressWarnings( "unused" )
	public EndBlockTile( TileEntityType<? extends EndBlockTile> _type ) {
		
		super( _type );
	}
	
	@Override
	public boolean shouldRenderFace( @Nonnull Direction face ) {
		
		if( level == null ) {
			return true;
		}
		BlockPos direction_pos = worldPosition.relative( face );
		BlockState direction_state = level.getBlockState( direction_pos );
		if( direction_state.getRenderShape() == BlockRenderType.INVISIBLE ) {
			return true;
		}
		if( direction_state.getBlock() instanceof IEndBlock ) {
			return false;
		}
		return !Block.isFaceFull( direction_state.getShape( level, direction_pos ), face.getOpposite() );
	}
}
