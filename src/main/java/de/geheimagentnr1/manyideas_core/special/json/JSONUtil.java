package de.geheimagentnr1.manyideas_core.special.json;

import com.google.gson.*;
import lombok.extern.log4j.Log4j2;
import net.minecraft.ResourceLocationException;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.crafting.CraftingHelper;
import org.jetbrains.annotations.NotNull;


@Log4j2
public class JSONUtil {
	
	
	@NotNull
	public static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
	
	public static String getStringFromJson( @NotNull JsonObject jsonObject, @NotNull String key ) {
		
		if( jsonObject.has( key ) ) {
			JsonElement element = jsonObject.get( key );
			if( element.isJsonPrimitive() ) {
				return element.getAsJsonPrimitive().getAsString();
			}
		}
		return null;
	}
	
	public static JsonObject getJsonObjectFromJson( @NotNull JsonObject jsonObject, @NotNull String key ) {
		
		if( jsonObject.has( key ) ) {
			JsonElement element = jsonObject.get( key );
			if( element.isJsonObject() ) {
				return element.getAsJsonObject();
			}
		}
		return null;
	}
	
	@NotNull
	public static ItemStack readItemStackFromJson( @NotNull JsonObject jsonObject ) {
		
		try {
			ItemStack stack = CraftingHelper.getItemStack( jsonObject, true );
			stack.setCount( 1 );
			return stack;
		} catch( ResourceLocationException | JsonSyntaxException exception ) {
			log.error( "Failed to load ItemStack", exception );
			return ItemStack.EMPTY;
		}
	}
}
