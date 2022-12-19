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
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;


@SuppressWarnings( { "AbstractClassExtendsConcreteClass", "unused", "AbstractClassNeverImplemented" } )
public abstract class DoubleDoorBlock extends DoorBlock implements RedstoneKeyable, BlockItemInterface,
	BlockRenderTypeInterface {
	
	
	protected DoubleDoorBlock( BlockBehaviour.Properties _properties, SoundEvent closeSound, SoundEvent openSound ) {
		
		super( _properties.noOcclusion().isViewBlocking( ( state, level, pos ) -> false ), closeSound, openSound );
		initDoubleDoorBlock( material == Material.METAL ? OpenedBy.REDSTONE : OpenedBy.BOTH );
	}
	
	protected DoubleDoorBlock(
		BlockBehaviour.Properties _properties,
		SoundEvent closeSound,
		SoundEvent openSound,
		OpenedBy openedBy ) {
		
		super( _properties.noOcclusion().isViewBlocking( ( state, level, pos ) -> false ), closeSound, openSound );
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
	public InteractionResult use(
		@Nonnull BlockState state,
		@Nonnull Level level,
		@Nonnull BlockPos pos,
		@Nonnull Player player,
		@Nonnull InteractionHand hand,
		@Nonnull BlockHitResult hitResult ) {
		
		if( player.getItemInHand( hand ).getItem() != ModItems.RESTONE_KEY &&
			OpenedByHelper.canBeOpened( state, true ) ) {
			boolean open = !state.getValue( OPEN );
			level.setBlock( pos, state.setValue( OPEN, open ), 10 );
			DoorsHelper.playDoorSound( level, pos, material, player, state.getValue( OPEN ) );
			
			BlockData neighbor = DoorsHelper.getNeighborBlock( level, pos, state );
			if( DoorsHelper.isNeighbor( state, neighbor ) ) {
				level.setBlock( neighbor.getPos(), neighbor.getState().setValue( OPEN, open ), 2 );
			}
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.PASS;
	}
	
	@Override
	public void setOpen(
		@Nullable Entity entity,
		@Nonnull Level level,
		@Nonnull BlockState state,
		@Nonnull BlockPos pos,
		boolean open ) {
		
		super.setOpen( entity, level, state, pos, open );
		BlockData neighbor = DoorsHelper.getNeighborBlock( level, pos, state );
		if( DoorsHelper.isNeighbor( state, neighbor ) ) {
			level.setBlock( neighbor.getPos(), neighbor.getState().setValue( OPEN, open ), 2 );
		}
	}
	
	@Override
	public void neighborChanged(
		@Nonnull BlockState state,
		@Nonnull Level level,
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
	protected void createBlockStateDefinition( @Nonnull StateDefinition.Builder<Block, BlockState> builder ) {
		
		super.createBlockStateDefinition( builder );
		builder.add( ModBlockStateProperties.OPENED_BY );
	}
	
	@Override
	public Component getTitle() {
		
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
		Level level,
		BlockState state,
		BlockPos pos,
		int stateIndex,
		Player player ) {
		
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
