package de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.woods;

import de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.AxisBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;


public abstract class Wood extends AxisBlock {
	
	
	protected Wood( String registry_name ) {
		
		super(
			Properties.of( Material.WOOD ).strength( 2.0F ).sound( SoundType.WOOD ),
			registry_name
		);
	}
}
