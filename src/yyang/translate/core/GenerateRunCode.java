package yyang.translate.core;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import yyang.translate.core.type.DataType;
import yyang.translate.core.type.MESSAGE;
import yyang.translate.core.type.STRUCT;

public class GenerateRunCode {

	public String GenerateToJava(List<MESSAGE> list) {
		StringBuilder body = new StringBuilder();
		body.append("import java.util.HashMap;\nimport yyang.translate.core.type.*;\n\n");
		for (MESSAGE m : list) {
			traversalChilds(m, body);
			body.append("\n");
		}
		return body.toString();
	}

	private void traversalChilds(STRUCT parent, StringBuilder body) {

		String name = parent.__NAME__();
		char first = name.charAt(0);
		String classname = Character.toUpperCase(first) + name.substring(1);
		if(!(parent instanceof MESSAGE)){
			body.append("public static ");
		}
		body.append("class " + classname + " extends STRUCT {\n");
		List<DataType> list = parent.__CHILDS__();
		HashMap<String,String> members = new HashMap<String,String>();
 
		for (DataType d : list) {
			if (d instanceof STRUCT) {
				traversalChilds((STRUCT) d, body);
			}
			
			String simpleName = d.__NAME__();
			char first_c = simpleName.charAt(0);
			String methodname = Character.toUpperCase(first_c) + simpleName.substring(1);
			members.put(methodname, d.getClass().getSimpleName());
			genrateSetterGetter(body,d);
		}
		body.append("private static HashMap<String,String> __MEMBERS__= null;\n");
		body.append("protected HashMap<String, String> __MEMBERS__(){\n");
		body.append("if(__MEMBERS__==null){\n__MEMBERS__= new HashMap<String,String>();\n");
		Set<String> keys= members.keySet();
		for(String key:keys){
			body.append("__MEMBERS__.put(\""+key+"\",\""+members.get(key)+"\");\n");
		}	
		
		body.append("}\nreturn __MEMBERS__;");
		body.append("}\n");
		body.append("}\n");
	}
	private void genrateSetterGetter(StringBuilder body,DataType type){

		String name = type.__NAME__();
		int arraycount = type.__ARRAYCOUNT__();		
		char first = name.charAt(0);
		String methodname = Character.toUpperCase(first) + name.substring(1);
		String classname = type.get_targetType();
		body.append("private " + classname + " " + name);
		if (arraycount>0){
			body.append("[]=new "+classname+"["+arraycount+"];\n");
		}else{
			body.append(";\n");
		}
		body.append("public void set" + methodname + "(" + classname + " args"+(arraycount>0?"[]":"")+"){ ");
		body.append("this." + name + "=args; ");
		body.append("}\n");
		body.append("public " + classname+ (arraycount>0?"[]":"") + " get" + methodname + "(){ ");
		body.append("return this." + name+";");
		body.append(" }\n");
	}

}
