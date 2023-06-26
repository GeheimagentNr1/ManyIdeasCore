package de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.rainbow;

import de.geheimagentnr1.minecraft_forge_api.elements.blocks.BlockItemInterface;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.NotNull;


public class RainbowTerracotta extends Block implements BlockItemInterface {
	
	
	@NotNull
	public static final String registry_name = "rainbow_terracotta";
	
	public RainbowTerracotta() {
		
		super( BlockBehaviour.Properties.of()
			.mapColor( MapColor.TERRACOTTA_WHITE )
			.strength( 1.25F, 4.2F )
			.requiresCorrectToolForDrops()
			.sound( SoundType.STONE ) );
	}
}
