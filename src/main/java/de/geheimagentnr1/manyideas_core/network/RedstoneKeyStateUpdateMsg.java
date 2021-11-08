package de.geheimagentnr1.manyideas_core.network;

import de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.screen.RedstoneKeyContainer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fmllegacy.network.NetworkEvent;
import net.minecraftforge.fmllegacy.network.PacketDistributor;

import java.util.Optional;
import java.util.function.Supplier;


public class RedstoneKeyStateUpdateMsg {
	
	
	private final int stateIndex;
	
	public RedstoneKeyStateUpdateMsg( int _stateIndex ) {
		
		stateIndex = _stateIndex;
	}
	
	//package-private
	static RedstoneKeyStateUpdateMsg decode( FriendlyByteBuf buffer ) {
		
		return new RedstoneKeyStateUpdateMsg( buffer.readInt() );
	}
	
	//package-private
	void encode( FriendlyByteBuf buffer ) {
		
		buffer.writeInt( stateIndex );
	}
	
	public static void sendToServer( int _stateIndex ) {
		
		Network.CHANNEL.send( PacketDistributor.SERVER.noArg(), new RedstoneKeyStateUpdateMsg( _stateIndex ) );
	}
	
	//package-private
	void handle( Supplier<NetworkEvent.Context> context ) {
		
		Optional.ofNullable( context.get().getSender() ).ifPresent( player -> {
			if( player.containerMenu instanceof RedstoneKeyContainer menu ) {
				menu.setBlockStateValue( player.getLevel(), stateIndex, player );
			}
		} );
		context.get().setPacketHandled( true );
	}
}
