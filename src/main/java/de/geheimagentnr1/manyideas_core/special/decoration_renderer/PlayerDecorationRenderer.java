package de.geheimagentnr1.manyideas_core.special.decoration_renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.PlayerModelPart;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;


//package-private
class PlayerDecorationRenderer {
	
	
	private final ItemStack stack;
	
	private final boolean isBlock;
	
	//package-private
	PlayerDecorationRenderer( ItemStack _stack ) {
		
		stack = _stack;
		isBlock = _stack.getItem() instanceof BlockItem;
	}
	
	//package-private
	void renderItemStack( Player player, int light, PoseStack poseStack, MultiBufferSource buffer ) {
		
		if( player.isInvisible() || !player.isModelPartShown( PlayerModelPart.CAPE ) || player.isFallFlying() ) {
			return;
		}
		poseStack.pushPose();
		poseStack.translate( 0.0D, 2.4 - ( player.isCrouching() ? 0.3D : 0.0D ), 0.0D );
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
		poseStack.mulPose( Axis.YP.rotationDegrees( (float)( bouncing * 40.0D % 360 ) ) );
		Minecraft.getInstance().getItemRenderer().renderStatic(
			stack,
			ItemTransforms.TransformType.FIXED,
			light,
			OverlayTexture.NO_OVERLAY,
			poseStack,
			buffer,
			player.getId()
		);
		poseStack.popPose();
		poseStack.popPose();
		poseStack.popPose();
		poseStack.popPose();
	}
}
