package de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.rainbow;

import de.geheimagentnr1.minecraft_forge_api.elements.blocks.BlockItemInterface;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.NotNull;


public class RainbowCarpet extends CarpetBlock implements BlockItemInterface {
	
	
	@NotNull
	public static final String registry_name = "rainbow_carpet";
	
	public RainbowCarpet() {
		
		super( BlockBehaviour.Properties.of().mapColor( MapColor.SNOW ).strength( 0.1F ).sound( SoundType.WOOL ) );
	}
}
