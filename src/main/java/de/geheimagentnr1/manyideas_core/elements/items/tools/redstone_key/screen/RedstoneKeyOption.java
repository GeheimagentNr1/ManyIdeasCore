package de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import de.geheimagentnr1.manyideas_core.network.RedstoneKeyStateUpdateMsg;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FocusableGui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.IRenderable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class RedstoneKeyOption extends FocusableGui implements IRenderable {
	
	
	private final RedstoneKeyScreen parent;
	
	private final int x;
	
	private final int y;
	
	private final ResourceLocation icons;
	
	private final int stateIndex;
	
	private final String title;
	
	private final String description;
	
	private ToggleButton button;
	
	public RedstoneKeyOption(
		RedstoneKeyScreen _parent,
		int _x,
		int _y,
		ResourceLocation _icons,
		int _stateIndex,
		String _title,
		String _description ) {
		
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
	public void render( MatrixStack matrixStack, int p_230430_2_, int p_230430_3_, float p_230430_4_ ) {
		
		FontRenderer font = Minecraft.getInstance().font;
		font.draw( matrixStack, title, x + 30, y + 2, Objects.requireNonNull( TextFormatting.DARK_GRAY.getColor() ) );
		font.draw(
			matrixStack,
			description,
			x + 30,
			y + 12,
			Objects.requireNonNull( TextFormatting.WHITE.getColor() )
		);
	}
	
	public void resetSelected() {
		
		button.setSelected( false );
	}
	
	@Nonnull
	@Override
	public List<? extends IGuiEventListener> children() {
		
		return Collections.singletonList( button );
	}
	
	public ToggleButton getButton() {
		
		return button;
	}
	
	public void setSelected() {
		
		button.setSelected( true );
	}
}
