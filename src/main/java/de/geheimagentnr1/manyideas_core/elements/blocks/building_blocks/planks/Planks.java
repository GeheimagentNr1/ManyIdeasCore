package de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.planks;

import de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.AxisBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.NotNull;


public abstract class Planks extends AxisBlock {


	protected Planks() {

		super( createProperties() );
	}

	protected Planks( @NotNull BlockBehaviour.Properties properties ) {

		super( properties );
	}

	@NotNull
	public static BlockBehaviour.Properties createProperties() {

		return BlockBehaviour.Properties.of()
			.mapColor( MapColor.WOOD )
			.strength( 2.0F, 3.0F )
			.sound( SoundType.WOOD );
	}
}
