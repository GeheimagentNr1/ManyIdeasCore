package de.geheimagentnr1.manyideas_core.core.util;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import org.jetbrains.annotations.NotNull;


public class TranslationKeyHelper {
	
	
	@NotNull
	public static MutableComponent generateMessageTranslationTextComponent(
		@NotNull String modId,
		@NotNull String name ) {
		
		return Component.translatable( generateMessageTranslationKey( modId, name ) );
	}
	
	@NotNull
	public static String generateMessageTranslationKey( @NotNull String modId, @NotNull String name ) {
		
		return "message." + modId + "." + name;
	}
	
	@NotNull
	public static String generateContainerTranslationKey( @NotNull String modId, @NotNull String name ) {
		
		return "container." + modId + "." + name;
	}
	
	@NotNull
	public static MutableComponent generateContainerTranslationTextComponent(
		@NotNull String modId,
		@NotNull String name ) {
		
		return Component.translatable( generateContainerTranslationKey( modId, name ) );
	}
	
	@NotNull
	public static MutableComponent generateContainerTranslationText(
		@NotNull String modId,
		@NotNull String name ) {
		
		return generateContainerTranslationTextComponent( modId, name );
	}
}
