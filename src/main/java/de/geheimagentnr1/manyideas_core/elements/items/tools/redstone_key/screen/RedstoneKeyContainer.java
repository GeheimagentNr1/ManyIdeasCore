package de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.screen;

import de.geheimagentnr1.manyideas_core.elements.items.ModItems;
import de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.interfaces.RedstoneKeyable;
import de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.models.Option;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;


public class RedstoneKeyContainer extends Container {
	
	
	private final ResourceLocation icons;
	
	private final BlockPos pos;
	
	private final RedstoneKeyable redstoneKeyableBlock;
	
	private final List<Option> options;
	
	private int selectedIndex;
	
	public RedstoneKeyContainer(
		int containerID,
		ResourceLocation _icons,
		BlockPos _pos,
		RedstoneKeyable _redstoneKeyableBlock,
		List<Option> _options,
		int _selectedIndex ) {
		
		super( ModItems.RESTONE_KEY_CONTAINER, containerID );
		icons = _icons;
		pos = _pos;
		redstoneKeyableBlock = _redstoneKeyableBlock;
		options = _options;
		selectedIndex = _selectedIndex;
	}
	
	public RedstoneKeyContainer( int containerID, PacketBuffer data ) {
		
		super( ModItems.RESTONE_KEY_CONTAINER, containerID );
		icons = data.readResourceLocation();
		pos = data.readBlockPos();
		redstoneKeyableBlock = (RedstoneKeyable)Minecraft.getInstance().world.getBlockState( pos ).getBlock();
		int optionsCount = data.readInt();
		options = new ArrayList<>();
		for( int i = 0; i < optionsCount; i++ ) {
			options.add( new Option( data.readString(), data.readString() ) );
		}
		selectedIndex = data.readInt();
	}
	
	@Override
	public boolean canInteractWith( @Nonnull PlayerEntity playerIn ) {
		
		return true;
	}
	
	public ResourceLocation getIcons() {
		
		return icons;
	}
	
	public BlockPos getPos() {
		
		return pos;
	}
	
	public List<Option> getOptions() {
		
		return options;
	}
	
	public int getSelectedIndex() {
		
		return selectedIndex;
	}
	
	public void setSelectedIndex( int _selected ) {
		
		selectedIndex = _selected;
	}
	
	public void setBlockStateValue( World world, int stateIndex, PlayerEntity player ) {
		
		selectedIndex = stateIndex;
		BlockState state = world.getBlockState( pos );
		Block block = state.getBlock();
		if( block == redstoneKeyableBlock ) {
			redstoneKeyableBlock.setBlockStateValue( world, state, pos, stateIndex, player );
		}
	}
}
