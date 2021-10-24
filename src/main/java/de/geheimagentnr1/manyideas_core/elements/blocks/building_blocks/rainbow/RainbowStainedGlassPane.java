package de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.rainbow;

import de.geheimagentnr1.manyideas_core.elements.blocks.BlockItemInterface;
import de.geheimagentnr1.manyideas_core.elements.blocks.BlockRenderTypeInterface;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.StainedGlassPaneBlock;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;


public class RainbowStainedGlassPane extends StainedGlassPaneBlock implements BlockItemInterface,
	BlockRenderTypeInterface {
	
	
	public static final String registry_name = "rainbow_stained_glass_pane";
	
	public RainbowStainedGlassPane() {
		
		super(
			DyeColor.WHITE,
			Properties.of( Material.GLASS ).strength( 0.3F ).sound( SoundType.GLASS )
		);
		setRegistryName( registry_name );
	}
	
	@Override
	public RenderType getRenderType() {
		
		return RenderType.translucent();
	}
	
	@Override
	public Item getBlockItem( Item.Properties properties ) {
		
		return createBlockItem( ModBlocks.RAINBOW_STAINED_GLASS_PANE, properties, registry_name );
	}
}
