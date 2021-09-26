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
	
	
	private final ImmutableList<Item> ACCEPTED_INPUT_ITEMS;
	
	private final IWorldPosCallable worldPosCallable;
	
	private final IntReferenceHolder selectedRecipe = IntReferenceHolder.single();
	
	private final World world;
	
	private List<TableSawRecipe> recipes = Lists.newArrayList();
	
	private ItemStack itemStackInput = ItemStack.EMPTY;
	
	//package-private
	long lastOnTake;
	
	private final Slot inputInventorySlot;
	
	private final Slot outputInventorySlot;
	
	private Runnable inventoryUpdateListener = () -> {
	};
	
	@SuppressWarnings( "ThisEscapedInObjectConstruction" )
	private final IInventory inputInventory = new TableSawInputInventory( this );
	
	private final CraftResultInventory resultI = new CraftResultInventory();
	
	protected TableSawContainer(
		ContainerType<? extends TableSawContainer> tableSawContainerType,
		int windowIdIn,
		PlayerInventory playerInventoryIn ) {
		
		this( tableSawContainerType, windowIdIn, playerInventoryIn, IWorldPosCallable.DUMMY );
	}
	
	@SuppressWarnings( {
		"OverridableMethodCallDuringObjectConstruction",
		"AbstractMethodCallInConstructor",
		"OverriddenMethodCallDuringObjectConstruction",
		"ThisEscapedInObjectConstruction",
		"rawtypes"
	} )
	protected TableSawContainer(
		ContainerType<? extends TableSawContainer> tableSawContainerType,
		int windowIdIn,
		PlayerInventory playerInventoryIn,
		IWorldPosCallable worldPosCallableIn ) {
		
		super( tableSawContainerType, windowIdIn );
		worldPosCallable = worldPosCallableIn;
		world = playerInventoryIn.player.world;
		inputInventorySlot = addSlot( new Slot( inputInventory, 0, 20, 33 ) );
		outputInventorySlot = addSlot( new TableSawOutputSlot(
			this,
			worldPosCallableIn,
			inputInventorySlot,
			resultI
		) );
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
				TableSawRecipe tableSawRecipe = (TableSawRecipe)iRecipe;
				ItemStack[] itemStacks = tableSawRecipe.getIngredients().get( 0 ).getMatchingStacks();
				for( ItemStack itemStack : itemStacks ) {
					acceptable_input.add( itemStack.getItem() );
				}
			}
		} );
		ACCEPTED_INPUT_ITEMS = ImmutableList.copyOf( acceptable_input );
	}
	
	@SuppressWarnings( "rawtypes" )
	protected abstract List<IRecipeType> getAcceptedRecipeTypes();
	
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
	
	@Override
	public boolean canInteractWith( @Nonnull PlayerEntity playerIn ) {
		
		return isWithinUsableDistance( worldPosCallable, playerIn, getCanInteractBlock() );
	}
	
	protected abstract Block getCanInteractBlock();
	
	@Override
	public boolean enchantItem( @Nonnull PlayerEntity playerIn, int id ) {
		
		if( id >= 0 && id < recipes.size() ) {
			selectedRecipe.set( id );
			updateRecipeResultSlot();
		}
		return true;
	}
	
	@Override
	public void onCraftMatrixChanged( @Nonnull IInventory inventoryIn ) {
		
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
		
		if( recipes.isEmpty() ) {
			outputInventorySlot.putStack( ItemStack.EMPTY );
		} else {
			TableSawRecipe tableSawRecipe = recipes.get( selectedRecipe.get() );
			outputInventorySlot.putStack( tableSawRecipe.getCraftingResult( inputInventory ) );
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
	
	@Override
	public boolean canMergeSlot( @Nonnull ItemStack stack, @Nonnull Slot slotIn ) {
		
		return false;
	}
	
	@Nonnull
	@Override
	public ItemStack transferStackInSlot( @Nonnull PlayerEntity playerIn, int index ) {
		
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
	
	@Override
	public void onContainerClosed( @Nonnull PlayerEntity playerIn ) {
		
		super.onContainerClosed( playerIn );
		resultI.removeStackFromSlot( 1 );
		worldPosCallable.consume( ( worldIn, pos ) -> clearContainer( playerIn, playerIn.world, inputInventory ) );
	}
}