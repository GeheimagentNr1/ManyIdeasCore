package de.geheimagentnr1.manyideas_core.elements.commands;

import de.geheimagentnr1.manyideas_core.elements.commands.givedb.GiveDBCommand;
import de.geheimagentnr1.minecraft_forge_api.elements.commands.CommandInterface;
import de.geheimagentnr1.minecraft_forge_api.elements.commands.CommandsRegisterFactory;
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
}
