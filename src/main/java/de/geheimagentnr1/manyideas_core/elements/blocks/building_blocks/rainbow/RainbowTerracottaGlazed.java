package de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.rainbow;

import de.geheimagentnr1.manyideas_core.elements.blocks.BlockItemInterface;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.GlazedTerracottaBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;


public class RainbowTerracottaGlazed extends GlazedTerracottaBlock implements BlockItemInterface {
	
	
	public static final String registry_name = "rainbow_terracotta_glazed";
	
	public RainbowTerracottaGlazed() {
		
		super( Block.Properties.create( Material.ROCK )
			.hardnessAndResistance( 1.4F )
			.harvestTool( ToolType.PICKAXE )
			.harvestLevel( 0 )
			.sound( SoundType.STONE ) );
		setRegistryName( registry_name );
	}
	
	@Override
	public Item getBlockItem( Item.Properties properties ) {
		
		return createBlockItem( ModBlocks.RAINBOW_TERRACOTTA_GLAZED, properties, registry_name );
	}
}
