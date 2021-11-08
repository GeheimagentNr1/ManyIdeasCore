package de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.woods;

import de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.AxisBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;


public abstract class Wood extends AxisBlock {
	
	
	protected Wood( String registry_name ) {
		
		super(
			BlockBehaviour.Properties.of( Material.WOOD ).strength( 2.0F ).sound( SoundType.WOOD ),
			registry_name
		);
	}
}
