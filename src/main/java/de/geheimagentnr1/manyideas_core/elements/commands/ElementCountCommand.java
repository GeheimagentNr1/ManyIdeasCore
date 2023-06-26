package de.geheimagentnr1.manyideas_core.elements.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import de.geheimagentnr1.minecraft_forge_api.elements.commands.CommandInterface;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.TreeMap;
import java.util.TreeSet;


class ElementCountCommand implements CommandInterface {
	
	
	@NotNull
	@Override
	public LiteralArgumentBuilder<CommandSourceStack> build() {
		
		LiteralArgumentBuilder<CommandSourceStack> elementCountCommand = Commands.literal( "element_count" );
		elementCountCommand.executes( command -> {
			TreeSet<String> names = new TreeSet<>();
			TreeMap<String, Integer> block_item_counts = new TreeMap<>();
			TreeMap<String, Integer> item_counts = new TreeMap<>();
			TreeMap<String, Integer> block_counts = countBlocks( names );
			countItems( names, item_counts, block_item_counts );
			
			for( String name : names ) {
				command.getSource().sendSuccess( () -> Component.literal( name ), false );
				command.getSource().sendSuccess(
					() -> Component.literal( "block count: " + block_counts.get( name ) ),
					false
				);
				command.getSource().sendSuccess(
					() -> Component.literal( "block item count: " + block_item_counts.get( name ) ),
					false
				);
				command.getSource().sendSuccess(
					() -> Component.literal( "item count: " + item_counts.get( name ) ),
					false
				);
			}
			return Command.SINGLE_SUCCESS;
		} );
		return elementCountCommand;
	}
	
	private void countItems(
		@NotNull TreeSet<String> names,
		@NotNull TreeMap<String, Integer> item_counts,
		@NotNull TreeMap<String, Integer> block_item_counts ) {
		
		for( Item item : BuiltInRegistries.ITEM ) {
			if( item instanceof BlockItem ) {
				addElementToTreeMap( item, BuiltInRegistries.ITEM, names, block_item_counts );
			} else {
				addElementToTreeMap( item, BuiltInRegistries.ITEM, names, item_counts );
			}
		}
	}
	
	@NotNull
	private TreeMap<String, Integer> countBlocks( @NotNull TreeSet<String> names ) {
		
		TreeMap<String, Integer> block_counts = new TreeMap<>();
		
		for( Block block : BuiltInRegistries.BLOCK ) {
			addElementToTreeMap( block, BuiltInRegistries.BLOCK, names, block_counts );
		}
		return block_counts;
	}
	
	private <T> void addElementToTreeMap(
		@NotNull T element,
		@NotNull Registry<T> registry,
		@NotNull TreeSet<String> names,
		@NotNull TreeMap<String, Integer> counts ) {
		
		ResourceLocation resourceLocation = registry.getKey( element );
		if( resourceLocation != null ) {
			Integer block_count = counts.get( resourceLocation.getNamespace() );
			if( block_count == null ) {
				names.add( resourceLocation.getNamespace() );
			}
			block_count = block_count == null ? 0 : block_count;
			counts.put( resourceLocation.getNamespace(), block_count + 1 );
		}
	}
}
