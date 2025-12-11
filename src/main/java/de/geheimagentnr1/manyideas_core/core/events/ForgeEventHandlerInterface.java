package de.geheimagentnr1.manyideas_core.core.events;

import net.neoforged.neoforge.client.event.RenderPlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;


public interface ForgeEventHandlerInterface {
	
	
	default void handlePlayerLoggedInEvent( @NotNull PlayerEvent.PlayerLoggedInEvent event ) {
	
	}
	
	default void handlePreRenderPlayerEvent( @NotNull RenderPlayerEvent.Pre event ) {
	
	}
}
