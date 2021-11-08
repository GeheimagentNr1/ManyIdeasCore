package de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.models.Option;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class RedstoneKeyScreen extends AbstractContainerScreen<RedstoneKeyContainer> {
	
	
	private static final ResourceLocation REDSTONE_KEY_GUI_TEXTURE = new ResourceLocation(
		ManyIdeasCore.MODID,
		"textures/gui/redstone_key/redstone_key_gui.png"
	);
	
	private final List<RedstoneKeyOption> optionsGui = new ArrayList<>();
	
	public RedstoneKeyScreen( RedstoneKeyContainer _menu, Inventory _inventory, Component _title ) {
		
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
	public void render( @Nonnull PoseStack poseStack, int mouseX, int mouseY, float partialTicks ) {
		
		super.render( poseStack, mouseX, mouseY, partialTicks );
		optionsGui.forEach( optionGui -> optionGui.render( poseStack, mouseX, mouseY, partialTicks ) );
	}
	
	@Override
	protected void renderLabels( @Nonnull PoseStack poseStack, int mouseX, int mouseY ) {
		
		int titleStartX = width / 2 - leftPos - font.width( title.getString() ) / 2;
		font.draw( poseStack, title.getString(), titleStartX, 5, 4210752 );
	}
	
	@Override
	protected void renderBg( @Nonnull PoseStack p_97787_, float partialTicks, int mouseX, int mouseY ) {
		
		Objects.requireNonNull( minecraft );
		
		RenderSystem.setShader( GameRenderer::getPositionTexShader );
		RenderSystem.setShaderColor( 1.0F, 1.0F, 1.0F, 1.0F );
		RenderSystem.setShaderTexture( 0, REDSTONE_KEY_GUI_TEXTURE );
		blit( p_97787_, leftPos, ( height - imageHeight ) / 2, 0, 0, imageWidth, imageHeight );
	}
	
	public void resetSelected() {
		
		optionsGui.forEach( RedstoneKeyOption::resetSelected );
	}
	
	public void setSelected( int selectedIndex ) {
		
		menu.setSelectedIndex( selectedIndex );
	}
}
