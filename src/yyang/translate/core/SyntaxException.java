package yyang.translate.core;

public class SyntaxException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5481836449562271280L;
	private int line;
	private int column;

	public SyntaxException(String msg, int line, int column) {
		super(msg);
		this.line = line;
		this.column = column;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public String toString(){
		return String.format("line:%d,column:%d, %s",line,column,getMessage());
	}
}
