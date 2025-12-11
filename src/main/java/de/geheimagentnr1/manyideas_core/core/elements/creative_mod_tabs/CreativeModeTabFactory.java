package de.geheimagentnr1.manyideas_core.core.elements.creative_mod_tabs;

import de.geheimagentnr1.manyideas_core.core.registry.RegistryEntry;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Stream;


public interface CreativeModeTabFactory {
	
	
	@NotNull
	String getRegistryName();
	
	@NotNull
	ItemLike getIconItem();
	
	@NotNull
	List<RegistryEntry<Block>> getDisplayBlocks();
	
	@NotNull
	default Stream<ItemStack> buildItemStacksOfBlockRegistryEntry( RegistryEntry<Block> registryEntry ) {
		
		return Stream.of( new ItemStack( registryEntry.getValue() ) );
	}
	
	@NotNull
	List<RegistryEntry<Item>> getDisplayItems();
}
