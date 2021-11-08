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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;


public class RedstoneKeyNamedContainerProvider implements MenuProvider {
	
	
	private final Component displayName;
	
	private final ResourceLocation icons;
	
	private final BlockPos pos;
	
	private final RedstoneKeyable redstoneKeyableBlock;
	
	private final List<Option> options;
	
	private final int selectedIndex;
	
	public RedstoneKeyNamedContainerProvider(
		Component _displayName,
		ResourceLocation _icons,
		BlockPos _pos,
		RedstoneKeyable _redstoneKeyableBlock,
		List<Option> _options,
		int _selectedIndex ) {
		
		displayName = _displayName;
		icons = _icons;
		pos = _pos;
		redstoneKeyableBlock = _redstoneKeyableBlock;
		options = _options;
		selectedIndex = _selectedIndex;
	}
	
	@Nonnull
	@Override
	public Component getDisplayName() {
		
		return displayName;
	}
	
	@Nullable
	@Override
	public AbstractContainerMenu createMenu(
		int menuId,
		@Nonnull Inventory inventory,
		@Nonnull Player player ) {
		
		return new RedstoneKeyContainer( menuId, icons, pos, redstoneKeyableBlock, options, selectedIndex );
	}
}
