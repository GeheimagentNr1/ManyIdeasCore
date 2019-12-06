package de.geheimagentnr1.manyideascore.elements.items;

import net.minecraft.item.Item;


@SuppressWarnings( { "AbstractClassExtendsConcreteClass", "WeakerAccess" } )
public abstract class BaseItem extends Item {
	
	
	protected BaseItem( Item.Properties item_properties, String registry_name ) {
		
		super( item_properties );
		setRegistryName( registry_name );
	}
}
