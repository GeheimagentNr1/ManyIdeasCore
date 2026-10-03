package de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipePattern;
import org.jetbrains.annotations.NotNull;


//Since 26.1 RecipeSerializer is a record of codec and stream codec, this class builds it
public class DyedShapedRecipeSerializer {
	
	
	private static final MapCodec<DyedShapedRecipe> CODEC =
		RecordCodecBuilder.mapCodec( ( builder ) -> builder.group(
			ShapedRecipePattern.MAP_CODEC.forGetter( DyedShapedRecipe::getPattern ),
			DyedRecipe.RESULT_CODEC.fieldOf( "result" ).forGetter( DyedShapedRecipe::getResult )
		).apply( builder, DyedShapedRecipe::new ) );
	
	private static final StreamCodec<RegistryFriendlyByteBuf, DyedShapedRecipe> STREAM_CODEC = StreamCodec.of(
		DyedShapedRecipeSerializer::toNetwork, DyedShapedRecipeSerializer::fromNetwork
	);
	
	public MapCodec<DyedShapedRecipe> codec() {
		
		return CODEC;
	}
	
	public StreamCodec<RegistryFriendlyByteBuf, DyedShapedRecipe> streamCodec() {
		
		return STREAM_CODEC;
	}
	
	@NotNull
	public RecipeSerializer<DyedShapedRecipe> createSerializer() {
		
		return new RecipeSerializer<>( codec(), streamCodec() );
	}
	
	private static DyedShapedRecipe fromNetwork( @NotNull RegistryFriendlyByteBuf buffer ) {
		
		return new DyedShapedRecipe(
			ShapedRecipePattern.STREAM_CODEC.decode( buffer ),
			ItemStackTemplate.STREAM_CODEC.decode( buffer )
		);
	}
	
	private static void toNetwork( @NotNull RegistryFriendlyByteBuf buffer, @NotNull DyedShapedRecipe recipe ) {
		
		ShapedRecipePattern.STREAM_CODEC.encode( buffer, recipe.getPattern() );
		ItemStackTemplate.STREAM_CODEC.encode( buffer, recipe.getResult() );
	}
}
