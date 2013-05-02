package yyang.translate.core.type;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import yyang.translate.core.Decoder;
import yyang.translate.core.Encoder;

public class BYTE extends DataType implements Encoder<byte[]>, Decoder<Byte> {

	@Override
	public String get_targetType() {
		return "byte";
	}

	@Override
	public Byte decode(InputStream source) throws IOException {
		byte b = Integer.valueOf(source.read()).byteValue();
		return b;
	}

	public byte[] encode(byte[] val) {
		byte dest[] = new byte[val.length];
		System.arraycopy(val, 0, dest, 0, val.length);
		return dest;
	}

	@Override
	public void Encode(byte[] val, OutputStream out) throws IOException {
//		DataOutputStream wout = new DataOutputStream(out);
		out.write(val);
		
	}

}
