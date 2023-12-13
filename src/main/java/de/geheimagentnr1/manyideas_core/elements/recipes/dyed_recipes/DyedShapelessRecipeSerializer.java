package de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;


public class DyedShapelessRecipeSerializer implements RecipeSerializer<DyedShapelessRecipe> {
	
	
	private static final int MAX_INGREDIENTS = 9;
	
	private static final Codec<DyedShapelessRecipe> SHAPELESS_CODEC =
		RecordCodecBuilder.create( builder -> builder.group(
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
	
	@Override
	public Codec<DyedShapelessRecipe> codec() {
		
		return SHAPELESS_CODEC;
	}
	
	@Nullable
	@Override
	public DyedShapelessRecipe fromNetwork( @NotNull FriendlyByteBuf buffer ) {
		
		int ingredientCount = buffer.readVarInt();
		NonNullList<Ingredient> ingredients = NonNullList.withSize( ingredientCount, Ingredient.EMPTY );
		for( int i = 0; i < ingredientCount; i++ ) {
			ingredients.set( i, Ingredient.fromNetwork( buffer ) );
		}
		return new DyedShapelessRecipe(
			ingredients,
			buffer.readItem()
		);
	}
	
	@Override
	public void toNetwork( @NotNull FriendlyByteBuf buffer, @NotNull DyedShapelessRecipe recipe ) {
		
		NonNullList<Ingredient> ingredients = recipe.getIngredients();
		buffer.writeVarInt( ingredients.size() );
		for( Ingredient ingredient : ingredients ) {
			ingredient.toNetwork( buffer );
		}
		buffer.writeItem( recipe.getResult() );
	}
}
