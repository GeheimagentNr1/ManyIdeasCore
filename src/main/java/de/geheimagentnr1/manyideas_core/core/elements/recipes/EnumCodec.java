package de.geheimagentnr1.manyideas_core.core.elements.recipes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.codecs.PrimitiveCodec;
import de.geheimagentnr1.manyideas_core.core.util.SimpleStringRepresentable;
import org.jetbrains.annotations.NotNull;


public class EnumCodec<T extends Enum<T> & SimpleStringRepresentable> implements PrimitiveCodec<T> {
	
	
	@NotNull
	private final Class<T> enumClass;
	
	public EnumCodec( @NotNull Class<T> enumClass ) {
		
		this.enumClass = enumClass;
	}
	
	@Override
	public <T1> DataResult<T> read( DynamicOps<T1> ops, T1 input ) {
		
		return Codec.STRING.read( ops, input ).map( value -> {
			for( T enumConstant : enumClass.getEnumConstants() ) {
				if( enumConstant.getSerializedName().equals( value ) ) {
					return enumConstant;
				}
			}
			return null;
		} );
	}
	
	@Override
	public <T1> T1 write( DynamicOps<T1> ops, T value ) {
		
		return ops.createString( value.getSerializedName() );
	}
}
