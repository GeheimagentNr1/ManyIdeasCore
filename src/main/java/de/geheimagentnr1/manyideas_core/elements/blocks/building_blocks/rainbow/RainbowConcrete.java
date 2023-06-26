package de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.rainbow;

import de.geheimagentnr1.minecraft_forge_api.elements.blocks.BlockItemInterface;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.NotNull;


public class RainbowConcrete extends Block implements BlockItemInterface {
	
	
	@NotNull
	public static final String registry_name = "rainbow_concrete";
	
	public RainbowConcrete() {
		
		super(
			BlockBehaviour.Properties.of()
				.mapColor( DyeColor.WHITE )
				.strength( 1.8F )
				.requiresCorrectToolForDrops()
				.sound( SoundType.STONE )
		);
		initConcretePowder();
	}
	
	private void initConcretePowder() {
		
		RainbowConcretePowder.CONCRETE_BLOCK = this;
	}
}
