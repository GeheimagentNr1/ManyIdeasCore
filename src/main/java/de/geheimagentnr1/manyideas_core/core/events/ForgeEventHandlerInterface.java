package de.geheimagentnr1.manyideas_core.core.events;

import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.client.event.RenderPlayerEvent;
import org.jetbrains.annotations.NotNull;


public interface ForgeEventHandlerInterface {
	
	
	default void handleClientPlayerLoggingInEvent( @NotNull ClientPlayerNetworkEvent.LoggingIn event ) {
	
	}
	
	default void handlePreRenderPlayerEvent( @NotNull RenderPlayerEvent.Pre event ) {
	
	}
}
