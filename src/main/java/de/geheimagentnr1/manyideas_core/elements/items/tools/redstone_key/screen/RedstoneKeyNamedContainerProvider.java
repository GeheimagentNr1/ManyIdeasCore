package de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.screen;

import de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.interfaces.RedstoneKeyable;
import de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.models.Option;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;


public class RedstoneKeyNamedContainerProvider implements INamedContainerProvider {
	
	
	private final ITextComponent displayName;
	
	private final ResourceLocation icons;
	
	private final BlockPos pos;
	
	private final RedstoneKeyable redstoneKeyableBlock;
	
	private final List<Option> options;
	
	private final int selectedIndex;
	
	public RedstoneKeyNamedContainerProvider(
		ITextComponent _displayName,
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
	public ITextComponent getDisplayName() {
		
		return displayName;
	}
	
	@Nullable
	@Override
	public Container createMenu(
		int containerID,
		@Nonnull PlayerInventory playerInventory,
		@Nonnull PlayerEntity player ) {
		
		return new RedstoneKeyContainer( containerID, icons, pos, redstoneKeyableBlock, options, selectedIndex );
	}
}
