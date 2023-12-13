package de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipePattern;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;


public class DyedShapedRecipeSerializer implements RecipeSerializer<DyedShapedRecipe> {
	
	
	private static final Codec<DyedShapedRecipe> CODEC =
		RecordCodecBuilder.create( ( builder ) -> builder.group(
			ShapedRecipePattern.MAP_CODEC.forGetter( DyedShapedRecipe::getPattern ),
			DyedRecipe.RESULT_CODEC.fieldOf( "result" ).forGetter( DyedShapedRecipe::getResult )
		).apply( builder, DyedShapedRecipe::new ) );
	
	@Override
	public Codec<DyedShapedRecipe> codec() {
		
		return CODEC;
	}
	
	@Nullable
	@Override
	public DyedShapedRecipe fromNetwork( @NotNull FriendlyByteBuf buffer ) {
		
		return new DyedShapedRecipe(
			ShapedRecipePattern.fromNetwork( buffer ),
			buffer.readItem()
		);
	}
	
	@Override
	public void toNetwork( @NotNull FriendlyByteBuf buffer, @NotNull DyedShapedRecipe recipe ) {
		
		recipe.getPattern().toNetwork( buffer );
		buffer.writeItem( recipe.getResult() );
	}
}
