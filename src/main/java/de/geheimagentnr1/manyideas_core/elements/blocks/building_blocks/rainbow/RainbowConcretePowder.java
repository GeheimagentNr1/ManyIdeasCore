package de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.rainbow;

import de.geheimagentnr1.minecraft_forge_api.elements.blocks.BlockItemInterface;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ConcretePowderBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.NotNull;


public class RainbowConcretePowder extends ConcretePowderBlock implements BlockItemInterface {
	
	
	@NotNull
	public static final String registry_name = "rainbow_concrete_powder";
	
	//package-private
	static Block CONCRETE_BLOCK;
	
	public RainbowConcretePowder() {
		
		super(
			CONCRETE_BLOCK,
			BlockBehaviour.Properties.of()
				.mapColor( DyeColor.WHITE )
				.strength( 0.5F )
				.sound( SoundType.SAND )
		);
	}
}
