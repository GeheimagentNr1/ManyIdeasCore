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
	DyeCraftingTableResultCraftingSlot(
		PlayerEntity _player,
		CraftingInventory _craftingInventory,
		IInventory inventoryIn,
		int slotIndex,
		int xPosition,
		int yPosition ) {
		
		super( inventoryIn, slotIndex, xPosition, yPosition );
		player = _player;
		craftingInventory = _craftingInventory;
	}
	
	@Override
	public boolean mayPlace( @Nonnull ItemStack stack ) {
		
		return false;
	}
	
	@Nonnull
	@Override
	public ItemStack remove( int count ) {
		
		if( hasItem() ) {
			amountCrafted += Math.min( count, getItem().getCount() );
		}
		
		return super.remove( count );
	}
	
	@Override
	protected void onQuickCraft( @Nonnull ItemStack stack, int count ) {
		
		amountCrafted += count;
		checkTakeAchievements( stack );
	}
	
	@Override
	protected void onSwapCraft( int count ) {
		
		amountCrafted += count;
	}
	
	@Override
	protected void checkTakeAchievements( @Nonnull ItemStack stack ) {
		
		if( amountCrafted > 0 ) {
			stack.onCraftedBy( player.level, player, amountCrafted );
			BasicEventHooks.firePlayerCraftingEvent( player, stack, craftingInventory );
		}
		if( container instanceof IRecipeHolder ) {
			( (IRecipeHolder)container ).awardUsedRecipes( player );
		}
		amountCrafted = 0;
	}
	
	@Nonnull
	@Override
	public ItemStack onTake( @Nonnull PlayerEntity _player, @Nonnull ItemStack stack ) {
		
		checkTakeAchievements( stack );
		ForgeHooks.setCraftingPlayer( _player );
		NonNullList<ItemStack> ingredients = _player.level.getRecipeManager().getRemainingItemsFor(
			RecipeTypes.DYED,
			craftingInventory,
			_player.level
		);
		ForgeHooks.setCraftingPlayer( null );
		for( int i = 0; i < ingredients.size(); ++i ) {
			ItemStack crafting_stack = craftingInventory.getItem( i );
			ItemStack ingredient = ingredients.get( i );
			if( !crafting_stack.isEmpty() ) {
				craftingInventory.removeItem( i, 1 );
				crafting_stack = craftingInventory.getItem( i );
			}
			
			if( !ingredient.isEmpty() ) {
				if( crafting_stack.isEmpty() ) {
					craftingInventory.setItem( i, ingredient );
				} else {
					if( ItemStack.isSame( crafting_stack, ingredient ) && ItemStack.tagMatches(
						crafting_stack,
						ingredient
					) ) {
						ingredient.grow( crafting_stack.getCount() );
						craftingInventory.setItem( i, ingredient );
					} else {
						if( !player.inventory.add( ingredient ) ) {
							player.drop( ingredient, false );
						}
					}
				}
			}
		}
		return stack;
	}
}
