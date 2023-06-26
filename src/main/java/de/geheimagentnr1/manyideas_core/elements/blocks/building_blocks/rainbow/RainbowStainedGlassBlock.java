package de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.rainbow;

import de.geheimagentnr1.minecraft_forge_api.elements.blocks.BlockItemInterface;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StainedGlassBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.NotNull;


public class RainbowStainedGlassBlock extends StainedGlassBlock implements BlockItemInterface {
	
	
	@NotNull
	public static final String registry_name = "rainbow_stained_glass_block";
	
	public RainbowStainedGlassBlock() {
		
		super(
			DyeColor.WHITE,
			BlockBehaviour.Properties.of()
				.mapColor( DyeColor.WHITE )
				.strength( 0.3F )
				.noOcclusion()
				.isValidSpawn( ( state, level, pos, entityType ) -> false )
				.isRedstoneConductor( ( state, level, pos ) -> false )
				.isSuffocating( ( state, level, pos ) -> false )
				.isViewBlocking( ( state, level, pos ) -> false )
				.sound( SoundType.GLASS )
		);
	}
}
