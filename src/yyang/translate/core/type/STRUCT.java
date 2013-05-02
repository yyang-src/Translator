package yyang.translate.core.type;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import yyang.translate.core.Decoder;
import yyang.translate.core.Encoder;

public class STRUCT extends DataType{
	private List<DataType> __CHILDS__;

	public List<DataType> __CHILDS__() {
		return __CHILDS__;
	}

	public void __CHILDS__(DataType child) {
		if (__CHILDS__ == null)
			__CHILDS__ = new ArrayList<DataType>();
		__CHILDS__.add(child);
	}

	public String get_targetType() {
		String name = __NAME__();
		char first = name.charAt(0);
		String methodname = Character.toUpperCase(first) + name.substring(1);
		return methodname;
	}

	public byte[] Encode() {

		return null;
	}

	protected HashMap<String, String> __MEMBERS__() {
		return null;
	}


	public byte[] encode() {
		HashMap<String, String> members = __MEMBERS__();
		Set<String> keys = members.keySet();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		for (String key : keys) {
			Method method;
			try {
				method = this.getClass().getMethod("get" + key);
			} catch (SecurityException e1) {
				throw new RuntimeException(e1);
			} catch (NoSuchMethodException e1) {
				throw new RuntimeException(e1);
			}
			boolean isArray = method.getReturnType().isArray();
			Object val;
			try {
				val = method.invoke(this);
			} catch (IllegalArgumentException e1) {
				throw new RuntimeException(e1);
			} catch (IllegalAccessException e1) {
				throw new RuntimeException(e1);
			} catch (InvocationTargetException e1) {
				throw new RuntimeException(e1);
			}
			String type = members.get(key);
			if ("STRUCT".equals(type)) {
				if (isArray) {
					STRUCT st[] = ((STRUCT[]) val);
					for (STRUCT s : st) {
						byte[] bytes = s.encode();
						try {
							out.write(bytes);
						} catch (IOException e) {
							throw new RuntimeException(e);
						}
					}
				}else{
					try {
						out.write(((STRUCT) val).encode());
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				}
			} else {
				Class cls = null;
				try {
					cls = Class.forName("yyang.translate.core.type." + type);
				} catch (ClassNotFoundException e) {
					throw new RuntimeException("Can Not Found '" + type
							+ "' type");
				}
				Encoder coder = null;
				try {
					coder = (Encoder) cls.newInstance();
				} catch (InstantiationException e) {
					throw new RuntimeException(e);
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				}
				try {
					out.write(coder.encode(val));
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
				
			}
 
			
		}
		return out.toByteArray();
	}

}
