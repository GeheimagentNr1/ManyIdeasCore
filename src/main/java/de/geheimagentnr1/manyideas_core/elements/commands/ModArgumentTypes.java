package de.geheimagentnr1.manyideas_core.elements.commands;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.commands.givedb.ColorArgument;
import de.geheimagentnr1.manyideas_core.elements.commands.givedb.DyeItemArgument;
import net.minecraft.commands.synchronization.ArgumentTypes;
import net.minecraft.commands.synchronization.EmptyArgumentSerializer;


public class ModArgumentTypes {
	
	
	public static void registerArgumentTypes() {
		
		ArgumentTypes.register(
			ManyIdeasCore.MODID + ":" + ColorArgument.registry_name,
			ColorArgument.class,
			new EmptyArgumentSerializer<>( ColorArgument::color )
		);
		ArgumentTypes.register(
			ManyIdeasCore.MODID + ":" + DyeItemArgument.registry_name,
			DyeItemArgument.class,
			new EmptyArgumentSerializer<>( DyeItemArgument::dyeItem )
		);
	}
}
