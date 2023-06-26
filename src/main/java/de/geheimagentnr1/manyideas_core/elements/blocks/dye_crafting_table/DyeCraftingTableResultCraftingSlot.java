package de.geheimagentnr1.manyideas_core.elements.blocks.dye_crafting_table;

import de.geheimagentnr1.manyideas_core.elements.recipes.ModRecipeTypesRegisterFactory;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.RecipeHolder;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.ForgeEventFactory;
import org.jetbrains.annotations.NotNull;


//package-private
class DyeCraftingTableResultCraftingSlot extends Slot {
	
	
	@NotNull
	private final CraftingContainer craftingContainer;
	
	@NotNull
	private final Player player;
	
	private int amountCrafted;
	
	//package-private
	@SuppressWarnings( "SameParameterValue" )
	DyeCraftingTableResultCraftingSlot(
		@NotNull Player _player,
		@NotNull CraftingContainer _craftingContainer,
		@NotNull Container _container,
		int slotIndex,
		int xPosition,
		int yPosition ) {
		
		super( _container, slotIndex, xPosition, yPosition );
		player = _player;
		craftingContainer = _craftingContainer;
	}
	
	@Override
	public boolean mayPlace( @NotNull ItemStack stack ) {
		
		return false;
	}
	
	@NotNull
	@Override
	public ItemStack remove( int count ) {
		
		if( hasItem() ) {
			amountCrafted += Math.min( count, getItem().getCount() );
		}
		
		return super.remove( count );
	}
	
	@Override
	protected void onQuickCraft( @NotNull ItemStack stack, int count ) {
		
		amountCrafted += count;
		checkTakeAchievements( stack );
	}
	
	@Override
	protected void onSwapCraft( int count ) {
		
		amountCrafted += count;
	}
	
	@Override
	protected void checkTakeAchievements( @NotNull ItemStack stack ) {
		
		if( amountCrafted > 0 ) {
			stack.onCraftedBy( player.level(), player, amountCrafted );
			ForgeEventFactory.firePlayerCraftingEvent( player, stack, craftingContainer );
		}
		if( container instanceof RecipeHolder ) {
			( (RecipeHolder)container ).awardUsedRecipes( player, craftingContainer.getItems() );
		}
		amountCrafted = 0;
	}
	
	@Override
	public void onTake( @NotNull Player _player, @NotNull ItemStack stack ) {
		
		checkTakeAchievements( stack );
		ForgeHooks.setCraftingPlayer( _player );
		NonNullList<ItemStack> ingredients = _player.level().getRecipeManager().getRemainingItemsFor(
			ModRecipeTypesRegisterFactory.DYED,
			craftingContainer,
			_player.level()
		);
		ForgeHooks.setCraftingPlayer( null );
		for( int i = 0; i < ingredients.size(); ++i ) {
			ItemStack crafting_stack = craftingContainer.getItem( i );
			ItemStack ingredient = ingredients.get( i );
			if( !crafting_stack.isEmpty() ) {
				craftingContainer.removeItem( i, 1 );
				crafting_stack = craftingContainer.getItem( i );
			}
			
			if( !ingredient.isEmpty() ) {
				if( crafting_stack.isEmpty() ) {
					craftingContainer.setItem( i, ingredient );
				} else {
					if( ItemStack.isSameItem( crafting_stack, ingredient ) && ItemStack.isSameItemSameTags
						(
							crafting_stack,
							ingredient
						) ) {
						ingredient.grow( crafting_stack.getCount() );
						craftingContainer.setItem( i, ingredient );
					} else {
						if( !player.getInventory().add( ingredient ) ) {
							player.drop( ingredient, false );
						}
					}
				}
			}
		}
	}
}
