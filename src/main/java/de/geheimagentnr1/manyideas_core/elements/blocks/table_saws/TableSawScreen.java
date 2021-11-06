package de.geheimagentnr1.manyideas_core.elements.blocks.table_saws;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;


@OnlyIn( Dist.CLIENT )
public class TableSawScreen extends ContainerScreen<TableSawContainer> {
	
	
	private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(
		ManyIdeasCore.MODID,
		"textures/gui/table_saws/table_saw_gui.png"
	);
	
	private float scrollOffs;
	
	private boolean scrolling;
	
	private int startIndex;
	
	private boolean displayRecipes;
	
	public TableSawScreen( TableSawContainer _menu, PlayerInventory _inventory, ITextComponent _title ) {
		
		super( _menu, _inventory, _title );
		_menu.setInventoryUpdateListener( this::containerChanged );
		initData();
	}
	
	private void initData() {
		
		--titleLabelY;
	}
	
	public void render( @Nonnull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks ) {
		
		super.render( matrixStack, mouseX, mouseY, partialTicks );
		renderTooltip( matrixStack, mouseX, mouseY );
	}
	
	protected void renderBg( @Nonnull MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY ) {
		
		renderBackground( matrixStack );
		//noinspection deprecation
		RenderSystem.color4f( 1.0F, 1.0F, 1.0F, 1.0F );
		Objects.requireNonNull( minecraft ).getTextureManager().bind( BACKGROUND_TEXTURE );
		int i = leftPos;
		int j = topPos;
		blit( matrixStack, i, j, 0, 0, imageWidth, imageHeight );
		int k = (int)( 41.0F * scrollOffs );
		blit( matrixStack, i + 119, j + 15 + k, 176 + ( isScrollBarActive() ? 0 : 12 ), 0, 12, 15 );
		int l = leftPos + 52;
		int i1 = topPos + 14;
		int j1 = startIndex + 12;
		renderButtons( matrixStack, mouseX, mouseY, l, i1, j1 );
		renderRecipes( l, i1, j1 );
	}
	
	protected void renderTooltip( @Nonnull MatrixStack matrixStack, int mouseX, int mouseY ) {
		
		super.renderTooltip( matrixStack, mouseX, mouseY );
		if( displayRecipes ) {
			int i = leftPos + 52;
			int j = topPos + 14;
			int k = startIndex + 12;
			List<TableSawRecipe> list = menu.getRecipes();
			
			for( int l = startIndex; l < k && l < menu.getNumRecipes(); ++l ) {
				int i1 = l - startIndex;
				int j1 = i + ( i1 % 4 << 4 );
				int k1 = j + i1 / 4 * 18 + 2;
				if( mouseX >= j1 && mouseX < j1 + 16 && mouseY >= k1 && mouseY < k1 + 18 ) {
					renderTooltip( matrixStack, list.get( l ).getResultItem(), mouseX, mouseY );
				}
			}
		}
		
	}
	
	private void renderButtons(
		MatrixStack matrixStack,
		int mouseX,
		int mouseY,
		int left,
		int top,
		int recipeIndexOffsetMax ) {
		
		for( int i = startIndex; i < recipeIndexOffsetMax && i < menu.getNumRecipes(); ++i ) {
			int j = i - startIndex;
			int k = left + ( j % 4 << 4 );
			int l = j / 4;
			int i1 = top + l * 18 + 2;
			int j1 = imageHeight;
			if( i == menu.getSelectedRecipeIndex() ) {
				j1 += 18;
			} else {
				if( mouseX >= k && mouseY >= i1 && mouseX < k + 16 && mouseY < i1 + 18 ) {
					j1 += 36;
				}
			}
			
			blit( matrixStack, k, i1 - 1, 0, j1, 16, 18 );
		}
		
	}
	
	private void renderRecipes( int left, int top, int recipeIndexOffsetMax ) {
		
		List<TableSawRecipe> list = menu.getRecipes();
		
		for( int i = startIndex; i < recipeIndexOffsetMax && i < menu.getNumRecipes(); ++i ) {
			int j = i - startIndex;
			int k = left + ( j % 4 << 4 );
			int l = j / 4;
			int i1 = top + l * 18 + 2;
			Objects.requireNonNull( minecraft )
				.getItemRenderer()
				.renderAndDecorateItem( list.get( i ).getResultItem(), k, i1 );
		}
		
	}
	
	public boolean mouseClicked( double p_231044_1_, double p_231044_3_, int p_231044_5_ ) {
		
		scrolling = false;
		if( displayRecipes ) {
			int i = leftPos + 52;
			int j = topPos + 14;
			int k = startIndex + 12;
			
			for( int l = startIndex; l < k; ++l ) {
				int i1 = l - startIndex;
				double d0 = p_231044_1_ - ( i + ( i1 % 4 << 4 ) );
				double d1 = p_231044_3_ - ( j + i1 / 4.0 * 18 );
				if( d0 >= 0.0D && d1 >= 0.0D && d0 < 16.0D && d1 < 18.0D &&
					menu.clickMenuButton( Objects.requireNonNull( Objects.requireNonNull( minecraft ).player ), l ) ) {
					Minecraft.getInstance()
						.getSoundManager()
						.play( SimpleSound.forUI( SoundEvents.UI_STONECUTTER_SELECT_RECIPE, 1.0F ) );
					Objects.requireNonNull( minecraft.gameMode ).handleInventoryButtonClick( ( menu ).containerId, l );
					return true;
				}
			}
			
			int i1 = leftPos + 119;
			int i2 = topPos + 9;
			if( p_231044_1_ >= i1 && p_231044_1_ < ( i1 + 12 ) && p_231044_3_ >= i2 &&
				p_231044_3_ < ( i2 + 54 ) ) {
				scrolling = true;
			}
		}
		
		return super.mouseClicked( p_231044_1_, p_231044_3_, p_231044_5_ );
	}
	
	public boolean mouseDragged(
		double p_231045_1_,
		double p_231045_3_,
		int p_231045_5_,
		double p_231045_6_,
		double p_231045_8_ ) {
		
		if( scrolling && isScrollBarActive() ) {
			int i = topPos + 14;
			int j = i + 54;
			scrollOffs = ( (float)p_231045_3_ - i - 7.5F ) / ( ( j - i ) - 15.0F );
			scrollOffs = MathHelper.clamp( scrollOffs, 0.0F, 1.0F );
			startIndex = (int)( ( scrollOffs * getOffscreenRows() ) + 0.5D ) << 2;
			return true;
		} else {
			return super.mouseDragged( p_231045_1_, p_231045_3_, p_231045_5_, p_231045_6_, p_231045_8_ );
		}
	}
	
	public boolean mouseScrolled( double p_231043_1_, double p_231043_3_, double p_231043_5_ ) {
		
		if( isScrollBarActive() ) {
			int i = getOffscreenRows();
			scrollOffs -= p_231043_5_ / i;
			scrollOffs = MathHelper.clamp( scrollOffs, 0.0F, 1.0F );
			startIndex = (int)( ( scrollOffs * i ) + 0.5D ) << 2;
		}
		
		return true;
	}
	
	private boolean isScrollBarActive() {
		
		return displayRecipes && menu.getNumRecipes() > 12;
	}
	
	private int getOffscreenRows() {
		
		return ( menu.getNumRecipes() + 4 - 1 ) / 4 - 3;
	}
	
	private void containerChanged() {
		
		displayRecipes = menu.hasInputItem();
		if( !displayRecipes ) {
			scrollOffs = 0.0F;
			startIndex = 0;
		}
		
	}
}