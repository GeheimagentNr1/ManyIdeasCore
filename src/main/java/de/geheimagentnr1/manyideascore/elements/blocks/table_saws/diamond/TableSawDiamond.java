package de.geheimagentnr1.manyideascore.elements.blocks.table_saws.diamond;

import de.geheimagentnr1.manyideascore.elements.blocks.BlockItemInterface;
import de.geheimagentnr1.manyideascore.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideascore.elements.blocks.table_saws.TableSaw;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.IWorldPosCallable;


public class TableSawDiamond extends TableSaw implements BlockItemInterface {
	
	
	public final static String registry_name = "table_saw_diamond";
	
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
		
		return new BlockItem( ModBlocks.TABLE_SAW_DIAMOND, properties ).setRegistryName( registry_name );
	}
}
