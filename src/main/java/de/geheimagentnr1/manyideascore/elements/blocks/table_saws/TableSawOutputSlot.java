package de.geheimagentnr1.manyideascore.elements.blocks.table_saws;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;

import javax.annotation.Nonnull;


//package-private
class TableSawOutputSlot extends Slot {
	
	
	private final TableSawContainer tableSawContainer;
	
	private final IWorldPosCallable worldPosCallableIn;
	
	private final Slot inputInventorySlot;
	
	//package-private
	TableSawOutputSlot( TableSawContainer _tableSawContainer, IWorldPosCallable _worldPosCallableIn,
		Slot _inputInventorySlot, IInventory inventoryIn ) {
		
		super( inventoryIn, 1, 143, 33 );
		tableSawContainer = _tableSawContainer;
		worldPosCallableIn = _worldPosCallableIn;
		inputInventorySlot = _inputInventorySlot;
	}
	
	/**
	 * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
	 */
	@Override
	public boolean isItemValid( ItemStack stack ) {
		
		return false;
	}
	
	@Nonnull
	@Override
	public ItemStack onTake( PlayerEntity thePlayer, @Nonnull ItemStack stack ) {
		
		ItemStack itemstack = inputInventorySlot.decrStackSize( 1 );
		if( !itemstack.isEmpty() ) {
			tableSawContainer.updateRecipeResultSlot();
		}
		stack.getItem().onCreated( stack, thePlayer.world, thePlayer );
		worldPosCallableIn.consume( ( world, pos ) -> {
			long l = world.getGameTime();
			if( tableSawContainer.lastOnTake != l ) {
				world.playSound( null, pos, SoundEvents.UI_STONECUTTER_TAKE_RESULT,
					SoundCategory.BLOCKS, 1.0F, 1.0F );
				tableSawContainer.lastOnTake = l;
			}
		} );
		return super.onTake( thePlayer, stack );
	}
}
