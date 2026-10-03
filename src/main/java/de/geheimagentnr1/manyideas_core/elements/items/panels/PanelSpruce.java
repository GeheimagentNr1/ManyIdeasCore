package de.geheimagentnr1.manyideas_core.elements.items.panels;

import de.geheimagentnr1.manyideas_core.core.registry.RegistryHelper;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;


public class PanelSpruce extends Item {
	
	
	@NotNull
	public static final String registry_name = "panel_spruce";
	
	public PanelSpruce() {
		
		super( RegistryHelper.itemProperties() );
	}
}
