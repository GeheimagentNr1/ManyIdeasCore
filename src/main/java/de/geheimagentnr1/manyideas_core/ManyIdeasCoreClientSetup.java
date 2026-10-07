package de.geheimagentnr1.manyideas_core;

import de.geheimagentnr1.manyideas_core.elements.recipes.ClientRecipes;
import de.geheimagentnr1.manyideas_core.special.decoration_renderer.PlayerDecorationManager;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.common.NeoForge;


@EventBusSubscriber( modid = ManyIdeasCore.MODID, value = Dist.CLIENT )
public class ManyIdeasCoreClientSetup {

	private static final PlayerDecorationManager MANAGER = new PlayerDecorationManager();

	static {
		NeoForge.EVENT_BUS.addListener( MANAGER::handlePreRenderPlayerEvent );
		NeoForge.EVENT_BUS.addListener( MANAGER::handleClientPlayerLoggingInEvent );
		//Before JEI starts with the received recipes
		NeoForge.EVENT_BUS.addListener( EventPriority.HIGHEST, ClientRecipes::handleRecipesReceivedEvent );
	}

	@SubscribeEvent
	public static void onFMLClientSetup( FMLClientSetupEvent event ) {

		MANAGER.handleFMLClientSetupEvent( event );
	}
}
