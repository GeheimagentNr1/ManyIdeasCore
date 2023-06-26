package de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.woods.logs_stripped_smooth;

import de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.woods.Wood;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.NotNull;


public class LogStrippedSmoothAcacia extends Wood {
	
	
	@NotNull
	public static final String registry_name = "log_stripped_smooth_acacia";
	
	public LogStrippedSmoothAcacia() {
		
		super( BlockBehaviour.Properties.of().mapColor( MapColor.COLOR_ORANGE ) );
	}
}