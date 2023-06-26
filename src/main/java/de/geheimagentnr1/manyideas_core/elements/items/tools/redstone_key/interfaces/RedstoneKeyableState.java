package de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.interfaces;

import de.geheimagentnr1.minecraft_forge_api.util.SimpleStringRepresentable;
import de.geheimagentnr1.minecraft_forge_api.util.TranslationKeyHelper;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;


@SuppressWarnings( { "InterfaceWithOnlyOneDirectInheritor", "unused" } )
public interface RedstoneKeyableState extends SimpleStringRepresentable {
	
	
	//public
	@NotNull
	String getTitle();
	
	//public
	@NotNull
	String getDescription();
	
	//public
	@NotNull
	default String buildTitle( @NotNull String modId ) {
		
		return TranslationKeyHelper.generateMessageTranslationKey(
			modId,
			getSerializedName().toLowerCase( Locale.ROOT ) + ".title"
		);
	}
	
	//public
	@NotNull
	default String buildDescription( @NotNull String modId ) {
		
		return TranslationKeyHelper.generateMessageTranslationKey(
			modId,
			getSerializedName().toLowerCase( Locale.ROOT ) + ".description"
		);
	}
}
