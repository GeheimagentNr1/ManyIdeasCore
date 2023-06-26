package de.geheimagentnr1.manyideas_core.elements.commands.givedb;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import de.geheimagentnr1.manyideas_core.elements.block_state_properties.Color;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;


//package-private
class ColorParser {
	
	
	@NotNull
	private static final DynamicCommandExceptionType COLOR_INVALID =
		new DynamicCommandExceptionType( function -> Component.translatable(
			"argument.color.invalid",
			function
		) );
	
	@NotNull
	private static final Set<String> COLORS = getItemKeySet();
	
	@NotNull
	private final StringReader reader;
	
	private Color dyeColor;
	
	private Function<SuggestionsBuilder, CompletableFuture<Suggestions>> suggestionsBuilder;
	
	//package-private
	ColorParser( @NotNull StringReader _reader ) {
		
		reader = _reader;
	}
	
	@NotNull
	private static Set<String> getItemKeySet() {
		
		Set<String> keySet = new TreeSet<>();
		
		for( Color dyeColor : Color.values() ) {
			keySet.add( dyeColor.getSerializedName() );
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
	
	@NotNull
	private Optional<Color> getItemForRegistry( @NotNull ResourceLocation resourceLocation ) {
		
		for( Color dye_color : Color.values() ) {
			if( dye_color.name().equals( resourceLocation.getPath().toUpperCase( Locale.ENGLISH ) ) ) {
				return Optional.of( dye_color );
			}
		}
		return Optional.empty();
	}
	
	//package-private
	@SuppressWarnings( "ReturnOfThis" )
	@NotNull
	ColorParser parse() throws CommandSyntaxException {
		
		suggestionsBuilder = this::suggestColor;
		readColor();
		suggestionsBuilder = this::suggestColorFuture;
		return this;
	}
	
	@NotNull
	private CompletableFuture<Suggestions> suggestColorFuture( @NotNull SuggestionsBuilder builder ) {
		
		return builder.buildFuture();
	}
	
	@NotNull
	private CompletableFuture<Suggestions> suggestColor( @NotNull SuggestionsBuilder builder ) {
		
		return SharedSuggestionProvider.suggest( COLORS, builder );
	}
	
	//package-private
	@NotNull
	CompletableFuture<Suggestions> fillSuggestions( @NotNull SuggestionsBuilder builder ) {
		
		return suggestionsBuilder.apply( builder.createOffset( reader.getCursor() ) );
	}
}
