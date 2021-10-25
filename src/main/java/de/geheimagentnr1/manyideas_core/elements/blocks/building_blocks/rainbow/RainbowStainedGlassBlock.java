package de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.rainbow;

import de.geheimagentnr1.manyideas_core.elements.blocks.BlockItemInterface;
import de.geheimagentnr1.manyideas_core.elements.blocks.BlockRenderTypeInterface;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.StainedGlassBlock;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;


public class RainbowStainedGlassBlock extends StainedGlassBlock implements BlockItemInterface,
	BlockRenderTypeInterface {
	
	
	public static final String registry_name = "rainbow_stained_glass_block";
	
	public RainbowStainedGlassBlock() {
		
		super(
			DyeColor.WHITE,
			Properties.of( Material.GLASS )
				.strength( 0.3F )
				.noOcclusion()
				.isValidSpawn( ( p_test_1_, p_test_2_, p_test_3_, p_test_4_ ) -> false )
				.isRedstoneConductor( ( p_test_1_, p_test_2_, p_test_3_ ) -> false )
				.isSuffocating( ( p_test_1_, p_test_2_, p_test_3_ ) -> false )
				.isViewBlocking( ( p_test_1_, p_test_2_, p_test_3_ ) -> false )
				.sound( SoundType.GLASS )
		);
		setRegistryName( registry_name );
	}
	
	@Override
	public RenderType getRenderType() {
		
		return RenderType.translucent();
	}
	
	@Override
	public Item getBlockItem( Item.Properties properties ) {
		
		return createBlockItem( ModBlocks.RAINBOW_STAINED_GLASS_BLOCK, properties, registry_name );
	}
}
