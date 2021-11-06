package de.geheimagentnr1.manyideas_core.elements.items.tools;

import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideas_core.elements.items.CoreBaseItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.IForgeShearable;

import javax.annotation.Nonnull;
import java.util.*;


public class MysteriousShears extends CoreBaseItem {
	
	
	public static final String registry_name = "mysterious_shears";
	
	private static final ArrayList<Block> DESTROYS_BLOCKS = new ArrayList<>( Arrays.asList(
		Blocks.COBWEB,
		Blocks.GRASS,
		Blocks.FERN,
		Blocks.DEAD_BUSH,
		Blocks.VINE,
		Blocks.TRIPWIRE
	) );
	
	public MysteriousShears() {
		
		super( new Item.Properties().durability( 238 ), registry_name );
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
		World level,
		@Nonnull BlockState state,
		@Nonnull BlockPos pos,
		@Nonnull LivingEntity livingEntity ) {
		
		if( !level.isClientSide ) {
			stack.hurtAndBreak( 1, livingEntity, entity -> entity.broadcastBreakEvent( EquipmentSlotType.MAINHAND ) );
		}
		if( state.is( BlockTags.LEAVES ) || DESTROYS_BLOCKS.contains( state.getBlock() ) ||
			state.is( BlockTags.WOOL ) ) {
			return true;
		} else {
			return super.mineBlock( stack, level, state, pos, livingEntity );
		}
	}
	
	/**
	 * Check whether this Item can harvest the given Block
	 */
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
	
	/**
	 * Returns true if the item can be used on the given entity, e.g. shears on sheep.
	 */
	@Nonnull
	@Override
	public ActionResultType interactLivingEntity(
		@Nonnull ItemStack stack,
		@Nonnull PlayerEntity player,
		LivingEntity target,
		@Nonnull Hand hand ) {
		
		if( target.level.isClientSide ) {
			return ActionResultType.PASS;
		}
		if( target instanceof IForgeShearable ) {
			IForgeShearable shear_target = (IForgeShearable)target;
			BlockPos pos = new BlockPos( target.getX(), target.getY(), target.getZ() );
			if( shear_target.isShearable( stack, target.level, pos ) ) {
				
				List<ItemStack> drops;
				if( shear_target instanceof SheepEntity && target.getCustomName() != null &&
					target.getCustomName().getString().equals( "jeb_" ) ) {
					drops = Collections.singletonList( new ItemStack(
						ModBlocks.RAINBOW_WOOL,
						random.nextInt( 3 ) + 1
					) );
					( (SheepEntity)shear_target ).setSheared( true );
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
			return ActionResultType.SUCCESS;
		}
		return ActionResultType.PASS;
	}
}
