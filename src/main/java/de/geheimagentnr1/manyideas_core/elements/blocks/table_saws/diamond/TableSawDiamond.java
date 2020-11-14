package de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.diamond;

import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.TableSaw;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.Item;
import net.minecraft.util.IWorldPosCallable;


public class TableSawDiamond extends TableSaw {
	
	
	public static final String registry_name = "table_saw_diamond";
	
	public TableSawDiamond() {
		
		super( registry_name );
	}
	
	@Override
	protected Container getContainer( int windowID, PlayerInventory playerInventory,
		IWorldPosCallable worldPosCallable ) {
		
		return new TableSawDiamondContainer( windowID, playerInventory, worldPosCallable );
	}
	
	@Override
	public Item getBlockItem( Item.Properties properties ) {
		
		return createBlockItem( ModBlocks.TABLE_SAW_DIAMOND, properties, registry_name );
	}
}
