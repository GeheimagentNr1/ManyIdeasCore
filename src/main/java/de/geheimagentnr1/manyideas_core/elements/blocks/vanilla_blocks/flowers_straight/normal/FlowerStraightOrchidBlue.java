package de.geheimagentnr1.manyideas_core.elements.blocks.vanilla_blocks.flowers_straight.normal;

import de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.flowers_straight.Flower;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.NotNull;


public class FlowerStraightOrchidBlue extends Flower {
	
	
	@NotNull
	public static final String registry_name = "flower_straight_orchid_blue";
	
	public FlowerStraightOrchidBlue() {
		
		super(
			MobEffects.SATURATION,
			7,
			BlockBehaviour.Properties.of()
				.mapColor( MapColor.PLANT )
				.noCollission()
				.instabreak()
				.sound( SoundType.GRASS )
		);
	}
}
