package de.geheimagentnr1.manyideas_core.elements.commands.givedb;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.world.item.Item;

import java.util.concurrent.CompletableFuture;


public class DyeItemArgument implements ArgumentType<Item> {
	
	
	public static final String registry_name = "dye_item";
	
	public static DyeItemArgument dyeItem() {
		
		return new DyeItemArgument();
	}
	
	//package-private
	@SuppressWarnings( "SameParameterValue" )
	static <S> Item getItem( CommandContext<S> context, String name ) {
		
		return context.getArgument( name, Item.class );
	}
	
	@Override
	public Item parse( StringReader reader ) throws CommandSyntaxException {
		
		DyeItemParser parser = new DyeItemParser( reader ).parse();
		return parser.getItem();
	}
	
	@Override
	public <S> CompletableFuture<Suggestions> listSuggestions(
		CommandContext<S> context,
		SuggestionsBuilder builder ) {
		
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
