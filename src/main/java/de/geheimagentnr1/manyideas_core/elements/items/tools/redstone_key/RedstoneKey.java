package de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key;

import de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.interfaces.RedstoneKeyable;
import de.geheimagentnr1.manyideas_core.elements.items.CoreBaseItem;
import de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.models.Option;
import de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.screen.RedstoneKeyNamedContainerProvider;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nonnull;
import java.util.List;


public class RedstoneKey extends CoreBaseItem {
	
	
	public static final String registry_name = "redstone_key";
	
	public RedstoneKey() {
		
		super( new Item.Properties(), registry_name );
	}
	
	@Nonnull
	@Override
	public ActionResultType onItemUse( ItemUseContext context ) {
		
		World world = context.getWorld();
		BlockPos pos = context.getPos();
		BlockState state = world.getBlockState( pos );
		Block block = state.getBlock();
		PlayerEntity player = context.getPlayer();
		if( block instanceof RedstoneKeyable ) {
			RedstoneKeyable redstoneKeyableBlock = (RedstoneKeyable)block;
			if( !world.isRemote && player != null ) {
				ITextComponent title = redstoneKeyableBlock.getTitle();
				ResourceLocation icons = redstoneKeyableBlock.getIconTextures();
				List<Option> options = redstoneKeyableBlock.getOptions();
				int stateIndex = redstoneKeyableBlock.getStateIndex( state );
				
				NetworkHooks.openGui(
					(ServerPlayerEntity)player,
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
							packetBuffer.writeString( option.getTitle() );
							packetBuffer.writeString( option.getDescription() );
						} );
						packetBuffer.writeInt( stateIndex );
					}
				);
			}
			return ActionResultType.SUCCESS;
		}
		return ActionResultType.PASS;
	}
}
