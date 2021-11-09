package de.geheimagentnr1.manyideas_core.elements.blocks.end_block;

import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.TheEndPortalBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;


public class EndBlockEntity extends TheEndPortalBlockEntity {
	
	
	public EndBlockEntity( BlockPos pos, BlockState state ) {
		
		super( ModBlocks.END_BLOCK_ENTITY, pos, state );
	}
	
	@SuppressWarnings( "unused" )
	public EndBlockEntity( BlockEntityType<? extends TheEndPortalBlockEntity> _type, BlockPos pos, BlockState state ) {
		
		super( _type, pos, state );
	}
	
	@Override
	public boolean shouldRenderFace( @Nonnull Direction face ) {
		
		return shouldRender( level, worldPosition, face );
	}
	
	@SuppressWarnings( "WeakerAccess" )
	public static boolean shouldRender( Level level, BlockPos pos, Direction face ) {
		
		if( level == null ) {
			return true;
		}
		BlockPos direction_pos = pos.relative( face );
		BlockState direction_state = level.getBlockState( direction_pos );
		if( direction_state.getRenderShape() == RenderShape.INVISIBLE ) {
			return true;
		}
		return !Block.isFaceFull( direction_state.getShape( level, direction_pos ), face.getOpposite() );
	}
}
