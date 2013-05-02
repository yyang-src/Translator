package yyang.translate.core.type;

import yyang.translate.core.Decoder;
import yyang.translate.core.Encoder;

public class STRING extends DataType implements Encoder<String[]>, Decoder {
	public String get_targetType() {
		return "String";
	}

	public byte[] encode(String[] val) {
		int length = 0;
		for (String s : val) {
			int len = s.length();
			length += 2 + len * 2;// 1 char = 2 byte
		}
		byte[] target = new byte[length];
		Encoder<short[]> lencoder = new INT16();
		for (int i = 0; i < val.length; i++) {
			String s = val[i];
			int len = s.length() * 2;
			byte[] lens = lencoder.encode(new short[] { Integer.valueOf(len)
					.shortValue() });
			int index = (2 + s.length()) * i;
			target[index] = lens[0];
			target[index + 1] = lens[0];
			byte[] strBytes = s.getBytes();
			for (int j = 0; j < len; j++) {
				target[index + 2 + j] = strBytes[j];
			}
		}
		return target;
	}

}
