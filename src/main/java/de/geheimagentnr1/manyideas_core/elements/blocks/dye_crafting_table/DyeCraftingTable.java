package de.geheimagentnr1.manyideas_core.elements.blocks.dye_crafting_table;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.blocks.BlockItemInterface;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideas_core.util.TranslationKeyHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;


public class DyeCraftingTable extends Block implements BlockItemInterface {
	
	
	public static final String registry_name = "dye_crafting_table";
	
	private static final Component CONTAINER_TITLE = TranslationKeyHelper.generateContainerTranslationText(
		ManyIdeasCore.MODID,
		registry_name
	);
	
	private static final VoxelShape SHAPE = Shapes.or(
		Block.box( 0.0, 14.0, 0.0, 16.0, 15.75, 16.0 ),
		Block.box( 0.0, 0.0, 0.0, 2.0, 14.0, 2.0 ),
		Block.box( 14.0, 0.0, 0.0, 16.0, 14.0, 2.0 ),
		Block.box( 14.0, 0.0, 14.0, 16.0, 14.0, 16.0 ),
		Block.box( 0.0, 0.0, 14.0, 2.0, 14.0, 16.0 )
	);
	
	public DyeCraftingTable() {
		
		super( BlockBehaviour.Properties.of( Material.WOOD )
			.strength( 2.5F )
			.harvestTool( ToolType.AXE )
			.sound( SoundType.WOOD ) );
		setRegistryName( registry_name );
	}
	
	@SuppressWarnings( "deprecation" )
	@Nonnull
	@Override
	public VoxelShape getShape(
		@Nonnull BlockState state,
		@Nonnull BlockGetter level,
		@Nonnull BlockPos pos,
		@Nonnull CollisionContext context ) {
		
		return SHAPE;
	}
	
	@Nullable
	@Override
	public BlockState getStateForPlacement( BlockPlaceContext context ) {
		
		return defaultBlockState().setValue(
			BlockStateProperties.HORIZONTAL_FACING,
			context.getHorizontalDirection()
		);
	}
	
	@SuppressWarnings( "deprecation" )
	@Nonnull
	@Override
	public InteractionResult use(
		@Nonnull BlockState state,
		@Nonnull Level level,
		@Nonnull BlockPos pos,
		@Nonnull Player player,
		@Nonnull InteractionHand hand,
		@Nonnull BlockHitResult hitResult ) {
		
		player.openMenu( state.getMenuProvider( level, pos ) );
		return InteractionResult.SUCCESS;
	}
	
	@SuppressWarnings( "deprecation" )
	@Nullable
	@Override
	public MenuProvider getMenuProvider( @Nonnull BlockState state, @Nonnull Level level, @Nonnull BlockPos pos ) {
		
		return new SimpleMenuProvider(
			( menuId, inventory, player ) -> new DyeCraftingTableMenu(
				menuId,
				inventory,
				ContainerLevelAccess.create( level, pos )
			),
			CONTAINER_TITLE
		);
	}
	
	@Override
	protected void createBlockStateDefinition( StateDefinition.Builder<Block, BlockState> builder ) {
		
		builder.add( BlockStateProperties.HORIZONTAL_FACING );
	}
	
	@Override
	public Item getBlockItem( Item.Properties _properties ) {
		
		return createBlockItem( ModBlocks.DYE_CRAFTING_TABLE, _properties, registry_name );
	}
}
