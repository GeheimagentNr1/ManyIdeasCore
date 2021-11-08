package de.geheimagentnr1.manyideas_core.special.json;

import com.google.gson.*;
import net.minecraft.ResourceLocationException;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.crafting.CraftingHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class JSONUtil {
	
	
	private static final Logger LOGGER = LogManager.getLogger();
	
	public static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
	
	public static String getStringFromJson( JsonObject jsonObject, String key ) {
		
		if( jsonObject.has( key ) ) {
			JsonElement element = jsonObject.get( key );
			if( element.isJsonPrimitive() ) {
				return element.getAsJsonPrimitive().getAsString();
			}
		}
		return null;
	}
	
	public static JsonObject getJsonObjectFromJson( JsonObject jsonObject, String key ) {
		
		if( jsonObject.has( key ) ) {
			JsonElement element = jsonObject.get( key );
			if( element.isJsonObject() ) {
				return element.getAsJsonObject();
			}
		}
		return null;
	}
	
	public static ItemStack readItemStackFromJson( JsonObject jsonObject ) {
		
		try {
			ItemStack stack = CraftingHelper.getItemStack( jsonObject, true );
			stack.setCount( 1 );
			return stack;
		} catch( ResourceLocationException | JsonSyntaxException exception ) {
			LOGGER.error( "Failed to load ItemStack", exception );
			return ItemStack.EMPTY;
		}
	}
}
