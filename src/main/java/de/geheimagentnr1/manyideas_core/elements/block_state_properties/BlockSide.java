package de.geheimagentnr1.manyideas_core.elements.block_state_properties;

import net.minecraft.util.StringRepresentable;

import javax.annotation.Nonnull;
import java.util.Locale;


public enum BlockSide implements StringRepresentable {
	SINGLE,
	LEFT,
	MIDDLE,
	RIGHT;
	
	@Nonnull
	@Override
	public String getSerializedName() {
		
		return name().toLowerCase( Locale.ENGLISH );
	}
}
