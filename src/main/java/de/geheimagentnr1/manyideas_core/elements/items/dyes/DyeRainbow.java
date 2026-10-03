package de.geheimagentnr1.manyideas_core.elements.items.dyes;

import de.geheimagentnr1.manyideas_core.core.registry.RegistryHelper;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;


public class DyeRainbow extends Item {
	
	
	@NotNull
	public static final String registry_name = "dye_rainbow";
	
	public DyeRainbow() {
		
		super( RegistryHelper.itemProperties() );
	}
}
