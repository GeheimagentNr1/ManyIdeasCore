package de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes;

import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.NotNull;

import java.util.List;


//Since 26.1 RecipeSerializer is a record of codec and stream codec, this class builds it
public class DyedShapelessRecipeSerializer {


	private static final int MAX_INGREDIENTS = 9;

	private static final MapCodec<DyedShapelessRecipe> SHAPELESS_CODEC =
		RecordCodecBuilder.mapCodec( builder -> builder.group(
			Ingredient.CODEC.listOf().fieldOf( "ingredients" ).<NonNullList<Ingredient>>flatXmap(
				( recipe ) -> {
					if( recipe.isEmpty() ) {
						return DataResult.error( () -> "No ingredients for shapeless recipe" );
					} else if( recipe.size() > MAX_INGREDIENTS ) {
						return DataResult.error( () -> "Too many ingredients for shapeless recipe" );
					} else {
						NonNullList<Ingredient> list = NonNullList.create();
						list.addAll( recipe );
						return DataResult.success( list );
					}
				},
				list -> DataResult.<List<Ingredient>>success( list )
			).forGetter( DyedShapelessRecipe::getIngredients ),
			DyedRecipe.RESULT_CODEC.fieldOf( "result" ).forGetter( DyedShapelessRecipe::getResult )
		).apply( builder, DyedShapelessRecipe::new ) );

	private static final StreamCodec<RegistryFriendlyByteBuf, DyedShapelessRecipe> STREAM_CODEC = StreamCodec.of(
		DyedShapelessRecipeSerializer::toNetwork, DyedShapelessRecipeSerializer::fromNetwork
	);

	public MapCodec<DyedShapelessRecipe> codec() {

		return SHAPELESS_CODEC;
	}

	public StreamCodec<RegistryFriendlyByteBuf, DyedShapelessRecipe> streamCodec() {

		return STREAM_CODEC;
	}
	
	@NotNull
	public RecipeSerializer<DyedShapelessRecipe> createSerializer() {
		
		return new RecipeSerializer<>( codec(), streamCodec() );
	}

	private static DyedShapelessRecipe fromNetwork( @NotNull RegistryFriendlyByteBuf buffer ) {

		int ingredientCount = buffer.readVarInt();
		NonNullList<Ingredient> ingredients = NonNullList.create();
		for( int i = 0; i < ingredientCount; i++ ) {
			ingredients.add( Ingredient.CONTENTS_STREAM_CODEC.decode( buffer ) );
		}
		return new DyedShapelessRecipe(
			ingredients,
			ItemStackTemplate.STREAM_CODEC.decode( buffer )
		);
	}

	private static void toNetwork( @NotNull RegistryFriendlyByteBuf buffer, @NotNull DyedShapelessRecipe recipe ) {

		NonNullList<Ingredient> ingredients = recipe.getIngredients();
		buffer.writeVarInt( ingredients.size() );
		for( Ingredient ingredient : ingredients ) {
			Ingredient.CONTENTS_STREAM_CODEC.encode( buffer, ingredient );
		}
		ItemStackTemplate.STREAM_CODEC.encode( buffer, recipe.getResult() );
	}
}
