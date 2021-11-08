package de.geheimagentnr1.manyideas_core.elements.items.saws;

import de.geheimagentnr1.manyideas_core.elements.items.CoreBaseItem;
import net.minecraft.world.item.Item;


public class SawIron extends CoreBaseItem {
	
	
	public static final String registry_name = "saw_iron";
	
	public SawIron() {
		
		super( new Item.Properties(), registry_name );
	}
}
