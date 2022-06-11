package de.geheimagentnr1.manyideas_core.util;


import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;


@SuppressWarnings( "WeakerAccess" )
public class TranslationKeyHelper {
	
	
	public static String generateContainerTranslationKey( String modid, String registry_name ) {
		
		return Util.makeDescriptionId( "container", new ResourceLocation( modid, registry_name ) );
	}
	
	public static Component generateContainerTranslationText( String modid, String registry_name ) {
		
		return translationKeyToTranslationText( generateContainerTranslationKey( modid, registry_name ) );
	}
	
	public static Component translationKeyToTranslationText( String translation_key ) {
		
		return Component.translatable( translation_key );
	}
	
	public static String generateMessageTranslationKey( String modid, String registry_name ) {
		
		return Util.makeDescriptionId( "message", new ResourceLocation( modid, registry_name ) );
	}
	
	public static Component generateMessageTranslationTextComponent( String modid, String registry_name ) {
		
		return Component.translatable( generateMessageTranslationKey( modid, registry_name ) );
	}
}
