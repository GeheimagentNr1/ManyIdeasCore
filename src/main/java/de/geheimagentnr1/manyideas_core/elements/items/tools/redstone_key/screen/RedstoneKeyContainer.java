package de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.screen;

import de.geheimagentnr1.manyideas_core.elements.items.ModItemsRegisterFactory;
import de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.interfaces.RedstoneKeyable;
import de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.models.Option;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class RedstoneKeyContainer extends AbstractContainerMenu {
	
	
	@NotNull
	private final ResourceLocation icons;
	
	@NotNull
	private final BlockPos pos;
	
	@NotNull
	private final RedstoneKeyable redstoneKeyableBlock;
	
	@NotNull
	private final List<Option> options;
	
	private int selectedIndex;
	
	public RedstoneKeyContainer(
		int windowId,
		@NotNull ResourceLocation _icons,
		@NotNull BlockPos _pos,
		@NotNull RedstoneKeyable _redstoneKeyableBlock,
		@NotNull List<Option> _options,
		int _selectedIndex ) {
		
		super( ModItemsRegisterFactory.RESTONE_KEY_CONTAINER, windowId );
		icons = _icons;
		pos = _pos;
		redstoneKeyableBlock = _redstoneKeyableBlock;
		options = _options;
		selectedIndex = _selectedIndex;
	}
	
	public RedstoneKeyContainer( int windowId, @NotNull FriendlyByteBuf data ) {
		
		super( ModItemsRegisterFactory.RESTONE_KEY_CONTAINER, windowId );
		icons = data.readResourceLocation();
		pos = data.readBlockPos();
		redstoneKeyableBlock = (RedstoneKeyable)Objects.requireNonNull( Minecraft.getInstance().level )
			.getBlockState( pos )
			.getBlock();
		int optionsCount = data.readInt();
		options = new ArrayList<>();
		for( int i = 0; i < optionsCount; i++ ) {
			options.add( new Option( data.readUtf(), data.readUtf() ) );
		}
		selectedIndex = data.readInt();
	}
	
	@NotNull
	@Override
	public ItemStack quickMoveStack( @NotNull Player player, int index ) {
		
		return ItemStack.EMPTY;
	}
	
	@Override
	public boolean stillValid( @NotNull Player player ) {
		
		return true;
	}
	
	@NotNull
	public ResourceLocation getIcons() {
		
		return icons;
	}
	
	@NotNull
	public List<Option> getOptions() {
		
		return options;
	}
	
	public int getSelectedIndex() {
		
		return selectedIndex;
	}
	
	public void setSelectedIndex( int _selected ) {
		
		selectedIndex = _selected;
	}
	
	public void setBlockStateValue( @NotNull Level level, int stateIndex, @NotNull Player player ) {
		
		selectedIndex = stateIndex;
		BlockState state = level.getBlockState( pos );
		Block block = state.getBlock();
		if( block == redstoneKeyableBlock ) {
			redstoneKeyableBlock.setBlockStateValue( level, state, pos, stateIndex, player );
		}
	}
}
