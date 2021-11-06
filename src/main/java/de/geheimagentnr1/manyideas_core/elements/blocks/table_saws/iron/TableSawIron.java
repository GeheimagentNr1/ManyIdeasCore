package de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.iron;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.TableSaw;
import de.geheimagentnr1.manyideas_core.util.TranslationKeyHelper;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.Item;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.text.ITextComponent;


public class TableSawIron extends TableSaw {
	
	
	public static final String registry_name = "table_saw_iron";
	
	private static final ITextComponent CONTAINER_TITLE = TranslationKeyHelper.generateContainerTranslationText(
		ManyIdeasCore.MODID,
		registry_name
	);
	
	public TableSawIron() {
		
		super( registry_name );
	}
	
	@Override
	protected Container getContainer(
		int menuId,
		PlayerInventory playerInventory,
		IWorldPosCallable worldPosCallable ) {
		
		return new TableSawIronContainer( menuId, playerInventory, worldPosCallable );
	}
	
	@Override
	protected ITextComponent getContainerName() {
		
		return CONTAINER_TITLE;
	}
	
	@Override
	public Item getBlockItem( Item.Properties _properties ) {
		
		return createBlockItem( ModBlocks.TABLE_SAW_IRON, _properties, registry_name );
	}
}
