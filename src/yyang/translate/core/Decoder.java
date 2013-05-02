package yyang.translate.core;

import java.io.IOException;
import java.io.InputStream;

public interface Decoder<T> {

	T decode(InputStream source)throws IOException;

}
