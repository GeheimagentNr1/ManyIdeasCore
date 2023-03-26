package de.geheimagentnr1.manyideas_core.elements.blocks.table_saws;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;


//package-private
class TableSawOutputSlot extends Slot {
	
	
	private final TableSawMenu tableSawMenu;
	
	private final ContainerLevelAccess levelPosCallable;
	
	private final Slot inputInventorySlot;
	
	//package-private
	TableSawOutputSlot(
		TableSawMenu _tableSawMenu,
		ContainerLevelAccess _levelPosCallable,
		Slot _inputInventorySlot,
		Container _container ) {
		
		super( _container, 1, 143, 33 );
		tableSawMenu = _tableSawMenu;
		levelPosCallable = _levelPosCallable;
		inputInventorySlot = _inputInventorySlot;
	}
	
	@Override
	public boolean mayPlace( @Nonnull ItemStack stack ) {
		
		return false;
	}
	
	@Override
	public void onTake( @Nonnull Player player, @Nonnull ItemStack stack ) {
		
		stack.onCraftedBy( player.level, player, stack.getCount() );
		tableSawMenu.getResultContainer().awardUsedRecipes( player );
		ItemStack itemstack = inputInventorySlot.remove( 1 );
		if( !itemstack.isEmpty() ) {
			tableSawMenu.updateRecipeResultSlot();
		}
		stack.getItem().onCraftedBy( stack, player.level, player );
		levelPosCallable.execute( ( level, pos ) -> {
			long l = level.getGameTime();
			if( tableSawMenu.lastOnTake != l ) {
				level.playSound( null, pos, SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundSource.BLOCKS, 1.0F, 1.0F );
				tableSawMenu.lastOnTake = l;
			}
		} );
		super.onTake( player, stack );
	}
}
