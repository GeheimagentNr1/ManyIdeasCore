package de.geheimagentnr1.manyideas_core.elements.blocks.table_saws;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.List;


public abstract class TableSawMenu extends AbstractContainerMenu {
	
	
	private final ImmutableList<Item> ACCEPTED_INPUT_ITEMS;
	
	private final ContainerLevelAccess containerLevelAccess;
	
	private final DataSlot selectedRecipe = DataSlot.standalone();
	
	private final Level level;
	
	private List<TableSawRecipe> recipes = Lists.newArrayList();
	
	private ItemStack itemStackInput = ItemStack.EMPTY;
	
	//package-private
	long lastOnTake;
	
	private final Slot inputInventorySlot;
	
	private final Slot outputInventorySlot;
	
	private Runnable inventoryUpdateListener = () -> {
	};
	
	@SuppressWarnings( "ThisEscapedInObjectConstruction" )
	private final Container inputInventory = new TableSawInputContainer( this );
	
	private final ResultContainer resultContainer = new ResultContainer();
	
	protected TableSawMenu(
		MenuType<? extends TableSawMenu> tableSawContainerType,
		int menuId,
		Inventory inventory ) {
		
		this( tableSawContainerType, menuId, inventory, ContainerLevelAccess.NULL );
	}
	
	@SuppressWarnings( {
		"OverridableMethodCallDuringObjectConstruction",
		"AbstractMethodCallInConstructor",
		"OverriddenMethodCallDuringObjectConstruction",
		"ThisEscapedInObjectConstruction",
		"rawtypes"
	} )
	protected TableSawMenu(
		MenuType<? extends TableSawMenu> tableSawMenu,
		int menuId,
		Inventory inventory,
		ContainerLevelAccess _worldPosCallable ) {
		
		super( tableSawMenu, menuId );
		containerLevelAccess = _worldPosCallable;
		level = inventory.player.level;
		inputInventorySlot = addSlot( new Slot( inputInventory, 0, 20, 33 ) );
		outputInventorySlot = addSlot( new TableSawOutputSlot(
			this,
			_worldPosCallable,
			inputInventorySlot,
			resultContainer
		) );
		for( int i = 0; i < 3; ++i ) {
			for( int j = 0; j < 9; ++j ) {
				addSlot( new Slot( inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18 ) );
			}
		}
		for( int k = 0; k < 9; ++k ) {
			addSlot( new Slot( inventory, k, 8 + k * 18, 142 ) );
		}
		addDataSlot( selectedRecipe );
		HashSet<Item> acceptable_input = new HashSet<>();
		List<RecipeType> acceptedRecipeTypes = getAcceptedRecipeTypes();
		level.getRecipeManager().getRecipes().forEach( iRecipe -> {
			if( acceptedRecipeTypes.contains( iRecipe.getType() ) ) {
				TableSawRecipe tableSawRecipe = (TableSawRecipe)iRecipe;
				ItemStack[] itemStacks = tableSawRecipe.getIngredients().get( 0 ).getItems();
				for( ItemStack itemStack : itemStacks ) {
					acceptable_input.add( itemStack.getItem() );
				}
			}
		} );
		ACCEPTED_INPUT_ITEMS = ImmutableList.copyOf( acceptable_input );
	}
	
	@SuppressWarnings( "rawtypes" )
	protected abstract List<RecipeType> getAcceptedRecipeTypes();
	
	@OnlyIn( Dist.CLIENT )
	public int getSelectedRecipeIndex() {
		
		return selectedRecipe.get();
	}
	
	@OnlyIn( Dist.CLIENT )
	public List<TableSawRecipe> getRecipes() {
		
		return recipes;
	}
	
	@OnlyIn( Dist.CLIENT )
	public int getNumRecipes() {
		
		return recipes.size();
	}
	
	@OnlyIn( Dist.CLIENT )
	public boolean hasInputItem() {
		
		return inputInventorySlot.hasItem() && !recipes.isEmpty();
	}
	
	@Override
	public boolean stillValid( @Nonnull Player player ) {
		
		return stillValid( containerLevelAccess, player, getCanInteractBlock() );
	}
	
	protected abstract Block getCanInteractBlock();
	
	@Override
	public boolean clickMenuButton( @Nonnull Player player, int id ) {
		
		if( id >= 0 && id < recipes.size() ) {
			selectedRecipe.set( id );
			updateRecipeResultSlot();
		}
		return true;
	}
	
	@Override
	public void slotsChanged( @Nonnull Container container ) {
		
		ItemStack itemstack = inputInventorySlot.getItem();
		if( itemstack.getItem() != itemStackInput.getItem() ) {
			itemStackInput = itemstack.copy();
			updateAvailableRecipes( container, itemstack );
		}
		
	}
	
	private void updateAvailableRecipes( Container inventory, ItemStack stack ) {
		
		recipes.clear();
		selectedRecipe.set( -1 );
		outputInventorySlot.set( ItemStack.EMPTY );
		if( !stack.isEmpty() ) {
			recipes = getAvaiableRecipes( inventory, level );
		}
	}
	
	protected abstract List<TableSawRecipe> getAvaiableRecipes( Container container, Level _level );
	
	//package-private
	void updateRecipeResultSlot() {
		
		if( recipes.isEmpty() ) {
			outputInventorySlot.set( ItemStack.EMPTY );
		} else {
			TableSawRecipe tableSawRecipe = recipes.get( selectedRecipe.get() );
			outputInventorySlot.set( tableSawRecipe.assemble( inputInventory ) );
		}
		broadcastChanges();
	}
	
	//package-private
	@OnlyIn( Dist.CLIENT )
	void setInventoryUpdateListener( Runnable listener ) {
		
		inventoryUpdateListener = listener;
	}
	
	//package-private
	Runnable getInventoryUpdateListener() {
		
		return inventoryUpdateListener;
	}
	
	@Override
	public boolean canTakeItemForPickAll( @Nonnull ItemStack stack, @Nonnull Slot slot ) {
		
		return false;
	}
	
	@Nonnull
	@Override
	public ItemStack quickMoveStack( @Nonnull Player player, int index ) {
		
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = slots.get( index );
		if( slot.hasItem() ) {
			ItemStack itemstack1 = slot.getItem();
			Item item = itemstack1.getItem();
			itemstack = itemstack1.copy();
			if( index == 1 ) {
				item.onCraftedBy( itemstack1, player.level, player );
				if( !moveItemStackTo( itemstack1, 2, 38, true ) ) {
					return ItemStack.EMPTY;
				}
				slot.onQuickCraft( itemstack1, itemstack );
			} else {
				if( index == 0 ) {
					if( !moveItemStackTo( itemstack1, 2, 38, false ) ) {
						return ItemStack.EMPTY;
					}
				} else {
					if( ACCEPTED_INPUT_ITEMS.contains( item ) ) {
						if( !moveItemStackTo( itemstack1, 0, 1, false ) ) {
							return ItemStack.EMPTY;
						}
					} else {
						if( index < 29 ) {
							if( !moveItemStackTo( itemstack1, 29, 38, false ) ) {
								return ItemStack.EMPTY;
							}
						} else {
							if( index < 38 && !moveItemStackTo( itemstack1, 2, 29, false ) ) {
								return ItemStack.EMPTY;
							}
						}
					}
				}
			}
			if( itemstack1.isEmpty() ) {
				slot.set( ItemStack.EMPTY );
			}
			slot.setChanged();
			if( itemstack1.getCount() == itemstack.getCount() ) {
				return ItemStack.EMPTY;
			}
			slot.onTake( player, itemstack1 );
			broadcastChanges();
		}
		return itemstack;
	}
	
	@Override
	public void removed( @Nonnull Player player ) {
		
		super.removed( player );
		resultContainer.removeItemNoUpdate( 1 );
		containerLevelAccess.execute( ( worldIn, pos ) -> clearContainer( player, inputInventory ) );
	}
	
	public ResultContainer getResultContainer() {
		
		return resultContainer;
	}
}