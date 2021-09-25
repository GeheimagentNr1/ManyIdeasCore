package de.geheimagentnr1.manyideas_core.elements.blocks.vanilla_blocks.metal_smoker;

import de.geheimagentnr1.manyideas_core.elements.blocks.BlockItemInterface;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Random;


public class MetalSmoker extends AbstractFurnaceBlock implements BlockItemInterface {
	
	
	public static final String registry_name = "metal_smoker";
	
	public MetalSmoker() {
		
		super( Block.Properties.create( Material.ROCK )
			.hardnessAndResistance( 3.5F )
			.lightValue( 13 )
			.sound( SoundType.STONE ) );
		setRegistryName( registry_name );
	}
	
	@Override
	public TileEntity createNewTileEntity( @Nonnull IBlockReader worldIn ) {
		
		return new MetalSmokerTile();
	}
	
	/**
	 * Interface for handling interaction with blocks that impliment AbstractFurnaceBlock. Called in
	 * onBlockActivated
	 * inside AbstractFurnaceBlock.
	 */
	@Override
	protected void interactWith( World worldIn, @Nonnull BlockPos pos, @Nonnull PlayerEntity player ) {
		
		TileEntity tileentity = worldIn.getTileEntity( pos );
		if( tileentity instanceof MetalSmokerTile ) {
			MetalSmokerTile metalSmokerTile = (MetalSmokerTile)tileentity;
			player.openContainer( metalSmokerTile );
		}
	}
	
	/**
	 * Called periodically clientside on blocks near the player to show effects (like furnace fire particles).
	 * Note that
	 * this method is unrelated to {randomTick} and {needsRandomTick}, and will always be called regardless
	 * of whether the block can receive random update ticks
	 */
	@Override
	public void animateTick(
		BlockState stateIn, @Nonnull World worldIn, @Nonnull BlockPos pos,
		@Nonnull Random rand ) {
		
		if( stateIn.get( LIT ) ) {
			double x = pos.getX() + 0.5D;
			double y = pos.getY();
			double z = pos.getZ() + 0.5D;
			if( rand.nextDouble() < 0.1D ) {
				worldIn.playSound( x, y, z, SoundEvents.BLOCK_SMOKER_SMOKE, SoundCategory.BLOCKS, 1.0F, 1.0F, false );
			}
			worldIn.addParticle( ParticleTypes.SMOKE, x, y + 1.1D, z, 0.0D, 0.0D, 0.0D );
		}
	}
	
	@Override
	public Item getBlockItem( Item.Properties properties ) {
		
		return createBlockItem( ModBlocks.METAL_SMOKER, properties, registry_name );
	}
}
