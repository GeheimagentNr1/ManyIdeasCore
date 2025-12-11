package de.geheimagentnr1.manyideas_core.elements.blocks.dye_crafting_table;

import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.RecipeCraftingHolder;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.event.EventHooks;
import org.jetbrains.annotations.NotNull;


//package-private
class DyeCraftingTableResultCraftingSlot extends Slot {
	
	
	@NotNull
	private final CraftingContainer craftingContainer;
	
	@NotNull
	private final Player player;
	
	private int removeCount;
	
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
			removeCount += Math.min( count, getItem().getCount() );
		}
		
		return super.remove( count );
	}
	
	@Override
	protected void onQuickCraft( @NotNull ItemStack stack, int count ) {
		
		removeCount += count;
		checkTakeAchievements( stack );
	}
	
	@Override
	protected void onSwapCraft( int count ) {
		
		removeCount += count;
	}
	
	@Override
	protected void checkTakeAchievements( @NotNull ItemStack stack ) {
		
		if( removeCount > 0 ) {
			stack.onCraftedBy( player.level(), player, removeCount );
			net.neoforged.neoforge.event.EventHooks.firePlayerCraftingEvent( player, stack, craftingContainer );
		}
		if( container instanceof RecipeCraftingHolder recipeCraftingHolder ) {
			recipeCraftingHolder.awardUsedRecipes( player, craftingContainer.getItems() );
		}
		removeCount = 0;
	}
	
	@Override
	public void onTake( @NotNull Player pPlayer, @NotNull ItemStack pStack ) {
		
		checkTakeAchievements( pStack );
		CraftingInput.Positioned positionedCraftInput = craftingContainer.asPositionedCraftInput();
		CraftingInput craftingInput = positionedCraftInput.input();
		int left = positionedCraftInput.left();
		int top = positionedCraftInput.top();
		net.neoforged.neoforge.common.CommonHooks.setCraftingPlayer( pPlayer );
		NonNullList<ItemStack> ingredients = pPlayer.level().getRecipeManager().getRemainingItemsFor(
			RecipeType.CRAFTING,
			craftingInput,
			pPlayer.level()
		);
		net.neoforged.neoforge.common.CommonHooks.setCraftingPlayer( null );
		
		for( int i = 0; i < craftingInput.height(); i++ ) {
			for( int j = 0; j < craftingInput.width(); j++ ) {
				int stackIndex = j + left + ( i + top ) * craftingContainer.getWidth();
				ItemStack crafting_stack = craftingContainer.getItem( stackIndex );
				ItemStack ingredient = ingredients.get( j + i * craftingInput.width() );
				if( !crafting_stack.isEmpty() ) {
					this.craftingContainer.removeItem( stackIndex, 1 );
					crafting_stack = this.craftingContainer.getItem( stackIndex );
				}
				
				if( !ingredient.isEmpty() ) {
					if( crafting_stack.isEmpty() ) {
						this.craftingContainer.setItem( stackIndex, ingredient );
					} else {
						if( ItemStack.isSameItemSameComponents( crafting_stack, ingredient ) ) {
							//ingredient.grow( - crafting_stack.getCount() );
							this.craftingContainer.setItem( stackIndex, ingredient );
						} else {
							if( !this.player.getInventory().add( ingredient ) ) {
								this.player.drop( ingredient, false );
							}
						}
					}
				}
			}
		}
	}
	
	@Override
	public boolean isFake() {
		
		return true;
	}
}
