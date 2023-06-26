package de.geheimagentnr1.manyideas_core.elements.recipes.single_item_recipes;

import com.google.gson.JsonObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.NotNull;


public class SingleItemRecipeSerializer<T extends SingleItemRecipe> implements RecipeSerializer<T> {
	
	
	@NotNull
	private final ISingleItemRecipeFactory<T> factory;
	
	public SingleItemRecipeSerializer( @NotNull ISingleItemRecipeFactory<T> _factory ) {
		
		factory = _factory;
	}
	
	@SuppressWarnings( "deprecation" )
	@NotNull
	@Override
	public T fromJson( @NotNull ResourceLocation recipeId, @NotNull JsonObject json ) {
		
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
			BuiltInRegistries.ITEM.get( new ResourceLocation( resultName ) ),
			resultCount
		);
		return factory.create( recipeId, group, ingredient, result );
	}
	
	@Override
	@NotNull
	public T fromNetwork( @NotNull ResourceLocation recipeId, @NotNull FriendlyByteBuf buffer ) {
		
		String group = buffer.readUtf( 32767 );
		Ingredient ingredient = Ingredient.fromNetwork( buffer );
		ItemStack result = buffer.readItem();
		return factory.create( recipeId, group, ingredient, result );
	}
	
	@Override
	public void toNetwork( @NotNull FriendlyByteBuf buffer, @NotNull T recipe ) {
		
		buffer.writeUtf( recipe.getGroup() );
		recipe.ingredient.toNetwork( buffer );
		buffer.writeItem( recipe.result );
	}
	
}
