package de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.screen;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.models.Option;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class RedstoneKeyScreen extends AbstractContainerScreen<RedstoneKeyContainer> {
	
	
	@NotNull
	private static final Identifier REDSTONE_KEY_GUI_TEXTURE = Identifier.fromNamespaceAndPath(
		ManyIdeasCore.MODID,
		"textures/gui/redstone_key/redstone_key_gui.png"
	);
	
	@NotNull
	private final List<RedstoneKeyOption> optionsGui = new ArrayList<>();
	
	public RedstoneKeyScreen(
		@NotNull RedstoneKeyContainer _menu,
		@NotNull Inventory _inventory,
		@NotNull Component _title ) {
		
		super( _menu, _inventory, _title );
	}
	
	@Override
	protected void init() {
		
		super.init();
		
		int xStart = leftPos + 15;
		int yStart = topPos + 20;
		List<Option> options = menu.getOptions();
		optionsGui.clear();
		for( int index = 0; index < options.size(); index++ ) {
			Option option = options.get( index );
			RedstoneKeyOption optionGui = new RedstoneKeyOption(
				this,
				xStart,
				yStart + index * 30,
				menu.getIcons(),
				index,
				I18n.get( option.getTitle() ),
				I18n.get( option.getDescription() )
			);
			optionsGui.add( optionGui );
			addWidget( optionGui.getButton() );
			renderables.add( optionGui );
		}
		optionsGui.get( menu.getSelectedIndex() ).setSelected();
	}
	
	@Override
	public void extractBackground( @NotNull GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTick ) {
		
		super.extractBackground( guiGraphics, mouseX, mouseY, partialTick );
		//Overload without RenderPipeline: the RenderPipeline class moved in 26.3
		int x = leftPos;
		int y = ( height - imageHeight ) / 2;
		guiGraphics.blit(
			REDSTONE_KEY_GUI_TEXTURE,
			x,
			y,
			x + imageWidth,
			y + imageHeight,
			0.0F,
			imageWidth / 256.0F,
			0.0F,
			imageHeight / 256.0F
		);
	}
	
	@Override
	protected void extractLabels( @NotNull GuiGraphicsExtractor guiGraphics, int x, int y ) {
		
		int titleStartX = width / 2 - leftPos - font.width( title.getString() ) / 2;
		guiGraphics.text( font, title.getString(), titleStartX, 5, 0xFF404040, false );
	}
	
	public void resetSelected() {
		
		optionsGui.forEach( RedstoneKeyOption::resetSelected );
	}
	
	public void setSelected( int selectedIndex ) {
		
		menu.setSelectedIndex( selectedIndex );
	}
}
