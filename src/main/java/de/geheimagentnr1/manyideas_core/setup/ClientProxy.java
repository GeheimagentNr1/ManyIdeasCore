package de.geheimagentnr1.manyideas_core.setup;

import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideas_core.elements.blocks.dye_crafting_table.DyeCraftingTableScreen;
import de.geheimagentnr1.manyideas_core.elements.blocks.end_block.EndBlockTile;
import de.geheimagentnr1.manyideas_core.elements.blocks.end_block.EndBlockTileRenderer;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.TableSawScreen;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.fml.client.registry.ClientRegistry;


public class ClientProxy implements IProxy {
	
	
	@Override
	public void init() {
		
		ScreenManager.registerFactory( ModBlocks.DYE_CRAFTING_TABLE_CONTAINER, DyeCraftingTableScreen::new );
		ScreenManager.registerFactory( ModBlocks.TABLE_SAW_STONE_CONTAINER, TableSawScreen::new );
		ScreenManager.registerFactory( ModBlocks.TABLE_SAW_IRON_CONTAINER, TableSawScreen::new );
		ScreenManager.registerFactory( ModBlocks.TABLE_SAW_DIAMOND_CONTAINER, TableSawScreen::new );
		
		ClientRegistry.bindTileEntitySpecialRenderer( EndBlockTile.class, new EndBlockTileRenderer() );
	}
}
