package de.geheimagentnr1.manyideas_core.elements.items.plates;

import de.geheimagentnr1.manyideas_core.core.registry.RegistryHelper;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;


public class PlateGold extends Item {
	
	
	@NotNull
	public static final String registry_name = "plate_gold";
	
	public PlateGold() {
		
		super( RegistryHelper.itemProperties() );
	}
}
