package de.geheimagentnr1.manyideas_core.elements.block_state_properties;

import net.minecraft.util.StringRepresentable;

import javax.annotation.Nonnull;
import java.util.Locale;


public enum Color implements StringRepresentable {
	BLACK,
	BLUE,
	BROWN,
	CYAN,
	GRAY,
	GREEN,
	LIGHT_BLUE,
	LIGHT_GRAY,
	LIME,
	MAGENTA,
	ORANGE,
	PINK,
	PURPLE,
	RAINBOW,
	RED,
	WHITE,
	YELLOW;
	
	
	@Nonnull
	@Override
	public String getSerializedName() {
		
		return name().toLowerCase( Locale.ENGLISH );
	}
}
