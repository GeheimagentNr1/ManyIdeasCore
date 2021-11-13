package de.geheimagentnr1.manyideas_core.elements.blocks.end_block;

import de.geheimagentnr1.manyideas_core.elements.blocks.BlockItemInterface;
import de.geheimagentnr1.manyideas_core.elements.blocks.BlockRenderTypeInterface;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;


public class EndBlock extends Block implements BlockItemInterface, BlockRenderTypeInterface {
	
	
	public static final String registry_name = "end_block";
	
	public EndBlock() {
		
		super( AbstractBlock.Properties.of( Material.STONE )
			.strength( 50.0F, 1200.0F )
			.requiresCorrectToolForDrops()
			.harvestTool( ToolType.PICKAXE )
			.harvestLevel( 3 )
			.noOcclusion()
			.isViewBlocking( ( state, level, pos ) -> false )
			.sound( SoundType.GLASS ) );
		setRegistryName( registry_name );
	}
	
	@Override
	public RenderType getRenderType() {
		
		return RenderType.cutout();
	}
	
	@SuppressWarnings( "deprecation" )
	@Nonnull
	@Override
	public BlockRenderType getRenderShape( @Nonnull BlockState state ) {
		
		return BlockRenderType.ENTITYBLOCK_ANIMATED;
	}
	
	@Override
	public boolean hasTileEntity( BlockState state ) {
		
		return true;
	}
	
	@Nullable
	@Override
	public TileEntity createTileEntity( BlockState state, IBlockReader world ) {
		
		return new EndBlockTile();
	}
	
	@Override
	public Item getBlockItem( Item.Properties _properties ) {
		
		return createBlockItem( ModBlocks.END_BLOCK, _properties, registry_name );
	}
}
