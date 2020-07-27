package de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.rainbow;

import de.geheimagentnr1.manyideas_core.elements.blocks.BlockItemInterface;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;


public class RainbowWool extends Block implements BlockItemInterface {
	
	
	public static final String registry_name = "rainbow_wool";
	
	public RainbowWool() {
		
		super( Block.Properties.create( Material.WOOL ).hardnessAndResistance( 0.8F ).sound( SoundType.CLOTH ) );
		setRegistryName( registry_name );
	}
	
	@Override
	public Item getBlockItem( Item.Properties properties ) {
		
		return createBlockItem( ModBlocks.RAINBOW_WOOL, properties, registry_name );
	}
}
