package de.geheimagentnr1.manyideas_core.elements.blocks.dye_crafting_table;

import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocksRegisterFactory;
import de.geheimagentnr1.manyideas_core.elements.recipes.ModRecipeTypesRegisterFactory;
import de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes.DyedRecipe;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Optional;


public class DyeCraftingTableMenu extends AbstractContainerMenu {
	
	
	@NotNull
	private final CraftingContainer craftingContainer;
	
	@NotNull
	private final ResultContainer resultContainer;
	
	@NotNull
	private final ContainerLevelAccess containerLevelAccess;
	
	@NotNull
	private final Player player;
	
	public DyeCraftingTableMenu( int windowId, @NotNull Inventory inventory ) {
		
		this( windowId, inventory, ContainerLevelAccess.NULL );
	}
	
	//package-private
	@SuppressWarnings( { "OverridableMethodCallDuringObjectConstruction", "ThisEscapedInObjectConstruction" } )
	DyeCraftingTableMenu(
		int windowId,
		@NotNull Inventory inventory,
		@NotNull ContainerLevelAccess _containerLevelAccess ) {
		
		super( ModBlocksRegisterFactory.DYE_CRAFTING_TABLE_MENU, windowId );
		craftingContainer = new TransientCraftingContainer( this, 3, 3 );
		resultContainer = new ResultContainer();
		containerLevelAccess = _containerLevelAccess;
		player = inventory.player;
		addSlot( new DyeCraftingTableResultCraftingSlot(
			inventory.player,
			craftingContainer,
			resultContainer,
			0,
			124,
			35
		) );
		for( int i = 0; i < 3; i++ ) {
			for( int j = 0; j < 3; j++ ) {
				addSlot( new Slot( craftingContainer, j + i * 3, 30 + j * 18, 17 + i * 18 ) );
			}
		}
		for( int i = 0; i < 3; i++ ) {
			for( int j = 0; j < 9; j++ ) {
				addSlot( new Slot( inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18 ) );
			}
		}
		for( int i = 0; i < 9; i++ ) {
			addSlot( new Slot( inventory, i, 8 + i * 18, 142 ) );
		}
	}
	
	private static void changeCaftingSlot(
		@NotNull AbstractContainerMenu menu,
		@NotNull Level level,
		@NotNull Player player,
		@NotNull CraftingContainer craftingContainer,
		@NotNull ResultContainer resultContainer ) {
		
		if( !level.isClientSide() ) {
			ServerPlayer serverPlayer = (ServerPlayer)player;
			ItemStack stack = ItemStack.EMPTY;
			Optional<DyedRecipe> recipeOptional =
				Objects.requireNonNull( level.getServer() ).getRecipeManager().getRecipeFor(
					ModRecipeTypesRegisterFactory.DYED,
					craftingContainer,
					level
				);
			if( recipeOptional.isPresent() ) {
				DyedRecipe recipe = recipeOptional.get();
				stack = recipe.assemble( craftingContainer, level.registryAccess() );
			}
			resultContainer.setItem( 0, stack );
			menu.setRemoteSlot( 0, stack );
			serverPlayer.connection.send( new ClientboundContainerSetSlotPacket(
				menu.containerId,
				menu.incrementStateId(),
				0,
				stack
			) );
		}
	}
	
	@Override
	public void slotsChanged( @NotNull Container container ) {
		
		containerLevelAccess.execute( ( level, pos ) -> changeCaftingSlot(
			this,
			level,
			player,
			craftingContainer,
			resultContainer
		) );
	}
	
	@Override
	public void removed( @NotNull Player _player ) {
		
		super.removed( _player );
		containerLevelAccess.execute( ( level, pos ) -> clearContainer( _player, craftingContainer ) );
	}
	
	@Override
	public boolean stillValid( @NotNull Player _player ) {
		
		return stillValid( containerLevelAccess, _player, ModBlocksRegisterFactory.DYE_CRAFTING_TABLE );
	}
	
	@NotNull
	@Override
	public ItemStack quickMoveStack( @NotNull Player _player, int index ) {
		
		ItemStack resultStack = ItemStack.EMPTY;
		Slot slot = slots.get( index );
		if( slot.hasItem() ) {
			ItemStack stack = slot.getItem();
			resultStack = stack.copy();
			if( index == 0 ) {
				containerLevelAccess.execute( ( level, pos ) -> stack.getItem().onCraftedBy( stack, level, _player ) );
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
			slot.onTake( _player, stack );
			if( index == 0 ) {
				_player.drop( stack, false );
			}
		}
		return resultStack;
	}
	
	@Override
	public boolean canTakeItemForPickAll( @NotNull ItemStack stack, @NotNull Slot slot ) {
		
		return slot.container != resultContainer;
	}
}
