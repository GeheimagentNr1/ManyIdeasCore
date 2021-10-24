package de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.rainbow;

import de.geheimagentnr1.manyideas_core.elements.blocks.BlockItemInterface;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;


public class RainbowConcrete extends Block implements BlockItemInterface {
	
	
	public static final String registry_name = "rainbow_concrete";
	
	public RainbowConcrete() {
		
		super( Properties.of( Material.STONE ).strength( 1.8F ).sound( SoundType.STONE ) );
		setRegistryName( registry_name );
		initConcretePowder();
	}
	
	private void initConcretePowder() {
		
		RainbowConcretePowder.CONCRETE_BLOCK = this;
	}
	
	@Override
	public Item getBlockItem( Item.Properties properties ) {
		
		return createBlockItem( ModBlocks.RAINBOW_CONCRETE, properties, registry_name );
	}
}
