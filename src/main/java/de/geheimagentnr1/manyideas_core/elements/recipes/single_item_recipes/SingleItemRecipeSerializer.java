package de.geheimagentnr1.manyideas_core.elements.recipes.single_item_recipes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class SingleItemRecipeSerializer<T extends SingleItemRecipe> implements RecipeSerializer<T> {
	
	
	private static final MapCodec<ItemStack> RESULT_CODEC = RecordCodecBuilder.mapCodec( builder -> builder.group(
		BuiltInRegistries.ITEM.byNameCodec().fieldOf( "result" ).forGetter( ItemStack::getItem ),
		ExtraCodecs.POSITIVE_INT.fieldOf( "count" ).orElse( 1 ).forGetter( ItemStack::getCount )
	).apply( builder, ItemStack::new ) );
	
	@NotNull
	private final ISingleItemRecipeFactory<T> factory;
	
	private final MapCodec<T> codec;
	
	private final StreamCodec<RegistryFriendlyByteBuf, T> streamCodec;
	
	public SingleItemRecipeSerializer( @NotNull ISingleItemRecipeFactory<T> _factory ) {
		
		factory = _factory;
		codec = RecordCodecBuilder.mapCodec( ( builder ) -> builder.group(
			Codec.STRING.optionalFieldOf( "group", "" ).forGetter( SingleItemRecipe::getGroup ),
			Ingredient.CODEC_NONEMPTY.fieldOf( "ingredient" ).forGetter( SingleItemRecipe::getIngredient ),
			RESULT_CODEC.forGetter( SingleItemRecipe::getResult )
		).apply( builder, factory::create ) );
		this.streamCodec = StreamCodec.composite(
			ByteBufCodecs.STRING_UTF8, SingleItemRecipe::getGroup,
			Ingredient.CONTENTS_STREAM_CODEC, SingleItemRecipe::getIngredient,
			ItemStack.STREAM_CODEC, SingleItemRecipe::getResult,
			factory::create
		);
	}
	
	@Override
	public MapCodec<T> codec() {
		
		return codec;
	}
	
	@Override
	public StreamCodec<RegistryFriendlyByteBuf, T> streamCodec() {
		
		return streamCodec;
	}
}
