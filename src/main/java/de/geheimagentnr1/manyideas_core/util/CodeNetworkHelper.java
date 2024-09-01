package de.geheimagentnr1.manyideas_core.util;


import de.geheimagentnr1.manyideas_core.elements.block_state_properties.Color;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class CodeNetworkHelper {
	
	
	public static Color fromNetwork( @NotNull RegistryFriendlyByteBuf buffer ) {
		
		return buffer.readEnum( Color.class );
	}
	
	public static void toNetwork( @NotNull RegistryFriendlyByteBuf buffer, @NotNull Color color ) {
		
		buffer.writeEnum( color  );
	}
}
