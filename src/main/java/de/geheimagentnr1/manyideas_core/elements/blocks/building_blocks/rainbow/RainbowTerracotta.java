package de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.rainbow;

import de.geheimagentnr1.manyideas_core.elements.blocks.BlockItemInterface;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ToolType;


public class RainbowTerracotta extends Block implements BlockItemInterface {
	
	
	public static final String registry_name = "rainbow_terracotta";
	
	public RainbowTerracotta() {
		
		super( BlockBehaviour.Properties.of( Material.STONE ).strength( 1.25F, 4.2F )
			.requiresCorrectToolForDrops()
			.harvestTool( ToolType.PICKAXE )
			.harvestLevel( 0 )
			.sound( SoundType.STONE ) );
		setRegistryName( registry_name );
	}
	
	@Override
	public Item getBlockItem( Item.Properties _properties ) {
		
		return createBlockItem( ModBlocks.RAINBOW_TERRACOTTA, _properties, registry_name );
	}
}
