package de.geheimagentnr1.manyideas_core.elements.commands.givedb;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;


public class DyeItemArgument implements ArgumentType<Item> {
	
	
	@NotNull
	public static final String registry_name = "dye_item";
	
	@NotNull
	public static DyeItemArgument dyeItem() {
		
		return new DyeItemArgument();
	}
	
	//package-private
	@SuppressWarnings( "SameParameterValue" )
	@NotNull
	static <S> Item getItem( @NotNull CommandContext<S> context, @NotNull String name ) {
		
		return context.getArgument( name, Item.class );
	}
	
	@Override
	public Item parse( @NotNull StringReader reader ) throws CommandSyntaxException {
		
		DyeItemParser parser = new DyeItemParser( reader ).parse();
		return parser.getItem();
	}
	
	@NotNull
	@Override
	public <S> CompletableFuture<Suggestions> listSuggestions(
		@NotNull CommandContext<S> context,
		@NotNull SuggestionsBuilder builder ) {
		
		StringReader reader = new StringReader( builder.getInput() );
		reader.setCursor( builder.getStart() );
		DyeItemParser parser = new DyeItemParser( reader );
		
		try {
			parser.parse();
		} catch( CommandSyntaxException ignored ) {
		}
		return parser.fillSuggestions( builder );
	}
}
