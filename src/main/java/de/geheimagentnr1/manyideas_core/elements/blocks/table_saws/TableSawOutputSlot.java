package de.geheimagentnr1.manyideas_core.elements.blocks.table_saws;

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
	
	private final IWorldPosCallable worldPosCallable;
	
	private final Slot inputInventorySlot;
	
	//package-private
	TableSawOutputSlot(
		TableSawContainer _tableSawContainer,
		IWorldPosCallable _worldPosCallable,
		Slot _inputInventorySlot,
		IInventory inventory ) {
		
		super( inventory, 1, 143, 33 );
		tableSawContainer = _tableSawContainer;
		worldPosCallable = _worldPosCallable;
		inputInventorySlot = _inputInventorySlot;
	}
	
	@Override
	public boolean mayPlace( @Nonnull ItemStack stack ) {
		
		return false;
	}
	
	@Nonnull
	@Override
	public ItemStack onTake( @Nonnull PlayerEntity player, @Nonnull ItemStack stack ) {
		
		ItemStack itemstack = inputInventorySlot.remove( 1 );
		if( !itemstack.isEmpty() ) {
			tableSawContainer.updateRecipeResultSlot();
		}
		stack.getItem().onCraftedBy( stack, player.level, player );
		worldPosCallable.execute( ( world, pos ) -> {
			long l = world.getGameTime();
			if( tableSawContainer.lastOnTake != l ) {
				world.playSound( null, pos, SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundCategory.BLOCKS, 1.0F, 1.0F );
				tableSawContainer.lastOnTake = l;
			}
		} );
		return super.onTake( player, stack );
	}
}
