package de.geheimagentnr1.manyideas_core.elements.blocks.dye_crafting_table;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Objects;


@OnlyIn( Dist.CLIENT )
public class DyeCraftingTableScreen extends ContainerScreen<DyeCraftingTableContainer> {
	
	
	private static final ResourceLocation CRAFTING_TABLE_GUI_TEXTURES = new ResourceLocation(
		"minecraft",
		"textures/gui/container/crafting_table.png"
	);
	
	public DyeCraftingTableScreen( DyeCraftingTableContainer _container, PlayerInventory inv, ITextComponent _title ) {
		
		super( _container, inv, _title );
		titleLabelX = 38;
	}
	
	@Override
	public void render( MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks ) {
		
		super.render( matrixStack, mouseX, mouseY, partialTicks );
		renderTooltip( matrixStack, mouseX, mouseY );
	}
	
	@Override
	protected void renderBg( MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY ) {
		
		RenderSystem.color4f( 1.0F, 1.0F, 1.0F, 1.0F );
		Objects.requireNonNull( minecraft ).getTextureManager().bind( CRAFTING_TABLE_GUI_TEXTURES );
		blit( matrixStack, leftPos, ( height - imageHeight ) / 2, 0, 0, imageWidth, imageHeight );
	}
}
