package de.geheimagentnr1.manyideas_core.elements.blocks.mortar;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;


//package-private
class MortarCraftingContainer implements Container {
	
	
	private ItemStack crafting_stack;
	
	//package-private
	MortarCraftingContainer( ItemStack _crafting_stack ) {
		
		crafting_stack = _crafting_stack;
	}
	
	@Override
	public int getContainerSize() {
		
		return 1;
	}
	
	@Override
	public boolean isEmpty() {
		
		return crafting_stack.isEmpty();
	}
	
	@Nonnull
	@Override
	public ItemStack getItem( int index ) {
		
		return crafting_stack;
	}
	
	@Nonnull
	@Override
	public ItemStack removeItem( int index, int count ) {
		
		return crafting_stack;
	}
	
	@Nonnull
	@Override
	public ItemStack removeItemNoUpdate( int index ) {
		
		return crafting_stack;
	}
	
	@Override
	public void setItem( int index, @Nonnull ItemStack stack ) {
	
	}
	
	@Override
	public void setChanged() {
	
	}
	
	@Override
	public boolean stillValid( @Nonnull Player player ) {
		
		return true;
	}
	
	@Override
	public void clearContent() {
		
		crafting_stack = null;
	}
}
