package de.geheimagentnr1.manyideas_core.elements.blocks.dye_crafting_table;

import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideas_core.elements.recipes.RecipeTypes;
import de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes.DyedRecipe;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.CraftingResultSlot;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SSetSlotPacket;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.Optional;


public class DyeCraftingTableContainer extends Container {
	
	
	private final CraftingInventory craftingInventory;
	
	private final CraftResultInventory resultInventory;
	
	private final IWorldPosCallable worldPosCallable;
	
	private final PlayerEntity player;
	
	public DyeCraftingTableContainer( int windowID, PlayerInventory playerInventory ) {
		
		this( windowID, playerInventory, IWorldPosCallable.DUMMY );
	}
	
	//package-private
	@SuppressWarnings( { "OverridableMethodCallDuringObjectConstruction", "ThisEscapedInObjectConstruction" } )
	DyeCraftingTableContainer( int windowID, PlayerInventory playerInventory, IWorldPosCallable _worldPosCallable ) {
		
		super( ModBlocks.DYE_CRAFTING_TABLE_CONTAINER, windowID );
		craftingInventory = new CraftingInventory( this, 3, 3 );
		resultInventory = new CraftResultInventory();
		worldPosCallable = _worldPosCallable;
		player = playerInventory.player;
		addSlot( new CraftingResultSlot( playerInventory.player, craftingInventory, resultInventory, 0, 124, 35 ) );
		for( int i = 0; i < 3; i++ ) {
			for( int j = 0; j < 3; j++ ) {
				addSlot(
					new Slot( craftingInventory, j + i * 3, 30 + j * 18, 17 + i * 18 ) );
			}
		}
		
		for( int i = 0; i < 3; i++ ) {
			for( int j = 0; j < 9; j++ ) {
				addSlot( new Slot( playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18 ) );
			}
		}
		
		for( int i = 0; i < 9; i++ ) {
			addSlot( new Slot( playerInventory, i, 8 + i * 18, 142 ) );
		}
		
	}
	
	private static void changeCaftingSlot( int windowId, World world, PlayerEntity player,
		CraftingInventory craftingInventory, CraftResultInventory resultInventory ) {
		
		if( !world.isRemote ) {
			ServerPlayerEntity serverPlayer = (ServerPlayerEntity)player;
			ItemStack stack = ItemStack.EMPTY;
			Optional<DyedRecipe> recipeOptional = Objects.requireNonNull( world.getServer() ).getRecipeManager()
				.getRecipe( RecipeTypes.DYED, craftingInventory, world );//TODO
			if( recipeOptional.isPresent() ) {
				DyedRecipe recipe = recipeOptional.get();
				stack = recipe.getCraftingResult( craftingInventory );
			}
			resultInventory.setInventorySlotContents( 0, stack );
			serverPlayer.connection.sendPacket( new SSetSlotPacket( windowId, 0, stack ) );
		}
	}
	
	@Override
	public void onCraftMatrixChanged( IInventory inventory ) {
		
		worldPosCallable.consume( ( world, pos ) ->
			changeCaftingSlot( windowId, world, player, craftingInventory, resultInventory ) );
	}
	
	@Override
	public void onContainerClosed( PlayerEntity player ) {
		
		super.onContainerClosed( player );
		worldPosCallable.consume( ( world, pos ) -> clearContainer( player, world, craftingInventory ) );
	}
	
	@Override
	public boolean canInteractWith( @Nonnull PlayerEntity playerIn ) {
		
		return isWithinUsableDistance( worldPosCallable, playerIn, ModBlocks.DYE_CRAFTING_TABLE );
	}
	
	@Nonnull
	@Override
	public ItemStack transferStackInSlot( PlayerEntity player, int index ) {
		
		ItemStack resultStack = ItemStack.EMPTY;
		Slot slot = inventorySlots.get( index );
		if( slot != null && slot.getHasStack() ) {
			ItemStack stack = slot.getStack();
			resultStack = stack.copy();
			if( index == 0 ) {
				worldPosCallable.consume( ( world, pos ) -> stack.getItem().onCreated( stack, world, player ) );
				if( !mergeItemStack( stack, 10, 46, true ) ) {
					return ItemStack.EMPTY;
				}
				slot.onSlotChange( stack, resultStack );
			} else {
				if( index >= 10 && index < 37 ) {
					if( !mergeItemStack( stack, 37, 46, false ) ) {
						return ItemStack.EMPTY;
					}
				} else {
					if( index >= 37 && index < 46 ) {
						if( !mergeItemStack( stack, 10, 37, false ) ) {
							return ItemStack.EMPTY;
						}
					} else {
						if( !mergeItemStack( stack, 10, 46, false ) ) {
							return ItemStack.EMPTY;
						}
					}
				}
			}
			if( stack.isEmpty() ) {
				slot.putStack( ItemStack.EMPTY );
			} else {
				slot.onSlotChanged();
			}
			if( stack.getCount() == resultStack.getCount() ) {
				return ItemStack.EMPTY;
			}
			ItemStack dropStack = slot.onTake( player, stack );
			if( index == 0 ) {
				player.dropItem( dropStack, false );
			}
		}
		
		return resultStack;
	}
	
	@Override
	public boolean canMergeSlot( ItemStack stack, Slot slot ) {
		
		return slot.inventory != resultInventory;
	}
}
