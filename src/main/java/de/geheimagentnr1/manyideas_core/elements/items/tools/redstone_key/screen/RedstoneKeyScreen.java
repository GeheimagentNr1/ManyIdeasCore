package de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.models.Option;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class RedstoneKeyScreen extends ContainerScreen<RedstoneKeyContainer> {
	
	
	private static final ResourceLocation REDSTONE_KEY_GUI_TEXTURE = new ResourceLocation(
		ManyIdeasCore.MODID,
		"textures/gui/redstone_key/redstone_key_gui.png"
	);
	
	private final List<RedstoneKeyOption> optionsGui = new ArrayList<>();
	
	public RedstoneKeyScreen( RedstoneKeyContainer screenContainer, PlayerInventory inv, ITextComponent titleIn ) {
		
		super( screenContainer, inv, titleIn );
	}
	
	@Override
	protected void init() {
		
		super.init();
		
		int xStart = guiLeft + 15;
		int yStart = guiTop + 20;
		List<Option> options = container.getOptions();
		optionsGui.clear();
		for( int index = 0; index < options.size(); index++ ) {
			Option option = options.get( index );
			RedstoneKeyOption optionGui = new RedstoneKeyOption(
				this,
				xStart,
				yStart + index * 30,
				container.getIcons(),
				index,
				I18n.format( option.getTitle() ),
				I18n.format( option.getDescription() )
			);
			optionsGui.add( optionGui );
			addButton( optionGui.getButton() );
			children.add( optionGui );
		}
		optionsGui.get( container.getSelectedIndex() ).setSelected();
	}
	
	@Override
	public void render( int mouseX, int mouseY, float partialTicks ) {
		
		renderBackground();
		super.render( mouseX, mouseY, partialTicks );
		optionsGui.forEach( optionGui -> optionGui.render( mouseX, mouseY, partialTicks ) );
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer( float partialTicks, int mouseX, int mouseY ) {
		
		Objects.requireNonNull( minecraft );
		RenderSystem.color4f( 1.0F, 1.0F, 1.0F, 1.0F );
		minecraft.getTextureManager().bindTexture( REDSTONE_KEY_GUI_TEXTURE );
		blit( guiLeft, ( height - ySize ) / 2, 0, 0, xSize, ySize );
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer( int mouseX, int mouseY ) {
		
		int titleStartX = width / 2 - guiLeft - font.getStringWidth( title.getFormattedText() ) / 2;
		font.drawString( title.getFormattedText(), titleStartX, 5, 4210752 );
	}
	
	public void resetSelected() {
		
		optionsGui.forEach( RedstoneKeyOption::resetSelected );
	}
	
	public void setSelected( int selectedIndex ) {
		
		container.setSelectedIndex( selectedIndex );
	}
}
