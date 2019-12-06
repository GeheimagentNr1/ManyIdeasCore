package de.geheimagentnr1.manyideascore.elements.blocks.mortal;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;


//package-private
class MortalCraftingInventory implements IInventory {
	
	
	private ItemStack crafting_stack;
	
	//package-private
	MortalCraftingInventory( ItemStack _crafting_stack ) {
		
		crafting_stack = _crafting_stack;
	}
	
	@Override
	public int getSizeInventory() {
		
		return 1;
	}
	
	@Override
	public boolean isEmpty() {
		
		return crafting_stack.isEmpty();
	}
	
	@Nonnull
	@Override
	public ItemStack getStackInSlot( int index ) {
		
		return crafting_stack;
	}
	
	@Nonnull
	@Override
	public ItemStack decrStackSize( int index, int count ) {
		
		return crafting_stack;
	}
	
	@Nonnull
	@Override
	public ItemStack removeStackFromSlot( int index ) {
		
		return crafting_stack;
	}
	
	@Override
	public void setInventorySlotContents( int index, @Nonnull ItemStack stack ) {
	
	}
	
	@Override
	public void markDirty() {
	
	}
	
	@Override
	public boolean isUsableByPlayer( @Nonnull PlayerEntity player ) {
		
		return true;
	}
	
	@Override
	public void clear() {
		
		crafting_stack = null;
	}
}
