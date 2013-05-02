package yyang.translate.core.type;


public abstract class DataType  {
	private String __NAME__;
	private int __ARRAYCOUNT__ = 0;
	public int __ARRAYCOUNT__() {
		return __ARRAYCOUNT__;
	}

	public void __ARRAYCOUNT__(int arrayCount) {
		this.__ARRAYCOUNT__ = arrayCount;
	}

	public String __NAME__() {
		return __NAME__;
	}

	public void __NAME__(String name) {
		this.__NAME__ = name;
	}
	
	public abstract String get_targetType();
}
