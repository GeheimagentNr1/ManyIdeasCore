package de.geheimagentnr1.manyideas_core.elements.blocks.vanilla_blocks.flowers_straight.tall;

import de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.flowers_straight.TallFlower;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.NotNull;


public class FlowerTallStraightSunflower extends TallFlower {
	
	
	@NotNull
	public static final String registry_name = "flower_tall_straight_sunflower";
	
	public FlowerTallStraightSunflower() {

		this( createProperties() );
	}

	public FlowerTallStraightSunflower( @NotNull BlockBehaviour.Properties properties ) {

		super( properties );
	}

	@NotNull
	public static BlockBehaviour.Properties createProperties() {

		return BlockBehaviour.Properties.of()
			.mapColor( MapColor.PLANT )
			.noCollission()
			.instabreak()
			.sound( SoundType.GRASS );
	}
}
