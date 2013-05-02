package yyang.translate.core.type;

import yyang.translate.core.Decoder;
import yyang.translate.core.Encoder;


public class FLOAT extends DataType implements Encoder<float []>,Decoder{

 	public byte[] encode(float[] val) {
 		byte[] target=new byte[val.length*4];
 		for(int i=0;i<val.length;i++){ 			
 			int n = Float.floatToRawIntBits(val[i]);
 			for(int j = 0;j < 4;j++){
 				target[i*4+j] = (byte)(n >> (24 - j * 8)); 
 			}
 		}
		return target;
	}

	public String get_targetType() {
		return "float";
	}
}
