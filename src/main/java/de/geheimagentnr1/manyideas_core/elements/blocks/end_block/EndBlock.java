package de.geheimagentnr1.manyideas_core.elements.blocks.end_block;

import de.geheimagentnr1.manyideas_core.elements.blocks.BlockItemInterface;
import de.geheimagentnr1.manyideas_core.elements.blocks.BlockRenderTypeInterface;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;


public class EndBlock extends BaseEntityBlock implements BlockItemInterface, BlockRenderTypeInterface {
	
	
	public static final String registry_name = "end_block";
	
	public EndBlock() {
		
		super( BlockBehaviour.Properties.of( Material.STONE )
			.strength( 50.0F, 1200.0F )
			.requiresCorrectToolForDrops()
			.noOcclusion()
			.isViewBlocking( ( state, level, pos ) -> false )
			.sound( SoundType.GLASS ) );
		setRegistryName( registry_name );
	}
	
	@Override
	public RenderType getRenderType() {
		
		return RenderType.cutout();
	}
	
	@Nonnull
	@Override
	public RenderShape getRenderShape( @Nonnull BlockState state ) {
		
		return RenderShape.ENTITYBLOCK_ANIMATED;
	}
	
	@Nullable
	@Override
	public BlockEntity newBlockEntity( @Nonnull BlockPos pos, @Nonnull BlockState state ) {
		
		return new EndBlockEntity( pos, state );
	}
	
	@Override
	public Item getBlockItem( Item.Properties _properties ) {
		
		return createBlockItem( ModBlocks.END_BLOCK, _properties, registry_name );
	}
}
