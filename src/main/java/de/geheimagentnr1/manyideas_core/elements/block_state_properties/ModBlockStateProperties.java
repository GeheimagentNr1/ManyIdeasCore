package de.geheimagentnr1.manyideas_core.elements.block_state_properties;

import net.minecraft.state.EnumProperty;


public class ModBlockStateProperties {
	
	
	@SuppressWarnings( "unused" )
	public static final EnumProperty<BlockSide> BLOCK_SIDE = EnumProperty.create( "side", BlockSide.class );
	
	public static final EnumProperty<CanBeOpenedByBlockState> OPENED_BY = EnumProperty.create(
		"can_be_opened_by",
		CanBeOpenedByBlockState.class
	);
	
	public static final EnumProperty<Color> COLOR = EnumProperty.create( "color", Color.class );
}
