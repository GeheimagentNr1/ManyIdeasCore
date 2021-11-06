package de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.interfaces;

import de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.models.Option;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import java.util.List;


@SuppressWarnings( "InterfaceNeverImplemented" )
public interface RedstoneKeyable {
	
	
	//public
	@SuppressWarnings( "SameReturnValue" )
	ITextComponent getTitle();
	
	//public
	@SuppressWarnings( "SameReturnValue" )
	ResourceLocation getIconTextures();
	
	//public
	List<Option> getOptions();
	
	//public
	int getStateIndex( BlockState state );
	
	//public
	void setBlockStateValue( World level, BlockState state, BlockPos pos, int stateIndex, PlayerEntity player );
}
