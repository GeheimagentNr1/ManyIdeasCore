package de.geheimagentnr1.manyideas_core.core.network;

import de.geheimagentnr1.manyideas_core.core.events.ModEventHandlerInterface;
import net.minecraft.resources.Identifier;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import org.jetbrains.annotations.NotNull;


public abstract class AbstractNetwork implements ModEventHandlerInterface {
	
	
	private PayloadRegistrar registrar;
	
	@NotNull
	protected abstract String getModId();
	
	@NotNull
	protected abstract String getNetworkName();
	
	public abstract void registerPackets();
	
	@NotNull
	protected PayloadRegistrar getRegistrar() {
		
		return registrar;
	}
	
	@NotNull
	protected Identifier createResourceLocation( @NotNull String path ) {
		
		return Identifier.fromNamespaceAndPath( getModId(), path );
	}
	
	protected void doRegisterPayloadHandlersEvent( @NotNull RegisterPayloadHandlersEvent event ) {
		
		registrar = event.registrar( getModId() );
		registerPackets();
	}
}
