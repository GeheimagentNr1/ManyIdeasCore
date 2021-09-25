package de.geheimagentnr1.manyideas_core.elements.commands.givedb;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import de.geheimagentnr1.manyideas_core.elements.block_state_properties.Color;
import de.geheimagentnr1.manyideas_core.util.DyeBlockHelper;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.Collection;


public class GiveDBCommand {
	
	
	@SuppressWarnings( "SameReturnValue" )
	public static void register( CommandDispatcher<CommandSource> dispatcher ) {
		
		LiteralArgumentBuilder<CommandSource> givedbCommand =
			Commands.literal( "givedb" ).requires( commandSource -> commandSource.hasPermissionLevel( 2 ) );
		givedbCommand.executes( command -> {
			command.getSource()
				.sendFeedback( new StringTextComponent( "/givedb <target> <name of a dye block> <color>" ), true );
			return 1;
		} );
		givedbCommand.then( Commands.argument( "targets", EntityArgument.players() )
			.then( Commands.argument( "dye_item", DyeItemArgument.dyeItem() )
				.then( Commands.argument( "color", ColorArgument.color() )
					.executes( context -> giveItem(
						context.getSource(),
						DyeItemArgument.getItem( context, "dye_item" ),
						ColorArgument.getColor( context, "color" ),
						EntityArgument.getPlayers( context, "targets" ),
						1
					) )
					.then( Commands.argument( "count", IntegerArgumentType.integer( 1 ) )
						.executes( context -> giveItem(
							context.getSource(),
							DyeItemArgument.getItem( context, "dye_item" ),
							ColorArgument.getColor( context, "color" ),
							EntityArgument.getPlayers( context, "targets" ),
							IntegerArgumentType.getInteger( context, "count" )
						) ) ) ) ) );
		
		dispatcher.register( givedbCommand );
	}
	
	private static int giveItem(
		CommandSource source,
		Item item,
		Color color,
		Collection<ServerPlayerEntity> targets,
		int count ) {
		
		for( ServerPlayerEntity player : targets ) {
			player.addItemStackToInventory( DyeBlockHelper.setColorToItemStack(
				new ItemStack( item, count ),
				color
			) );
			player.playSound(
				SoundEvents.ENTITY_ITEM_PICKUP,
				0.2F,
				( ( player.getRNG().nextFloat() - player.getRNG().nextFloat() ) * 0.7F + 1.0F ) * 2.0F
			);
		}
		if( targets.size() == 1 ) {
			source.sendFeedback( new TranslationTextComponent(
				"commands.give.success.single",
				count,
				DyeBlockHelper.setColorToItemStack( new ItemStack( item, count ), color ).getTextComponent(),
				targets.iterator().next().getDisplayName()
			), true );
		} else {
			source.sendFeedback( new TranslationTextComponent(
				"commands.give.success.single",
				count,
				DyeBlockHelper.setColorToItemStack( new ItemStack( item, count ), color ).getTextComponent(),
				targets.size()
			), true );
		}
		return targets.size();
	}
}
