package de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.planks;

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


public class PlanksColored extends DyeBlock {
	
	
	public static final String registry_name = "planks_colored";
	
	public PlanksColored() {
		
		super(
			AbstractBlock.Properties.of( Material.WOOD ).strength( 2.0F, 3.0F ).sound( SoundType.WOOD ),
			registry_name
		);
	}
	
	@Nullable
	@Override
	public BlockState getStateForPlacement( @Nonnull BlockItemUseContext context ) {
		
		BlockState place_state = Objects.requireNonNull( super.getStateForPlacement( context ) );
		return place_state.setValue( BlockStateProperties.AXIS, context.getClickedFace().getAxis() );
	}
	
	@Override
	protected void createBlockStateDefinition( @Nonnull StateContainer.Builder<Block, BlockState> builder ) {
		
		super.createBlockStateDefinition( builder );
		builder.add( BlockStateProperties.AXIS );
	}
	
	@Override
	public Item getBlockItem( Item.Properties _properties ) {
		
		return new DyeBlockItem( ModBlocks.PLANKS_COLORED, _properties, registry_name );
	}
}
