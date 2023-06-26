package de.geheimagentnr1.manyideas_core.elements.commands.givedb;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import de.geheimagentnr1.manyideas_core.elements.block_state_properties.Color;
import de.geheimagentnr1.manyideas_core.util.DyeBlockHelper;
import de.geheimagentnr1.minecraft_forge_api.elements.commands.CommandInterface;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;


public class GiveDBCommand implements CommandInterface {
	
	
	@NotNull
	@Override
	public LiteralArgumentBuilder<CommandSourceStack> build() {
		
		LiteralArgumentBuilder<CommandSourceStack> givedbCommand =
			Commands.literal( "givedb" ).requires( commandSource -> commandSource.hasPermission( 2 ) );
		givedbCommand.executes( command -> {
			command.getSource()
				.sendSuccess( () -> Component.literal( "/givedb <target> <name of a dye block> <color>" ), true );
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
		
		return givedbCommand;
	}
	
	private int giveItem(
		@NotNull CommandSourceStack source,
		@NotNull Item item,
		@NotNull Color color,
		@NotNull Collection<ServerPlayer> targets,
		int count ) {
		
		for( ServerPlayer player : targets ) {
			player.addItem( DyeBlockHelper.setColorToItemStack(
				new ItemStack( item, count ),
				color
			) );
			player.playSound(
				SoundEvents.ITEM_PICKUP,
				0.2F,
				( ( player.getRandom().nextFloat() - player.getRandom().nextFloat() ) * 0.7F + 1.0F ) * 2.0F
			);
		}
		if( targets.size() == 1 ) {
			source.sendSuccess( () -> Component.translatable(
				"commands.give.success.single",
				count,
				DyeBlockHelper.setColorToItemStack( new ItemStack( item, count ), color ).getDisplayName(),
				targets.iterator().next().getDisplayName()
			), true );
		} else {
			source.sendSuccess( () -> Component.translatable(
				"commands.give.success.single",
				count,
				DyeBlockHelper.setColorToItemStack( new ItemStack( item, count ), color ).getDisplayName(),
				targets.size()
			), true );
		}
		return targets.size();
	}
}
