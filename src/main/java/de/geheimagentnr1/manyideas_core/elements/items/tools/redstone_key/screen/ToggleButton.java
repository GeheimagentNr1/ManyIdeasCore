package de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.AbstractButton;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

import java.util.function.Consumer;


public class ToggleButton extends AbstractButton {
	
	
	private static final ResourceLocation TOGGLE_BUTTON = new ResourceLocation(
		ManyIdeasCore.MODID,
		"textures/gui/redstone_key/toggle_button.png"
	);
	
	private final ResourceLocation icon_textures;
	
	private final int iconIndex;
	
	private final Consumer<Boolean> onPress;
	
	private boolean selected;
	
	public ToggleButton(
		int _x,
		int _y,
		ResourceLocation _icon_textures,
		int _iconIndex,
		Consumer<Boolean> _onPress ) {
		
		super( _x, _y, 22, 22, new StringTextComponent( "" ) );
		icon_textures = _icon_textures;
		iconIndex = _iconIndex;
		onPress = _onPress;
	}
	
	@Override
	public void renderButton( MatrixStack matrixStack, int mouseX, int mouseY, float partial ) {
		
		Minecraft.getInstance().getTextureManager().bind( TOGGLE_BUTTON );
		RenderSystem.color4f( 1.0f, 1.0f, 1.0f, 1.0f );
		int textureStartindex = 0;
		
		if( active ) {
			if( selected ) {
				textureStartindex = 1;
			} else {
				if( isHovered() ) {
					textureStartindex = 3;
				}
			}
		} else {
			textureStartindex = 2;
		}
		blit( matrixStack, x, y, width * textureStartindex, 0, width, height, 128, 32 );
		Minecraft.getInstance().getTextureManager().bind( icon_textures );
		blit( matrixStack, x + 3, y + 3, iconIndex << 4, 0, 16, 16, 64, 16 );
	}
	
	@Override
	public void onPress() {
		
		onPress.accept( selected );
	}
	
	public void setSelected( boolean _selected ) {
		
		selected = _selected;
	}
}
