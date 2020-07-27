package de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.iron;

import de.geheimagentnr1.manyideas_core.elements.blocks.BlockItemInterface;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.TableSaw;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.Item;
import net.minecraft.util.IWorldPosCallable;


public class TableSawIron extends TableSaw implements BlockItemInterface {
	
	
	public static final String registry_name = "table_saw_iron";
	
	public TableSawIron() {
		
		super( registry_name );
	}
	
	@Override
	protected Container getContainer( int windowID, PlayerInventory playerInventory,
		IWorldPosCallable worldPosCallable ) {
		
		return new TableSawIronContainer( windowID, playerInventory, worldPosCallable );
	}
	
	@Override
	public Item getBlockItem( Item.Properties properties ) {
		
		return createBlockItem( ModBlocks.TABLE_SAW_IRON, properties, registry_name );
	}
}
