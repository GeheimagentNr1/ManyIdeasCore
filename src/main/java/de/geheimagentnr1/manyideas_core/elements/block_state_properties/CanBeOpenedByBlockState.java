package de.geheimagentnr1.manyideas_core.elements.block_state_properties;

import net.minecraft.util.IStringSerializable;

import javax.annotation.Nonnull;
import java.util.Locale;


public enum CanBeOpenedByBlockState implements IStringSerializable {
	NOTHING,
	HAND,
	REDSTONE,
	BOTH;
	
	@Nonnull
	@Override
	public String getName() {
		
		return name().toLowerCase( Locale.ENGLISH );
	}
}
