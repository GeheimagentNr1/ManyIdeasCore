package de.geheimagentnr1.manyideas_core.elements.items;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import net.minecraft.item.Item;


public abstract class CoreBaseItem extends BaseItem {
	
	
	protected CoreBaseItem( Item.Properties item_properties, String registry_name ) {
		
		super( item_properties.group( ManyIdeasCore.setup.coreItemGroup ), registry_name );
	}
}
