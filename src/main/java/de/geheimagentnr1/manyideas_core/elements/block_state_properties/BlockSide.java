package de.geheimagentnr1.manyideas_core.elements.block_state_properties;

import net.minecraft.util.IStringSerializable;

import javax.annotation.Nonnull;
import java.util.Locale;


public enum BlockSide implements IStringSerializable {
	SINGLE,
	LEFT,
	MIDDLE,
	RIGHT;
	
	@Nonnull
	@Override
	public String getName() {
		
		return name().toLowerCase( Locale.ENGLISH );
	}
}
