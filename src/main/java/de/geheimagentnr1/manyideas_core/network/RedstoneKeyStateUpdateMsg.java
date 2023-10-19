package de.geheimagentnr1.manyideas_core.network;

import de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.screen.RedstoneKeyContainer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.event.network.CustomPayloadEvent;
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;


public class RedstoneKeyStateUpdateMsg {
	
	
	private final int stateIndex;
	
	public RedstoneKeyStateUpdateMsg( int _stateIndex ) {
		
		stateIndex = _stateIndex;
	}
	
	//package-private
	@NotNull
	static RedstoneKeyStateUpdateMsg decode( @NotNull FriendlyByteBuf buffer ) {
		
		return new RedstoneKeyStateUpdateMsg( buffer.readInt() );
	}
	
	//package-private
	void encode( @NotNull FriendlyByteBuf buffer ) {
		
		buffer.writeInt( stateIndex );
	}
	
	public static void sendToServer( int _stateIndex ) {
		
		Network.getInstance().getChannel().send(
			new RedstoneKeyStateUpdateMsg( _stateIndex ),
			PacketDistributor.SERVER.noArg()
		);
	}
	
	//package-private
	void handle( @NotNull CustomPayloadEvent.Context context ) {
		
		Optional.ofNullable( context.getSender() ).ifPresent( player -> {
			if( player.containerMenu instanceof RedstoneKeyContainer menu ) {
				menu.setBlockStateValue( player.level(), stateIndex, player );
			}
		} );
		context.setPacketHandled( true );
	}
}
