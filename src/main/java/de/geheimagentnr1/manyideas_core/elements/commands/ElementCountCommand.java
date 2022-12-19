package de.geheimagentnr1.manyideas_core.elements.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.TreeMap;
import java.util.TreeSet;


public class ElementCountCommand {
	
	
	@SuppressWarnings( "SameReturnValue" )
	public static void register( CommandDispatcher<CommandSourceStack> dispatcher ) {
		
		LiteralArgumentBuilder<CommandSourceStack> elementCountCommand = Commands.literal( "element_count" );
		elementCountCommand.executes( command -> {
			TreeSet<String> names = new TreeSet<>();
			TreeMap<String, Integer> block_item_counts = new TreeMap<>();
			TreeMap<String, Integer> item_counts = new TreeMap<>();
			TreeMap<String, Integer> block_counts = countBlocks( names );
			countItems( names, item_counts, block_item_counts );
			
			for( String name : names ) {
				command.getSource().sendSuccess( Component.literal( name ), false );
				command.getSource().sendSuccess(
					Component.literal( "block count: " + block_counts.get( name ) ),
					false
				);
				command.getSource().sendSuccess( Component.literal(
					"block item count: " + block_item_counts.get( name ) ), false );
				command.getSource().sendSuccess(
					Component.literal( "item count: " + item_counts.get( name ) ),
					false
				);
			}
			return 1;
		} );
		dispatcher.register( elementCountCommand );
	}
	
	@SuppressWarnings( "deprecation" )
	private static void countItems(
		TreeSet<String> names,
		TreeMap<String, Integer> item_counts,
		TreeMap<String, Integer> block_item_counts ) {
		
		for( Item item : BuiltInRegistries.ITEM ) {
			if( item instanceof BlockItem ) {
				addElementToTreeMap( item, BuiltInRegistries.ITEM, names, block_item_counts );
			} else {
				addElementToTreeMap( item, BuiltInRegistries.ITEM, names, item_counts );
			}
		}
	}
	
	@SuppressWarnings( "deprecation" )
	private static TreeMap<String, Integer> countBlocks( TreeSet<String> names ) {
		
		TreeMap<String, Integer> block_counts = new TreeMap<>();
		
		for( Block block : BuiltInRegistries.BLOCK ) {
			addElementToTreeMap( block, BuiltInRegistries.BLOCK, names, block_counts );
		}
		return block_counts;
	}
	
	private static <T> void addElementToTreeMap(
		T element,
		Registry<T> registry,
		TreeSet<String> names,
		TreeMap<String, Integer> counts ) {
		
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
