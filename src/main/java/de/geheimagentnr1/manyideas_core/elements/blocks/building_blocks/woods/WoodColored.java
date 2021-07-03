package de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.woods;

import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.dyed.DyeBlock;
import de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.dyed.DyeBlockItem;
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
			Block.Properties.create( Material.WOOD ).hardnessAndResistance( 2.0F ).sound( SoundType.WOOD ),
			registry_name
		);
	}
	
	@Nullable
	@Override
	public BlockState getStateForPlacement( @Nonnull BlockItemUseContext context ) {
		
		BlockState place_state = Objects.requireNonNull( super.getStateForPlacement( context ) );
		return place_state.with( BlockStateProperties.AXIS, context.getFace().getAxis() );
	}
	
	@Override
	protected void fillStateContainer( @Nonnull StateContainer.Builder<Block, BlockState> builder ) {
		
		super.fillStateContainer( builder );
		builder.add( BlockStateProperties.AXIS );
	}
	
	@Override
	public Item getBlockItem( Item.Properties properties ) {
		
		return new DyeBlockItem( ModBlocks.WOOD_COLORED, properties, registry_name );
	}
}
