package de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes;

import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.NotNull;


public class DyedShapelessRecipeSerializer implements RecipeSerializer<DyedShapelessRecipe> {
	
	
	private static final int MAX_INGREDIENTS = 9;
	
	private static final MapCodec<DyedShapelessRecipe> SHAPELESS_CODEC =
		RecordCodecBuilder.mapCodec( builder -> builder.group(
			Ingredient.CODEC_NONEMPTY.listOf().fieldOf( "ingredients" ).flatXmap(
				( recipe ) -> {
					Ingredient[] ingredients = recipe.stream()
						.filter( ( ingredient ) -> !ingredient.isEmpty() )
						.toArray( Ingredient[]::new );
					if( ingredients.length == 0 ) {
						return DataResult.error( () -> "No ingredients for shapeless recipe" );
					} else {
						return ingredients.length > MAX_INGREDIENTS
							? DataResult.error( () -> "Too many ingredients for shapeless recipe" )
							: DataResult.success( NonNullList.of( Ingredient.EMPTY, ingredients ) );
					}
				},
				DataResult::success
			).forGetter( DyedShapelessRecipe::getIngredients ),
			DyedRecipe.RESULT_CODEC.fieldOf( "result" ).forGetter( DyedShapelessRecipe::getResult )
		).apply( builder, DyedShapelessRecipe::new ) );
	
	private static final StreamCodec<RegistryFriendlyByteBuf, DyedShapelessRecipe> STREAM_CODEC = StreamCodec.of(
		DyedShapelessRecipeSerializer::toNetwork, DyedShapelessRecipeSerializer::fromNetwork
	);
	
	@Override
	public MapCodec<DyedShapelessRecipe> codec() {
		
		return SHAPELESS_CODEC;
	}
	
	@Override
	public StreamCodec<RegistryFriendlyByteBuf, DyedShapelessRecipe> streamCodec() {
		
		return STREAM_CODEC;
	}
	
	private static DyedShapelessRecipe fromNetwork( @NotNull RegistryFriendlyByteBuf buffer ) {
		
		int ingredientCount = buffer.readVarInt();
		NonNullList<Ingredient> ingredients = NonNullList.withSize( ingredientCount, Ingredient.EMPTY );
		for( int i = 0; i < ingredientCount; i++ ) {
			ingredients.set( i, Ingredient.CONTENTS_STREAM_CODEC.decode( buffer ) );
		}
		return new DyedShapelessRecipe(
			ingredients,
			ItemStack.STREAM_CODEC.decode( buffer )
		);
	}
	
	private static void toNetwork( @NotNull RegistryFriendlyByteBuf buffer, @NotNull DyedShapelessRecipe recipe ) {
		
		NonNullList<Ingredient> ingredients = recipe.getIngredients();
		buffer.writeVarInt( ingredients.size() );
		for( Ingredient ingredient : ingredients ) {
			Ingredient.CONTENTS_STREAM_CODEC.encode( buffer, ingredient );
		}
		ItemStack.STREAM_CODEC.encode( buffer, recipe.getResult() );
	}
}
