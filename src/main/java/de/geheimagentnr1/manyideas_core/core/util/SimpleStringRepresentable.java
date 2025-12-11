package de.geheimagentnr1.manyideas_core.core.util;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;


public interface SimpleStringRepresentable extends StringRepresentable {
	
	
	@NotNull
	String name();
	
	@NotNull
	@Override
	default String getSerializedName() {
		
		return name().toLowerCase();
	}
	
	@NotNull
	static String buildDeserializedName( @NotNull String serializedName ) {
		
		return serializedName.toUpperCase();
	}
}
