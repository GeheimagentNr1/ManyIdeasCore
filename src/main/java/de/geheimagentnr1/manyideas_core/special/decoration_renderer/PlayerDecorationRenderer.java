package de.geheimagentnr1.manyideas_core.special.decoration_renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerModelPart;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3f;


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
	void renderItemStack( PlayerEntity player, int light, MatrixStack matrixStack, IRenderTypeBuffer buffer ) {
		
		if( player.isInvisible() || !player.isModelPartShown( PlayerModelPart.CAPE ) || player.isFallFlying() ) {
			return;
		}
		matrixStack.pushPose();
		matrixStack.translate( 0.0D, 2.4 - ( player.isCrouching() ? 0.3D : 0.0D ), 0.0D );
		matrixStack.pushPose();
		float size;
		if( isBlock ) {
			size = 0.5F;
		} else {
			size = 0.4F;
		}
		matrixStack.scale( size, size, size );
		matrixStack.pushPose();
		double bouncing = ( System.currentTimeMillis() & Long.MAX_VALUE ) / 1000.0D;
		matrixStack.translate( 0.0D, StrictMath.sin( bouncing % ( 2 * Math.PI ) ) * 0.25, 0.0D );
		matrixStack.pushPose();
		matrixStack.mulPose( Vector3f.YP.rotationDegrees( (float)( bouncing * 40.0D % 360 ) ) );
		Minecraft.getInstance().getItemRenderer().renderStatic( stack, ItemCameraTransforms.TransformType.FIXED, light,
			OverlayTexture.NO_OVERLAY, matrixStack, buffer
		);
		matrixStack.popPose();
		matrixStack.popPose();
		matrixStack.popPose();
		matrixStack.popPose();
	}
}
