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
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.List;


public abstract class TableSawMenu extends AbstractContainerMenu {
	
	
	@NotNull
	private final ImmutableList<Item> ACCEPTED_INPUT_ITEMS;
	
	@NotNull
	private final ContainerLevelAccess containerLevelAccess;
	
	@NotNull
	private final DataSlot selectedRecipe = DataSlot.standalone();
	
	@NotNull
	private final Level level;
	
	@NotNull
	private List<TableSawRecipe> recipes = Lists.newArrayList();
	
	@NotNull
	private ItemStack itemStackInput = ItemStack.EMPTY;
	
	//package-private
	long lastOnTake;
	
	@NotNull
	private final Slot inputInventorySlot;
	
	@NotNull
	private final Slot outputInventorySlot;
	
	@NotNull
	private Runnable inventoryUpdateListener = () -> {
	};
	
	@SuppressWarnings( "ThisEscapedInObjectConstruction" )
	@NotNull
	private final Container inputInventory = new TableSawInputContainer( this );
	
	@NotNull
	private final ResultContainer resultContainer = new ResultContainer();
	
	protected TableSawMenu(
		@NotNull MenuType<? extends TableSawMenu> tableSawMenu,
		@NotNull int windowId,
		@NotNull Inventory inventory ) {
		
		this( tableSawMenu, windowId, inventory, ContainerLevelAccess.NULL );
	}
	
	@SuppressWarnings( {
		"OverridableMethodCallDuringObjectConstruction",
		"AbstractMethodCallInConstructor",
		"OverriddenMethodCallDuringObjectConstruction",
		"ThisEscapedInObjectConstruction"
	} )
	protected TableSawMenu(
		@NotNull MenuType<? extends TableSawMenu> tableSawMenu,
		int windowId,
		@NotNull Inventory inventory,
		@NotNull ContainerLevelAccess _containerLevelAccess ) {
		
		super( tableSawMenu, windowId );
		containerLevelAccess = _containerLevelAccess;
		level = inventory.player.level();
		inputInventorySlot = addSlot( new Slot( inputInventory, 0, 20, 33 ) );
		outputInventorySlot = addSlot( new TableSawOutputSlot(
			this,
			_containerLevelAccess,
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
		List<RecipeType<?>> acceptedRecipeTypes = getAcceptedRecipeTypes();
		level.getRecipeManager().getRecipes().forEach( iRecipe -> {
			if( acceptedRecipeTypes.contains( iRecipe.value().getType() ) ) {
				TableSawRecipe tableSawRecipe = (TableSawRecipe)iRecipe.value();
				ItemStack[] itemStacks = tableSawRecipe.getIngredients().get( 0 ).getItems();
				for( ItemStack itemStack : itemStacks ) {
					acceptable_input.add( itemStack.getItem() );
				}
			}
		} );
		ACCEPTED_INPUT_ITEMS = ImmutableList.copyOf( acceptable_input );
	}
	
	@NotNull
	protected abstract List<RecipeType<?>> getAcceptedRecipeTypes();
	
	@OnlyIn( Dist.CLIENT )
	public int getSelectedRecipeIndex() {
		
		return selectedRecipe.get();
	}
	
	@OnlyIn( Dist.CLIENT )
	@NotNull
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
	public boolean stillValid( @NotNull Player player ) {
		
		return stillValid( containerLevelAccess, player, getCanInteractBlock() );
	}
	
	protected abstract Block getCanInteractBlock();
	
	@Override
	public boolean clickMenuButton( @NotNull Player player, int id ) {
		
		if( id >= 0 && id < recipes.size() ) {
			selectedRecipe.set( id );
			updateRecipeResultSlot();
		}
		return true;
	}
	
	@Override
	public void slotsChanged( @NotNull Container container ) {
		
		ItemStack itemstack = inputInventorySlot.getItem();
		if( itemstack.getItem() != itemStackInput.getItem() ) {
			itemStackInput = itemstack.copy();
			updateAvailableRecipes( container, itemstack );
		}
		
	}
	
	private void updateAvailableRecipes( @NotNull Container inventory, @NotNull ItemStack stack ) {
		
		recipes.clear();
		selectedRecipe.set( -1 );
		outputInventorySlot.set( ItemStack.EMPTY );
		if( !stack.isEmpty() ) {
			recipes = getAvaiableRecipes( inventory, level );
		}
	}
	
	@NotNull
	protected abstract List<TableSawRecipe> getAvaiableRecipes( @NotNull Container container, @NotNull Level _level );
	
	//package-private
	void updateRecipeResultSlot() {
		
		if( recipes.isEmpty() ) {
			outputInventorySlot.set( ItemStack.EMPTY );
		} else {
			TableSawRecipe tableSawRecipe = recipes.get( selectedRecipe.get() );
			outputInventorySlot.set( tableSawRecipe.assemble( inputInventory, level.registryAccess() ) );
		}
		broadcastChanges();
	}
	
	//package-private
	@OnlyIn( Dist.CLIENT )
	void setInventoryUpdateListener( @NotNull Runnable listener ) {
		
		inventoryUpdateListener = listener;
	}
	
	//package-private
	@NotNull
	Runnable getInventoryUpdateListener() {
		
		return inventoryUpdateListener;
	}
	
	@Override
	public boolean canTakeItemForPickAll( @NotNull ItemStack stack, @NotNull Slot slot ) {
		
		return false;
	}
	
	@NotNull
	@Override
	public ItemStack quickMoveStack( @NotNull Player player, int index ) {
		
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = slots.get( index );
		if( slot.hasItem() ) {
			ItemStack itemstack1 = slot.getItem();
			Item item = itemstack1.getItem();
			itemstack = itemstack1.copy();
			if( index == 1 ) {
				item.onCraftedBy( itemstack1, player.level(), player );
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
	public void removed( @NotNull Player player ) {
		
		super.removed( player );
		resultContainer.removeItemNoUpdate( 1 );
		containerLevelAccess.execute( ( levelIn, pos ) -> clearContainer( player, inputInventory ) );
	}
	
	@NotNull
	public ResultContainer getResultContainer() {
		
		return resultContainer;
	}
}