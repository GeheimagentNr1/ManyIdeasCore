package de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.woods;

import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.dyed.DyeBlock;
import de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.dyed.DyeBlockItem;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;


public class WoodColored extends DyeBlock {
	
	
	public static final String registry_name = "wood_colored";
	
	public WoodColored() {
		
		super(
			AbstractBlock.Properties.of( Material.WOOD ).strength( 2.0F ).sound( SoundType.WOOD ),
			registry_name
		);
	}
	
	@Nullable
	@Override
	public BlockState getStateForPlacement( @Nonnull BlockItemUseContext context ) {
		
		BlockState state = Objects.requireNonNull( super.getStateForPlacement( context ) );
		return state.setValue( BlockStateProperties.AXIS, context.getClickedFace().getAxis() );
	}
	
	@Override
	protected void createBlockStateDefinition( @Nonnull StateContainer.Builder<Block, BlockState> builder ) {
		
		super.createBlockStateDefinition( builder );
		builder.add( BlockStateProperties.AXIS );
	}
	
	@Override
	public Item getBlockItem( Item.Properties _properties ) {
		
		return new DyeBlockItem( ModBlocks.WOOD_COLORED, _properties, registry_name );
	}
}
