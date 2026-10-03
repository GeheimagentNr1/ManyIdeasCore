package de.geheimagentnr1.manyideas_core.network;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.screen.RedstoneKeyContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;


public record RedstoneKeyStateUpdateMsg( int stateIndex ) implements CustomPacketPayload {
	
	
	@NotNull
	public static final CustomPacketPayload.Type<RedstoneKeyStateUpdateMsg> TYPE = new CustomPacketPayload.Type<>(
		Identifier.fromNamespaceAndPath( ManyIdeasCore.MODID, "redstone_key_state_update" )
	);
	
	@NotNull
	public static final StreamCodec<RegistryFriendlyByteBuf, RedstoneKeyStateUpdateMsg> STREAM_CODEC =
		StreamCodec.composite(
			ByteBufCodecs.INT,
			RedstoneKeyStateUpdateMsg::stateIndex,
			RedstoneKeyStateUpdateMsg::new
		);
	
	public static void sendToServer( int stateIndex ) {
		
		//PacketDistributor.sendToServer was removed in NeoForge 21.7, this works on 1.21.6 - 1.21.8
		Objects.requireNonNull( Minecraft.getInstance().getConnection() )
			.send( new ServerboundCustomPayloadPacket( new RedstoneKeyStateUpdateMsg( stateIndex ) ) );
	}
	
	public void handle( @NotNull IPayloadContext context ) {
		
		context.enqueueWork( () -> {
			if( context.player() instanceof ServerPlayer player ) {
				if( player.containerMenu instanceof RedstoneKeyContainer menu ) {
					menu.setBlockStateValue( player.level(), stateIndex, player );
				}
			}
		} );
	}
	
	@NotNull
	@Override
	public Type<? extends CustomPacketPayload> type() {
		
		return TYPE;
	}
}
