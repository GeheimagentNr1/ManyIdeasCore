package de.geheimagentnr1.manyideas_core.special.decoration_renderer;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.mojang.blaze3d.vertex.PoseStack;
import de.geheimagentnr1.manyideas_core.special.decoration_renderer.models.PlayerDecoration;
import de.geheimagentnr1.manyideas_core.special.decoration_renderer.models.PlayerDecorationItems;
import de.geheimagentnr1.manyideas_core.special.json.JSONUtil;
import de.geheimagentnr1.minecraft_forge_api.events.ForgeEventHandlerInterface;
import de.geheimagentnr1.minecraft_forge_api.events.ModEventHandlerInterface;
import lombok.extern.log4j.Log4j2;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;


@Log4j2
public class PlayerDecorationManager implements ModEventHandlerInterface, ForgeEventHandlerInterface {
	
	
	@NotNull
	private final Set<PlayerDecoration> PLAYER_DECORATIONS = new TreeSet<>(
		Comparator.comparing( PlayerDecoration::getName )
	);
	
	@NotNull
	private final Map<String, PlayerDecorationRenderer> DECORATION_LIST = new TreeMap<>();
	
	@NotNull
	private void loadDecorationJson() {
		
		try {
			URL url = URI.create( "https://raw.githubusercontent.com/GeheimagentNr1/Online_Mod_Data/master/" +
				"player_decorations.json" ).toURL();
			PLAYER_DECORATIONS.addAll(
				JSONUtil.GSON.fromJson(
					new InputStreamReader( url.openStream(), StandardCharsets.UTF_8 ),
					new TypeToken<List<PlayerDecoration>>() {
					
					}
				)
			);
		} catch( IOException | JsonParseException exception ) {
			log.error( "Failed to load Player Decorations", exception );
		}
	}
	
	private void readDecoration(
		@NotNull RegistryAccess.Frozen registryAccess,
		@NotNull PlayerDecoration playerDecoration ) {
		
		log.info( "Loading Player Decorations" );
		String name = playerDecoration.getName();
		log.info( "Read Player Decorations" );
		if( name != null ) {
			PlayerDecorationItems items = playerDecoration.getItems();
			if( items != null ) {
				ItemStack stack = ItemStack.EMPTY;
				JsonObject jsonModdedItem = items.getModded();
				if( jsonModdedItem != null ) {
					stack = JSONUtil.readItemStackFromJson( registryAccess, jsonModdedItem );
				}
				if( stack.isEmpty() ) {
					JsonObject jsonVanillaItem = items.getVanilla();
					if( jsonVanillaItem != null ) {
						stack = JSONUtil.readItemStackFromJson( registryAccess, jsonVanillaItem );
					}
				}
				if( !stack.isEmpty() ) {
					DECORATION_LIST.put( name, new PlayerDecorationRenderer( stack ) );
				}
			}
		}
		log.info( "Initialized Player Decorations" );
	}
	
	private void initDecorationList( @NotNull RegistryAccess.Frozen registryAccess ) {
		
		for( PlayerDecoration playerDecoration : PLAYER_DECORATIONS ) {
			readDecoration( registryAccess, playerDecoration );
		}
	}
	
	private void renderForPlayer(
		@NotNull Player player,
		int light,
		@NotNull PoseStack poseStack,
		@NotNull MultiBufferSource buffer ) {
		
		PlayerDecorationRenderer playerDecorationRenderer = DECORATION_LIST.get(
			player.getName().getString() );
		
		if( playerDecorationRenderer != null ) {
			playerDecorationRenderer.renderItemStack( player, light, poseStack, buffer );
		}
	}
	
	@OnlyIn( Dist.CLIENT )
	@SubscribeEvent
	@Override
	public void handleFMLClientSetupEvent( @NotNull FMLClientSetupEvent event ) {
		
		loadDecorationJson();
	}
	
	@OnlyIn( Dist.CLIENT )
	@SubscribeEvent
	@Override
	public void handlePlayerLoggedInEvent( @NotNull PlayerEvent.PlayerLoggedInEvent event ) {
		
		ClientPacketListener connection = Minecraft.getInstance().getConnection();
		if( connection != null ) {
			initDecorationList( connection.registryAccess() );
		}
	}
	
	@OnlyIn( Dist.CLIENT )
	@SubscribeEvent
	@Override
	public void handlePreRenderPlayerEvent( @NotNull RenderPlayerEvent.Pre event ) {
		
		if( event.getEntity() != null ) {
			renderForPlayer(
				event.getEntity(),
				event.getPackedLight(),
				event.getPoseStack(),
				event.getMultiBufferSource()
			);
		}
	}
}
