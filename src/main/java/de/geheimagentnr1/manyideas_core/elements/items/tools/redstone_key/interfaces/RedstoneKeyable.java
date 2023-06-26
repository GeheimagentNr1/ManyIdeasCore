package de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.interfaces;

import de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.models.Option;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.List;


@SuppressWarnings( "InterfaceNeverImplemented" )
public interface RedstoneKeyable {
	
	
	//public
	@SuppressWarnings( "SameReturnValue" )
	@NotNull
	Component getTitle();
	
	//public
	@SuppressWarnings( "SameReturnValue" )
	@NotNull
	ResourceLocation getIconTextures();
	
	//public
	@NotNull
	List<Option> getOptions();
	
	//public
	int getStateIndex( @NotNull BlockState state );
	
	//public
	void setBlockStateValue(
		@NotNull Level level,
		@NotNull BlockState state,
		@NotNull BlockPos pos,
		int stateIndex,
		@NotNull Player player );
}
