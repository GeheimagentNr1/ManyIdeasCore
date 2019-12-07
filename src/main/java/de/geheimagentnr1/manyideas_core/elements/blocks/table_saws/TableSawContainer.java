package de.geheimagentnr1.manyideas_core.elements.blocks.table_saws;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.IntReferenceHolder;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.List;


public abstract class TableSawContainer extends Container {
	
	
	/**
	 * The list of items that can be accepted into the input slot of the TableSawtter container.
	 */
	private final ImmutableList<Item> ACCEPTED_INPUT_ITEMS;
	
	private final IWorldPosCallable worldPosCallable;
	
	/**
	 * The index of the selected recipe in the GUI.
	 */
	private final IntReferenceHolder selectedRecipe = IntReferenceHolder.single();
	
	private final World world;
	
	private List<TableSawRecipe> recipes = Lists.newArrayList();
	
	/**
	 * The {ItemStack} set in the input slot by the player.
	 */
	private ItemStack itemStackInput = ItemStack.EMPTY;
	
	/**
	 * Stores the game time of the last time the player took items from the the crafting result slot. This is used to
	 * prevent the sound from being played multiple times on the same tick.
	 */
	//package-private
	long lastOnTake;
	
	private final Slot inputInventorySlot;
	
	/**
	 * The inventory slot that stores the output of the crafting recipe.
	 */
	private final Slot outputInventorySlot;
	
	private Runnable inventoryUpdateListener = () -> {
	};
	
	@SuppressWarnings( "ThisEscapedInObjectConstruction" )
	private final IInventory inputInventory = new TableSawInputInventory( this );
	
	/**
	 * The inventory that stores the output of the crafting recipe.
	 */
	private final CraftResultInventory resultI = new CraftResultInventory();
	
	protected TableSawContainer( ContainerType<? extends TableSawContainer> tableSawContainerType, int windowIdIn,
		PlayerInventory playerInventoryIn ) {
		
		this( tableSawContainerType, windowIdIn, playerInventoryIn, IWorldPosCallable.DUMMY );
	}
	
	@SuppressWarnings( { "OverridableMethodCallDuringObjectConstruction", "AbstractMethodCallInConstructor",
		"OverriddenMethodCallDuringObjectConstruction", "ThisEscapedInObjectConstruction" } )
	protected TableSawContainer( ContainerType<? extends TableSawContainer> tableSawContainerType, int windowIdIn,
		PlayerInventory playerInventoryIn, IWorldPosCallable worldPosCallableIn ) {
		
		super( tableSawContainerType, windowIdIn );
		worldPosCallable = worldPosCallableIn;
		world = playerInventoryIn.player.world;
		inputInventorySlot = addSlot( new Slot( inputInventory, 0, 20, 33 ) );
		outputInventorySlot = addSlot( new TableSawOutputSlot( this, worldPosCallableIn, inputInventorySlot,
			resultI ) );
		for( int i = 0; i < 3; ++i ) {
			for( int j = 0; j < 9; ++j ) {
				addSlot( new Slot( playerInventoryIn, j + i * 9 + 9, 8 + j * 18, 84 + i * 18 ) );
			}
		}
		for( int k = 0; k < 9; ++k ) {
			addSlot( new Slot( playerInventoryIn, k, 8 + k * 18, 142 ) );
		}
		trackInt( selectedRecipe );
		HashSet<Item> acceptable_input = new HashSet<>();
		List<IRecipeType> acceptedRecipeTypes = getAcceptedRecipeTypes();
		world.getRecipeManager().getRecipes().forEach( iRecipe -> {
			if( acceptedRecipeTypes.contains( iRecipe.getType() ) ) {
				TableSawRecipe TableSawRecipe = (TableSawRecipe)iRecipe;
				ItemStack[] itemStacks = TableSawRecipe.getIngredients().get( 0 ).getMatchingStacks();
				for( ItemStack itemStack : itemStacks ) {
					acceptable_input.add( itemStack.getItem() );
				}
			}
		} );
		ACCEPTED_INPUT_ITEMS = ImmutableList.copyOf( acceptable_input );
	}
	
	protected abstract List<IRecipeType> getAcceptedRecipeTypes();
	
	/**
	 * Returns the index of the selected recipe.
	 */
	@OnlyIn( Dist.CLIENT )
	public int getSelectedRecipe() {
		
		return selectedRecipe.get();
	}
	
	@OnlyIn( Dist.CLIENT )
	public List<TableSawRecipe> getRecipeList() {
		
		return recipes;
	}
	
	@OnlyIn( Dist.CLIENT )
	public int getRecipeListSize() {
		
		return recipes.size();
	}
	
	@OnlyIn( Dist.CLIENT )
	public boolean hasItemsinInputSlot() {
		
		return inputInventorySlot.getHasStack() && !recipes.isEmpty();
	}
	
	/**
	 * Determines whether supplied player can use this container
	 */
	@Override
	public boolean canInteractWith( @Nonnull PlayerEntity playerIn ) {
		
		return isWithinUsableDistance( worldPosCallable, playerIn, getCanInteractBlock() );
	}
	
	protected abstract Block getCanInteractBlock();
	
	/**
	 * Handles the given Button-click on the server, currently only used by enchanting. Name is for legacy.
	 */
	@Override
	public boolean enchantItem( PlayerEntity playerIn, int id ) {
		
		if( id >= 0 && id < recipes.size() ) {
			selectedRecipe.set( id );
			updateRecipeResultSlot();
		}
		return true;
	}
	
	/**
	 * Callback for when the crafting matrix is changed.
	 */
	@Override
	public void onCraftMatrixChanged( IInventory inventoryIn ) {
		
		ItemStack itemstack = inputInventorySlot.getStack();
		if( itemstack.getItem() != itemStackInput.getItem() ) {
			itemStackInput = itemstack.copy();
			updateAvailableRecipes( inventoryIn, itemstack );
		}
		
	}
	
	private void updateAvailableRecipes( IInventory inventoryIn, ItemStack stack ) {
		
		recipes.clear();
		selectedRecipe.set( -1 );
		outputInventorySlot.putStack( ItemStack.EMPTY );
		if( !stack.isEmpty() ) {
			recipes = getAvaiableRecipes( inventoryIn, world );
		}
	}
	
	protected abstract List<TableSawRecipe> getAvaiableRecipes( IInventory inventoryIn, World _world );
	
	//package-private
	void updateRecipeResultSlot() {
		
		if( !recipes.isEmpty() ) {
			TableSawRecipe TableSawRecipe = recipes.get( selectedRecipe.get() );
			outputInventorySlot.putStack( TableSawRecipe.getCraftingResult( inputInventory ) );
		} else {
			outputInventorySlot.putStack( ItemStack.EMPTY );
		}
		
		detectAndSendChanges();
	}
	
	//package-private
	@OnlyIn( Dist.CLIENT )
	void setInventoryUpdateListener( Runnable listenerIn ) {
		
		inventoryUpdateListener = listenerIn;
	}
	
	//package-private
	Runnable getInventoryUpdateListener() {
		
		return inventoryUpdateListener;
	}
	
	/**
	 * Called to determine if the current slot is valid for the stack merging (double-click) code. The stack passed
	 * in is
	 * null for the initial slot that was double-clicked.
	 */
	@Override
	public boolean canMergeSlot( ItemStack stack, Slot slotIn ) {
		
		return false;
	}
	
	/**
	 * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player
	 * inventory and the other inventory(s).
	 */
	@Nonnull
	@Override
	public ItemStack transferStackInSlot( PlayerEntity playerIn, int index ) {
		
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = inventorySlots.get( index );
		if( slot != null && slot.getHasStack() ) {
			ItemStack itemstack1 = slot.getStack();
			Item item = itemstack1.getItem();
			itemstack = itemstack1.copy();
			if( index == 1 ) {
				item.onCreated( itemstack1, playerIn.world, playerIn );
				if( !mergeItemStack( itemstack1, 2, 38, true ) ) {
					return ItemStack.EMPTY;
				}
				slot.onSlotChange( itemstack1, itemstack );
			} else {
				if( index == 0 ) {
					if( !mergeItemStack( itemstack1, 2, 38, false ) ) {
						return ItemStack.EMPTY;
					}
				} else {
					if( ACCEPTED_INPUT_ITEMS.contains( item ) ) {
						if( !mergeItemStack( itemstack1, 0, 1, false ) ) {
							return ItemStack.EMPTY;
						}
					} else {
						if( index < 29 ) {
							if( !mergeItemStack( itemstack1, 29, 38, false ) ) {
								return ItemStack.EMPTY;
							}
						} else {
							if( index < 38 && !mergeItemStack( itemstack1, 2, 29, false ) ) {
								return ItemStack.EMPTY;
							}
						}
					}
				}
			}
			if( itemstack1.isEmpty() ) {
				slot.putStack( ItemStack.EMPTY );
			}
			slot.onSlotChanged();
			if( itemstack1.getCount() == itemstack.getCount() ) {
				return ItemStack.EMPTY;
			}
			slot.onTake( playerIn, itemstack1 );
			detectAndSendChanges();
		}
		return itemstack;
	}
	
	/**
	 * Called when the container is closed.
	 */
	@Override
	public void onContainerClosed( PlayerEntity playerIn ) {
		
		super.onContainerClosed( playerIn );
		resultI.removeStackFromSlot( 1 );
		worldPosCallable.consume( ( worldIn, pos ) -> clearContainer( playerIn, playerIn.world, inputInventory ) );
	}
}