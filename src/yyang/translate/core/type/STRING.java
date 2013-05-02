package yyang.translate.core.type;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import yyang.translate.core.Decoder;
import yyang.translate.core.Encoder;

public class STRING extends DataType implements Encoder<String[]>, Decoder<String> {
	public String get_targetType() {
		return "String";
	}

	@Override
	public String decode(InputStream source) throws IOException {
		Decoder<Short> lencoder = new INT16();
		short length = lencoder.decode(source);
		byte[] b = new byte[length];
		source.read(b,0,length);
		return new String(b);
	}

	@Override
	public void Encode(String[] val, OutputStream out) throws IOException {
		DataOutputStream wout = new DataOutputStream(out);
		for (int i = 0; i < val.length; i++){
			wout.writeShort(val[i].length());
			wout.write(val[i].getBytes());
		}
		 
	}

}
