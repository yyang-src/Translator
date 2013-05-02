package yyang.translate.core;

public interface Encoder<T> {
 public byte[] encode(T val);
}
