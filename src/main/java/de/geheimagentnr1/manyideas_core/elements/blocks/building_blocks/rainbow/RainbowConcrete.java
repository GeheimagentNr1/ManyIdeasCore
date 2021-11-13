package de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.rainbow;

import de.geheimagentnr1.manyideas_core.elements.blocks.BlockItemInterface;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;


public class RainbowConcrete extends Block implements BlockItemInterface {
	
	
	public static final String registry_name = "rainbow_concrete";
	
	public RainbowConcrete() {
		
		super( BlockBehaviour.Properties.of( Material.STONE ).strength( 1.8F )
			.requiresCorrectToolForDrops()
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
