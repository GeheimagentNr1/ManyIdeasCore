package de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.doors;

import de.geheimagentnr1.manyideas_core.elements.block_state_properties.ModBlockStateProperties;
import de.geheimagentnr1.manyideas_core.elements.block_state_properties.OpenedBy;
import de.geheimagentnr1.manyideas_core.elements.blocks.BlockItemInterface;
import de.geheimagentnr1.manyideas_core.elements.items.ModItems;
import de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.interfaces.RedstoneKeyable;
import de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.models.Option;
import de.geheimagentnr1.manyideas_core.util.doors.BlockData;
import de.geheimagentnr1.manyideas_core.util.doors.DoorsHelper;
import de.geheimagentnr1.manyideas_core.util.doors.OpenedByHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.List;


@SuppressWarnings( { "AbstractClassExtendsConcreteClass", "unused", "AbstractClassNeverImplemented" } )
public abstract class DoubleDoorBlock extends DoorBlock implements RedstoneKeyable, BlockItemInterface {
	
	
	protected DoubleDoorBlock( Block.Properties properties, String registry_name ) {
		
		super( properties );
		setRegistryName( registry_name );
		initDoubleDoorBlock( material == Material.IRON ? OpenedBy.REDSTONE : OpenedBy.BOTH );
	}
	
	protected DoubleDoorBlock( Block.Properties properties, String registry_name, OpenedBy openedBy ) {
		
		super( properties );
		setRegistryName( registry_name );
		initDoubleDoorBlock( openedBy );
	}
	
	private void initDoubleDoorBlock( OpenedBy openedBy ) {
		
		setDefaultState( getDefaultState().with( ModBlockStateProperties.OPENED_BY, openedBy ) );
	}
	
	@Override
	public boolean onBlockActivated(
		@Nonnull BlockState state,
		@Nonnull World worldIn,
		@Nonnull BlockPos pos,
		@Nonnull PlayerEntity player,
		@Nonnull Hand handIn,
		@Nonnull BlockRayTraceResult hit ) {
		
		if( player.getHeldItem( handIn ).getItem() != ModItems.RESTONE_KEY &&
			OpenedByHelper.canBeOpened( state, true ) ) {
			boolean open = !state.get( OPEN );
			worldIn.setBlockState( pos, state.with( OPEN, open ), 10 );
			DoorsHelper.playDoorSound( worldIn, pos, material, player, state.get( OPEN ) );
			
			BlockData neighbor = DoorsHelper.getNeighborBlock( worldIn, pos, state );
			if( DoorsHelper.isNeighbor( state, neighbor ) ) {
				worldIn.setBlockState( neighbor.getPos(), neighbor.getState().with( OPEN, open ), 2 );
			}
			return true;
		}
		return false;
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
		@Nonnull World worldIn,
		@Nonnull BlockPos pos,
		@Nonnull Block blockIn,
		@Nonnull BlockPos fromPos,
		boolean isMoving ) {
		
		if( blockIn != this && OpenedByHelper.canBeOpened( state, false ) ) {
			BlockData neighbor = DoorsHelper.getNeighborBlock( worldIn, pos, state );
			boolean isNeighbor = DoorsHelper.isNeighbor( state, neighbor );
			boolean isDoorPowered = DoorsHelper.isDoorPowered( worldIn, pos, state ) ||
				isNeighbor && DoorsHelper.isDoorPowered( worldIn, neighbor.getPos(), neighbor.getState() );
			
			if( isDoorPowered != state.get( POWERED ) ) {
				if( state.get( OPEN ) != isDoorPowered ) {
					DoorsHelper.playDoorSound( worldIn, pos, material, null, isDoorPowered );
				}
				worldIn.setBlockState( pos, state.with( POWERED, isDoorPowered ).with( OPEN, isDoorPowered ), 2 );
				if( isNeighbor ) {
					worldIn.setBlockState(
						neighbor.getPos(),
						neighbor.getState().with( POWERED, isDoorPowered ).with( OPEN, isDoorPowered ),
						2
					);
				}
			}
		}
	}
	
	@Override
	public void onBlockHarvested(
		@Nonnull World worldIn,
		@Nonnull BlockPos pos,
		@Nonnull BlockState state,
		@Nonnull PlayerEntity player ) {
		
		DoubleBlockHalf doubleblockhalf = state.get( HALF );
		BlockPos blockpos = doubleblockhalf == DoubleBlockHalf.LOWER ? pos.up() : pos.down();
		BlockState blockstate = worldIn.getBlockState( blockpos );
		if( blockstate.getBlock() == this && blockstate.get( HALF ) != doubleblockhalf ) {
			worldIn.setBlockState( blockpos, Blocks.AIR.getDefaultState(), 35 );
			worldIn.playEvent( player, 2001, blockpos, Block.getStateId( blockstate ) );
			ItemStack itemstack = player.getHeldItemMainhand();
			if( !worldIn.isRemote && !player.isCreative() && player.canHarvestBlock( state ) ) {
				Block.spawnDrops( state, worldIn, pos, null, player, itemstack );
				Block.spawnDrops( blockstate, worldIn, blockpos, null, player, itemstack );
			}
		}
		worldIn.playEvent( player, 2001, pos, getStateId( state ) );
	}
	
	@Override
	protected void fillStateContainer( @Nonnull StateContainer.Builder<Block, BlockState> builder ) {
		
		super.fillStateContainer( builder );
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
		World world,
		BlockState state,
		BlockPos pos,
		int stateIndex,
		PlayerEntity player ) {
		
		OpenedBy[] openedByValues = OpenedBy.values();
		if( stateIndex >= 0 && stateIndex < openedByValues.length ) {
			OpenedBy openedBy = openedByValues[stateIndex];
			
			world.setBlockState( pos, state.with( ModBlockStateProperties.OPENED_BY, openedBy ), 2 );
			
			BlockData other = DoorsHelper.getOtherBlock( world, pos, state );
			world.setBlockState(
				other.getPos(),
				other.getState().with( ModBlockStateProperties.OPENED_BY, openedBy ),
				2
			);
			
			BlockData neighbor = DoorsHelper.getNeighborBlock( world, pos, state );
			if( DoorsHelper.isNeighbor( state, neighbor ) ) {
				world.setBlockState(
					neighbor.getPos(),
					neighbor.getState().with( ModBlockStateProperties.OPENED_BY, openedBy ),
					2
				);
				
				BlockData otherNeighbor = DoorsHelper.getOtherBlock( world, neighbor.getPos(), neighbor.getState() );
				world.setBlockState(
					otherNeighbor.getPos(),
					otherNeighbor.getState().with( ModBlockStateProperties.OPENED_BY, openedBy ),
					2
				);
			}
		}
	}
}
