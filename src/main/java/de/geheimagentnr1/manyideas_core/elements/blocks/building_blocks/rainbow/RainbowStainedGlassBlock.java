package de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.rainbow;

import de.geheimagentnr1.manyideas_core.elements.blocks.BlockItemInterface;
import de.geheimagentnr1.manyideas_core.elements.blocks.BlockRenderTypeInterface;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StainedGlassBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;


public class RainbowStainedGlassBlock extends StainedGlassBlock implements BlockItemInterface,
	BlockRenderTypeInterface {
	
	
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
	
	@Override
	public RenderType getRenderType() {
		
		return RenderType.translucent();
	}
	
	@Override
	public Item getBlockItem( Item.Properties _properties ) {
		
		return createBlockItem( ModBlocks.RAINBOW_STAINED_GLASS_BLOCK, _properties, registry_name );
	}
}
