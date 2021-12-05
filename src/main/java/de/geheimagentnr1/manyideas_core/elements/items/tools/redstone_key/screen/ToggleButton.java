package de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;
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
		
		super( _x, _y, 22, 22, new TextComponent( "" ) );
		icon_textures = _icon_textures;
		iconIndex = _iconIndex;
		onPress = _onPress;
	}
	
	@Override
	public void renderButton( @Nonnull PoseStack matrixStack, int mouseX, int mouseY, float partial ) {
		
		RenderSystem.setShader( GameRenderer::getPositionTexShader );
		RenderSystem.setShaderTexture( 0, TOGGLE_BUTTON );
		RenderSystem.setShaderColor( 1.0F, 1.0F, 1.0F, 1.0F );
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
		blit( matrixStack, x, y, width * textureStartindex, 0, width, height, 128, 32 );
		RenderSystem.setShaderTexture( 0, icon_textures );
		blit( matrixStack, x + 3, y + 3, iconIndex << 4, 0, 16, 16, 64, 16 );
	}
	
	@Override
	public void onPress() {
		
		onPress.accept( selected );
	}
	
	public void setSelected( boolean _selected ) {
		
		selected = _selected;
	}
	
	@Override
	public void updateNarration( @Nonnull NarrationElementOutput output ) {
	
	}
}
