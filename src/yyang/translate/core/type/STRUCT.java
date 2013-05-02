package yyang.translate.core.type;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import yyang.translate.core.Decoder;
import yyang.translate.core.Encoder;

public class STRUCT extends DataType {
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

	protected List<String> __MEMBERS__() {
		return null;
	}
	protected List<String> __MEMBER_TYPES__(){
		return null;
	}

	public void Encode(OutputStream out) throws IOException{
		List<String> members = __MEMBERS__();
		List<String> member_types = __MEMBER_TYPES__();
		int mem_size = members.size();
		for (int i =0;i<mem_size;i++) {
			String key=members.get(i);
			String type = member_types.get(i);
			Method method;
			try {
				method = this.getClass().getMethod("get" + key);
			} catch (SecurityException e1) {
				throw new RuntimeException(e1);
			} catch (NoSuchMethodException e1) {
				throw new RuntimeException(e1);
			}
			Class<?> dataType = method.getReturnType();
			boolean isArray = dataType.isArray();
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
			
			if ("STRUCT".equals(type)) {
				if (isArray) {
					STRUCT st[] = ((STRUCT[]) val);
					for (STRUCT s : st) {
						s.Encode(out);
					}
				} else {
					((STRUCT) val).Encode(out);
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
				if (!isArray) {
					Object array = Array.newInstance(dataType, 1);
					Array.set(array, 0, val);
					val = array;
				}
				coder.Encode(val,out);
			}

		}
	}

	public void decode(InputStream source) throws IOException {
		List<String> members = __MEMBERS__();
		List<String> member_types = __MEMBER_TYPES__();
		int mem_size = members.size();
		for (int k =0;k<mem_size;k++) {
			String key=members.get(k);
			String type = member_types.get(k);
			Method method;
			try {
				method = this.getClass().getMethod("get" + key);
			} catch (SecurityException e1) {
				throw new RuntimeException(e1);
			} catch (NoSuchMethodException e1) {
				throw new RuntimeException(e1);
			}
			Class reType = method.getReturnType();
			boolean isArray = reType.isArray();
			String className = reType.getName();
			if ("STRUCT".equals(type)) {
				Class cls = null;
				if (isArray) {
					className = className.substring(2,className.length()-1);
					Object array = null;
					try {
						array = method.invoke(this);
					} catch (IllegalArgumentException e1) {
						throw new RuntimeException(e1);
					} catch (IllegalAccessException e1) {
						throw new RuntimeException(e1);
					} catch (InvocationTargetException e1) {
						throw new RuntimeException(e1);
					}

					int length = Array.getLength(array);
					
					try {
						cls = Class.forName(className);
					} catch (ClassNotFoundException e) {
						throw new RuntimeException("Can Not Found '"
								+ className + "' type");
					}
					for (int i = 0; i < length; i++) {
						STRUCT instance;
						try {
							Object obj = cls.newInstance();
							instance = (STRUCT) obj;
						} catch (InstantiationException e) {
							throw new RuntimeException(e);
						} catch (IllegalAccessException e) {
							throw new RuntimeException(e);
						}

						instance.decode(source);
						Array.set(array, i, instance);
					}

				} else {
					try {
						cls = Class.forName(className);
					} catch (ClassNotFoundException e) {
						throw new RuntimeException("Can Not Found '" + type
								+ "' type");
					}
					STRUCT instance;
					try {
						instance = (STRUCT) cls.newInstance();
					} catch (InstantiationException e) {
						throw new RuntimeException(e);
					} catch (IllegalAccessException e) {
						throw new RuntimeException(e);
					}
					instance.decode(source);
					try {
						method = this.getClass().getMethod("set" + key);
					} catch (SecurityException e1) {
						throw new RuntimeException(e1);
					} catch (NoSuchMethodException e1) {
						throw new RuntimeException(e1);
					}
					try {
						method.invoke(this, instance);
					} catch (IllegalArgumentException e1) {
						throw new RuntimeException(e1);
					} catch (IllegalAccessException e1) {
						throw new RuntimeException(e1);
					} catch (InvocationTargetException e1) {
						throw new RuntimeException(e1);
					}
				}
			} else {// base type
				Class cls = null;
				try {
					cls = Class.forName("yyang.translate.core.type." + type);
				} catch (ClassNotFoundException e) {
					throw new RuntimeException("Can Not Found '" + type
							+ "' type");
				}
				Decoder instance;
				try {
					instance = (Decoder) cls.newInstance();
				} catch (InstantiationException e) {
					throw new RuntimeException(e);
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				}
				if (isArray) {
					Object array = null;
					try {
						array = method.invoke(this);
					} catch (IllegalArgumentException e1) {
						throw new RuntimeException(e1);
					} catch (IllegalAccessException e1) {
						throw new RuntimeException(e1);
					} catch (InvocationTargetException e1) {
						throw new RuntimeException(e1);
					}

					int length = Array.getLength(array);
					for (int i = 0; i < length; i++) {
						Object val = instance.decode(source);
						Array.set(array, i, val);
					}
				} else {
					Object val = instance.decode(source);
					try {
						method = this.getClass().getMethod("set" + key, reType);
					} catch (SecurityException e1) {
						throw new RuntimeException(e1);
					} catch (NoSuchMethodException e1) {
						throw new RuntimeException(e1);
					}
					try {
						method.invoke(this, val);
					} catch (IllegalArgumentException e1) {
						throw new RuntimeException(e1);
					} catch (IllegalAccessException e1) {
						throw new RuntimeException(e1);
					} catch (InvocationTargetException e1) {
						throw new RuntimeException(e1);
					}
				}
			}
		}
	}

}
