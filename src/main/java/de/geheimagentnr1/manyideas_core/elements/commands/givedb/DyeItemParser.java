package de.geheimagentnr1.manyideas_core.elements.commands.givedb;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.dyed.DyeBlockItem;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;


//package-private
class DyeItemParser {
	
	
	@NotNull
	private static final DynamicCommandExceptionType ITEM_BAD_ID =
		new DynamicCommandExceptionType( function -> Component.translatable(
			"argument.item.id.invalid",
			function
		) );
	
	@NotNull
	private static final Function<SuggestionsBuilder, CompletableFuture<Suggestions>> DEFAULT_SUGGESTIONS_BUILDER =
		SuggestionsBuilder::buildFuture;
	
	@NotNull
	private static final Set<ResourceLocation> KEY_SET = getDyeItemKeySet();
	
	@NotNull
	private final StringReader reader;
	
	private Item item;
	
	private Function<SuggestionsBuilder, CompletableFuture<Suggestions>> suggestionsBuilder;
	
	//package-private
	DyeItemParser( @NotNull StringReader _reader ) {
		
		suggestionsBuilder = DEFAULT_SUGGESTIONS_BUILDER;
		reader = _reader;
	}
	
	@SuppressWarnings( "deprecation" )
	@NotNull
	private static Set<ResourceLocation> getDyeItemKeySet() {
		
		Set<ResourceLocation> keySet = new TreeSet<>();
		
		for( Item item : BuiltInRegistries.ITEM ) {
			if( item instanceof DyeBlockItem ) {
				keySet.add( BuiltInRegistries.ITEM.getKey( item ) );
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
	@NotNull
	private Optional<Item> getItemForRegistry( @NotNull ResourceLocation resourceLocation ) {
		
		Optional<Item> optional = BuiltInRegistries.ITEM.getOptional( resourceLocation );
		
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
	@NotNull
	DyeItemParser parse() throws CommandSyntaxException {
		
		suggestionsBuilder = this::suggestTagOrItem;
		readItem();
		suggestionsBuilder = this::suggestItem;
		
		return this;
	}
	
	@NotNull
	private CompletableFuture<Suggestions> suggestItem( @NotNull SuggestionsBuilder builder ) {
		
		return builder.buildFuture();
	}
	
	@NotNull
	private CompletableFuture<Suggestions> suggestTagOrItem( @NotNull SuggestionsBuilder builder ) {
		
		return SharedSuggestionProvider.suggestResource( KEY_SET, builder );
	}
	
	//package-private
	@NotNull
	CompletableFuture<Suggestions> fillSuggestions( @NotNull SuggestionsBuilder builder ) {
		
		return suggestionsBuilder.apply( builder.createOffset( reader.getCursor() ) );
	}
}
