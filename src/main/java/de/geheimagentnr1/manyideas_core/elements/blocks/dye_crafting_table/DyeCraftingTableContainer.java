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
	
	public DyeCraftingTableContainer( int containerID, PlayerInventory playerInventory ) {
		
		this( containerID, playerInventory, IWorldPosCallable.NULL );
	}
	
	//package-private
	@SuppressWarnings( { "OverridableMethodCallDuringObjectConstruction", "ThisEscapedInObjectConstruction" } )
	DyeCraftingTableContainer(
		int containerID,
		PlayerInventory playerInventory,
		IWorldPosCallable _worldPosCallable ) {
		
		super( ModBlocks.DYE_CRAFTING_TABLE_CONTAINER, containerID );
		craftingInventory = new CraftingInventory( this, 3, 3 );
		resultInventory = new CraftResultInventory();
		worldPosCallable = _worldPosCallable;
		player = playerInventory.player;
		addSlot( new DyeCraftingTableResultCraftingSlot(
			playerInventory.player,
			craftingInventory,
			resultInventory,
			0,
			124,
			35
		) );
		for( int i = 0; i < 3; i++ ) {
			for( int j = 0; j < 3; j++ ) {
				addSlot( new Slot( craftingInventory, j + i * 3, 30 + j * 18, 17 + i * 18 ) );
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
	
	private static void changeCaftingSlot(
		int windowId,
		World world,
		PlayerEntity player,
		CraftingInventory craftingInventory,
		CraftResultInventory resultInventory ) {
		
		if( !world.isClientSide() ) {
			ServerPlayerEntity serverPlayer = (ServerPlayerEntity)player;
			ItemStack stack = ItemStack.EMPTY;
			Optional<DyedRecipe> recipeOptional =
				Objects.requireNonNull( world.getServer() ).getRecipeManager().getRecipeFor(
					RecipeTypes.DYED,
					craftingInventory,
					world
				);
			if( recipeOptional.isPresent() ) {
				DyedRecipe recipe = recipeOptional.get();
				stack = recipe.assemble( craftingInventory );
			}
			resultInventory.setItem( 0, stack );
			serverPlayer.connection.send( new SSetSlotPacket( windowId, 0, stack ) );
		}
	}
	
	@Override
	public void slotsChanged( @Nonnull IInventory inventory ) {
		
		worldPosCallable.execute( ( world, pos ) -> changeCaftingSlot(
			containerId,
			world,
			player,
			craftingInventory,
			resultInventory
		) );
	}
	
	@Override
	public void removed( @Nonnull PlayerEntity _player ) {
		
		super.removed( _player );
		worldPosCallable.execute( ( world, pos ) -> clearContainer( _player, world, craftingInventory ) );
	}
	
	@Override
	public boolean stillValid( @Nonnull PlayerEntity _player ) {
		
		return stillValid( worldPosCallable, _player, ModBlocks.DYE_CRAFTING_TABLE );
	}
	
	@Nonnull
	@Override
	public ItemStack quickMoveStack( @Nonnull PlayerEntity _player, int index ) {
		
		ItemStack resultStack = ItemStack.EMPTY;
		Slot slot = slots.get( index );
		if( slot != null && slot.hasItem() ) {
			ItemStack stack = slot.getItem();
			resultStack = stack.copy();
			if( index == 0 ) {
				worldPosCallable.execute( ( world, pos ) -> stack.getItem().onCraftedBy( stack, world, _player ) );
				if( !moveItemStackTo( stack, 10, 46, true ) ) {
					return ItemStack.EMPTY;
				}
				slot.onQuickCraft( stack, resultStack );
			} else {
				if( index >= 10 && index < 37 ) {
					if( !moveItemStackTo( stack, 37, 46, false ) ) {
						return ItemStack.EMPTY;
					}
				} else {
					if( index >= 37 && index < 46 ) {
						if( !moveItemStackTo( stack, 10, 37, false ) ) {
							return ItemStack.EMPTY;
						}
					} else {
						if( !moveItemStackTo( stack, 10, 46, false ) ) {
							return ItemStack.EMPTY;
						}
					}
				}
			}
			if( stack.isEmpty() ) {
				slot.set( ItemStack.EMPTY );
			} else {
				slot.setChanged();
			}
			if( stack.getCount() == resultStack.getCount() ) {
				return ItemStack.EMPTY;
			}
			ItemStack dropStack = slot.onTake( _player, stack );
			if( index == 0 ) {
				_player.drop( dropStack, false );
			}
		}
		return resultStack;
	}
	
	@Override
	public boolean canTakeItemForPickAll( @Nonnull ItemStack stack, Slot slot ) {
		
		return slot.container != resultInventory;
	}
}
