package de.geheimagentnr1.manyideas_core.elements.blocks.end_block;

import net.minecraft.client.renderer.blockentity.AbstractEndPortalRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.EndPortalRenderState;
import org.jetbrains.annotations.NotNull;


public class EndBlockEntityRenderer extends AbstractEndPortalRenderer<EndBlockEntity, EndPortalRenderState> {
	
	
	public EndBlockEntityRenderer( @NotNull BlockEntityRendererProvider.Context context ) {
		
	}
	
	@NotNull
	@Override
	public EndPortalRenderState createRenderState() {
		
		return new EndPortalRenderState();
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
