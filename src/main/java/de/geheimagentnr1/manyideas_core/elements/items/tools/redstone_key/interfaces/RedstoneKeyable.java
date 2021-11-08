package de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.interfaces;

import de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.models.Option;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;


@SuppressWarnings( "InterfaceNeverImplemented" )
public interface RedstoneKeyable {
	
	
	//public
	@SuppressWarnings( "SameReturnValue" )
	Component getTitle();
	
	//public
	@SuppressWarnings( "SameReturnValue" )
	ResourceLocation getIconTextures();
	
	//public
	List<Option> getOptions();
	
	//public
	int getStateIndex( BlockState state );
	
	//public
	void setBlockStateValue( Level level, BlockState state, BlockPos pos, int stateIndex, Player player );
}
