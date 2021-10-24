package de.geheimagentnr1.manyideas_core.special.decoration_renderer;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.blaze3d.matrix.MatrixStack;
import de.geheimagentnr1.manyideas_core.special.json.JSONUtil;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.TreeMap;


public class PlayerDecorationManager {
	
	
	private static final Logger LOGGER = LogManager.getLogger();
	
	private static final TreeMap<String, PlayerDecorationRenderer> DECORATION_LIST = new TreeMap<>();
	
	private static JsonArray loadDecorationJson() {
		
		try {
			URL url = new URL( "https://raw.githubusercontent.com/GeheimagentNr1/Online_Mod_Data/master/" +
				"player_decorations.json" );
			return JSONUtil.GSON.fromJson(
				new InputStreamReader( url.openStream(), StandardCharsets.UTF_8 ),
				JsonArray.class
			);
		} catch( IOException exception ) {
			LOGGER.error( "Failed to load Player Decorations", exception );
			return new JsonArray();
		}
	}
	
	private static void readDecoration( JsonObject jsonDecoration ) {
		
		LOGGER.info( "Loading Player Decorations" );
		String name = JSONUtil.getStringFromJson( jsonDecoration, "name" );
		LOGGER.info( "Read Player Decorations" );
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
		LOGGER.info( "Initialized Player Decorations" );
	}
	
	public static void initDecorationList() {
		
		JsonArray jsonDecorations = loadDecorationJson();
		for( JsonElement element : jsonDecorations ) {
			if( element.isJsonObject() ) {
				readDecoration( element.getAsJsonObject() );
			}
		}
	}
	
	public static void renderForPlayer( PlayerEntity player, int light, MatrixStack matrixStack,
	                                    IRenderTypeBuffer buffer ) {
		
		PlayerDecorationRenderer playerDecorationRenderer = DECORATION_LIST.get(
			player.getName().getString() );
		
		if( playerDecorationRenderer != null ) {
			playerDecorationRenderer.renderItemStack( player, light, matrixStack, buffer );
		}
	}
}
