package de.geheimagentnr1.manyideascore.elements.blocks.building_blocks.woods;

import de.geheimagentnr1.manyideascore.elements.blocks.template_blocks.AxisBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;


public abstract class Wood extends AxisBlock {
	
	
	protected Wood() {
		
		super( Block.Properties.create( Material.WOOD ).hardnessAndResistance( 2.0F ).sound( SoundType.WOOD ) );
	}
}
