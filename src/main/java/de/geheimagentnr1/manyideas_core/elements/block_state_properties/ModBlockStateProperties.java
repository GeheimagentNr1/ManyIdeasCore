package de.geheimagentnr1.manyideas_core.elements.block_state_properties;

import net.minecraft.world.level.block.state.properties.EnumProperty;


public class ModBlockStateProperties {
	
	
	@SuppressWarnings( "unused" )
	public static final EnumProperty<BlockSide> BLOCK_SIDE = EnumProperty.create( "side", BlockSide.class );
	
	public static final EnumProperty<Color> COLOR = EnumProperty.create( "color", Color.class );
	
	public static final EnumProperty<EveryDirectionFacing> EVERY_DIRECTION_FACING = EnumProperty.create(
		"facing",
		EveryDirectionFacing.class
	);
	
	public static final EnumProperty<OpenedBy> OPENED_BY = EnumProperty.create( "opened_by", OpenedBy.class );
}
