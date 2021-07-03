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
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

import javax.annotation.Nonnull;
import java.util.*;


public class MysteriousShears extends CoreBaseItem {
	
	
	public static final String registry_name = "mysterious_shears";
	
	private static final ArrayList<Block> destroys_blocks = new ArrayList<>( Arrays.asList(
		Blocks.COBWEB,
		Blocks.GRASS,
		Blocks.FERN,
		Blocks.DEAD_BUSH,
		Blocks.VINE,
		Blocks.TRIPWIRE
	) );
	
	public MysteriousShears() {
		
		super( new Item.Properties().maxDamage( 238 ), registry_name );
	}
	
	@Override
	public boolean hasEffect( @Nonnull ItemStack stack ) {
		
		return true;
	}
	
	/**
	 * Called when a Block is destroyed using this Item. Return true to trigger the "Use Item" statistic.
	 */
	@Override
	public boolean onBlockDestroyed(
		@Nonnull ItemStack stack,
		World worldIn,
		@Nonnull BlockState state,
		@Nonnull BlockPos pos,
		@Nonnull LivingEntity entityLiving ) {
		
		if( !worldIn.isRemote ) {
			stack.damageItem( 1, entityLiving, entity -> entity.sendBreakAnimation( EquipmentSlotType.MAINHAND ) );
		}
		if( state.isIn( BlockTags.LEAVES ) || destroys_blocks.contains( state.getBlock() ) ||
			state.isIn( BlockTags.WOOL ) ) {
			return true;
		} else {
			return super.onBlockDestroyed( stack, worldIn, state, pos, entityLiving );
		}
	}
	
	/**
	 * Check whether this Item can harvest the given Block
	 */
	@Override
	public boolean canHarvestBlock( BlockState blockIn ) {
		
		Block block = blockIn.getBlock();
		return block == Blocks.COBWEB || block == Blocks.REDSTONE_WIRE || block == Blocks.TRIPWIRE;
	}
	
	@Override
	public float getDestroySpeed( @Nonnull ItemStack stack, BlockState state ) {
		
		if( state.getBlock() == Blocks.COBWEB || state.isIn( BlockTags.LEAVES ) ) {
			return 15.0F;
		} else {
			return state.isIn( BlockTags.WOOL ) ? 5.0F : super.getDestroySpeed( stack, state );
		}
	}
	
	/**
	 * Returns true if the item can be used on the given entity, e.g. shears on sheep.
	 */
	@SuppressWarnings( "deprecation" )
	@Override
	public boolean itemInteractionForEntity(
		@Nonnull ItemStack stack,
		@Nonnull PlayerEntity playerIn,
		LivingEntity target,
		@Nonnull Hand hand ) {
		
		if( target.world.isRemote ) {
			return false;
		}
		if( target instanceof IShearable ) {
			IShearable shear_target = (IShearable)target;
			BlockPos pos = new BlockPos( target.posX, target.posY, target.posZ );
			if( shear_target.isShearable( stack, target.world, pos ) ) {
				
				List<ItemStack> drops;
				if( shear_target instanceof SheepEntity && target.getCustomName() != null &&
					target.getCustomName().getFormattedText().equals( "jeb_" ) ) {
					drops = Collections.singletonList( new ItemStack(
						ModBlocks.RAINBOW_WOOL,
						random.nextInt( 3 ) + 1
					) );
					( (SheepEntity)shear_target ).setSheared( true );
					target.playSound( SoundEvents.ENTITY_SHEEP_SHEAR, 1.0F, 1.0F );
				} else {
					drops = shear_target.onSheared(
						stack,
						target.world,
						pos,
						EnchantmentHelper.getEnchantmentLevel( Enchantments.FORTUNE, stack )
					);
				}
				drops.forEach( drop -> {
					ItemEntity ent = target.entityDropItem( drop, 1.0F );
					Objects.requireNonNull( ent ).setMotion( ent.getMotion()
						.add( ( random.nextFloat() - random.nextFloat() ) * 0.1F,
							random.nextFloat() * 0.05F,
							( random.nextFloat() - random.nextFloat() ) * 0.1F
						) );
				} );
				stack.damageItem( 1, target, e -> e.sendBreakAnimation( hand ) );
			}
			return true;
		}
		return false;
	}
}
