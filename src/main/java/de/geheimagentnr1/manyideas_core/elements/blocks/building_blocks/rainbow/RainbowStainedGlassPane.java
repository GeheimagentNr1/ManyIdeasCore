package de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.rainbow;

import de.geheimagentnr1.minecraft_forge_api.elements.blocks.BlockItemInterface;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StainedGlassPaneBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.NotNull;


public class RainbowStainedGlassPane extends StainedGlassPaneBlock implements BlockItemInterface {
	
	
	@NotNull
	public static final String registry_name = "rainbow_stained_glass_pane";
	
	public RainbowStainedGlassPane() {
		
		super(
			DyeColor.WHITE,
			BlockBehaviour.Properties.of().mapColor( DyeColor.WHITE ).strength( 0.3F ).sound( SoundType.GLASS )
		);
	}
}
