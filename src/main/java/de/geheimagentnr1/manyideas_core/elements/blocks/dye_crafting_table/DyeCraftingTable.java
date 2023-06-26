package de.geheimagentnr1.manyideas_core.elements.blocks.dye_crafting_table;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.minecraft_forge_api.elements.blocks.BlockItemInterface;
import de.geheimagentnr1.minecraft_forge_api.util.TranslationKeyHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;


public class DyeCraftingTable extends Block implements BlockItemInterface {
	
	
	@NotNull
	public static final String registry_name = "dye_crafting_table";
	
	@NotNull
	private static final Component CONTAINER_TITLE = TranslationKeyHelper.generateContainerTranslationText(
		ManyIdeasCore.MODID,
		registry_name
	);
	
	@NotNull
	private static final VoxelShape SHAPE = Shapes.or(
		Block.box( 0.0, 14.0, 0.0, 16.0, 15.75, 16.0 ),
		Block.box( 0.0, 0.0, 0.0, 2.0, 14.0, 2.0 ),
		Block.box( 14.0, 0.0, 0.0, 16.0, 14.0, 2.0 ),
		Block.box( 14.0, 0.0, 14.0, 16.0, 14.0, 16.0 ),
		Block.box( 0.0, 0.0, 14.0, 2.0, 14.0, 16.0 )
	);
	
	public DyeCraftingTable() {
		
		super( BlockBehaviour.Properties.of()
			.mapColor( MapColor.WOOD )
			.strength( 2.5F )
			.sound( SoundType.WOOD ) );
	}
	
	@SuppressWarnings( "deprecation" )
	@NotNull
	@Override
	public VoxelShape getShape(
		@NotNull BlockState state,
		@NotNull BlockGetter level,
		@NotNull BlockPos pos,
		@NotNull CollisionContext context ) {
		
		return SHAPE;
	}
	
	@Nullable
	@Override
	public BlockState getStateForPlacement( @NotNull BlockPlaceContext context ) {
		
		return defaultBlockState().setValue(
			BlockStateProperties.HORIZONTAL_FACING,
			context.getHorizontalDirection()
		);
	}
	
	@SuppressWarnings( "deprecation" )
	@NotNull
	@Override
	public InteractionResult use(
		@NotNull BlockState state,
		@NotNull Level level,
		@NotNull BlockPos pos,
		@NotNull Player player,
		@NotNull InteractionHand hand,
		@NotNull BlockHitResult hitResult ) {
		
		player.openMenu( state.getMenuProvider( level, pos ) );
		return InteractionResult.SUCCESS;
	}
	
	@SuppressWarnings( "deprecation" )
	@Nullable
	@Override
	public MenuProvider getMenuProvider( @NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos ) {
		
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
	protected void createBlockStateDefinition( @NotNull StateDefinition.Builder<Block, BlockState> builder ) {
		
		builder.add( BlockStateProperties.HORIZONTAL_FACING );
	}
}
