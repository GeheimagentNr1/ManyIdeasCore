package de.geheimagentnr1.manyideas_core.elements.blocks.end_block;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.AbstractEndPortalRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.EndPortalRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import org.jetbrains.annotations.NotNull;


public class EndBlockEntityRenderer extends AbstractEndPortalRenderer<EndBlockEntity, EndPortalRenderState> {
	
	
	public EndBlockEntityRenderer( @NotNull BlockEntityRendererProvider.Context context ) {
		
	}
	
	@NotNull
	@Override
	public EndPortalRenderState createRenderState() {
		
		return new EndPortalRenderState();
	}
	
	//Full block (vanilla end portal: 0.375 - 0.75)
	@Override
	public void submit(
		@NotNull EndPortalRenderState state,
		@NotNull PoseStack poseStack,
		@NotNull SubmitNodeCollector submitNodeCollector,
		@NotNull CameraRenderState camera ) {
		
		submitCube( state.facesToShow, RenderTypes.endPortal(), poseStack, submitNodeCollector );
	}
}
