package de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.woods;

import de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.AxisBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;


public abstract class Wood extends AxisBlock {
	
	
	protected Wood( String registry_name ) {
		
		super(
			Block.Properties.create( Material.WOOD )
				.hardnessAndResistance( 2.0F )
				.harvestTool( ToolType.AXE )
				.sound( SoundType.WOOD ),
			registry_name
		);
	}
}
