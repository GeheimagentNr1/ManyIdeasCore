package de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.interfaces;

import de.geheimagentnr1.manyideas_core.util.TranslationKeyHelper;
import net.minecraft.util.StringRepresentable;

import java.util.Locale;


@SuppressWarnings( { "InterfaceWithOnlyOneDirectInheritor", "unused" } )
public interface RedstoneKeyableState extends StringRepresentable {
	
	
	//public
	String getTitle();
	
	//public
	String getDescription();
	
	//public
	default String buildTitle( String modId ) {
		
		return TranslationKeyHelper.generateMessageTranslationKey(
			modId,
			getSerializedName().toLowerCase( Locale.ROOT ) + ".title"
		);
	}
	
	//public
	default String buildDescription( String modId ) {
		
		return TranslationKeyHelper.generateMessageTranslationKey(
			modId,
			getSerializedName().toLowerCase( Locale.ROOT ) + ".description"
		);
	}
}
