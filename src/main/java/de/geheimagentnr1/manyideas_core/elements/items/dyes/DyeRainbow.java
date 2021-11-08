package de.geheimagentnr1.manyideas_core.elements.items.dyes;

import de.geheimagentnr1.manyideas_core.elements.items.CoreBaseItem;
import net.minecraft.world.item.Item;


public class DyeRainbow extends CoreBaseItem {
	
	
	public static final String registry_name = "dye_rainbow";
	
	public DyeRainbow() {
		
		super( new Item.Properties(), registry_name );
	}
}
