package de.geheimagentnr1.manyideas_core.elements.blocks.dye_crafting_table;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;


@OnlyIn( Dist.CLIENT )
public class DyeCraftingTableScreen extends AbstractContainerScreen<DyeCraftingTableMenu> {
	
	
	private static final ResourceLocation CRAFTING_TABLE_GUI_TEXTURES = new ResourceLocation(
		"minecraft",
		"textures/gui/container/crafting_table.png"
	);
	
	public DyeCraftingTableScreen(
		DyeCraftingTableMenu _menu,
		Inventory _inventory,
		Component _title ) {
		
		super( _menu, _inventory, _title );
		initData();
	}
	
	private void initData() {
		
		titleLabelX = 38;
	}
	
	@Override
	public void render( @Nonnull PoseStack poseStack, int mouseX, int mouseY, float partialTicks ) {
		
		super.render( poseStack, mouseX, mouseY, partialTicks );
		renderTooltip( poseStack, mouseX, mouseY );
	}
	
	@Override
	protected void renderBg( @Nonnull PoseStack matrixStack, float partialTicks, int mouseX, int mouseY ) {
		
		RenderSystem.setShader( GameRenderer::getPositionTexShader );
		RenderSystem.setShaderColor( 1.0F, 1.0F, 1.0F, 1.0F );
		RenderSystem.setShaderTexture( 0, CRAFTING_TABLE_GUI_TEXTURES );
		blit( matrixStack, leftPos, ( height - imageHeight ) / 2, 0, 0, imageWidth, imageHeight );
	}
}
