package de.geheimagentnr1.manyideas_core.elements.block_state_properties;

import net.minecraft.state.EnumProperty;


public class ModBlockStateProperties {
	
	
	public static final EnumProperty<CanBeOpenedByBlockState> OPENED_BY = EnumProperty.create( "can_be_opened_by",
		CanBeOpenedByBlockState.class );
	
	public static final EnumProperty<Color> COLOR = EnumProperty.create( "color", Color.class );
}
