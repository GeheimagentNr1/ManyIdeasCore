package de.geheimagentnr1.manyideas_core.elements.items.plates;

import de.geheimagentnr1.manyideas_core.core.registry.RegistryHelper;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;


public class PlateIron extends Item {
	
	
	@NotNull
	public static final String registry_name = "plate_iron";
	
	public PlateIron() {
		
		super( RegistryHelper.itemProperties() );
	}
}
