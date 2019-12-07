package de.geheimagentnr1.manyideas_core.elements.block_state_properties;

import net.minecraft.state.EnumProperty;


public class ModBlockStateProperties {
	
	
	public final static EnumProperty<CanBeOpenedByBlockState> OPENED_BY = EnumProperty.create( "can_be_opened_by",
		CanBeOpenedByBlockState.class );
	
	public final static EnumProperty<Color> COLOR = EnumProperty.create( "color", Color.class );
}
