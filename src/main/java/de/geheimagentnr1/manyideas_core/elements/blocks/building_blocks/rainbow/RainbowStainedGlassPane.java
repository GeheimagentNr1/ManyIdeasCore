package de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.rainbow;

import de.geheimagentnr1.manyideas_core.elements.blocks.BlockItemInterface;
import de.geheimagentnr1.manyideas_core.elements.blocks.BlockRenderTypeInterface;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StainedGlassPaneBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;


public class RainbowStainedGlassPane extends StainedGlassPaneBlock implements BlockItemInterface,
	BlockRenderTypeInterface {
	
	
	public static final String registry_name = "rainbow_stained_glass_pane";
	
	public RainbowStainedGlassPane() {
		
		super(
			DyeColor.WHITE,
			BlockBehaviour.Properties.of().mapColor( DyeColor.WHITE ).strength( 0.3F ).sound( SoundType.GLASS )
		);
	}
	
	@Override
	public RenderType getRenderType() {
		
		return RenderType.translucent();
	}
	
	@Override
	public Item getBlockItem( Item.Properties _properties ) {
		
		return createBlockItem( ModBlocks.RAINBOW_STAINED_GLASS_PANE, _properties, registry_name );
	}
}
