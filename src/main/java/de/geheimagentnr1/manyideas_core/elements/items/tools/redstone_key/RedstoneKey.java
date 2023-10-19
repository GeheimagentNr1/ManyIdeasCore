package de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key;

import de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.interfaces.RedstoneKeyable;
import de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.models.Option;
import de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.screen.RedstoneKeyNamedContainerProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class RedstoneKey extends Item {
	
	
	@NotNull
	public static final String registry_name = "redstone_key";
	
	public RedstoneKey() {
		
		super( new Properties() );
	}
	
	@NotNull
	@Override
	public InteractionResult useOn( @NotNull UseOnContext context ) {
		
		Level level = context.getLevel();
		BlockPos pos = context.getClickedPos();
		BlockState state = level.getBlockState( pos );
		Block block = state.getBlock();
		Player player = context.getPlayer();
		if( block instanceof RedstoneKeyable redstoneKeyableBlock ) {
			if( !level.isClientSide && player != null ) {
				Component title = redstoneKeyableBlock.getTitle();
				ResourceLocation icons = redstoneKeyableBlock.getIconTextures();
				List<Option> options = redstoneKeyableBlock.getOptions();
				int stateIndex = redstoneKeyableBlock.getStateIndex( state );
				
				if( player instanceof ServerPlayer serverPlayer ) {
					serverPlayer.openMenu(
						new RedstoneKeyNamedContainerProvider(
							title,
							icons,
							pos,
							redstoneKeyableBlock,
							options,
							stateIndex
						),
						packetBuffer -> {
							packetBuffer.writeResourceLocation( icons );
							packetBuffer.writeBlockPos( pos );
							packetBuffer.writeInt( options.size() );
							options.forEach( option -> {
								packetBuffer.writeUtf( option.getTitle() );
								packetBuffer.writeUtf( option.getDescription() );
							} );
							packetBuffer.writeInt( stateIndex );
						}
					);
				}
			}
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.PASS;
	}
}
