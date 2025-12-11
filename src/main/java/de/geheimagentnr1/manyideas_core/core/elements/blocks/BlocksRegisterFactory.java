package de.geheimagentnr1.manyideas_core.core.elements.blocks;

import de.geheimagentnr1.manyideas_core.core.events.ModEventHandlerInterface;
import de.geheimagentnr1.manyideas_core.core.registry.RegistryEntry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public abstract class BlocksRegisterFactory implements ModEventHandlerInterface {
	
	
	@NotNull
	private final List<RegistryEntry<Block>> blocks = new ArrayList<>();
	
	@NotNull
	protected abstract String getModId();
	
	@NotNull
	protected List<RegistryEntry<Block>> blocks() {
		
		return List.of();
	}
	
	@NotNull
	protected List<RegistryEntry<BlockEntityType<?>>> blockEntityTypes() {
		
		return List.of();
	}
	
	@NotNull
	protected List<RegistryEntry<MenuType<?>>> menuTypes() {
		
		return List.of();
	}
	
	@NotNull
	public List<RegistryEntry<Block>> getBlocks() {
		
		return blocks;
	}
	
	protected void doRegisterEvent( @NotNull RegisterEvent event ) {
		
		String modId = getModId();
		event.register( Registries.BLOCK, helper -> {
			blocks.clear();
			blocks.addAll( blocks() );
			for( RegistryEntry<Block> entry : blocks ) {
				helper.register( entry.getResourceLocation( modId ), entry.getValue() );
			}
		} );
		event.register( Registries.ITEM, helper -> {
			for( RegistryEntry<Block> entry : blocks ) {
				Block block = entry.getValue();
				Item item;
				if( block instanceof BlockItemInterface blockItemInterface ) {
					item = blockItemInterface.getBlockItem( block, new Item.Properties() );
				} else {
					item = new BlockItem( block, new Item.Properties() );
				}
				helper.register( entry.getResourceLocation( modId ), item );
			}
		} );
		event.register( Registries.BLOCK_ENTITY_TYPE, helper -> {
			for( RegistryEntry<BlockEntityType<?>> entry : blockEntityTypes() ) {
				helper.register( entry.getResourceLocation( modId ), entry.getValue() );
			}
		} );
		event.register( Registries.MENU, helper -> {
			for( RegistryEntry<MenuType<?>> entry : menuTypes() ) {
				helper.register( entry.getResourceLocation( modId ), entry.getValue() );
			}
		} );
	}
}
