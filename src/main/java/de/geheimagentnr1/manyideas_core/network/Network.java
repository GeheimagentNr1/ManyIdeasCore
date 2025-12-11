package de.geheimagentnr1.manyideas_core.network;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.core.network.AbstractNetwork;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import org.jetbrains.annotations.NotNull;


public class Network extends AbstractNetwork {
	
	
	@NotNull
	private static final Network INSTANCE = new Network();
	
	@NotNull
	public static Network getInstance() {
		
		return INSTANCE;
	}
	
	@NotNull
	@Override
	protected String getModId() {
		
		return ManyIdeasCore.MODID;
	}
	
	@NotNull
	@Override
	protected String getNetworkName() {
		
		return "main";
	}
	
	@Override
	public void registerPackets() {
		
		getRegistrar().playToServer(
			RedstoneKeyStateUpdateMsg.TYPE,
			RedstoneKeyStateUpdateMsg.STREAM_CODEC,
			RedstoneKeyStateUpdateMsg::handle
		);
	}
	
	@SubscribeEvent
	public void handleRegisterPayloadHandlersEvent( @NotNull RegisterPayloadHandlersEvent event ) {
		
		doRegisterPayloadHandlersEvent( event );
	}
}
