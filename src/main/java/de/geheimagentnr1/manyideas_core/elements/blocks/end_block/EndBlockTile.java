package de.geheimagentnr1.manyideas_core.elements.blocks.end_block;

import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.EndPortalTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

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
		
		return shouldRender( world, pos, face );
	}
	
	@SuppressWarnings( "WeakerAccess" )
	public static boolean shouldRender( World world, BlockPos pos, Direction face ) {
		
		if( world == null ) {
			return true;
		}
		BlockPos direction_pos = pos.offset( face );
		BlockState direction_state = world.getBlockState( direction_pos );
		if( direction_state.getRenderType() == BlockRenderType.INVISIBLE ) {
			return true;
		}
		return !Block.hasSolidSide( direction_state, world, direction_pos, face.getOpposite() );
	}
}
