package de.geheimagentnr1.manyideas_core.handlers;

import de.geheimagentnr1.manyideas_core.elements.commands.ElementCountCommand;
import de.geheimagentnr1.manyideas_core.elements.commands.givedb.GiveDBCommand;
import de.geheimagentnr1.manyideas_core.special.decoration_renderer.PlayerDecorationManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@SuppressWarnings( "unused" )
@Mod.EventBusSubscriber( bus = Mod.EventBusSubscriber.Bus.FORGE )
public class ForgeEventHandler {
	
	
	@SubscribeEvent
	public static void handleRegisterCommandsEvent( RegisterCommandsEvent event ) {
		
		ElementCountCommand.register( event.getDispatcher() );
		GiveDBCommand.register( event.getDispatcher() );
	}
	
	@OnlyIn( Dist.CLIENT )
	@SubscribeEvent
	public static void handlePreRenderPlayerEvent( RenderPlayerEvent.Pre event ) {
		
		if( event.getEntity() != null ) {
			PlayerDecorationManager.renderForPlayer(
				event.getEntity(),
				event.getPackedLight(),
				event.getPoseStack(),
				event.getMultiBufferSource()
			);
		}
	}
}
