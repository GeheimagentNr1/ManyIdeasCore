package de.geheimagentnr1.manyideas_core.network;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.minecraft_forge_api.network.AbstractNetwork;
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
		
		getChannel().messageBuilder( RedstoneKeyStateUpdateMsg.class )
			.encoder( RedstoneKeyStateUpdateMsg::encode )
			.decoder( RedstoneKeyStateUpdateMsg::decode )
			.consumerNetworkThread( RedstoneKeyStateUpdateMsg::handle )
			.add();
	}
}
