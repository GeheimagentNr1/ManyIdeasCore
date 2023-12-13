package de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.doors;

import de.geheimagentnr1.manyideas_core.elements.block_state_properties.ModBlockStateProperties;
import de.geheimagentnr1.manyideas_core.elements.block_state_properties.OpenedBy;
import de.geheimagentnr1.manyideas_core.elements.items.ModItemsRegisterFactory;
import de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.interfaces.RedstoneKeyable;
import de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.models.Option;
import de.geheimagentnr1.manyideas_core.util.doors.BlockData;
import de.geheimagentnr1.manyideas_core.util.doors.DoorsHelper;
import de.geheimagentnr1.manyideas_core.util.doors.OpenedByHelper;
import de.geheimagentnr1.minecraft_forge_api.elements.blocks.BlockItemInterface;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
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
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;


@SuppressWarnings( { "AbstractClassExtendsConcreteClass", "unused", "AbstractClassNeverImplemented" } )
public abstract class DoubleDoorBlock extends DoorBlock implements RedstoneKeyable, BlockItemInterface {
	
	
	protected DoubleDoorBlock(
		@NotNull BlockBehaviour.Properties _properties,
		@NotNull BlockSetType _type,
		@NotNull OpenedBy openedBy ) {
		
		super( _type, _properties.noOcclusion().isViewBlocking( ( state, level, pos ) -> false ) );
		initDoubleDoorBlock( openedBy );
	}
	
	private void initDoubleDoorBlock( @NotNull OpenedBy openedBy ) {
		
		registerDefaultState( defaultBlockState().setValue( ModBlockStateProperties.OPENED_BY, openedBy ) );
	}
	
	@NotNull
	@Override
	public InteractionResult use(
		@NotNull BlockState state,
		@NotNull Level level,
		@NotNull BlockPos pos,
		@NotNull Player player,
		@NotNull InteractionHand hand,
		@NotNull BlockHitResult hitResult ) {
		
		if( player.getItemInHand( hand ).getItem() != ModItemsRegisterFactory.RESTONE_KEY &&
			OpenedByHelper.canBeOpened( state, true ) ) {
			boolean open = !state.getValue( OPEN );
			level.setBlock( pos, state.setValue( OPEN, open ), 10 );
			DoorsHelper.playDoorSound( level, pos, type(), player, state.getValue( OPEN ) );
			
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
		@NotNull Level level,
		@NotNull BlockState state,
		@NotNull BlockPos pos,
		boolean open ) {
		
		super.setOpen( entity, level, state, pos, open );
		BlockData neighbor = DoorsHelper.getNeighborBlock( level, pos, state );
		if( DoorsHelper.isNeighbor( state, neighbor ) ) {
			level.setBlock( neighbor.getPos(), neighbor.getState().setValue( OPEN, open ), 2 );
		}
	}
	
	@Override
	public void neighborChanged(
		@NotNull BlockState state,
		@NotNull Level level,
		@NotNull BlockPos pos,
		@NotNull Block block,
		@NotNull BlockPos fromPos,
		boolean isMoving ) {
		
		if( block != this && OpenedByHelper.canBeOpened( state, false ) ) {
			BlockData neighbor = DoorsHelper.getNeighborBlock( level, pos, state );
			boolean isNeighbor = DoorsHelper.isNeighbor( state, neighbor );
			boolean isDoorPowered = DoorsHelper.isDoorPowered( level, pos, state ) ||
				isNeighbor && DoorsHelper.isDoorPowered( level, neighbor.getPos(), neighbor.getState() );
			
			if( isDoorPowered != state.getValue( POWERED ) ) {
				if( state.getValue( OPEN ) != isDoorPowered ) {
					DoorsHelper.playDoorSound( level, pos, type(), null, isDoorPowered );
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
	protected void createBlockStateDefinition( @NotNull StateDefinition.Builder<Block, BlockState> builder ) {
		
		super.createBlockStateDefinition( builder );
		builder.add( ModBlockStateProperties.OPENED_BY );
	}
	
	@NotNull
	@Override
	public Component getTitle() {
		
		return OpenedByHelper.OPEN_BY_CONTAINER_TITLE;
	}
	
	@NotNull
	@Override
	public ResourceLocation getIconTextures() {
		
		return OpenedByHelper.ICON_TEXTURES;
	}
	
	@NotNull
	@Override
	public List<Option> getOptions() {
		
		return OpenedByHelper.buildOptions();
	}
	
	@Override
	public int getStateIndex( @NotNull BlockState state ) {
		
		return OpenedByHelper.getStateIndex( state );
	}
	
	@Override
	public void setBlockStateValue(
		@NotNull Level level,
		@NotNull BlockState state,
		@NotNull BlockPos pos,
		int stateIndex,
		@NotNull Player player ) {
		
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
