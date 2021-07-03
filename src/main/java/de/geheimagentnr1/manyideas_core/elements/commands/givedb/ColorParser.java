package de.geheimagentnr1.manyideas_core.elements.commands.givedb;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import de.geheimagentnr1.manyideas_core.elements.block_state_properties.Color;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;


//package-private
class ColorParser {
	
	
	private static final DynamicCommandExceptionType COLOR_INVALID =
		new DynamicCommandExceptionType( function -> new TranslationTextComponent(
			"argument.color.invalid",
			function
		) );
	
	private static final Set<String> COLORS = getItemKeySet();
	
	private final StringReader reader;
	
	private Color dyeColor;
	
	private Function<SuggestionsBuilder, CompletableFuture<Suggestions>> suggestionsBuilder;
	
	//package-private
	ColorParser( StringReader _reader ) {
		
		reader = _reader;
	}
	
	private static Set<String> getItemKeySet() {
		
		Set<String> keySet = new TreeSet<>();
		
		for( Color dyeColor : Color.values() ) {
			keySet.add( dyeColor.getName() );
		}
		return keySet;
	}
	
	public Color getColor() {
		
		return dyeColor;
	}
	
	private void readColor() throws CommandSyntaxException {
		
		int cursor = reader.getCursor();
		ResourceLocation resourceLocation = ResourceLocation.read( reader );
		dyeColor = getItemForRegistry( resourceLocation ).orElseThrow( () -> {
			reader.setCursor( cursor );
			return COLOR_INVALID.createWithContext( reader, resourceLocation.toString() );
		} );
	}
	
	private Optional<Color> getItemForRegistry( ResourceLocation resourceLocation ) {
		
		for( Color dye_color : Color.values() ) {
			if( dye_color.name().equals( resourceLocation.getPath().toUpperCase( Locale.ENGLISH ) ) ) {
				return Optional.of( dye_color );
			}
		}
		return Optional.empty();
	}
	
	//package-private
	@SuppressWarnings( "ReturnOfThis" )
	ColorParser parse() throws CommandSyntaxException {
		
		suggestionsBuilder = this::suggestColor;
		readColor();
		suggestionsBuilder = this::suggestColorFuture;
		return this;
	}
	
	private CompletableFuture<Suggestions> suggestColorFuture( SuggestionsBuilder builder ) {
		
		return builder.buildFuture();
	}
	
	private CompletableFuture<Suggestions> suggestColor( SuggestionsBuilder builder ) {
		
		return ISuggestionProvider.suggest( COLORS, builder );
	}
	
	//package-private
	CompletableFuture<Suggestions> fillSuggestions( SuggestionsBuilder builder ) {
		
		return suggestionsBuilder.apply( builder.createOffset( reader.getCursor() ) );
	}
}
