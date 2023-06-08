package de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.rainbow;

import de.geheimagentnr1.manyideas_core.elements.blocks.BlockItemInterface;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.GlazedTerracottaBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;


public class RainbowTerracottaGlazed extends GlazedTerracottaBlock implements BlockItemInterface {
	
	
	public static final String registry_name = "rainbow_terracotta_glazed";
	
	public RainbowTerracottaGlazed() {
		
		super( BlockBehaviour.Properties.of()
			.mapColor( DyeColor.WHITE )
			.strength( 1.4F )
			.requiresCorrectToolForDrops()
			.sound( SoundType.STONE ) );
	}
	
	@Override
	public Item getBlockItem( Item.Properties _properties ) {
		
		return createBlockItem( ModBlocks.RAINBOW_TERRACOTTA_GLAZED, _properties, registry_name );
	}
}
