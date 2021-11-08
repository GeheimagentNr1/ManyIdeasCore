package de.geheimagentnr1.manyideas_core.elements.blocks.end_block;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.TheEndPortalRenderer;


public class EndBlockTileRenderer extends TheEndPortalRenderer<EndBlockEntity> {
	
	
	public EndBlockTileRenderer( BlockEntityRendererProvider.Context context ) {
		
		super( context );
	}
	
	@Override
	protected float getOffsetUp() {
		
		return 1;
	}
	
	@Override
	protected float getOffsetDown() {
		
		return 0;
	}
}
