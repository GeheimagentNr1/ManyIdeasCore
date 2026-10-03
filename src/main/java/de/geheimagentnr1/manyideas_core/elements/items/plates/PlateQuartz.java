package de.geheimagentnr1.manyideas_core.elements.items.plates;

import de.geheimagentnr1.manyideas_core.core.registry.RegistryHelper;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;


public class PlateQuartz extends Item {
	
	
	@NotNull
	public static final String registry_name = "plate_quartz";
	
	public PlateQuartz() {
		
		super( RegistryHelper.itemProperties() );
	}
}
