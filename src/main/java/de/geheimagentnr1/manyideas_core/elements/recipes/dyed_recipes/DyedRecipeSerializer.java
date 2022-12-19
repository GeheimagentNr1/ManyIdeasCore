package de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes;

import com.google.common.collect.Sets;
import com.google.gson.*;
import de.geheimagentnr1.manyideas_core.elements.block_state_properties.Color;
import de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.dyed.DyeBlockItem;
import de.geheimagentnr1.manyideas_core.elements.recipes.RecipeSerializers;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Stream;


public class DyedRecipeSerializer implements RecipeSerializer<DyedRecipe> {
	
	
	@Nonnull
	@Override
	public DyedRecipe fromJson( @Nonnull ResourceLocation recipeId, @Nonnull JsonObject json ) {
		
		boolean shaped = GsonHelper.getAsBoolean( json, "shaped" );
		NonNullList<Ingredient> ingredients = NonNullList.create();
		int recipeWidth;
		int recipeHeight;
		if( shaped ) {
			String[][] pattern = patternFromJson( GsonHelper.getAsJsonArray( json, "pattern" ) );
			recipeWidth = 3;
			recipeHeight = 3;
			deserializeShapedIngredients( ingredients, pattern, GsonHelper.getAsJsonObject( json, "keys" ) );
		} else {
			deserializeNonShapedIngredients( ingredients, GsonHelper.getAsJsonArray( json, "ingredients" ) );
			recipeWidth = ingredients.size();
			recipeHeight = 1;
		}
		ItemStack result = deserializeResult( GsonHelper.getAsJsonObject( json, "result" ) );
		return new DyedRecipe(
			recipeId,
			RecipeSerializers.DYED,
			shaped,
			ingredients,
			result,
			recipeWidth,
			recipeHeight
		);
	}
	
	
	private static String[][] patternFromJson( JsonArray patternArray ) {
		
		String[][] pattern = new String[3][3];
		if( patternArray.size() != 3 ) {
			throw new JsonSyntaxException( "Invalid pattern: pattern has not 3 rows" );
		}
		for( int i = 0; i < 3; i++ ) {
			JsonArray rowArray = patternArray.get( i ).getAsJsonArray();
			if( rowArray.size() != 3 ) {
				throw new JsonSyntaxException( "Invalid pattern: row " + i + " has not 3 column" );
			}
			for( int j = 0; j < 3; j++ ) {
				pattern[i][j] = rowArray.get( j ).getAsString();
			}
		}
		return pattern;
	}
	
	private void deserializeShapedIngredients(
		NonNullList<Ingredient> ingredients,
		String[][] pattern,
		JsonObject keysJson ) {
		
		Map<String, Ingredient> keyMapping = deserializeKeys( keysJson );
		Set<String> keys = Sets.newHashSet( keyMapping.keySet() );
		keys.remove( " " );
		for( int i = 0; i < 3; i++ ) {
			for( int j = 0; j < 3; j++ ) {
				Ingredient ingredient = keyMapping.get( pattern[i][j] );
				if( ingredient == null ) {
					throw new JsonSyntaxException(
						"Pattern references symbol '" + pattern[i][j] + "' but it's not defined in the key" );
				}
				keys.remove( pattern[i][j] );
				ingredients.add( ingredient );
			}
		}
		if( !keys.isEmpty() ) {
			throw new JsonSyntaxException( "Key defines symbols that aren't used in pattern: " + keys );
		}
	}
	
	private Map<String, Ingredient> deserializeKeys( JsonObject keys ) {
		
		Map<String, Ingredient> map = new HashMap<>();
		
		for( Map.Entry<String, JsonElement> entry : keys.entrySet() ) {
			if( entry.getKey().length() != 1 ) {
				throw new JsonSyntaxException(
					"Invalid key entry: '" + entry.getKey() + "' is an invalid symbol (must be 1 character only)." );
			}
			if( " ".equals( entry.getKey() ) ) {
				throw new JsonSyntaxException( "Invalid key entry: ' ' is a reserved symbol." );
			}
			map.put( entry.getKey(), deserializeIngredient( entry.getValue().getAsJsonObject() ) );
		}
		map.put( " ", Ingredient.EMPTY );
		return map;
	}
	
	private void deserializeNonShapedIngredients( NonNullList<Ingredient> ingredients, JsonArray ingredientsJSON ) {
		
		for( int i = 0; i < ingredientsJSON.size(); i++ ) {
			ingredients.add( deserializeIngredient( ingredientsJSON.get( i ).getAsJsonObject() ) );
		}
	}
	
	//package-private
	Ingredient deserializeIngredient( JsonObject ingredient ) {
		
		if( ingredient.has( "color_item" ) ) {
			return deserializeColorStackList( ingredient );
		}
		if( ingredient.has( "color_tag" ) ) {
			return deserializeColorTagList( ingredient );
		}
		return Ingredient.fromValues( Stream.of( Ingredient.valueFromJson( ingredient ) ) );
	}
	
	@SuppressWarnings( "deprecation" )
	private Ingredient deserializeColorStackList( JsonObject ingredient ) {
		
		ResourceLocation location = new ResourceLocation( GsonHelper.getAsString( ingredient, "color_item" ) );
		Item item = BuiltInRegistries.ITEM.getOptional( location ).orElseThrow( () -> new JsonSyntaxException(
			"Unknown item '" + location + "'" ) );
		if( !( item instanceof DyeBlockItem ) ) {
			throw new JsonSyntaxException( location + " is not a DyeBlockItem" );
		}
		return new ColorIngredient( new ColorStackList( new ItemStack( item ) ) );
	}
	
	@SuppressWarnings( "deprecation" )
	private Ingredient deserializeColorTagList( JsonObject ingredient ) {
		
		JsonObject color_tag = GsonHelper.getAsJsonObject( ingredient, "color_tag" );
		TreeMap<ItemStack, Color> stacks =
			new TreeMap<>( Comparator.comparing( o -> BuiltInRegistries.ITEM.getKey( o.getItem() ) ) );
		for( Color color : Color.values() ) {
			String registry_key = GsonHelper.getAsString( color_tag, color.getSerializedName(), "" );
			if( !registry_key.isEmpty() ) {
				ResourceLocation location = new ResourceLocation( registry_key );
				stacks.put( new ItemStack( BuiltInRegistries.ITEM.getOptional( location )
					.orElseThrow( () -> new JsonSyntaxException( "Unknown item '" + location + "'" ) ) ), color );
			}
		}
		return new ColorIngredient( new ColorTagList( stacks ) );
	}
	
	@SuppressWarnings( "deprecation" )
	private ItemStack deserializeResult( JsonObject result ) {
		
		String registry_key = GsonHelper.getAsString( result, "item" );
		Item item =
			BuiltInRegistries.ITEM.getOptional( new ResourceLocation( registry_key ) )
				.orElseThrow( () -> new JsonSyntaxException(
					"Unknown item '" + registry_key + "'" ) );
		if( !( item instanceof DyeBlockItem ) ) {
			throw new JsonParseException( "Unallowed Recipe Result" );
		}
		return new ItemStack( item, GsonHelper.getAsInt( result, "count", 1 ) );
	}
	
	@Nullable
	@Override
	public DyedRecipe fromNetwork( @Nonnull ResourceLocation recipeId, @Nonnull FriendlyByteBuf buffer ) {
		
		boolean shaped = buffer.readBoolean();
		int recipeWidth = buffer.readVarInt();
		int recipeHeight = buffer.readVarInt();
		NonNullList<Ingredient> ingredients = NonNullList.create();
		for( int k = 0; k < recipeWidth * recipeHeight; ++k ) {
			ingredients.add( Ingredient.fromNetwork( buffer ) );
		}
		ItemStack result = buffer.readItem();
		return new DyedRecipe(
			recipeId,
			RecipeSerializers.DYED,
			shaped,
			ingredients,
			result,
			recipeWidth,
			recipeHeight
		);
	}
	
	@Override
	public void toNetwork( @Nonnull FriendlyByteBuf buffer, @Nonnull DyedRecipe recipe ) {
		
		buffer.writeBoolean( recipe.isShaped() );
		buffer.writeVarInt( recipe.getRecipeWidth() );
		buffer.writeVarInt( recipe.getRecipeHeight() );
		for( Ingredient ingredient : recipe.getIngredients() ) {
			ingredient.toNetwork( buffer );
		}
		buffer.writeItem( recipe.getResult() );
	}
}
