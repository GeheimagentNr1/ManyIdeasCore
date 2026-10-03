package de.geheimagentnr1.manyideas_core.elements.blocks.dye_crafting_table;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;


@OnlyIn( Dist.CLIENT )
public class DyeCraftingTableScreen extends AbstractContainerScreen<DyeCraftingTableMenu> {
	
	
	@NotNull
	private static final Identifier CRAFTING_TABLE_GUI_TEXTURES = Identifier.withDefaultNamespace(
		"textures/gui/container/crafting_table.png"
	);
	
	public DyeCraftingTableScreen(
		@NotNull DyeCraftingTableMenu _menu,
		@NotNull Inventory _inventory,
		@NotNull Component _title ) {
		
		super( _menu, _inventory, _title );
	}
	
	@Override
	protected void init() {
		
		super.init();
		titleLabelX = 29;
	}
	
	@Override
	public void extractBackground( @NotNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick ) {
		
		super.extractBackground( graphics, mouseX, mouseY, partialTick );
		//Overload without RenderPipeline: the RenderPipeline class moved in 26.3
		int x = leftPos;
		int y = ( height - imageHeight ) / 2;
		graphics.blit(
			CRAFTING_TABLE_GUI_TEXTURES,
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
}
