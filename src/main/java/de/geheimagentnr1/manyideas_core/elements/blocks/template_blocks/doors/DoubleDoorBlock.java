package de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.doors;

import de.geheimagentnr1.manyideas_core.elements.block_state_properties.ModBlockStateProperties;
import de.geheimagentnr1.manyideas_core.elements.block_state_properties.OpenedBy;
import de.geheimagentnr1.manyideas_core.elements.blocks.BlockItemInterface;
import de.geheimagentnr1.manyideas_core.elements.blocks.BlockRenderTypeInterface;
import de.geheimagentnr1.manyideas_core.elements.items.ModItems;
import de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.interfaces.RedstoneKeyable;
import de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.models.Option;
import de.geheimagentnr1.manyideas_core.util.doors.BlockData;
import de.geheimagentnr1.manyideas_core.util.doors.DoorsHelper;
import de.geheimagentnr1.manyideas_core.util.doors.OpenedByHelper;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.List;


@SuppressWarnings( { "AbstractClassExtendsConcreteClass", "unused", "AbstractClassNeverImplemented" } )
public abstract class DoubleDoorBlock extends DoorBlock implements RedstoneKeyable, BlockItemInterface,
	BlockRenderTypeInterface {
	
	
	protected DoubleDoorBlock( AbstractBlock.Properties _properties, String registry_name ) {
		
		super( _properties.noOcclusion().isViewBlocking( ( state, level, pos ) -> false ) );
		setRegistryName( registry_name );
		initDoubleDoorBlock( material == Material.METAL ? OpenedBy.REDSTONE : OpenedBy.BOTH );
	}
	
	protected DoubleDoorBlock( AbstractBlock.Properties _properties, String registry_name, OpenedBy openedBy ) {
		
		super( _properties.noOcclusion().isViewBlocking( ( state, level, pos ) -> false ) );
		setRegistryName( registry_name );
		initDoubleDoorBlock( openedBy );
	}
	
	private void initDoubleDoorBlock( OpenedBy openedBy ) {
		
		registerDefaultState( defaultBlockState().setValue( ModBlockStateProperties.OPENED_BY, openedBy ) );
	}
	
	@Override
	public RenderType getRenderType() {
		
		return RenderType.cutout();
	}
	
	@Nonnull
	@Override
	public ActionResultType use(
		@Nonnull BlockState state,
		@Nonnull World level,
		@Nonnull BlockPos pos,
		@Nonnull PlayerEntity player,
		@Nonnull Hand hand,
		@Nonnull BlockRayTraceResult hitResult ) {
		
		if( player.getItemInHand( hand ).getItem() != ModItems.RESTONE_KEY &&
			OpenedByHelper.canBeOpened( state, true ) ) {
			boolean open = !state.getValue( OPEN );
			level.setBlock( pos, state.setValue( OPEN, open ), 10 );
			DoorsHelper.playDoorSound( level, pos, material, player, state.getValue( OPEN ) );
			
			BlockData neighbor = DoorsHelper.getNeighborBlock( level, pos, state );
			if( DoorsHelper.isNeighbor( state, neighbor ) ) {
				level.setBlock( neighbor.getPos(), neighbor.getState().setValue( OPEN, open ), 2 );
			}
			return ActionResultType.SUCCESS;
		}
		return ActionResultType.PASS;
	}
	
	@Override
	public void toggleDoor( @Nonnull World worldIn, @Nonnull BlockPos pos, boolean open ) {
		
		super.toggleDoor( worldIn, pos, open );
		BlockState state = worldIn.getBlockState( pos );
		BlockData neighbor = DoorsHelper.getNeighborBlock( worldIn, pos, state );
		if( DoorsHelper.isNeighbor( state, neighbor ) ) {
			worldIn.setBlockState( neighbor.getPos(), neighbor.getState().with( OPEN, open ), 2 );
		}
	}
	
	@Override
	public void neighborChanged(
		@Nonnull BlockState state,
		@Nonnull World level,
		@Nonnull BlockPos pos,
		@Nonnull Block block,
		@Nonnull BlockPos fromPos,
		boolean isMoving ) {
		
		if( block != this && OpenedByHelper.canBeOpened( state, false ) ) {
			BlockData neighbor = DoorsHelper.getNeighborBlock( level, pos, state );
			boolean isNeighbor = DoorsHelper.isNeighbor( state, neighbor );
			boolean isDoorPowered = DoorsHelper.isDoorPowered( level, pos, state ) ||
				isNeighbor && DoorsHelper.isDoorPowered( level, neighbor.getPos(), neighbor.getState() );
			
			if( isDoorPowered != state.getValue( POWERED ) ) {
				if( state.getValue( OPEN ) != isDoorPowered ) {
					DoorsHelper.playDoorSound( level, pos, material, null, isDoorPowered );
				}
				level.setBlock( pos, state.setValue( POWERED, isDoorPowered ).setValue( OPEN, isDoorPowered ), 2 );
				if( isNeighbor ) {
					level.setBlock(
						neighbor.getPos(),
						neighbor.getState().setValue( POWERED, isDoorPowered ).setValue( OPEN, isDoorPowered ),
						2
					);
				}
			}
		}
	}
	
	@Override
	protected void createBlockStateDefinition( @Nonnull StateContainer.Builder<Block, BlockState> builder ) {
		
		super.createBlockStateDefinition( builder );
		builder.add( ModBlockStateProperties.OPENED_BY );
	}
	
	@Override
	public ITextComponent getTitle() {
		
		return OpenedByHelper.OPEN_BY_CONTAINER_TITLE;
	}
	
	@Override
	public ResourceLocation getIconTextures() {
		
		return OpenedByHelper.ICON_TEXTURES;
	}
	
	@Override
	public List<Option> getOptions() {
		
		return OpenedByHelper.buildOptions();
	}
	
	@Override
	public int getStateIndex( BlockState state ) {
		
		return OpenedByHelper.getStateIndex( state );
	}
	
	@Override
	public void setBlockStateValue(
		World level,
		BlockState state,
		BlockPos pos,
		int stateIndex,
		PlayerEntity player ) {
		
		OpenedBy[] openedByValues = OpenedBy.values();
		if( stateIndex >= 0 && stateIndex < openedByValues.length ) {
			OpenedBy openedBy = openedByValues[stateIndex];
			
			level.setBlock( pos, state.setValue( ModBlockStateProperties.OPENED_BY, openedBy ), 2 );
			
			BlockData other = DoorsHelper.getOtherBlock( level, pos, state );
			level.setBlock(
				other.getPos(),
				other.getState().setValue( ModBlockStateProperties.OPENED_BY, openedBy ),
				2
			);
			
			BlockData neighbor = DoorsHelper.getNeighborBlock( level, pos, state );
			if( DoorsHelper.isNeighbor( state, neighbor ) ) {
				level.setBlock(
					neighbor.getPos(),
					neighbor.getState().setValue( ModBlockStateProperties.OPENED_BY, openedBy ),
					2
				);
				
				BlockData otherNeighbor = DoorsHelper.getOtherBlock( level, neighbor.getPos(), neighbor.getState() );
				level.setBlock(
					otherNeighbor.getPos(),
					otherNeighbor.getState().setValue( ModBlockStateProperties.OPENED_BY, openedBy ),
					2
				);
			}
		}
	}
}
