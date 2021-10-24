package de.geheimagentnr1.manyideas_core.elements.blocks.end_block;

import net.minecraft.client.renderer.tileentity.EndPortalTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;


public class EndBlockTileRenderer extends EndPortalTileEntityRenderer<EndBlockTile> {
	
	
	public EndBlockTileRenderer( TileEntityRendererDispatcher rendererDispatcher ) {
		
		super( rendererDispatcher );
	}
	
	@Override
	protected float getOffset() {
		
		return 1.0F;
	}
}
