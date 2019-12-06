package de.geheimagentnr1.manyideascore.elements.block_state_properties;

import net.minecraft.util.IStringSerializable;

import java.util.Locale;


public enum Color implements IStringSerializable {
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
	
	
	@Override
	public String getName() {
		
		return name().toLowerCase( Locale.ENGLISH );
	}
}
