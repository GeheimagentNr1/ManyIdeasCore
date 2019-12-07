package de.geheimagentnr1.manyideas_core.handlers;

import de.geheimagentnr1.manyideas_core.elements.commands.ElementCountCommand;
import de.geheimagentnr1.manyideas_core.elements.commands.givedb.GiveDBCommand;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;


@SuppressWarnings( "unused" )
@Mod.EventBusSubscriber( bus = Mod.EventBusSubscriber.Bus.FORGE )
public class ForgeRegistryEventHandler {
	
	
	@SubscribeEvent
	public static void handlerServerStartEvent( FMLServerStartingEvent event ) {
		
		ElementCountCommand.register( event.getCommandDispatcher() );
		GiveDBCommand.register( event.getCommandDispatcher() );
	}
}
