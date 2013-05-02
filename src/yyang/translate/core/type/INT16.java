package yyang.translate.core.type;

import yyang.translate.core.Decoder;
import yyang.translate.core.Encoder;

public class INT16 extends DataType implements Encoder<short[]>, Decoder {
	public String get_targetType() {
		return "short";
	}

	public byte[] encode(short[] val) {
		byte[] target= new byte[2*val.length];
 		for(int i=0;i<val.length;i++){ 			
 			int n = Short.valueOf(val[i]).intValue();
 			for(int j = 0;j < 2;j++){
 				target[i*2+j] = (byte)(n >> (24 - j * 8)); 
 			}
 		}
		return target;
	}
}
