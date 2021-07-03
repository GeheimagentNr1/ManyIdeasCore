package de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.rainbow;

import de.geheimagentnr1.manyideas_core.elements.blocks.BlockItemInterface;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.StainedGlassBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;


public class RainbowStainedGlassBlock extends StainedGlassBlock implements BlockItemInterface {
	
	
	public static final String registry_name = "rainbow_stained_glass_block";
	
	public RainbowStainedGlassBlock() {
		
		super(
			DyeColor.WHITE,
			Block.Properties.create( Material.GLASS ).hardnessAndResistance( 0.3F ).sound( SoundType.GLASS )
		);
		setRegistryName( registry_name );
	}
	
	@Override
	public Item getBlockItem( Item.Properties properties ) {
		
		return createBlockItem( ModBlocks.RAINBOW_STAINED_GLASS_BLOCK, properties, registry_name );
	}
}
