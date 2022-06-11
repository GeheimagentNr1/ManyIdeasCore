package de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.rainbow;

import de.geheimagentnr1.manyideas_core.elements.blocks.BlockItemInterface;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;


public class RainbowWool extends Block implements BlockItemInterface {
	
	
	public static final String registry_name = "rainbow_wool";
	
	public RainbowWool() {
		
		super( BlockBehaviour.Properties.of( Material.WOOL ).strength( 0.8F ).sound( SoundType.WOOL ) );
	}
	
	@Override
	public Item getBlockItem( Item.Properties _properties ) {
		
		return createBlockItem( ModBlocks.RAINBOW_WOOL, _properties, registry_name );
	}
}
