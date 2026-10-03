package de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.rainbow;

import de.geheimagentnr1.manyideas_core.core.elements.blocks.BlockItemInterface;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.GlazedTerracottaBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.NotNull;


public class RainbowTerracottaGlazed extends GlazedTerracottaBlock implements BlockItemInterface {
	
	
	@NotNull
	public static final String registry_name = "rainbow_terracotta_glazed";
	
	public RainbowTerracottaGlazed() {

		this( createProperties() );
	}

	public RainbowTerracottaGlazed( @NotNull BlockBehaviour.Properties properties ) {

		super( properties );
	}

	@NotNull
	public static BlockBehaviour.Properties createProperties() {

		return BlockBehaviour.Properties.of()
			.mapColor( DyeColor.WHITE )
			.strength( 1.4F )
			.requiresCorrectToolForDrops()
			.sound( SoundType.STONE );
	}
}
