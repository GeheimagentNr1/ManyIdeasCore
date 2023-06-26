package de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.screen;

import de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.interfaces.RedstoneKeyable;
import de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.models.Option;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;


public class RedstoneKeyNamedContainerProvider implements MenuProvider {
	
	
	@NotNull
	private final Component displayName;
	
	@NotNull
	private final ResourceLocation icons;
	
	@NotNull
	private final BlockPos pos;
	
	@NotNull
	private final RedstoneKeyable redstoneKeyableBlock;
	
	@NotNull
	private final List<Option> options;
	
	private final int selectedIndex;
	
	public RedstoneKeyNamedContainerProvider(
		@NotNull Component _displayName,
		@NotNull ResourceLocation _icons,
		@NotNull BlockPos _pos,
		@NotNull RedstoneKeyable _redstoneKeyableBlock,
		@NotNull List<Option> _options,
		int _selectedIndex ) {
		
		displayName = _displayName;
		icons = _icons;
		pos = _pos;
		redstoneKeyableBlock = _redstoneKeyableBlock;
		options = _options;
		selectedIndex = _selectedIndex;
	}
	
	@NotNull
	@Override
	public Component getDisplayName() {
		
		return displayName;
	}
	
	@Nullable
	@Override
	public AbstractContainerMenu createMenu(
		int menuId,
		@NotNull Inventory inventory,
		@NotNull Player player ) {
		
		return new RedstoneKeyContainer( menuId, icons, pos, redstoneKeyableBlock, options, selectedIndex );
	}
}
