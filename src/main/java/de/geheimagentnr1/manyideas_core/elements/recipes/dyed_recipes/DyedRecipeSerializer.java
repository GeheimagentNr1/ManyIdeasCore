package de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes;

import com.google.common.collect.Sets;
import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.dyed.DyeBlockItem;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.apache.commons.lang3.NotImplementedException;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;
import java.util.function.Function;


public class DyedRecipeSerializer implements RecipeSerializer<DyedRecipe> {
	
	
	private static final int MAX_WIDTH = 3;
	
	private static final int MAX_HEIGHT = 3;
	
	private static final Codec<Ingredient> INGREDIENT_OR_COLOR_INGREDIENT_CODEC = ExtraCodecs.xor(
		Ingredient.CODEC_NONEMPTY,
		ColorIngredientSerializer.CODEC
	).xmap(
		either -> either.map(
			Function.identity(),
			Function.identity()
		),
		ingredient -> {
			if( ingredient instanceof ColorIngredient colorIngredient ) {
				return Either.right( colorIngredient );
			} else {
				return Either.left( ingredient );
			}
		}
	);
	
	private static final Codec<Item> DYE_BLOCK_ITEM_CODEC = ExtraCodecs.validate(
		BuiltInRegistries.ITEM.byNameCodec(),
		( item ) -> item instanceof DyeBlockItem
			? DataResult.success( item )
			: DataResult.error( () -> "Non DyeBlockItem result not allowed here" )
	);
	
	
	private static final Codec<ItemStack> RESULT_CODEC = RecordCodecBuilder.create( builder -> builder.group(
		DYE_BLOCK_ITEM_CODEC.fieldOf( "item" ).forGetter( ItemStack::getItem ),
		ExtraCodecs.strictOptionalField( ExtraCodecs.POSITIVE_INT, "count", 1 ).forGetter( ItemStack::getCount )
	).apply( builder, ItemStack::new ) );
	
	private static final Codec<List<String>> PATTERN_CODEC = Codec.STRING.listOf().flatXmap(
		( pattern ) -> {
			if( pattern.size() > MAX_HEIGHT ) {
				return DataResult.error( () -> "Invalid pattern: too many rows, " + MAX_HEIGHT + " is maximum" );
			} else {
				if( pattern.isEmpty() ) {
					return DataResult.error( () -> "Invalid pattern: empty pattern not allowed" );
				} else {
					int length = pattern.get( 0 ).length();
					
					for( String element : pattern ) {
						if( element.length() > MAX_WIDTH ) {
							return DataResult.error( () -> "Invalid pattern: too many columns, " + MAX_WIDTH +
								" is maximum" );
						}
						if( length != element.length() ) {
							return DataResult.error( () -> "Invalid pattern: each row must be the same width" );
						}
					}
					return DataResult.success( pattern );
				}
			}
		},
		DataResult::success
	);
	
	private static final Codec<String> SINGLE_CHARACTER_STRING_CODEC = Codec.STRING.flatXmap( ( key ) -> {
		if( key.length() == 1 ) {
			return " ".equals( key )
				? DataResult.error( () -> "Invalid key entry: ' ' is a reserved symbol." )
				: DataResult.success( key );
		} else {
			return DataResult.error( () -> "Invalid key entry: '" + key +
				"' is an invalid symbol (must be 1 character only)." );
		}
	}, DataResult::success );
	
	private static final Codec<RawShapedDyedRecipe> RAW_CODEC =
		RecordCodecBuilder.create( ( builder ) -> builder.group(
			ExtraCodecs.validate(
				Codec.BOOL,
				value -> value
					? DataResult.success( true )
					: DataResult.error( () -> "Invalid value for shaped recipe" )
			).fieldOf( "shaped" ).forGetter( RawShapedDyedRecipe::shaped ),
			ExtraCodecs.strictUnboundedMap(
					SINGLE_CHARACTER_STRING_CODEC,
					INGREDIENT_OR_COLOR_INGREDIENT_CODEC
				)
				.fieldOf( "key" )
				.forGetter( RawShapedDyedRecipe::key ),
			PATTERN_CODEC.fieldOf( "pattern" ).forGetter( RawShapedDyedRecipe::pattern ),
			RESULT_CODEC.fieldOf( "result" ).forGetter( RawShapedDyedRecipe::result )
		).apply( builder, RawShapedDyedRecipe::new ) );
	
	private static final Codec<DyedRecipe> SHAPED_CODEC =
		RAW_CODEC.flatXmap(
			( recipe ) -> {
				String[] pattern = DyedRecipe.shrink( recipe.pattern() );
				int width = pattern[0].length();
				int height = pattern.length;
				NonNullList<Ingredient> nonnulllist = NonNullList.withSize( width * height, Ingredient.EMPTY );
				Set<String> keys = Sets.newHashSet( recipe.key().keySet() );
				
				for( int k = 0; k < pattern.length; ++k ) {
					String row = pattern[k];
					
					for( int l = 0; l < row.length(); ++l ) {
						String key = row.substring( l, l + 1 );
						Ingredient ingredient = key.equals( " " ) ? Ingredient.EMPTY : recipe.key().get( key );
						if( ingredient == null ) {
							return DataResult.error( () -> "Pattern references symbol '" + key +
								"' but it's not defined in the key" );
						}
						
						keys.remove( key );
						nonnulllist.set( l + width * k, ingredient );
					}
				}
				if( keys.isEmpty() ) {
					return DataResult.success(
						new DyedRecipe(
							true,
							nonnulllist,
							recipe.result(),
							width,
							height
						)
					);
				} else {
					return DataResult.error( () -> "Key defines symbols that aren't used in pattern: " + keys );
				}
			},
			( recipe ) -> {
				throw new NotImplementedException( "Serializing DyedRecipe is not implemented yet." );
			}
		);
	
	private static final Codec<DyedRecipe> SHAPELESS_CODEC = RecordCodecBuilder.create( builder -> builder.group(
		ExtraCodecs.validate(
			Codec.BOOL,
			value -> value
				? DataResult.error( () -> "Invalid value for shapeless recipe" )
				: DataResult.success( false )
		).fieldOf( "shaped" ).forGetter( DyedRecipe::isShaped ),
		INGREDIENT_OR_COLOR_INGREDIENT_CODEC.listOf().fieldOf( "ingredients" ).flatXmap(
			( recipe ) -> {
				Ingredient[] ingredients = recipe.stream()
					.filter( ( ingredient ) -> !ingredient.isEmpty() )
					.toArray( Ingredient[]::new );
				if( ingredients.length == 0 ) {
					return DataResult.error( () -> "No ingredients for shapeless recipe" );
				} else {
					return ingredients.length > MAX_WIDTH * MAX_HEIGHT
						? DataResult.error( () -> "Too many ingredients for shapeless recipe" )
						: DataResult.success( NonNullList.of( Ingredient.EMPTY, ingredients ) );
				}
			},
			DataResult::success
		).forGetter( DyedRecipe::getIngredients ),
		RESULT_CODEC.fieldOf( "result" ).forGetter( DyedRecipe::getResult )
	).apply( builder, ( shaped, ingredients, stack ) -> new DyedRecipe(
		shaped,
		ingredients,
		stack,
		ingredients.size(),
		1
	) ) );
	
	private static final Codec<DyedRecipe> CODEC = ExtraCodecs.xor(
		SHAPED_CODEC,
		SHAPELESS_CODEC
	).xmap(
		either -> either.map(
			Function.identity(),
			Function.identity()
		),
		dyedRecipe -> {
			if( dyedRecipe.isShaped() ) {
				return Either.left( dyedRecipe );
			} else {
				return Either.right( dyedRecipe );
			}
		}
	);
	
	@Override
	public Codec<DyedRecipe> codec() {
		
		return CODEC;
	}
	
	@Nullable
	@Override
	public DyedRecipe fromNetwork( @NotNull FriendlyByteBuf buffer ) {
		
		boolean shaped = buffer.readBoolean();
		int recipeWidth = buffer.readVarInt();
		int recipeHeight = buffer.readVarInt();
		NonNullList<Ingredient> ingredients = NonNullList.create();
		for( int k = 0; k < recipeWidth * recipeHeight; ++k ) {
			ingredients.add( Ingredient.fromNetwork( buffer ) );
		}
		ItemStack result = buffer.readItem();
		return new DyedRecipe(
			shaped,
			ingredients,
			result,
			recipeWidth,
			recipeHeight
		);
	}
	
	@Override
	public void toNetwork( @NotNull FriendlyByteBuf buffer, @NotNull DyedRecipe recipe ) {
		
		buffer.writeBoolean( recipe.isShaped() );
		buffer.writeVarInt( recipe.getRecipeWidth() );
		buffer.writeVarInt( recipe.getRecipeHeight() );
		for( Ingredient ingredient : recipe.getIngredients() ) {
			ingredient.toNetwork( buffer );
		}
		buffer.writeItem( recipe.getResult() );
	}
}
