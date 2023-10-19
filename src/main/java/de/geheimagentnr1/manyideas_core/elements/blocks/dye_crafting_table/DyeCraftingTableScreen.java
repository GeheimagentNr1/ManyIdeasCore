package de.geheimagentnr1.manyideas_core.elements.blocks.dye_crafting_table;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;


@OnlyIn( Dist.CLIENT )
public class DyeCraftingTableScreen extends AbstractContainerScreen<DyeCraftingTableMenu> {
	
	
	@NotNull
	private static final ResourceLocation CRAFTING_TABLE_GUI_TEXTURES = new ResourceLocation(
		"minecraft",
		"textures/gui/container/crafting_table.png"
	);
	
	public DyeCraftingTableScreen(
		@NotNull DyeCraftingTableMenu _menu,
		@NotNull Inventory _inventory,
		@NotNull Component _title ) {
		
		super( _menu, _inventory, _title );
		initData();
	}
	
	private void initData() {
		
		titleLabelX = 38;
	}
	
	@Override
	public void render( @NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick ) {
		
		renderBackground( guiGraphics, mouseX, mouseY, partialTick );
		super.render( guiGraphics, mouseX, mouseY, partialTick );
	}
	
	@Override
	protected void renderBg( @NotNull GuiGraphics guiGraphics, float partialTick, int x, int y ) {
		
		guiGraphics.blit(
			CRAFTING_TABLE_GUI_TEXTURES,
			leftPos,
			( height - imageHeight ) / 2,
			0,
			0,
			imageWidth,
			imageHeight
		);
	}
}
