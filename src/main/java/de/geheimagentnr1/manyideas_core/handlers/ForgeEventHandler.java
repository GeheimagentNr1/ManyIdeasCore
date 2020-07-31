package de.geheimagentnr1.manyideas_core.handlers;

import de.geheimagentnr1.manyideas_core.elements.commands.ElementCountCommand;
import de.geheimagentnr1.manyideas_core.elements.commands.givedb.GiveDBCommand;
import de.geheimagentnr1.manyideas_core.special.decoration_renderer.PlayerDecorationManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;


@SuppressWarnings( "unused" )
@Mod.EventBusSubscriber( bus = Mod.EventBusSubscriber.Bus.FORGE )
public class ForgeEventHandler {
	
	
	@SubscribeEvent
	public static void handlerServerStartEvent( FMLServerStartingEvent event ) {
		
		ElementCountCommand.register( event.getCommandDispatcher() );
		GiveDBCommand.register( event.getCommandDispatcher() );
	}
	
	@OnlyIn( Dist.CLIENT )
	@SubscribeEvent
	public static void handlePreRenderPlayerEvent( RenderPlayerEvent.Pre event ) {
		
		if( event.getPlayer() != null ) {
			PlayerDecorationManager.renderForPlayer( event.getPlayer(), event.getPartialRenderTick() );
		}
	}
}
