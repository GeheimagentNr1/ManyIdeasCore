package de.geheimagentnr1.manyideas_core.elements.commands.givedb;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import de.geheimagentnr1.manyideas_core.elements.block_state_properties.Color;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;


public class ColorArgument implements ArgumentType<Color> {
	
	
	@NotNull
	public static final String registry_name = "color";
	
	@NotNull
	public static ColorArgument color() {
		
		return new ColorArgument();
	}
	
	//package-private
	@SuppressWarnings( "SameParameterValue" )
	@NotNull
	static <S> Color getColor( @NotNull CommandContext<S> context, @NotNull String name ) {
		
		return context.getArgument( name, Color.class );
	}
	
	@NotNull
	@Override
	public Color parse( @NotNull StringReader reader ) throws CommandSyntaxException {
		
		ColorParser parser = new ColorParser( reader ).parse();
		return parser.getColor();
	}
	
	@NotNull
	@Override
	public <S> CompletableFuture<Suggestions> listSuggestions(
		@NotNull CommandContext<S> context,
		@NotNull SuggestionsBuilder builder ) {
		
		StringReader reader = new StringReader( builder.getInput() );
		reader.setCursor( builder.getStart() );
		ColorParser parser = new ColorParser( reader );
		
		try {
			parser.parse();
		} catch( CommandSyntaxException ignored ) {
		}
		return parser.fillSuggestions( builder );
	}
}
