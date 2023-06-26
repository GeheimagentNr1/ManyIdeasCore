package de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.woods;

import de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.AxisBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.NotNull;


public abstract class Wood extends AxisBlock {
	
	
	protected Wood( @NotNull BlockBehaviour.Properties _properties ) {
		
		super(
			_properties
				.strength( 2.0F )
				.sound( SoundType.WOOD )
		);
	}
}
