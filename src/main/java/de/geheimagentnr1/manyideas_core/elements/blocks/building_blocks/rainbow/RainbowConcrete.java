package de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.rainbow;

import de.geheimagentnr1.manyideas_core.elements.blocks.BlockItemInterface;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;


public class RainbowConcrete extends Block implements BlockItemInterface {
	
	
	public static final String registry_name = "rainbow_concrete";
	
	public RainbowConcrete() {
		
		super( AbstractBlock.Properties.of( Material.STONE ).strength( 1.8F )
			.requiresCorrectToolForDrops()
			.harvestTool( ToolType.PICKAXE )
			.harvestLevel( 0 )
			.sound( SoundType.STONE ) );
		setRegistryName( registry_name );
		initConcretePowder();
	}
	
	private void initConcretePowder() {
		
		RainbowConcretePowder.CONCRETE_BLOCK = this;
	}
	
	@Override
	public Item getBlockItem( Item.Properties _properties ) {
		
		return createBlockItem( ModBlocks.RAINBOW_CONCRETE, _properties, registry_name );
	}
}
