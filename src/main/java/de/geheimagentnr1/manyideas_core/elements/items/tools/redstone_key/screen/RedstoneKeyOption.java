package de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.screen;

import de.geheimagentnr1.manyideas_core.network.RedstoneKeyStateUpdateMsg;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.AbstractContainerEventHandler;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;


@SuppressWarnings( "WeakerAccess" )
public class RedstoneKeyOption extends AbstractContainerEventHandler implements Renderable {
	
	
	@NotNull
	private final RedstoneKeyScreen parent;
	
	private final int x;
	
	private final int y;
	
	@NotNull
	private final Identifier icons;
	
	private final int stateIndex;
	
	@NotNull
	private final String title;
	
	@NotNull
	private final String description;
	
	private ToggleButton button;
	
	public RedstoneKeyOption(
		@NotNull RedstoneKeyScreen _parent,
		int _x,
		int _y,
		@NotNull Identifier _icons,
		int _stateIndex,
		@NotNull String _title,
		@NotNull String _description ) {
		
		x = _x;
		y = _y;
		parent = _parent;
		icons = _icons;
		stateIndex = _stateIndex;
		title = _title;
		description = _description;
		init();
	}
	
	private void init() {
		
		button = new ToggleButton(
			x,
			y,
			icons,
			stateIndex,
			selected -> {
				if( !selected ) {
					parent.resetSelected();
					parent.setSelected( stateIndex );
					button.setSelected( true );
					RedstoneKeyStateUpdateMsg.sendToServer( stateIndex );
				}
			}
		);
	}
	
	@Override
	public void extractRenderState( @NotNull GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTick ) {
		
		button.extractRenderState( guiGraphics, mouseX, mouseY, partialTick );
		
		Font font = Minecraft.getInstance().font;
		guiGraphics.text(
			font,
			title,
			x + 30,
			y + 2,
			0xFF555555,//ChatFormatting.DARK_GRAY, getColor() was removed in 26.2
			false
		);
		guiGraphics.text(
			font,
			description,
			x + 30,
			y + 12,
			0xFFFFFFFF,//ChatFormatting.WHITE
			false
		);
	}
	
	public void resetSelected() {
		
		button.setSelected( false );
	}
	
	@NotNull
	@Override
	public List<? extends GuiEventListener> children() {
		
		return Collections.singletonList( button );
	}
	
	public ToggleButton getButton() {
		
		return button;
	}
	
	public void setSelected() {
		
		button.setSelected( true );
	}
}
