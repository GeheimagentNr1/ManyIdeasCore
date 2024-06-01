package de.geheimagentnr1.manyideas_core.special.json;

import com.google.gson.*;
import com.mojang.serialization.JsonOps;
import lombok.extern.log4j.Log4j2;
import net.minecraft.ResourceLocationException;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.RegistryOps;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;


@Log4j2
public class JSONUtil {
	
	
	@NotNull
	public static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
	
	@NotNull
	public static ItemStack readItemStackFromJson(
		@NotNull RegistryAccess.Frozen registryAccess,
		@NotNull JsonObject jsonObject ) {
		
		try {
			RegistryOps<JsonElement> registryops = registryAccess.createSerializationContext( JsonOps.INSTANCE );
			ItemStack stack = ItemStack.CODEC.parse( registryops, jsonObject ).getOrThrow( JsonParseException::new );
			stack.setCount( 1 );
			return stack;
		} catch( ResourceLocationException | JsonSyntaxException exception ) {
			log.error( "Failed to load ItemStack", exception );
			return ItemStack.EMPTY;
		}
	}
}
