package yyang.translate.core;

import java.io.IOException;
import java.io.OutputStream;

public interface Encoder<T> {
 public void Encode(T val,OutputStream out)throws IOException;
}
