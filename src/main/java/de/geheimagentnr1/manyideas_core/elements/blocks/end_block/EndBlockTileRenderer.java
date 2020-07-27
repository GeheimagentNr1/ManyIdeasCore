package de.geheimagentnr1.manyideas_core.elements.blocks.end_block;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;

import javax.annotation.Nonnull;
import java.nio.FloatBuffer;
import java.util.Random;


public class EndBlockTileRenderer extends TileEntityRenderer<EndBlockTile> {
	
	
	private static final ResourceLocation END_SKY_TEXTURE = new ResourceLocation( "minecraft",
		"textures/environment/end_sky.png" );
	
	private static final ResourceLocation END_PORTAL_TEXTURE = new ResourceLocation( "minecraft",
		"textures/entity/end_portal.png" );
	
	@SuppressWarnings( "UnsecureRandomNumberGeneration" )
	private static final Random RANDOM = new Random( 31100L );
	
	private static final FloatBuffer MODELVIEW = GLAllocation.createDirectFloatBuffer( 16 );
	
	private static final FloatBuffer PROJECTION = GLAllocation.createDirectFloatBuffer( 16 );
	
	private final FloatBuffer buffer = GLAllocation.createDirectFloatBuffer( 16 );
	
	@Override
	public void render( @Nonnull EndBlockTile tileEntityIn, double x, double y, double z, float partialTicks,
		int destroyStage ) {
		
		GlStateManager.disableLighting();
		RANDOM.setSeed( 31100L );
		GlStateManager.getMatrix( 2982, MODELVIEW );
		GlStateManager.getMatrix( 2983, PROJECTION );
		double d0 = x * x + y * y + z * z;
		int i = getPasses( d0 );
		boolean flag = false;
		GameRenderer gamerenderer = Minecraft.getInstance().gameRenderer;
		
		for( int j = 0; j < i; ++j ) {
			GlStateManager.pushMatrix();
			float f1 = 2.0F / ( 18 - j );
			if( j == 0 ) {
				bindTexture( END_SKY_TEXTURE );
				f1 = 0.15F;
				GlStateManager.enableBlend();
				GlStateManager.blendFunc( GlStateManager.SourceFactor.SRC_ALPHA,
					GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA );
			}
			
			if( j >= 1 ) {
				bindTexture( END_PORTAL_TEXTURE );
				flag = true;
				gamerenderer.setupFogColor( true );
			}
			
			if( j == 1 ) {
				GlStateManager.enableBlend();
				GlStateManager.blendFunc( GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE );
			}
			GlStateManager.texGenMode( GlStateManager.TexGen.S, 9216 );
			GlStateManager.texGenMode( GlStateManager.TexGen.T, 9216 );
			GlStateManager.texGenMode( GlStateManager.TexGen.R, 9216 );
			GlStateManager.texGenParam( GlStateManager.TexGen.S, 9474, getBuffer( 1.0F, 0.0F, 0.0F ) );
			GlStateManager.texGenParam( GlStateManager.TexGen.T, 9474, getBuffer( 0.0F, 1.0F, 0.0F ) );
			GlStateManager.texGenParam( GlStateManager.TexGen.R, 9474, getBuffer( 0.0F, 0.0F, 1.0F ) );
			GlStateManager.enableTexGen( GlStateManager.TexGen.S );
			GlStateManager.enableTexGen( GlStateManager.TexGen.T );
			GlStateManager.enableTexGen( GlStateManager.TexGen.R );
			GlStateManager.popMatrix();
			GlStateManager.matrixMode( 5890 );
			GlStateManager.pushMatrix();
			GlStateManager.loadIdentity();
			GlStateManager.translatef( 0.5F, 0.5F, 0.0F );
			GlStateManager.scalef( 0.5F, 0.5F, 1.0F );
			float f2 = j + 1;
			GlStateManager.translatef( 17.0F / f2,
				( 2.0F + f2 / 1.5F ) * ( ( Util.milliTime() % 800000L ) / 800000.0F ), 0.0F );
			GlStateManager.rotatef( ( f2 * f2 * 4321.0F + f2 * 9.0F ) * 2.0F, 0.0F, 0.0F, 1.0F );
			GlStateManager.scalef( 4.5F - f2 / 4.0F, 4.5F - f2 / 4.0F, 1.0F );
			GlStateManager.multMatrix( PROJECTION );
			GlStateManager.multMatrix( MODELVIEW );
			Tessellator tessellator = Tessellator.getInstance();
			BufferBuilder bufferbuilder = tessellator.getBuffer();
			bufferbuilder.begin( 7, DefaultVertexFormats.POSITION_COLOR );
			float f3 = ( RANDOM.nextFloat() * 0.5F + 0.1F ) * f1;
			float f4 = ( RANDOM.nextFloat() * 0.5F + 0.4F ) * f1;
			float f5 = ( RANDOM.nextFloat() * 0.5F + 0.5F ) * f1;
			if( tileEntityIn.shouldRender( Direction.NORTH ) ) {
				bufferbuilder.pos( x, y + 1.0D, z ).color( f3, f4, f5, 1.0F ).endVertex();
				bufferbuilder.pos( x + 1.0D, y + 1.0D, z ).color( f3, f4, f5, 1.0F ).endVertex();
				bufferbuilder.pos( x + 1.0D, y, z ).color( f3, f4, f5, 1.0F ).endVertex();
				bufferbuilder.pos( x, y, z ).color( f3, f4, f5, 1.0F ).endVertex();
			}
			if( tileEntityIn.shouldRender( Direction.EAST ) ) {
				bufferbuilder.pos( x + 1.0D, y + 1.0D, z ).color( f3, f4, f5, 1.0F ).endVertex();
				bufferbuilder.pos( x + 1.0D, y + 1.0D, z + 1.0D ).color( f3, f4, f5, 1.0F ).endVertex();
				bufferbuilder.pos( x + 1.0D, y, z + 1.0D ).color( f3, f4, f5, 1.0F ).endVertex();
				bufferbuilder.pos( x + 1.0D, y, z ).color( f3, f4, f5, 1.0F ).endVertex();
			}
			if( tileEntityIn.shouldRender( Direction.SOUTH ) ) {
				bufferbuilder.pos( x, y, z + 1.0D ).color( f3, f4, f5, 1.0F ).endVertex();
				bufferbuilder.pos( x + 1.0D, y, z + 1.0D ).color( f3, f4, f5, 1.0F ).endVertex();
				bufferbuilder.pos( x + 1.0D, y + 1.0D, z + 1.0D ).color( f3, f4, f5, 1.0F ).endVertex();
				bufferbuilder.pos( x, y + 1.0D, z + 1.0D ).color( f3, f4, f5, 1.0F ).endVertex();
			}
			if( tileEntityIn.shouldRender( Direction.WEST ) ) {
				bufferbuilder.pos( x, y, z ).color( f3, f4, f5, 1.0F ).endVertex();
				bufferbuilder.pos( x, y, z + 1.0D ).color( f3, f4, f5, 1.0F ).endVertex();
				bufferbuilder.pos( x, y + 1.0D, z + 1.0D ).color( f3, f4, f5, 1.0F ).endVertex();
				bufferbuilder.pos( x, y + 1.0D, z ).color( f3, f4, f5, 1.0F ).endVertex();
			}
			if( tileEntityIn.shouldRender( Direction.UP ) ) {
				bufferbuilder.pos( x, y + 1.0D, z + 1.0D ).color( f3, f4, f5, 1.0F ).endVertex();
				bufferbuilder.pos( x + 1.0D, y + 1.0D, z + 1.0D ).color( f3, f4, f5, 1.0F ).endVertex();
				bufferbuilder.pos( x + 1.0D, y + 1.0D, z ).color( f3, f4, f5, 1.0F ).endVertex();
				bufferbuilder.pos( x, y + 1.0D, z ).color( f3, f4, f5, 1.0F ).endVertex();
			}
			if( tileEntityIn.shouldRender( Direction.DOWN ) ) {
				bufferbuilder.pos( x, y, z ).color( f3, f4, f5, 1.0F ).endVertex();
				bufferbuilder.pos( x + 1.0D, y, z ).color( f3, f4, f5, 1.0F ).endVertex();
				bufferbuilder.pos( x + 1.0D, y, z + 1.0D ).color( f3, f4, f5, 1.0F ).endVertex();
				bufferbuilder.pos( x, y, z + 1.0D ).color( f3, f4, f5, 1.0F ).endVertex();
			}
			tessellator.draw();
			GlStateManager.popMatrix();
			GlStateManager.matrixMode( 5888 );
			bindTexture( END_SKY_TEXTURE );
		}
		
		GlStateManager.disableBlend();
		GlStateManager.disableTexGen( GlStateManager.TexGen.S );
		GlStateManager.disableTexGen( GlStateManager.TexGen.T );
		GlStateManager.disableTexGen( GlStateManager.TexGen.R );
		GlStateManager.enableLighting();
		if( flag ) {
			gamerenderer.setupFogColor( false );
		}
		
	}
	
	private int getPasses( double p_191286_1_ ) {
		
		int i;
		if( p_191286_1_ > 36864.0D ) {
			i = 1;
		} else {
			if( p_191286_1_ > 25600.0D ) {
				i = 3;
			} else {
				if( p_191286_1_ > 16384.0D ) {
					i = 5;
				} else {
					if( p_191286_1_ > 9216.0D ) {
						i = 7;
					} else {
						if( p_191286_1_ > 4096.0D ) {
							i = 9;
						} else {
							if( p_191286_1_ > 1024.0D ) {
								i = 11;
							} else {
								if( p_191286_1_ > 576.0D ) {
									i = 13;
								} else {
									if( p_191286_1_ > 256.0D ) {
										i = 14;
									} else {
										i = 15;
									}
								}
							}
						}
					}
				}
			}
		}
		
		return i;
	}
	
	private FloatBuffer getBuffer( float p_147525_1_, float p_147525_2_, float p_147525_3_ ) {
		
		buffer.clear();
		buffer.put( p_147525_1_ ).put( p_147525_2_ ).put( p_147525_3_ ).put( 0.0f );
		buffer.flip();
		return buffer;
	}
}
