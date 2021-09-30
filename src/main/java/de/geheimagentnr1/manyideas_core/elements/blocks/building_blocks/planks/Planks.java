package de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.planks;

import de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.AxisBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;


public abstract class Planks extends AxisBlock {
	
	
	protected Planks( String registry_name ) {
		
		super(
			Properties.create( Material.WOOD ).hardnessAndResistance( 2.0F, 3.0F ).sound( SoundType.WOOD ),
			registry_name
		);
	}
}
