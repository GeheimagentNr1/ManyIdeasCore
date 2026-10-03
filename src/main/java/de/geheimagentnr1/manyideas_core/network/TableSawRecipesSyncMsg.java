package de.geheimagentnr1.manyideas_core.network;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.TableSawRecipe;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.TableSawRecipes;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.diamond.TableSawDiamondRecipe;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.iron.TableSawIronRecipe;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.stone.TableSawStoneRecipe;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.OnDatapackSyncEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public record TableSawRecipesSyncMsg( @NotNull List<TableSawRecipe> recipes ) implements CustomPacketPayload {
	
	
	@NotNull
	public static final CustomPacketPayload.Type<TableSawRecipesSyncMsg> TYPE = new CustomPacketPayload.Type<>(
		ResourceLocation.fromNamespaceAndPath( ManyIdeasCore.MODID, "table_saw_recipes_sync" )
	);
	
	@NotNull
	public static final StreamCodec<RegistryFriendlyByteBuf, TableSawRecipesSyncMsg> STREAM_CODEC = StreamCodec.of(
		TableSawRecipesSyncMsg::encode,
		TableSawRecipesSyncMsg::decode
	);
	
	private static void encode( @NotNull RegistryFriendlyByteBuf buffer, @NotNull TableSawRecipesSyncMsg msg ) {
		
		buffer.writeVarInt( msg.recipes().size() );
		for( TableSawRecipe recipe : msg.recipes() ) {
			buffer.writeUtf( recipeName( recipe ) );
			buffer.writeUtf( recipe.getGroup() );
			Ingredient.CONTENTS_STREAM_CODEC.encode( buffer, recipe.getIngredient() );
			ItemStack.STREAM_CODEC.encode( buffer, recipe.getResult() );
		}
	}
	
	@NotNull
	private static TableSawRecipesSyncMsg decode( @NotNull RegistryFriendlyByteBuf buffer ) {
		
		int size = buffer.readVarInt();
		List<TableSawRecipe> recipes = new ArrayList<>( size );
		for( int i = 0; i < size; i++ ) {
			String name = buffer.readUtf();
			String group = buffer.readUtf();
			Ingredient ingredient = Ingredient.CONTENTS_STREAM_CODEC.decode( buffer );
			ItemStack result = ItemStack.STREAM_CODEC.decode( buffer );
			switch( name ) {
				case TableSawStoneRecipe.registry_name ->
					recipes.add( new TableSawStoneRecipe( group, ingredient, result ) );
				case TableSawIronRecipe.registry_name ->
					recipes.add( new TableSawIronRecipe( group, ingredient, result ) );
				case TableSawDiamondRecipe.registry_name ->
					recipes.add( new TableSawDiamondRecipe( group, ingredient, result ) );
				default -> {
				}
			}
		}
		return new TableSawRecipesSyncMsg( recipes );
	}
	
	@NotNull
	private static String recipeName( @NotNull TableSawRecipe recipe ) {
		
		if( recipe instanceof TableSawIronRecipe ) {
			return TableSawIronRecipe.registry_name;
		}
		if( recipe instanceof TableSawDiamondRecipe ) {
			return TableSawDiamondRecipe.registry_name;
		}
		return TableSawStoneRecipe.registry_name;
	}
	
	public void handle( @NotNull IPayloadContext context ) {
		
		context.enqueueWork( () -> TableSawRecipes.setClientRecipes( recipes ) );
	}
	
	@NotNull
	@Override
	public Type<? extends CustomPacketPayload> type() {
		
		return TYPE;
	}
	
	//Sends the table saw recipes on login and after /reload
	public static class SyncHandler {
		
		
		@SubscribeEvent
		public void handleOnDatapackSyncEvent( @NotNull OnDatapackSyncEvent event ) {
			
			ServerLevel level = event.getPlayerList().getServer().overworld();
			TableSawRecipesSyncMsg msg = new TableSawRecipesSyncMsg( TableSawRecipes.getServerRecipes( level ) );
			event.getRelevantPlayers().forEach( player -> PacketDistributor.sendToPlayer( player, msg ) );
		}
	}
}
