package de.geheimagentnr1.manyideas_core.elements.recipes.single_item_recipes;

import com.google.gson.JsonObject;
import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nonnull;


public class SingleItemRecipeSerializer<T extends SingleItemRecipe> extends ForgeRegistryEntry<RecipeSerializer<?>>
	implements RecipeSerializer<T> {
	
	
	private final ISingleItemRecipeFactory<T> factory;
	
	public SingleItemRecipeSerializer( ISingleItemRecipeFactory<T> _factory, String registry_name ) {
		
		factory = _factory;
		setRegistryName( registry_name );
	}
	
	@SuppressWarnings( "deprecation" )
	@Nonnull
	@Override
	public T fromJson( @Nonnull ResourceLocation recipeId, @Nonnull JsonObject json ) {
		
		String group = GsonHelper.getAsString( json, "group", "" );
		Ingredient ingredient;
		if( GsonHelper.isArrayNode( json, "ingredient" ) ) {
			ingredient = Ingredient.fromJson( GsonHelper.getAsJsonArray( json, "ingredient" ) );
		} else {
			ingredient = Ingredient.fromJson( GsonHelper.getAsJsonObject( json, "ingredient" ) );
		}
		
		String resultName = GsonHelper.getAsString( json, "result" );
		int resultCount = json.has( "count" ) ? GsonHelper.getAsInt( json, "count" ) : 1;
		ItemStack result = new ItemStack(
			Registry.ITEM.get( new ResourceLocation( resultName ) ),
			resultCount
		);
		return factory.create( recipeId, group, ingredient, result );
	}
	
	@Override
	public T fromNetwork( @Nonnull ResourceLocation recipeId, FriendlyByteBuf buffer ) {
		
		String group = buffer.readUtf( 32767 );
		Ingredient ingredient = Ingredient.fromNetwork( buffer );
		ItemStack result = buffer.readItem();
		return factory.create( recipeId, group, ingredient, result );
	}
	
	@Override
	public void toNetwork( FriendlyByteBuf buffer, T recipe ) {
		
		buffer.writeUtf( recipe.getGroup() );
		recipe.ingredient.toNetwork( buffer );
		buffer.writeItem( recipe.result );
	}
	
}
