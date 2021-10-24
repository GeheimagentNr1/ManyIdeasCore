package de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.rainbow;

import de.geheimagentnr1.manyideas_core.elements.blocks.BlockItemInterface;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;


public class RainbowTerracotta extends Block implements BlockItemInterface {
	
	
	public static final String registry_name = "rainbow_terracotta";
	
	public RainbowTerracotta() {
		
		super( Properties.of( Material.STONE ).strength( 1.25F, 4.2F ).sound( SoundType.STONE ) );
		setRegistryName( registry_name );
	}
	
	@Override
	public Item getBlockItem( Item.Properties properties ) {
		
		return createBlockItem( ModBlocks.RAINBOW_TERRACOTTA, properties, registry_name );
	}
}
