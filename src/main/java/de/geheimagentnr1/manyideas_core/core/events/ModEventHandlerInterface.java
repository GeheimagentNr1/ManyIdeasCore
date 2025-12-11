package de.geheimagentnr1.manyideas_core.core.events;

import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import org.jetbrains.annotations.NotNull;


public interface ModEventHandlerInterface {
	
	
	default void handleFMLClientSetupEvent( @NotNull FMLClientSetupEvent event ) {
	
	}
}
