package yyang.translate.core.type;

 

public abstract class DataType {
	private String name;
	private int arrayCount = 0;
	public int getArrayCount() {
		return arrayCount;
	}

	public void setArrayCount(int arrayCount) {
		this.arrayCount = arrayCount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
