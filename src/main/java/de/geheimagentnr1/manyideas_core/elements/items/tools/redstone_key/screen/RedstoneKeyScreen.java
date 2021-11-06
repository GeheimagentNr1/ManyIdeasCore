package de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.models.Option;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class RedstoneKeyScreen extends ContainerScreen<RedstoneKeyContainer> {
	
	
	private static final ResourceLocation REDSTONE_KEY_GUI_TEXTURE = new ResourceLocation(
		ManyIdeasCore.MODID,
		"textures/gui/redstone_key/redstone_key_gui.png"
	);
	
	private final List<RedstoneKeyOption> optionsGui = new ArrayList<>();
	
	public RedstoneKeyScreen( RedstoneKeyContainer _menu, PlayerInventory _inventory, ITextComponent _title ) {
		
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
			addButton( optionGui.getButton() );
			children.add( optionGui );
		}
		optionsGui.get( menu.getSelectedIndex() ).setSelected();
	}
	
	@Override
	public void render( @Nonnull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks ) {
		
		super.render( matrixStack, mouseX, mouseY, partialTicks );
		optionsGui.forEach( optionGui -> optionGui.render( matrixStack, mouseX, mouseY, partialTicks ) );
	}
	
	@Override
	protected void renderLabels( @Nonnull MatrixStack matrixStack, int mouseX, int mouseY ) {
		
		int titleStartX = width / 2 - leftPos - font.width( title.getString() ) / 2;
		font.draw( matrixStack, title.getString(), titleStartX, 5, 4210752 );
	}
	
	@Override
	protected void renderBg( @Nonnull MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY ) {
		
		Objects.requireNonNull( minecraft );
		//noinspection deprecation
		RenderSystem.color4f( 1.0F, 1.0F, 1.0F, 1.0F );
		minecraft.getTextureManager().bind( REDSTONE_KEY_GUI_TEXTURE );
		blit( matrixStack, leftPos, ( height - imageHeight ) / 2, 0, 0, imageWidth, imageHeight );
	}
	
	public void resetSelected() {
		
		optionsGui.forEach( RedstoneKeyOption::resetSelected );
	}
	
	public void setSelected( int selectedIndex ) {
		
		menu.setSelectedIndex( selectedIndex );
	}
}
