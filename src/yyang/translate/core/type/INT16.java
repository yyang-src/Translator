package yyang.translate.core.type;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import yyang.translate.core.Decoder;
import yyang.translate.core.Encoder;

public class INT16 extends DataType implements Encoder<short[]>, Decoder<Short> {
	public String get_targetType() {
		return "short";
	}

//	public byte[] encode(short[] val) {
//		byte[] target = new byte[2 * val.length];
//		int mark = 0x00FF;
//		for (int i = 0; i < val.length; i++) {
//			int n = Short.valueOf(val[i]).intValue();
//
//			for (int j = 0; j < 2; j++) {
//				target[i * 2 + j] = (byte) ((n >> (8 - j * 8)) & mark);
//			}
//		}
//		return target;
//	}
	@Override
	public void Encode(short[] val, OutputStream out) throws IOException {
		DataOutputStream wout = new DataOutputStream(out);
		for (int i = 0; i < val.length; i++) { 
			wout.writeShort(Short.valueOf(val[i]).intValue());
		}
		
	}
	@Override
	public Short decode(InputStream source) throws IOException{
		byte[] b = new byte[2];
		source.read(b,0,2);
		int bits = 0;
		for (int i = 0; i < 2; i++) {
			int n = Byte.valueOf(b[i]).intValue();
			bits |= (n << (8 - i * 8));
		}
		return Integer.valueOf(bits).shortValue();
	}


}
