package de.geheimagentnr1.manyideas_core.elements.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.block.Block;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.util.TreeMap;
import java.util.TreeSet;


public class ElementCountCommand {
	
	
	@SuppressWarnings( "SameReturnValue" )
	public static void register( CommandDispatcher<CommandSource> dispatcher ) {
		
		LiteralArgumentBuilder<CommandSource> elementCountCommand = Commands.literal( "element_count" );
		elementCountCommand.executes( command -> {
			TreeSet<String> names = new TreeSet<>();
			TreeMap<String, Integer> block_item_counts = new TreeMap<>();
			TreeMap<String, Integer> item_counts = new TreeMap<>();
			TreeMap<String, Integer> block_counts = countBlocks( names );
			countItems( names, item_counts, block_item_counts );
			
			for( String name : names ) {
				command.getSource().sendFeedback( new StringTextComponent( name ), false );
				command.getSource().sendFeedback(
					new StringTextComponent( "block count: " + block_counts.get( name ) ),
					false
				);
				command.getSource().sendFeedback( new StringTextComponent(
					"block item count: " + block_item_counts.get( name ) ), false );
				command.getSource().sendFeedback(
					new StringTextComponent( "item count: " + item_counts.get( name ) ),
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
		
		for( Item item : Registry.ITEM ) {
			if( item instanceof BlockItem ) {
				addElementToTreeMap( item, names, block_item_counts );
			} else {
				addElementToTreeMap( item, names, item_counts );
			}
		}
	}
	
	@SuppressWarnings( "deprecation" )
	private static TreeMap<String, Integer> countBlocks( TreeSet<String> names ) {
		
		TreeMap<String, Integer> block_counts = new TreeMap<>();
		
		for( Block block : Registry.BLOCK ) {
			addElementToTreeMap( block, names, block_counts );
		}
		return block_counts;
	}
	
	@SuppressWarnings( "rawtypes" )
	private static void addElementToTreeMap(
		ForgeRegistryEntry element,
		TreeSet<String> names,
		TreeMap<String, Integer> counts ) {
		
		ResourceLocation resourceLocation = element.getRegistryName();
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
