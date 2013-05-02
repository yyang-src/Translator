package yyang.translate.core;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;

import yyang.translate.core.type.DataType;
import yyang.translate.core.type.MESSAGE;
import yyang.translate.core.type.STRUCT;

public class FileParser {
	private Stack<DataType> stack = new Stack<DataType>();
	private Stack<STRUCT> structs = new Stack<STRUCT>();
	private List<MESSAGE> allData = new ArrayList<MESSAGE>();
	private Pattern namePattern = Pattern
			.compile("[a-zA-Z_][a-zA-Z0-9_\\x7f-\\xff]*");
	int status = 0;
	public List<MESSAGE> parseToTreeNode(String fileBody)
			throws SyntaxException, IOException {
		fileBody = fileBody.replaceAll("\r", "");
		
		int column = 0;
		int line = 0;
		StringReader reader = new StringReader(fileBody);
		boolean read = false;
		int length = fileBody.length();
		int count = 0;
		StringBuilder symbol = new StringBuilder();

		while ((count++) < length) {
			char ch = (char) reader.read();
			column++;
			switch (ch) {
			case '\n':
				line++;
				column = 0;
			case ' ':
			case '\t':
				if (!read)
					break;
				String token = symbol.toString();
				switch (status) {
				case 1:// type
					if (!checkTypeToken(token, line, column)) {
						status = 0;
						read = false;
						break;
					}
					DataType type = null;
					try {
						Class cls = Class.forName("yyang.translate.core.type."
								+ token);
						type = (DataType) cls.newInstance();
						setDataType(type);
					} catch (ClassNotFoundException e) {
						throw new SyntaxException("Unknow token '" + token
								+ "'", line, column);
					} catch (InstantiationException e) {
						throw new SyntaxException("Unknow token '" + token
								+ "'", line, column);
					} catch (IllegalAccessException e) {
						throw new SyntaxException("Unknow token '" + token
								+ "'", line, column);
					}
					break;
				case 2:// name
					setName(token, line, column);
					status = 0;
					break;
				default:
					throw new SyntaxException("Unknow status", line, column);
				}

				symbol.setLength(0);
				read = false;
				break;
			case ';':
				if(symbol.length()>0)
					setName(symbol.toString(),line,column);
				if (!read) {
					throw new SyntaxException("Syntax error", line, column);
				} else {
					setEnd(line, column);
				}
				status = 0;
				symbol.setLength(0);
				read = false;
				break;
			default:
				if (!read) {
					read = true;
					status++;
				}
				symbol.append(ch);
			}
		}

		return allData;

	}

	private boolean checkTypeToken(String token, int line, int column) {
		
		if ("MESSAGE".equals(token)) {
			if (stack.isEmpty()) {
				return true;
			} else {
				throw new SyntaxException(
						"'MESSAGE' can not support nesting itself", line,
						column);
			}
		} else {// not message
			if (stack.isEmpty()) {// message started
				throw new SyntaxException("First token must is 'MESSAGE'",
						line, column);
			} else {
				if ("END".equals(token)) {
					setEnd(line, column);
					return false;
				}
				return true;
			}
		}
	}

	private void setDataType(DataType type) {
		STRUCT parent = null;
		if (!structs.empty()) {
			parent = structs.peek();
			parent.__CHILDS__(type);
		}
		
		stack.push(type);
		if (type instanceof STRUCT) {
			structs.push((STRUCT) type);
		}
	}

	private void setEnd(int line, int column) {
		DataType obj = stack.peek();
		if (obj.__NAME__() == null) {
			throw new SyntaxException("Error or 'END'", line, column);
		}
		if (obj instanceof STRUCT) {
			STRUCT type = (STRUCT) obj;
			if (type.__CHILDS__() == null || type.__CHILDS__().isEmpty()) {
				throw new SyntaxException("Empty Struct", line, column);
			}
			structs.pop();
		}
		boolean islast = stack.size() == 1;
		DataType last = stack.pop();
		if (last instanceof MESSAGE) {
			allData.add((MESSAGE) last);
		} else {
			if(islast){
				throw new SyntaxException("Error MESSAGE '" + last.__NAME__() + "'",line, column);
			}
		}
	}

	private void setName(String token, int line, int column)
			throws SyntaxException {// keyword check.
		if ("END".equals(token)) {
			throw new SyntaxException("Error for 'END' keyword", line, column);
		}
		String name = token;
		if (token.endsWith("]")) {
			int s = token.indexOf("[");
			String count = token.substring(s + 1, token.length() - 1);
			if (count.isEmpty()) {
				throw new SyntaxException("Empty Array for '" + token + "'",
						line, column);
			} else {
				
				if (count.matches("\\d*")) {
					stack.peek().__ARRAYCOUNT__(Integer.parseInt(count));
					name = token.substring(0,s);
				} else {
					throw new SyntaxException("Syntax error on token '" + token
							+ "'", line, column);
				}
			}
		}

		if (!namePattern.matcher(name).matches()) {// named rule check
			throw new SyntaxException("Syntax error on token '" + token + "'",
					line, column);
		}
		stack.peek().__NAME__(name);
	}

}
