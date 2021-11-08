package de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.rainbow;

import de.geheimagentnr1.manyideas_core.elements.blocks.BlockItemInterface;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;


public class RainbowCarpet extends CarpetBlock implements BlockItemInterface {
	
	
	public static final String registry_name = "rainbow_carpet";
	
	public RainbowCarpet() {
		
		super( BlockBehaviour.Properties.of( Material.CLOTH_DECORATION ).strength( 0.1F ).sound( SoundType.WOOL ) );
		setRegistryName( registry_name );
	}
	
	@Override
	public Item getBlockItem( Item.Properties _properties ) {
		
		return createBlockItem( ModBlocks.RAINBOW_CARPET, _properties, registry_name );
	}
}
