package de.geheimagentnr1.manyideas_core.elements.blocks.dye_crafting_table;

import com.mojang.blaze3d.platform.GlStateManager;
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
	
	public DyeCraftingTableScreen(
		DyeCraftingTableContainer _container,
		PlayerInventory inv,
		ITextComponent titleIn ) {
		
		super( _container, inv, titleIn );
	}
	
	@SuppressWarnings( "ParameterNameDiffersFromOverriddenParameter" )
	@Override
	public void render( int mouseX, int mouseY, float partialTicks ) {
		
		super.render( mouseX, mouseY, partialTicks );
		renderHoveredToolTip( mouseX, mouseY );
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer( int mouseX, int mouseY ) {
		
		font.drawString( title.getFormattedText(), 28.0F, 6.0F, 4210752 );
		font.drawString( playerInventory.getDisplayName().getFormattedText(), 8.0F, ySize - 96 + 2, 4210752 );
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer( float partialTicks, int mouseX, int mouseY ) {
		
		renderBackground();
		GlStateManager.color4f( 1.0F, 1.0F, 1.0F, 1.0F );
		Objects.requireNonNull( minecraft ).getTextureManager().bindTexture( CRAFTING_TABLE_GUI_TEXTURES );
		blit( guiLeft, ( height - ySize ) / 2, 0, 0, xSize, ySize );
	}
}
