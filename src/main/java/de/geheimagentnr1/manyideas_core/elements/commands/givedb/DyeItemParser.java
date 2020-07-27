package de.geheimagentnr1.manyideas_core.elements.commands.givedb;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.dyed.DyeBlockItem;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;


//package-private
class DyeItemParser {
	
	
	private static final DynamicCommandExceptionType ITEM_BAD_ID = new DynamicCommandExceptionType( function ->
		new TranslationTextComponent( "argument.item.id.invalid", function ) );
	
	private static final Function<SuggestionsBuilder, CompletableFuture<Suggestions>> DEFAULT_SUGGESTIONS_BUILDER =
		SuggestionsBuilder::buildFuture;
	
	private static final Set<ResourceLocation> KEY_SET = getDyeItemKeySet();
	
	private final StringReader reader;
	
	private Item item;
	
	private Function<SuggestionsBuilder, CompletableFuture<Suggestions>> suggestionsBuilder;
	
	//package-private
	DyeItemParser( StringReader _reader ) {
		
		suggestionsBuilder = DEFAULT_SUGGESTIONS_BUILDER;
		reader = _reader;
	}
	
	@SuppressWarnings( "deprecation" )
	private static Set<ResourceLocation> getDyeItemKeySet() {
		
		Set<ResourceLocation> keySet = new TreeSet<>();
		
		for( Item item : Registry.ITEM ) {
			if( item instanceof DyeBlockItem ) {
				keySet.add( item.getRegistryName() );
			}
		}
		return keySet;
	}
	
	public Item getItem() {
		
		return item;
	}
	
	private void readItem() throws CommandSyntaxException {
		
		int cursor = reader.getCursor();
		ResourceLocation resourceLocation = ResourceLocation.read( reader );
		item = getItemForRegistry( resourceLocation ).orElseThrow( () -> {
			reader.setCursor( cursor );
			return ITEM_BAD_ID.createWithContext( reader, resourceLocation.toString() );
		} );
	}
	
	@SuppressWarnings( "deprecation" )
	private Optional<Item> getItemForRegistry( ResourceLocation resourceLocation ) {
		
		Optional<Item> optional = Registry.ITEM.getValue( resourceLocation );
		
		if( optional.isPresent() ) {
			if( optional.get() instanceof DyeBlockItem ) {
				return optional;
			} else {
				return Optional.empty();
			}
		} else {
			return optional;
		}
	}
	
	//package-private
	@SuppressWarnings( "ReturnOfThis" )
	DyeItemParser parse() throws CommandSyntaxException {
		
		suggestionsBuilder = this::suggestTagOrItem;
		readItem();
		suggestionsBuilder = this::suggestItem;
		
		return this;
	}
	
	private CompletableFuture<Suggestions> suggestItem( SuggestionsBuilder builder ) {
		
		return builder.buildFuture();
	}
	
	private CompletableFuture<Suggestions> suggestTagOrItem( SuggestionsBuilder builder ) {
		
		return ISuggestionProvider.suggestIterable( KEY_SET, builder );
	}
	
	//package-private
	CompletableFuture<Suggestions> fillSuggestions( SuggestionsBuilder builder ) {
		
		return suggestionsBuilder.apply( builder.createOffset( reader.getCursor() ) );
	}
}
