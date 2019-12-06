package de.geheimagentnr1.manyideascore.elements.commands;

import de.geheimagentnr1.manyideascore.ManyIdeasCore;
import de.geheimagentnr1.manyideascore.elements.commands.givedb.ColorArgument;
import de.geheimagentnr1.manyideascore.elements.commands.givedb.DyeItemArgument;
import net.minecraft.command.arguments.ArgumentSerializer;
import net.minecraft.command.arguments.ArgumentTypes;


public class ModArgumentTypes {
	
	public static void registerArgumentTypes() {
		
		ArgumentTypes.register( ManyIdeasCore.MODID + ":" + ColorArgument.registry_name,
			ColorArgument.class, new ArgumentSerializer<>( ColorArgument::color ) );
		ArgumentTypes.register( ManyIdeasCore.MODID + ":" + DyeItemArgument.registry_name,
			DyeItemArgument.class, new ArgumentSerializer<>( DyeItemArgument::dyeItem ) );
	}
}
