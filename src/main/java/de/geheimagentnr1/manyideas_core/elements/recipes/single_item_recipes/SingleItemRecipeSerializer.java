package de.geheimagentnr1.manyideas_core.elements.recipes.single_item_recipes;

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


public class SingleItemRecipeSerializer<T extends SingleItemRecipe> extends ForgeRegistryEntry<IRecipeSerializer<?>>
	implements IRecipeSerializer<T> {
	
	
	private final ISingleItemRecipeFactory<T> factory;
	
	public SingleItemRecipeSerializer( ISingleItemRecipeFactory<T> _factory ) {
		
		factory = _factory;
	}
	
	@SuppressWarnings( "deprecation" )
	@Nonnull
	@Override
	public T fromJson( @Nonnull ResourceLocation recipeId, @Nonnull JsonObject json ) {
		
		String group = JSONUtils.getAsString( json, "group", "" );
		Ingredient ingredient;
		if( JSONUtils.isArrayNode( json, "ingredient" ) ) {
			ingredient = Ingredient.fromJson( JSONUtils.getAsJsonArray( json, "ingredient" ) );
		} else {
			ingredient = Ingredient.fromJson( JSONUtils.getAsJsonObject( json, "ingredient" ) );
		}
		
		String resultName = JSONUtils.getAsString( json, "result" );
		int resultCount = json.has( "count" ) ? JSONUtils.getAsInt( json, "count" ) : 1;
		ItemStack result = new ItemStack(
			Registry.ITEM.get( new ResourceLocation( resultName ) ),
			resultCount
		);
		return factory.create( recipeId, group, ingredient, result );
	}
	
	@Override
	public T fromNetwork( @Nonnull ResourceLocation recipeId, PacketBuffer buffer ) {
		
		String group = buffer.readUtf( 32767 );
		Ingredient ingredient = Ingredient.fromNetwork( buffer );
		ItemStack result = buffer.readItem();
		return factory.create( recipeId, group, ingredient, result );
	}
	
	@Override
	public void toNetwork( PacketBuffer buffer, T recipe ) {
		
		buffer.writeUtf( recipe.getGroup() );
		recipe.ingredient.toNetwork( buffer );
		buffer.writeItem( recipe.result );
	}
	
}
