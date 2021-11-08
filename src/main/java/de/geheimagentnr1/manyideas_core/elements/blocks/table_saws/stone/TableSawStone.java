package de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.stone;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.TableSaw;
import de.geheimagentnr1.manyideas_core.util.TranslationKeyHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.Item;


public class TableSawStone extends TableSaw {
	
	
	public static final String registry_name = "table_saw_stone";
	
	private static final Component CONTAINER_TITLE = TranslationKeyHelper.generateContainerTranslationText(
		ManyIdeasCore.MODID,
		registry_name
	);
	
	public TableSawStone() {
		
		super( registry_name );
	}
	
	protected AbstractContainerMenu getMenu(
		int menuId,
		Inventory inventory,
		ContainerLevelAccess containerLevelAccess ) {
		
		return new TableSawStoneMenu( menuId, inventory, containerLevelAccess );
	}
	
	@Override
	protected Component getContainerName() {
		
		return CONTAINER_TITLE;
	}
	
	@Override
	public Item getBlockItem( Item.Properties _properties ) {
		
		return createBlockItem( ModBlocks.TABLE_SAW_STONE, _properties, registry_name );
	}
}
