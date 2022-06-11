package de.geheimagentnr1.manyideas_core.elements.commands;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.commands.givedb.ColorArgument;
import de.geheimagentnr1.manyideas_core.elements.commands.givedb.DyeItemArgument;
import net.minecraft.commands.synchronization.ArgumentTypeInfos;
import net.minecraft.commands.synchronization.SingletonArgumentInfo;
import net.minecraft.core.Registry;


public class ModArgumentTypes {
	
	
	public static void registerArgumentTypes() {
		
/*		Registry.register(
			Registry.COMMAND_ARGUMENT_TYPE,
			ManyIdeasCore.MODID + ":" + ColorArgument.registry_name,
			ArgumentTypeInfos.registerByClass(
				ColorArgument.class,
				SingletonArgumentInfo.contextFree( ColorArgument::color )
			)
		);
		Registry.register(
			Registry.COMMAND_ARGUMENT_TYPE,
			ManyIdeasCore.MODID + ":" + DyeItemArgument.registry_name,
			ArgumentTypeInfos.registerByClass(
				DyeItemArgument.class,
				SingletonArgumentInfo.contextFree( DyeItemArgument::dyeItem )
			)
		);*/
	}
}
