package de.geheimagentnr1.manyideascore.elements.recipes.single_item_recipes;

import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nonnull;


public class SingleItemRecipeSerializer<T extends SingleItemRecipe>
	extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<T> {
	
	
	private final ISingleItemRecipeFactory<T> factory;
	
	public SingleItemRecipeSerializer( ISingleItemRecipeFactory<T> _factory ) {
		
		factory = _factory;
	}
	
	@SuppressWarnings( "deprecation" )
	@Nonnull
	@Override
	public T read( @Nonnull ResourceLocation recipeId, @Nonnull JsonObject json ) {
		
		String group = JSONUtils.getString( json, "group", "" );
		Ingredient ingredient;
		if( JSONUtils.isJsonArray( json, "ingredient" ) ) {
			ingredient = Ingredient.deserialize( JSONUtils.getJsonArray( json, "ingredient" ) );
		} else {
			ingredient = Ingredient.deserialize( JSONUtils.getJsonObject( json, "ingredient" ) );
		}
		
		String resultName = JSONUtils.getString( json, "result" );
		int resultCount = JSONUtils.hasField( json, "count" ) ? JSONUtils.getInt( json, "count" ) : 1;
		ItemStack result = new ItemStack( Registry.ITEM.getOrDefault( new ResourceLocation( resultName ) ),
			resultCount );
		return factory.create( recipeId, group, ingredient, result );
	}
	
	@Override
	public T read( @Nonnull ResourceLocation recipeId, PacketBuffer buffer ) {
		
		String group = buffer.readString( 32767 );
		Ingredient ingredient = Ingredient.read( buffer );
		ItemStack result = buffer.readItemStack();
		return factory.create( recipeId, group, ingredient, result );
	}
	
	@Override
	public void write( PacketBuffer buffer, T recipe ) {
		
		buffer.writeString( recipe.getGroup() );
		recipe.ingredient.write( buffer );
		buffer.writeItemStack( recipe.result );
	}
	
}
