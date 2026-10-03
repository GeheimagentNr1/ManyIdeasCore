package de.geheimagentnr1.manyideas_core.special.decoration_renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;


//package-private
class PlayerDecorationRenderer {


	@NotNull
	private final ItemStack stack;

	private final boolean isBlock;
	
	@NotNull
	private final ItemStackRenderState itemStackRenderState = new ItemStackRenderState();

	//package-private
	PlayerDecorationRenderer( @NotNull ItemStack _stack ) {

		stack = _stack;
		isBlock = _stack.getItem() instanceof BlockItem;
	}

	//package-private
	void renderItemStack(
		@NotNull AvatarRenderState renderState,
		@NotNull PoseStack poseStack,
		@NotNull SubmitNodeCollector submitNodeCollector ) {

		if( renderState.isInvisible || !renderState.showCape || renderState.isFallFlying ) {
			return;
		}
		poseStack.pushPose();
		poseStack.translate( 0.0D, 2.4 - ( renderState.isCrouching ? 0.3D : 0.0D ), 0.0D );
		poseStack.pushPose();
		float size;
		if( isBlock ) {
			size = 0.5F;
		} else {
			size = 0.4F;
		}
		poseStack.scale( size, size, size );
		poseStack.pushPose();
		double bouncing = ( System.currentTimeMillis() & Long.MAX_VALUE ) / 1000.0D;
		poseStack.translate( 0.0D, StrictMath.sin( bouncing % ( 2 * Math.PI ) ) * 0.25, 0.0D );
		poseStack.pushPose();
		poseStack.rotate( Axis.YP.rotationDegrees( (float)( bouncing * 40.0D % 360 ) ) );
		Minecraft minecraft = Minecraft.getInstance();
		minecraft.getItemModelResolver().updateForTopItem(
			itemStackRenderState,
			stack,
			ItemDisplayContext.FIXED,
			minecraft.level,
			null,
			renderState.id
		);
		itemStackRenderState.submit(
			poseStack,
			submitNodeCollector,
			renderState.lightCoords,
			OverlayTexture.NO_OVERLAY,
			renderState.outlineColor
		);
		poseStack.popPose();
		poseStack.popPose();
		poseStack.popPose();
		poseStack.popPose();
	}
}
