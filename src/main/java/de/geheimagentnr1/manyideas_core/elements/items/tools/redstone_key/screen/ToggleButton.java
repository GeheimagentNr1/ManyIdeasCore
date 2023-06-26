package de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.screen;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;


public class ToggleButton extends AbstractButton {
	
	
	@NotNull
	private static final ResourceLocation TOGGLE_BUTTON = new ResourceLocation(
		ManyIdeasCore.MODID,
		"textures/gui/redstone_key/toggle_button.png"
	);
	
	@NotNull
	private final ResourceLocation icon_textures;
	
	private final int iconIndex;
	
	@NotNull
	private final Consumer<Boolean> onPress;
	
	private boolean selected;
	
	public ToggleButton(
		int _x,
		int _y,
		@NotNull ResourceLocation _icon_textures,
		int _iconIndex,
		@NotNull Consumer<Boolean> _onPress ) {
		
		super( _x, _y, 22, 22, Component.literal( "" ) );
		icon_textures = _icon_textures;
		iconIndex = _iconIndex;
		onPress = _onPress;
	}
	
	@Override
	public void renderWidget( @NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick ) {
		
		int textureStartindex = 0;
		
		if( active ) {
			if( selected ) {
				textureStartindex = 1;
			} else {
				if( isHovered ) {
					textureStartindex = 3;
				}
			}
		} else {
			textureStartindex = 2;
		}
		guiGraphics.blit( TOGGLE_BUTTON, getX(), getY(), width * textureStartindex, 0, width, height, 128, 32 );
		guiGraphics.blit( icon_textures, getX() + 3, getY() + 3, iconIndex << 4, 0, 16, 16, 64, 16 );
	}
	
	@Override
	public void onPress() {
		
		onPress.accept( selected );
	}
	
	public void setSelected( boolean _selected ) {
		
		selected = _selected;
	}
	
	@Override
	protected void updateWidgetNarration( @NotNull NarrationElementOutput output ) {
	
	}
}
