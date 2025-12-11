package de.geheimagentnr1.manyideas_core.core.elements.commands;

import de.geheimagentnr1.manyideas_core.core.events.ModEventHandlerInterface;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public abstract class CommandsRegisterFactory implements ModEventHandlerInterface {
	
	
	@NotNull
	public abstract List<CommandInterface> commands();
	
	protected void doFMLCommonSetupEvent( @NotNull FMLCommonSetupEvent event ) {
		
		NeoForge.EVENT_BUS.addListener( this::handleRegisterCommandsEvent );
	}
	
	public void handleRegisterCommandsEvent( @NotNull RegisterCommandsEvent event ) {
		
		for( CommandInterface command : commands() ) {
			event.getDispatcher().register( command.build() );
		}
	}
}
