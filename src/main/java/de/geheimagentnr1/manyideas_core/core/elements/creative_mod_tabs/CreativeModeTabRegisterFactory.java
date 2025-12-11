package de.geheimagentnr1.manyideas_core.core.elements.creative_mod_tabs;

import de.geheimagentnr1.manyideas_core.core.events.ModEventHandlerInterface;
import de.geheimagentnr1.manyideas_core.core.registry.RegistryEntry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public abstract class CreativeModeTabRegisterFactory implements ModEventHandlerInterface {
	
	
	@NotNull
	protected abstract List<CreativeModeTabFactory> factories();
	
	protected void doRegisterEvent( @NotNull RegisterEvent event ) {
		
		event.register( Registries.CREATIVE_MODE_TAB, helper -> {
			for( CreativeModeTabFactory factory : factories() ) {
				helper.register(
					net.minecraft.resources.ResourceLocation.fromNamespaceAndPath( "manyideas_core", factory.getRegistryName() ),
					CreativeModeTab.builder()
						.title( Component.translatable( "itemGroup." + factory.getRegistryName() ) )
						.icon( () -> new ItemStack( factory.getIconItem() ) )
						.displayItems( ( parameters, output ) -> {
							for( RegistryEntry<Block> block : factory.getDisplayBlocks() ) {
								factory.buildItemStacksOfBlockRegistryEntry( block ).forEach( output::accept );
							}
							for( RegistryEntry<Item> item : factory.getDisplayItems() ) {
								output.accept( new ItemStack( item.getValue() ) );
							}
						} )
						.build()
				);
			}
		} );
	}
}
