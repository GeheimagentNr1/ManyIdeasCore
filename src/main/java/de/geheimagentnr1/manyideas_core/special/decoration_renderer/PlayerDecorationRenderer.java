package de.geheimagentnr1.manyideas_core.special.decoration_renderer;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerModelPart;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;


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
	void renderItemStack( PlayerEntity player, float partialTicks ) {
		
		if( player.isInvisible() || !player.isWearing( PlayerModelPart.CAPE ) || player.isElytraFlying() ) {
			return;
		}
		GlStateManager.pushMatrix();
		
		Vec3d currentPos = Minecraft.getInstance().gameRenderer.getActiveRenderInfo().getProjectedView();
		Vec3d playerPos = player.getEyePosition( partialTicks );
		GlStateManager.translated(
			playerPos.x - currentPos.x,
			playerPos.y - currentPos.y,
			playerPos.z - currentPos.z
		);
		GlStateManager.translated( 0.0D, 1 - ( player.isSneaking() ? 0.125D : 0.0D ), 0.0D );
		
		renderItemStack();
		GlStateManager.popMatrix();
	}
	
	private void renderItemStack() {
		
		GlStateManager.pushMatrix();
		float size;
		if( isBlock ) {
			GlStateManager.translated( 0.0D, -0.1875D, 0.0D );
			size = 0.5F;
		} else {
			size = 0.4F;
		}
		GlStateManager.rotated( 180.0F, 1.0F, 0.0F, 1.0F );
		
		GlStateManager.scaled( size, size, size );
		
		double bouncing = ( System.currentTimeMillis() & Long.MAX_VALUE ) / 1000.0D;
		GlStateManager.translated( 0.0D, StrictMath.sin( bouncing % ( 2 * Math.PI ) ) * 0.25, 0.0D );
		GlStateManager.rotated( (float)( bouncing * 40.0D % 360 ), 0, 1, 0 );
		
		GlStateManager.disableLighting();
		GlStateManager.pushMatrix();
		
		if( !isBlock ) {
			GlStateManager.translated( 0.0D, 0.5D, 0.0D );
		}
		GlStateManager.rotated( 180.0F, 1.0F, 0.0F, 0.0F );
		renderItem();
		GlStateManager.popMatrix();
		GlStateManager.enableLighting();
		GlStateManager.popMatrix();
	}
	
	@SuppressWarnings( "deprecation" )
	private void renderItem() {
		
		GlStateManager.pushMatrix();
		GlStateManager.disableLighting();
		GlStateManager.pushLightingAttributes();
		RenderHelper.enableStandardItemLighting();
		Minecraft.getInstance().getItemRenderer().renderItem( stack, ItemCameraTransforms.TransformType.FIXED );
		RenderHelper.disableStandardItemLighting();
		GlStateManager.popAttributes();
		GlStateManager.enableLighting();
		GlStateManager.popMatrix();
	}
}
