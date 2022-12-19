package de.geheimagentnr1.manyideas_core.elements.items.tools;

import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.IForgeShearable;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class MysteriousShears extends Item {
	
	
	public static final String registry_name = "mysterious_shears";
	
	public MysteriousShears() {
		
		super( new Item.Properties().durability( 238 ) );
	}
	
	@Override
	public boolean isFoil( @Nonnull ItemStack stack ) {
		
		return true;
	}
	
	/**
	 * Called when a Block is destroyed using this Item. Return true to trigger the "Use Item" statistic.
	 */
	@Override
	public boolean mineBlock(
		@Nonnull ItemStack stack,
		@Nonnull Level level,
		@Nonnull BlockState state,
		@Nonnull BlockPos pos,
		@Nonnull LivingEntity livingEntity ) {
		
		if( !level.isClientSide ) {
			stack.hurtAndBreak( 1, livingEntity, entity -> entity.broadcastBreakEvent( EquipmentSlot.MAINHAND ) );
		}
		if( state.is( BlockTags.LEAVES ) ||
			state.is( Blocks.COBWEB ) ||
			state.is( Blocks.GRASS ) ||
			state.is( Blocks.FERN ) ||
			state.is( Blocks.DEAD_BUSH ) ||
			state.is( Blocks.HANGING_ROOTS ) ||
			state.is( Blocks.VINE ) ||
			state.is( Blocks.TRIPWIRE ) ||
			state.is( BlockTags.WOOL ) ) {
			return true;
		} else {
			return super.mineBlock( stack, level, state, pos, livingEntity );
		}
	}
	
	@Override
	public boolean isCorrectToolForDrops( BlockState state ) {
		
		Block block = state.getBlock();
		return block == Blocks.COBWEB || block == Blocks.REDSTONE_WIRE || block == Blocks.TRIPWIRE;
	}
	
	@Override
	public float getDestroySpeed( @Nonnull ItemStack stack, BlockState state ) {
		
		if( state.getBlock() == Blocks.COBWEB || state.is( BlockTags.LEAVES ) ) {
			return 15.0F;
		} else {
			return state.is( BlockTags.WOOL ) ? 5.0F : super.getDestroySpeed( stack, state );
		}
	}
	
	@Nonnull
	@Override
	public InteractionResult interactLivingEntity(
		@Nonnull ItemStack stack,
		@Nonnull Player player,
		@Nonnull LivingEntity target,
		@Nonnull InteractionHand hand ) {
		
		if( target.level.isClientSide ) {
			return InteractionResult.PASS;
		}
		if( target instanceof IForgeShearable shear_target ) {
			BlockPos pos = new BlockPos( target.getX(), target.getY(), target.getZ() );
			if( shear_target.isShearable( stack, target.level, pos ) ) {
				
				RandomSource random = player.getRandom();
				List<ItemStack> drops;
				if( shear_target instanceof Sheep && target.getCustomName() != null &&
					target.getCustomName().getString().equals( "jeb_" ) ) {
					drops = Collections.singletonList( new ItemStack(
						ModBlocks.RAINBOW_WOOL,
						random.nextInt( 3 ) + 1
					) );
					( (Sheep)shear_target ).setSheared( true );
					target.playSound( SoundEvents.SHEEP_SHEAR, 1.0F, 1.0F );
				} else {
					drops = shear_target.onSheared(
						player,
						stack,
						target.level,
						pos,
						EnchantmentHelper.getItemEnchantmentLevel( Enchantments.BLOCK_FORTUNE, stack )
					);
				}
				drops.forEach( drop -> {
					ItemEntity ent = target.spawnAtLocation( drop, 1.0F );
					Objects.requireNonNull( ent ).setDeltaMovement( ent.getDeltaMovement()
						.add(
							( random.nextFloat() - random.nextFloat() ) * 0.1F,
							random.nextFloat() * 0.05F,
							( random.nextFloat() - random.nextFloat() ) * 0.1F
						) );
				} );
				stack.hurtAndBreak( 1, target, e -> e.broadcastBreakEvent( hand ) );
			}
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.PASS;
	}
}
