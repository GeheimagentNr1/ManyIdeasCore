package de.geheimagentnr1.manyideas_core.elements.blocks.end_block;

import com.mojang.serialization.MapCodec;
import de.geheimagentnr1.minecraft_forge_api.elements.blocks.BlockItemInterface;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;


public class EndBlock extends BaseEntityBlock implements BlockItemInterface {
	
	
	@NotNull
	public static final String registry_name = "end_block";
	
	public EndBlock() {
		
		super( BlockBehaviour.Properties.of()
			.mapColor( MapColor.COLOR_BLACK )
			.strength( 50.0F, 1200.0F )
			.requiresCorrectToolForDrops()
			.noOcclusion()
			.isViewBlocking( ( state, level, pos ) -> false )
			.sound( SoundType.GLASS ) );
	}
	
	@Override
	protected MapCodec<? extends BaseEntityBlock> codec() {
		
		return null;
	}
	
	@NotNull
	@Override
	public RenderShape getRenderShape( @NotNull BlockState state ) {
		
		return RenderShape.ENTITYBLOCK_ANIMATED;
	}
	
	@Nullable
	@Override
	public BlockEntity newBlockEntity( @NotNull BlockPos pos, @NotNull BlockState state ) {
		
		return new EndBlockEntity( pos, state );
	}
}
