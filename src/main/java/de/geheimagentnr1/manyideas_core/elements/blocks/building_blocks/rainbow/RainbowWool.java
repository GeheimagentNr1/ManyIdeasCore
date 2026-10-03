package de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.rainbow;

import de.geheimagentnr1.manyideas_core.core.elements.blocks.BlockItemInterface;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.NotNull;


public class RainbowWool extends Block implements BlockItemInterface {
	
	
	@NotNull
	public static final String registry_name = "rainbow_wool";
	
	public RainbowWool() {

		this( createProperties() );
	}

	public RainbowWool( @NotNull BlockBehaviour.Properties properties ) {

		super( properties );
	}

	@NotNull
	public static BlockBehaviour.Properties createProperties() {

		return BlockBehaviour.Properties.of().mapColor( MapColor.SNOW ).strength( 0.8F ).sound( SoundType.WOOL );
	}
}
