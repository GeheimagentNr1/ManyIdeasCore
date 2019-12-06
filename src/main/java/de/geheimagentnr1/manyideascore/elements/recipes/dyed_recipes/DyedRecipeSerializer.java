package de.geheimagentnr1.manyideascore.elements.recipes.dyed_recipes;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.*;
import de.geheimagentnr1.manyideascore.elements.block_state_properties.Color;
import de.geheimagentnr1.manyideascore.elements.blocks.template_blocks.dyed.DyeBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Stream;


public class DyedRecipeSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>>
	implements IRecipeSerializer<DyedRecipe> {
	
	
/*	private final boolean shaped;
	
	private final NonNullList<Ingredient> ingredients;
	
	private final ItemStack result;
	
	private final int recipeWidth;
	
	private final int recipeHeight;*/
	
	@Nonnull
	@Override
	public DyedRecipe read( @Nonnull ResourceLocation recipeId, @Nonnull JsonObject json ) {
		
		boolean shaped = JSONUtils.getBoolean( json, "shaped" );
		NonNullList<Ingredient> ingredients = NonNullList.create();
		int recipeWidth;
		int recipeHeight;
		if( shaped ) {
			String[][] pattern = patternFromJson( JSONUtils.getJsonArray( json, "pattern" ) );
			recipeWidth = 3;
			recipeHeight = 3;
			deserializeShapedIngredients( ingredients, pattern, JSONUtils.getJsonObject( json, "keys" ) );
		} else {
			recipeWidth = 0;
			recipeHeight = 0;
			deserializeNonShapedIngredients( ingredients, JSONUtils.getJsonArray( json, "ingredients" ) );
		}
		ItemStack result = deserializeResult( JSONUtils.getJsonObject( json, "result" ) );
		return new DyedRecipe( recipeId, this, shaped, ingredients, result, recipeWidth, recipeHeight );
		/*String s = JSONUtils.getString( json, "group", "" );
		Map<String, Ingredient> map = deserializeKey( JSONUtils.getJsonObject( json, "key" ) );
		String[] astring = shrink( patternFromJson( JSONUtils.getJsonArray( json, "pattern" ) ) );
		int i = astring[0].length();
		int j = astring.length;
		NonNullList<Ingredient> nonnulllist = deserializeIngredients( astring, map, i, j );
		ItemStack itemstack = deserializeItem( JSONUtils.getJsonObject( json, "result" ) );
		return new DyedRecipesOlc( recipeId, s, i, j, nonnulllist, itemstack );*/
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
	
	private void deserializeShapedIngredients( NonNullList<Ingredient> ingredients, String[][] pattern,
		JsonObject keysJson ) {
		
		Map<String, Ingredient> keyMapping = deserializeKeys( keysJson );
		Set<String> keys = Sets.newHashSet( keyMapping.keySet() );
		keys.remove( " " );
		for( int i = 0; i < 3; i++ ) {
			for( int j = 0; j < 3; j++ ) {
				Ingredient ingredient = keyMapping.get( pattern[i][j] );
				if( ingredient == null ) {
					throw new JsonSyntaxException( "Pattern references symbol '" + pattern[i][j] +
						"' but it's not defined in the key" );
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
				throw new JsonSyntaxException( "Invalid key entry: '" + entry.getKey() +
					"' is an invalid symbol (must be 1 character only)." );
			}
			if( " ".equals( entry.getKey() ) ) {
				throw new JsonSyntaxException( "Invalid key entry: ' ' is a reserved symbol." );
			}
			map.put( entry.getKey(), deserializeIngredient( entry.getValue().getAsJsonObject() ) );
		}
		map.put( " ", Ingredient.EMPTY );
		return map;
	}
	
	private void deserializeNonShapedIngredients( NonNullList<Ingredient> ingredients,
		JsonArray ingredientsJSON ) {
		
		for( int i = 0; i < ingredientsJSON.size(); i++ ) {
			ingredients.add( deserializeIngredient( ingredientsJSON.get( i ).getAsJsonObject() ) );
		}
	}
	
	private Ingredient deserializeIngredient( JsonObject ingredient ) {
		
		if( ingredient.has( "color_item" ) ) {
			return deserializeColorStackList( ingredient );
		}
		if( ingredient.has( "color_tag" ) ) {
			return deserializeColorTagList( ingredient );
		}
		return Ingredient.fromItemListStream( Stream.of( Ingredient.deserializeItemList( ingredient ) ) );
	}
	
	@SuppressWarnings( "deprecation" )
	private Ingredient deserializeColorStackList( JsonObject ingredient ) {
		
		ResourceLocation location = new ResourceLocation( JSONUtils.getString( ingredient, "color_item" ) );
		Item item = Registry.ITEM.getValue( location )
			.orElseThrow( () -> new JsonSyntaxException( "Unknown item '" + location + "'" ) );
		if( !( item instanceof DyeBlockItem ) ) {
			throw new JsonSyntaxException( location + " is not a DyeBlockItem" );
		}
		return new ColorIngredient( new ColorStackList( new ItemStack( item ) ) );
	}
	
	@SuppressWarnings( "deprecation" )
	private Ingredient deserializeColorTagList( JsonObject ingredient ) {
		
		JsonObject color_tag = JSONUtils.getJsonObject( ingredient, "color_tag" );
		TreeMap<ItemStack, Color> stacks = new TreeMap<>(
			Comparator.comparing( o -> Objects.requireNonNull( o.getItem().getRegistryName() ) ) );
		for( Color color : Color.values() ) {
			String registry_key = JSONUtils.getString( color_tag, color.getName(), "" );
			if( !registry_key.isEmpty() ) {
				ResourceLocation location = new ResourceLocation( registry_key );
				stacks.put( new ItemStack( Registry.ITEM.getValue( location ).orElseThrow( () ->
						new JsonSyntaxException( "Unknown item '" + location + "'" ) ) ),
					color );
			}
		}
		return new ColorIngredient( new ColorTagList( stacks ) );
	}
	
	@SuppressWarnings( "deprecation" )
	private ItemStack deserializeResult( JsonObject result ) {
		
		String registry_key = JSONUtils.getString( result, "item" );
		Item item = Registry.ITEM.getValue( new ResourceLocation( registry_key ) )
			.orElseThrow( () -> new JsonSyntaxException( "Unknown item '" + registry_key + "'" ) );
		if( !( item instanceof DyeBlockItem ) ) {
			throw new JsonParseException( "Unallowed Recipe Result" );
		}
		return new ItemStack( item, JSONUtils.getInt( result, "count", 1 ) );
	}
	
	/*private static NonNullList<Ingredient> deserializeIngredients( String[] p_192402_0_,
		Map<String, Ingredient> p_192402_1_, int p_192402_2_, int p_192402_3_ ) {
		
		NonNullList<Ingredient> nonnulllist = NonNullList.withSize( p_192402_2_ * p_192402_3_, Ingredient.EMPTY );
		Set<String> set = Sets.newHashSet( p_192402_1_.keySet() );
		set.remove( " " );
		
		for( int i = 0; i < p_192402_0_.length; ++i ) {
			for( int j = 0; j < p_192402_0_[i].length(); ++j ) {
				String s = p_192402_0_[i].substring( j, j + 1 );
				Ingredient ingredient = p_192402_1_.get( s );
				if( ingredient == null ) {
					throw new JsonSyntaxException(
						"Pattern references symbol '" + s + "' but it's not defined in the key" );
				}
				
				set.remove( s );
				nonnulllist.set( j + p_192402_2_ * i, ingredient );
			}
		}
		
		if( !set.isEmpty() ) {
			throw new JsonSyntaxException( "Key defines symbols that aren't used in pattern: " + set );
		} else {
			return nonnulllist;
		}
	}
	
	@VisibleForTesting
	static String[] shrink( String... p_194134_0_ ) {
		
		int i = 2147483647;
		int j = 0;
		int k = 0;
		int l = 0;
		
		for( int i1 = 0; i1 < p_194134_0_.length; ++i1 ) {
			String s = p_194134_0_[i1];
			i = Math.min( i, firstNonSpace( s ) );
			int j1 = lastNonSpace( s );
			j = Math.max( j, j1 );
			if( j1 < 0 ) {
				if( k == i1 ) {
					++k;
				}
				
				++l;
			} else {
				l = 0;
			}
		}
		
		if( p_194134_0_.length == l ) {
			return new String[0];
		} else {
			String[] astring = new String[p_194134_0_.length - l - k];
			
			for( int k1 = 0; k1 < astring.length; ++k1 ) {
				astring[k1] = p_194134_0_[k1 + k].substring( i, j + 1 );
			}
			
			return astring;
		}
	}
	
	private static int firstNonSpace( String p_194135_0_ ) {
		
		int i;
		for( i = 0; i < p_194135_0_.length() && p_194135_0_.charAt( i ) == ' '; ++i ) {
		}
		
		return i;
	}
	
	private static int lastNonSpace( String p_194136_0_ ) {
		
		int i;
		for( i = p_194136_0_.length() - 1; i >= 0 && p_194136_0_.charAt( i ) == ' '; --i ) {
		}
		
		return i;
	}
	
	private static String[] patternFromJson( JsonArray p_192407_0_ ) {
		
		String[] astring = new String[p_192407_0_.size()];
		if( astring.length > DyedRecipesOlc.MAX_HEIGHT ) {
			throw new JsonSyntaxException(
				"Invalid pattern: too many rows, " + DyedRecipesOlc.MAX_HEIGHT + " is maximum" );
		} else {
			if( astring.length == 0 ) {
				throw new JsonSyntaxException( "Invalid pattern: empty pattern not allowed" );
			} else {
				for( int i = 0; i < astring.length; ++i ) {
					String s = JSONUtils.getString( p_192407_0_.get( i ), "pattern[" + i + "]" );
					if( s.length() > DyedRecipesOlc.MAX_WIDTH ) {
						throw new JsonSyntaxException(
							"Invalid pattern: too many columns, " + DyedRecipesOlc.MAX_WIDTH + " is maximum" );
					}
					
					if( i > 0 && astring[0].length() != s.length() ) {
						throw new JsonSyntaxException( "Invalid pattern: each row must be the same width" );
					}
					
					astring[i] = s;
				}
				
				return astring;
			}
		}
	}
	
	private static Map<String, Ingredient> deserializeKey( JsonObject p_192408_0_ ) {
		
		Map<String, Ingredient> map = Maps.newHashMap();
		Iterator var2 = p_192408_0_.entrySet().iterator();
		
		while( var2.hasNext() ) {
			Map.Entry<String, JsonElement> entry = (Map.Entry)var2.next();
			if( ( (String)entry.getKey() ).length() != 1 ) {
				throw new JsonSyntaxException( "Invalid key entry: '" + (String)entry.getKey() +
					"' is an invalid symbol (must be 1 character only)." );
			}
			
			if( " ".equals( entry.getKey() ) ) {
				throw new JsonSyntaxException( "Invalid key entry: ' ' is a reserved symbol." );
			}
			
			map.put( entry.getKey(), Ingredient.deserialize( (JsonElement)entry.getValue() ) );
		}
		
		map.put( " ", Ingredient.EMPTY );
		return map;
	}
	
	public static ItemStack deserializeItem( JsonObject p_199798_0_ ) {
		
		String s = JSONUtils.getString( p_199798_0_, "item" );
		Item var10000 = (Item)Registry.ITEM.getValue( new ResourceLocation( s ) ).orElseThrow( () -> {
			return new JsonSyntaxException( "Unknown item '" + s + "'" );
		} );
		if( p_199798_0_.has( "data" ) ) {
			throw new JsonParseException( "Disallowed data tag found" );
		} else {
			int i = JSONUtils.getInt( p_199798_0_, "count", 1 );
			return CraftingHelper.getItemStack( p_199798_0_, true );
		}
	}*/
	
	@Nullable
	@Override
	public DyedRecipe read( @Nonnull ResourceLocation recipeId, @Nonnull PacketBuffer buffer ) {
		
		boolean shaped = buffer.getBoolean( 0 );
		int recipeWidth = buffer.readVarInt();
		int recipeHeight = buffer.readVarInt();
		NonNullList<Ingredient> ingredients = NonNullList.withSize( recipeWidth * recipeHeight, Ingredient.EMPTY );
		for( int k = 0; k < ingredients.size(); ++k ) {
			ingredients.set( k, Ingredient.read( buffer ) );
		}
		ItemStack result = buffer.readItemStack();
		return new DyedRecipe( recipeId, this, shaped, ingredients, result, recipeWidth, recipeHeight );
	}
	
	@Override
	public void write( @Nonnull PacketBuffer buffer, @Nonnull DyedRecipe recipe ) {
		
		buffer.writeBoolean( recipe.isShaped() );
		buffer.writeVarInt( recipe.getRecipeWidth() );
		buffer.writeVarInt( recipe.getRecipeHeight() );
		for( Ingredient ingredient : recipe.getIngredients() ) {
			ingredient.write( buffer );
		}
		buffer.writeItemStack( recipe.getResult() );
	}
}
