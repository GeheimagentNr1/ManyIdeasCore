package de.geheimagentnr1.manyideas_core.elements.recipes.single_item_recipes;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;


public abstract class SingleItemRecipe implements IRecipe<IInventory> {
	
	
	protected final Ingredient ingredient;
	
	//package-private
	final ItemStack result;
	
	private final IRecipeType<?> type;
	
	private final IRecipeSerializer<?> serializer;
	
	private final ResourceLocation id;
	
	private final String group;
	
	protected SingleItemRecipe(
		IRecipeType<?> _type,
		IRecipeSerializer<?> _serializer,
		ResourceLocation _id,
		String _group,
		Ingredient _ingredient,
		ItemStack _result ) {
		
		type = _type;
		serializer = _serializer;
		id = _id;
		group = _group;
		ingredient = _ingredient;
		result = _result;
	}
	
	@Nonnull
	@Override
	public IRecipeType<?> getType() {
		
		return type;
	}
	
	@Nonnull
	@Override
	public IRecipeSerializer<?> getSerializer() {
		
		return serializer;
	}
	
	@Nonnull
	@Override
	public ResourceLocation getId() {
		
		return id;
	}
	
	@Nonnull
	@Override
	public String getGroup() {
		
		return group;
	}
	
	@Nonnull
	@Override
	public ItemStack getResultItem() {
		
		return result;
	}
	
	@Nonnull
	@Override
	public NonNullList<Ingredient> getIngredients() {
		
		NonNullList<Ingredient> nonnulllist = NonNullList.create();
		nonnulllist.add( ingredient );
		return nonnulllist;
	}
	
	@Override
	public boolean canCraftInDimensions( int width, int height ) {
		
		return true;
	}
	
	@Nonnull
	@Override
	public ItemStack assemble( @Nonnull IInventory inv ) {
		
		return result.copy();
	}
	
	
	public Ingredient getIngredient() {
		
		return ingredient;
	}
}
