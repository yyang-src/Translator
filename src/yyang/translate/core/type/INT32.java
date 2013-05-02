package yyang.translate.core.type;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import yyang.translate.core.Decoder;
import yyang.translate.core.Encoder;

public class INT32 extends DataType implements Encoder<int[]>, Decoder<Integer> {
	public String get_targetType() {
		return "int";
	}

	@Override
	public Integer decode(InputStream source) throws IOException {
		byte[] b = new byte[4];
		source.read(b, 0, 4);
		int bits = 0;
		for (int i = 0; i < 4; i++) {
			int n = Byte.valueOf(b[i]).intValue();
			bits |= (n << (24 - i * 8));
		}
		return bits;
	}

	@Override
	public void Encode(int[] val, OutputStream out) throws IOException {
		DataOutputStream wout = new DataOutputStream(out);
		for (int i = 0; i < val.length; i++)
			wout.writeInt(val[i]);
	}
}
