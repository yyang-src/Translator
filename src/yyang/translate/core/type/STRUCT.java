package yyang.translate.core.type;

import java.util.ArrayList;
import java.util.List;

public class STRUCT extends DataType {
	private List<DataType> childs;

	public List<DataType> getChilds() {
		return childs;
	}

	public void addChild(DataType child) {
		if (childs == null)
			childs = new ArrayList<DataType>();
		childs.add(child);
	}

	public void setChild(DataType child, int index) {
		if (childs == null)
			childs = new ArrayList<DataType>();
		while (childs.size() <= index)
			childs.add(null);
		childs.set(index, child);
	}
	
}
