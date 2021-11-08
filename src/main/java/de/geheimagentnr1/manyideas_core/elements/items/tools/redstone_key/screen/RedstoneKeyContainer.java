package de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.screen;

import de.geheimagentnr1.manyideas_core.elements.items.ModItems;
import de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.interfaces.RedstoneKeyable;
import de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.models.Option;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class RedstoneKeyContainer extends AbstractContainerMenu {
	
	
	private final ResourceLocation icons;
	
	private final BlockPos pos;
	
	private final RedstoneKeyable redstoneKeyableBlock;
	
	private final List<Option> options;
	
	private int selectedIndex;
	
	public RedstoneKeyContainer(
		int menuId,
		ResourceLocation _icons,
		BlockPos _pos,
		RedstoneKeyable _redstoneKeyableBlock,
		List<Option> _options,
		int _selectedIndex ) {
		
		super( ModItems.RESTONE_KEY_CONTAINER, menuId );
		icons = _icons;
		pos = _pos;
		redstoneKeyableBlock = _redstoneKeyableBlock;
		options = _options;
		selectedIndex = _selectedIndex;
	}
	
	public RedstoneKeyContainer( int menuId, FriendlyByteBuf data ) {
		
		super( ModItems.RESTONE_KEY_CONTAINER, menuId );
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
	
	@Override
	public boolean stillValid( @Nonnull Player player ) {
		
		return true;
	}
	
	public ResourceLocation getIcons() {
		
		return icons;
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
	
	public void setBlockStateValue( Level level, int stateIndex, Player player ) {
		
		selectedIndex = stateIndex;
		BlockState state = level.getBlockState( pos );
		Block block = state.getBlock();
		if( block == redstoneKeyableBlock ) {
			redstoneKeyableBlock.setBlockStateValue( level, state, pos, stateIndex, player );
		}
	}
}
