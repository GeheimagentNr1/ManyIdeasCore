package de.geheimagentnr1.manyideas_core.elements.items.saws;

import de.geheimagentnr1.manyideas_core.core.registry.RegistryHelper;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;


public class SawIron extends Item {
	
	
	@NotNull
	public static final String registry_name = "saw_iron";
	
	public SawIron() {
		
		super( RegistryHelper.itemProperties() );
	}
}
