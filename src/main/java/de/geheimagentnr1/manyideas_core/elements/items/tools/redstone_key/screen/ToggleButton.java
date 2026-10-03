package de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.screen;

import net.minecraft.client.input.InputWithModifiers;
import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;


public class ToggleButton extends AbstractButton {


	@NotNull
	private static final Identifier TOGGLE_BUTTON = Identifier.fromNamespaceAndPath(
		ManyIdeasCore.MODID,
		"textures/gui/redstone_key/toggle_button.png"
	);

	@NotNull
	private final Identifier icon_textures;

	private final int iconIndex;

	@NotNull
	private final Consumer<Boolean> onPress;

	private boolean selected;

	public ToggleButton(
		int _x,
		int _y,
		@NotNull Identifier _icon_textures,
		int _iconIndex,
		@NotNull Consumer<Boolean> _onPress ) {

		super( _x, _y, 22, 22, Component.literal( "" ) );
		icon_textures = _icon_textures;
		iconIndex = _iconIndex;
		onPress = _onPress;
	}

	@Override
	protected void extractContents( @NotNull GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTick ) {

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
		//blit overload without RenderPipeline (the RenderPipeline class moved in 26.3), UVs normalized
		guiGraphics.blit( TOGGLE_BUTTON, getX(), getY(), getX() + width, getY() + height, width * textureStartindex / 128.0F, width * ( textureStartindex + 1 ) / 128.0F, 0.0F, height / 32.0F );
		guiGraphics.blit( icon_textures, getX() + 3, getY() + 3, getX() + 19, getY() + 19, ( iconIndex << 4 ) / 64.0F, ( ( iconIndex << 4 ) + 16 ) / 64.0F, 0.0F, 1.0F );
	}

	@Override
	public void onPress( @NotNull InputWithModifiers input ) {

		onPress.accept( selected );
	}

	public void setSelected( boolean _selected ) {

		selected = _selected;
	}

	@Override
	protected void updateWidgetNarration( @NotNull NarrationElementOutput output ) {

	}
}
