package de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.rainbow;

import de.geheimagentnr1.manyideas_core.elements.blocks.BlockItemInterface;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import net.minecraft.block.CarpetBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;


public class RainbowCarpet extends CarpetBlock implements BlockItemInterface {
	
	
	public static final String registry_name = "rainbow_carpet";
	
	public RainbowCarpet() {
		
		super(
			DyeColor.WHITE,
			Properties.of( Material.CLOTH_DECORATION ).strength( 0.1F ).sound( SoundType.WOOL )
		);
		setRegistryName( registry_name );
	}
	
	@Override
	public Item getBlockItem( Item.Properties properties ) {
		
		return createBlockItem( ModBlocks.RAINBOW_CARPET, properties, registry_name );
	}
}
