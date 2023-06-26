package de.geheimagentnr1.manyideas_core.elements.blocks.end_block;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.TheEndPortalRenderer;
import org.jetbrains.annotations.NotNull;


public class EndBlockEntityRenderer extends TheEndPortalRenderer<EndBlockEntity> {
	
	
	public EndBlockEntityRenderer( @NotNull BlockEntityRendererProvider.Context context ) {
		
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
