package de.geheimagentnr1.manyideas_core.elements.block_state_properties;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;


public enum EveryDirectionFacing implements StringRepresentable {
	
	NORTH_UP,
	NORTH_EAST,
	NORTH_DOWN,
	NORTH_WEST,
	
	EAST_UP,
	EAST_NORTH,
	EAST_DOWN,
	EAST_SOUTH,
	
	SOUTH_UP,
	SOUTH_WEST,
	SOUTH_DOWN,
	SOUTH_EAST,
	
	WEST_UP,
	WEST_SOUTH,
	WEST_DOWN,
	WEST_NORTH,
	
	UP_NORTH,
	UP_EAST,
	UP_SOUTH,
	UP_WEST,
	
	DOWN_NORTH,
	DOWN_EAST,
	DOWN_SOUTH,
	DOWN_WEST;
	
	@NotNull
	@Override
	public String getSerializedName() {
		
		return name().toLowerCase( Locale.ENGLISH );
	}
}
