package yyang.translate.core.type;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import yyang.translate.core.Decoder;
import yyang.translate.core.Encoder;

public class FLOAT extends DataType implements Encoder<float[]>, Decoder<Float> {

	public void Encode(float[] val, OutputStream out) throws IOException {
		DataOutputStream wout = new DataOutputStream(out);
		for (int i = 0; i < val.length; i++)
			wout.writeFloat(val[i]);
	}

	public String get_targetType() {
		return "float";
	}

	public Float decode(InputStream source) throws IOException {
		DataInputStream d = new DataInputStream(source);
		float num = d.readFloat();
		return num;
	}

}
