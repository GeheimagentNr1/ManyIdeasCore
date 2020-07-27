package de.geheimagentnr1.manyideas_core.elements.blocks.dye_crafting_table;

import de.geheimagentnr1.manyideas_core.elements.recipes.RecipeTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.IRecipeHolder;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.hooks.BasicEventHooks;

import javax.annotation.Nonnull;


//package-private
class DyeCraftingTableResultCraftingSlot extends Slot {
	
	
	private final CraftingInventory craftingInventory;
	
	private final PlayerEntity player;
	
	private int amountCrafted;
	
	//package-private
	@SuppressWarnings( "SameParameterValue" )
	DyeCraftingTableResultCraftingSlot( PlayerEntity _player, CraftingInventory _craftingInventory,
		IInventory inventoryIn, int slotIndex, int xPosition, int yPosition ) {
		
		super( inventoryIn, slotIndex, xPosition, yPosition );
		player = _player;
		craftingInventory = _craftingInventory;
	}
	
	/**
	 * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
	 */
	@Override
	public boolean isItemValid( @Nonnull ItemStack stack ) {
		
		return false;
	}
	
	/**
	 * Decrease the size of the stack in slot (first int arg) by the amount of the second int arg. Returns the new
	 * stack.
	 */
	@Nonnull
	@Override
	public ItemStack decrStackSize( int amount ) {
		
		if( getHasStack() ) {
			amountCrafted += Math.min( amount, getStack().getCount() );
		}
		
		return super.decrStackSize( amount );
	}
	
	/**
	 * the itemStack passed in is the output - ie, iron ingots, and pickaxes, not ore and wood. Typically increases an
	 * internal count then calls onCrafting(item).
	 */
	@Override
	protected void onCrafting( @Nonnull ItemStack stack, int amount ) {
		
		amountCrafted += amount;
		onCrafting( stack );
	}
	
	@Override
	protected void onSwapCraft( int amount ) {
		
		amountCrafted += amount;
	}
	
	/**
	 * the itemStack passed in is the output - ie, iron ingots, and pickaxes, not ore and wood.
	 */
	@Override
	protected void onCrafting( @Nonnull ItemStack stack ) {
		
		if( amountCrafted > 0 ) {
			stack.onCrafting( player.world, player, amountCrafted );
			BasicEventHooks.firePlayerCraftingEvent( player, stack, craftingInventory );
		}
		if( inventory instanceof IRecipeHolder ) {
			( (IRecipeHolder)inventory ).onCrafting( player );
		}
		amountCrafted = 0;
	}
	
	@Nonnull
	@Override
	public ItemStack onTake( @Nonnull PlayerEntity thePlayer, @Nonnull ItemStack stack ) {
		
		onCrafting( stack );
		ForgeHooks.setCraftingPlayer( thePlayer );
		NonNullList<ItemStack> ingredients = thePlayer.world.getRecipeManager().getRecipeNonNull( RecipeTypes.DYED,
			craftingInventory, thePlayer.world );
		ForgeHooks.setCraftingPlayer( null );
		for( int i = 0; i < ingredients.size(); ++i ) {
			ItemStack crafting_stack = craftingInventory.getStackInSlot( i );
			ItemStack ingredient = ingredients.get( i );
			if( !crafting_stack.isEmpty() ) {
				craftingInventory.decrStackSize( i, 1 );
				crafting_stack = craftingInventory.getStackInSlot( i );
			}
			
			if( !ingredient.isEmpty() ) {
				if( crafting_stack.isEmpty() ) {
					craftingInventory.setInventorySlotContents( i, ingredient );
				} else {
					if( ItemStack.areItemsEqual( crafting_stack, ingredient ) &&
						ItemStack.areItemStackTagsEqual( crafting_stack, ingredient ) ) {
						ingredient.grow( crafting_stack.getCount() );
						craftingInventory.setInventorySlotContents( i, ingredient );
					} else {
						if( !player.inventory.addItemStackToInventory( ingredient ) ) {
							player.dropItem( ingredient, false );
						}
					}
				}
			}
		}
		return stack;
	}
}
