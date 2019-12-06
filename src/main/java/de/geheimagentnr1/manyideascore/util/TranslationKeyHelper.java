package de.geheimagentnr1.manyideascore.util;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.text.TranslationTextComponent;


@SuppressWarnings( "WeakerAccess" )
public class TranslationKeyHelper {
	
	
	public static String generateContainerTranslationKey( String modid, String registry_name ) {
		
		return Util.makeTranslationKey( "container", new ResourceLocation( modid, registry_name ) );
	}
	
	public static TranslationTextComponent generateContainerTranslationText( String modid, String registry_name ) {
		
		return translationKeyToTranslationText( generateContainerTranslationKey( modid, registry_name ) );
	}
	
	public static TranslationTextComponent translationKeyToTranslationText( String translation_key ) {
		
		return new TranslationTextComponent( translation_key );
	}
}
