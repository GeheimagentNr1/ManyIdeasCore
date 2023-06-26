package de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.woods.logs_stripped_smooth;

import de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.woods.Wood;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.NotNull;


public class LogStrippedSmoothSpruce extends Wood {
	
	
	@NotNull
	public static final String registry_name = "log_stripped_smooth_spruce";
	
	public LogStrippedSmoothSpruce() {
		
		super( BlockBehaviour.Properties.of().mapColor( MapColor.PODZOL ) );
	}
}