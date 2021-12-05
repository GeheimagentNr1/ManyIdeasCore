package de.geheimagentnr1.manyideas_core.network;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;


public class Network {
	
	
	private static final String PROTOCOL_VERSION = "1";
	
	//package-private
	static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
		new ResourceLocation( ManyIdeasCore.MODID, "main" ),
		() -> PROTOCOL_VERSION,
		PROTOCOL_VERSION::equals,
		PROTOCOL_VERSION::equals
	);
	
	public static void registerPackets() {
		
		CHANNEL.registerMessage(
			0,
			RedstoneKeyStateUpdateMsg.class,
			RedstoneKeyStateUpdateMsg::encode,
			RedstoneKeyStateUpdateMsg::decode,
			RedstoneKeyStateUpdateMsg::handle
		);
	}
}
