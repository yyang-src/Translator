package yyang.translate.core.type;

import yyang.translate.core.Encoder;


public class BYTE extends DataType implements Encoder<byte[]>{

 
	public byte[] encode(byte[] val) {
		byte dest[]=new byte[val.length];
		System.arraycopy(val, 0, dest, 0, val.length);
		return dest;
	}

	@Override
	public String get_targetType() {
		return "byte";
	}
 
 

}
