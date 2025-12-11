package de.geheimagentnr1.manyideas_core.elements.commands;

import de.geheimagentnr1.manyideas_core.elements.commands.givedb.GiveDBCommand;
import de.geheimagentnr1.manyideas_core.core.elements.commands.CommandInterface;
import de.geheimagentnr1.manyideas_core.core.elements.commands.CommandsRegisterFactory;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class ModCommandsRegisterFactory extends CommandsRegisterFactory {
	
	
	@NotNull
	@Override
	public List<CommandInterface> commands() {
		
		return List.of(
			new GiveDBCommand(),
			new ElementCountCommand()
		);
	}
	
	@SubscribeEvent
	public void handleFMLCommonSetupEvent( @NotNull FMLCommonSetupEvent event ) {
		
		doFMLCommonSetupEvent( event );
	}
}
