package yyang.translate.core.type;

import yyang.translate.core.Decoder;
import yyang.translate.core.Encoder;


public class INT32 extends DataType implements Encoder<int[]>,Decoder{
	public String get_targetType(){
		return "int";
	}

	@Override
	public byte[] encode(int[] val) {
 		byte[] target=new byte[val.length*4];
 		for(int i=0;i<val.length;i++){ 			
 			for(int j = 0;j < 4;j++){
 				target[i*4+j] = (byte)(val[i] >> (24 - j * 8)); 
 			}
 		}
		return target;
	}
}
