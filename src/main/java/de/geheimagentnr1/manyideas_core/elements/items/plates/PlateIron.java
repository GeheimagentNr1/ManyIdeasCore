package de.geheimagentnr1.manyideas_core.elements.items.plates;

import de.geheimagentnr1.manyideas_core.elements.items.CoreBaseItem;
import net.minecraft.world.item.Item;


public class PlateIron extends CoreBaseItem {
	
	
	public static final String registry_name = "plate_iron";
	
	public PlateIron() {
		
		super( new Item.Properties(), registry_name );
	}
}
