package de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.screen;

import de.geheimagentnr1.manyideas_core.network.RedstoneKeyStateUpdateMsg;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.AbstractContainerEventHandler;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.Objects;


@SuppressWarnings( "WeakerAccess" )
public class RedstoneKeyOption extends AbstractContainerEventHandler implements Renderable {
	
	
	@NotNull
	private final RedstoneKeyScreen parent;
	
	private final int x;
	
	private final int y;
	
	@NotNull
	private final ResourceLocation icons;
	
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
		@NotNull ResourceLocation _icons,
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
	public void render( @NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick ) {
		
		button.render( guiGraphics, mouseX, mouseY, partialTick );
		
		Font font = Minecraft.getInstance().font;
		guiGraphics.drawString(
			font,
			title,
			x + 30,
			y + 2,
			Objects.requireNonNull( ChatFormatting.DARK_GRAY.getColor() ),
			false
		);
		guiGraphics.drawString(
			font,
			description,
			x + 30,
			y + 12,
			Objects.requireNonNull( ChatFormatting.WHITE.getColor() ),
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
