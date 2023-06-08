package de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.planks;

import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.dyed.DyeBlock;
import de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.dyed.DyeBlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;


public class PlanksColored extends DyeBlock {
	
	
	public static final String registry_name = "planks_colored";
	
	public PlanksColored() {
		
		super(
			BlockBehaviour.Properties.of()
				.mapColor( MapColor.WOOD )
				.strength( 2.0F, 3.0F )
				.sound( SoundType.WOOD ),
			registry_name
		);
	}
	
	@Nullable
	@Override
	public BlockState getStateForPlacement( @Nonnull BlockPlaceContext context ) {
		
		BlockState place_state = Objects.requireNonNull( super.getStateForPlacement( context ) );
		return place_state.setValue( BlockStateProperties.AXIS, context.getClickedFace().getAxis() );
	}
	
	@Override
	protected void createBlockStateDefinition( @Nonnull StateDefinition.Builder<Block, BlockState> builder ) {
		
		super.createBlockStateDefinition( builder );
		builder.add( BlockStateProperties.AXIS );
	}
	
	@Override
	public Item getBlockItem( Item.Properties _properties ) {
		
		return new DyeBlockItem( ModBlocks.PLANKS_COLORED, _properties, registry_name );
	}
}
