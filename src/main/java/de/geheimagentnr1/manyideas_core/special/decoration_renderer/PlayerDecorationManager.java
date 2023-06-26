package de.geheimagentnr1.manyideas_core.special.decoration_renderer;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.blaze3d.vertex.PoseStack;
import de.geheimagentnr1.manyideas_core.special.json.JSONUtil;
import de.geheimagentnr1.minecraft_forge_api.events.ForgeEventHandlerInterface;
import de.geheimagentnr1.minecraft_forge_api.events.ModEventHandlerInterface;
import lombok.extern.log4j.Log4j2;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.TreeMap;


@Log4j2
public class PlayerDecorationManager implements ModEventHandlerInterface, ForgeEventHandlerInterface {
	
	
	@NotNull
	private final TreeMap<String, PlayerDecorationRenderer> DECORATION_LIST = new TreeMap<>();
	
	@NotNull
	private JsonArray loadDecorationJson() {
		
		try {
			URL url = new URL( "https://raw.githubusercontent.com/GeheimagentNr1/Online_Mod_Data/master/" +
				"player_decorations.json" );
			return JSONUtil.GSON.fromJson(
				new InputStreamReader( url.openStream(), StandardCharsets.UTF_8 ),
				JsonArray.class
			);
		} catch( IOException exception ) {
			log.error( "Failed to load Player Decorations", exception );
			return new JsonArray();
		}
	}
	
	private void readDecoration( @NotNull JsonObject jsonDecoration ) {
		
		log.info( "Loading Player Decorations" );
		String name = JSONUtil.getStringFromJson( jsonDecoration, "name" );
		log.info( "Read Player Decorations" );
		if( name != null ) {
			JsonObject jsonItems = JSONUtil.getJsonObjectFromJson( jsonDecoration, "items" );
			if( jsonItems != null ) {
				ItemStack stack = ItemStack.EMPTY;
				JsonObject jsonModdedItem = JSONUtil.getJsonObjectFromJson( jsonItems, "modded" );
				if( jsonModdedItem != null ) {
					stack = JSONUtil.readItemStackFromJson( jsonModdedItem );
				}
				if( stack.isEmpty() ) {
					JsonObject jsonVanillaItem = JSONUtil.getJsonObjectFromJson( jsonItems, "vanilla" );
					if( jsonVanillaItem != null ) {
						stack = JSONUtil.readItemStackFromJson( jsonVanillaItem );
					}
				}
				if( !stack.isEmpty() ) {
					DECORATION_LIST.put( name, new PlayerDecorationRenderer( stack ) );
				}
			}
		}
		log.info( "Initialized Player Decorations" );
	}
	
	private void initDecorationList() {
		
		JsonArray jsonDecorations = loadDecorationJson();
		for( JsonElement element : jsonDecorations ) {
			if( element.isJsonObject() ) {
				readDecoration( element.getAsJsonObject() );
			}
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
		
		initDecorationList();
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
