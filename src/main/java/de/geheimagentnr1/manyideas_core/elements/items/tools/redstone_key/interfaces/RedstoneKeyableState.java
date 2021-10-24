package de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.interfaces;

import de.geheimagentnr1.manyideas_core.util.TranslationKeyHelper;
import net.minecraft.util.IStringSerializable;

import java.util.Locale;


public interface RedstoneKeyableState extends IStringSerializable {
	
	
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
