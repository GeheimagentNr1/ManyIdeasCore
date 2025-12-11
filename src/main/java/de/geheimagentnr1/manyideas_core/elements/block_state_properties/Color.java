package de.geheimagentnr1.manyideas_core.elements.block_state_properties;

import com.mojang.serialization.Codec;
import de.geheimagentnr1.manyideas_core.core.util.SimpleStringRepresentable;


public enum Color implements SimpleStringRepresentable {
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
	
	public static final Codec<Color> CODEC = Codec.STRING.xmap(
		value -> valueOf( SimpleStringRepresentable.buildDeserializedName( value ) ),
		SimpleStringRepresentable::getSerializedName
	);
}
