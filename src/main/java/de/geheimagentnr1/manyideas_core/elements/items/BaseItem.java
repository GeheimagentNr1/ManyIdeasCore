package de.geheimagentnr1.manyideas_core.elements.items;

import net.minecraft.world.item.Item;


@SuppressWarnings( { "AbstractClassExtendsConcreteClass", "WeakerAccess" } )
public abstract class BaseItem extends Item {
	
	
	protected BaseItem( Item.Properties item_properties, String registry_name ) {
		
		super( item_properties );
		setRegistryName( registry_name );
	}
}
