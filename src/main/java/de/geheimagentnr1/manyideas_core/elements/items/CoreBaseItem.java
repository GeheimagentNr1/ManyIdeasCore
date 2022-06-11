package de.geheimagentnr1.manyideas_core.elements.items;

import de.geheimagentnr1.manyideas_core.elements.item_groups.ModItemGroups;
import net.minecraft.world.item.Item;


public abstract class CoreBaseItem extends BaseItem {
	
	
	protected CoreBaseItem( Item.Properties item_properties, String registry_name ) {
		
		super( item_properties.tab( ModItemGroups.MANYIDEAS_CORE_ITEM_GROUP ) );
	}
}
