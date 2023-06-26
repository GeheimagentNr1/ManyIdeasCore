package de.geheimagentnr1.manyideas_core.elements.creative_mod_tabs;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.config.ClientConfig;
import de.geheimagentnr1.manyideas_core.elements.block_state_properties.Color;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocksRegisterFactory;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModDebugBlocksRegisterFactory;
import de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.dyed.DyeBlockItem;
import de.geheimagentnr1.manyideas_core.elements.items.ModItemsRegisterFactory;
import de.geheimagentnr1.manyideas_core.util.DyeBlockHelper;
import de.geheimagentnr1.minecraft_forge_api.elements.creative_mod_tabs.CreativeModeTabFactory;
import de.geheimagentnr1.minecraft_forge_api.registry.RegistryEntry;
import lombok.RequiredArgsConstructor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


@RequiredArgsConstructor
public class ManyIdeasCoreCreativeModeTabFactory implements CreativeModeTabFactory {
	
	
	@NotNull
	private final ClientConfig clientConfig;
	
	@NotNull
	private final ModBlocksRegisterFactory modBlocksRegisterFactory;
	
	@NotNull
	private final ModDebugBlocksRegisterFactory modDebugBlocksRegisterFactory;
	
	@NotNull
	private final ModItemsRegisterFactory modItemsRegisterFactory;
	
	@NotNull
	@Override
	public String getRegistryName() {
		
		return ManyIdeasCore.MODID;
	}
	
	@NotNull
	@Override
	public ItemLike getIconItem() {
		
		return ModBlocksRegisterFactory.TABLE_SAW_DIAMOND;
	}
	
	
	@NotNull
	@Override
	public List<RegistryEntry<Block>> getDisplayBlocks() {
		
		List<RegistryEntry<Block>> displayBlocks = new ArrayList<>( modBlocksRegisterFactory.getBlocks() );
		displayBlocks.addAll( modDebugBlocksRegisterFactory.getBlocks() );
		return displayBlocks;
	}
	
	@NotNull
	@Override
	public Stream<ItemStack> buildItemStacksOfBlockRegistryEntry( RegistryEntry<Block> registryEntry ) {
		
		Item item = registryEntry.getValue().asItem();
		if( item instanceof DyeBlockItem ) {
			if( clientConfig.allColorsInItemGroup() ) {
				List<ItemStack> items = new ArrayList<>();
				for( Color color : Color.values() ) {
					items.add( DyeBlockHelper.createItemStackOfItem( item, color ) );
				}
				return items.stream();
			} else {
				return Stream.of( DyeBlockHelper.createItemStackOfItem( item, Color.WHITE ) );
			}
		} else {
			return Stream.of( new ItemStack( registryEntry.getValue() ) );
		}
	}
	
	@NotNull
	@Override
	public List<RegistryEntry<Item>> getDisplayItems() {
		
		return modItemsRegisterFactory.getItems();
	}
}
