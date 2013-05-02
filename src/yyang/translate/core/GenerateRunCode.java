package yyang.translate.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import yyang.translate.core.type.DataType;
import yyang.translate.core.type.MESSAGE;
import yyang.translate.core.type.STRUCT;

public class GenerateRunCode {

	public String GenerateToJava(List<MESSAGE> list,String targetDir) throws FileNotFoundException {
		StringBuilder body = new StringBuilder();
		for (MESSAGE m : list) {
			body.append("import java.util.List;\nimport java.util.ArrayList;\nimport yyang.translate.core.type.*;\n\n");
			traversalChilds(m, body);
			body.append("\n");
			PrintStream fileout=new PrintStream(new File(targetDir+"/"+m.get_targetType()+".java"));
			fileout.println(body);
			fileout.flush();
			fileout.close();
			body.setLength(0);
		}

		return body.toString();
	}

	private void traversalChilds(STRUCT parent, StringBuilder body) {

		String name = parent.__NAME__();
		char first = name.charAt(0);
		String classname = Character.toUpperCase(first) + name.substring(1);
		body.append("public ");
		if(!(parent instanceof MESSAGE)){
			body.append("static ");
		}
		body.append("class " + classname + " extends STRUCT {\n");
		List<DataType> list = parent.__CHILDS__();
		List<String> members = new ArrayList<String>();
		List<String> member_types = new ArrayList<String>();
		
		for (DataType d : list) {
			if (d instanceof STRUCT) {
				traversalChilds((STRUCT) d, body);
			}
			
			String simpleName = d.__NAME__();
			char first_c = simpleName.charAt(0);
			String methodname = Character.toUpperCase(first_c) + simpleName.substring(1);
			members.add(methodname);
			member_types.add(d.getClass().getSimpleName());
			genrateSetterGetter(body,d);
		}
		body.append("private static List<String> __MEMBERS__= null;\n");
		body.append("private static List<String> __MEMBER_TYPES__= null;\n");
		body.append("protected List<String> __MEMBERS__(){\n");
		body.append("if(__MEMBERS__==null){\n__MEMBERS__= new ArrayList<String>();\n");
		body.append("__MEMBER_TYPES__= new ArrayList<String>();\n");
		int length = members.size();
		for(int i=0;i<length;i++){			
			body.append("__MEMBERS__.add(\""+members.get(i)+"\");\n");
			body.append("__MEMBER_TYPES__.add(\""+member_types.get(i)+"\");\n");
		}
		body.append("}\nreturn __MEMBERS__;");
		body.append("}\n");
		body.append("protected List<String> __MEMBER_TYPES__(){\n");
		body.append("__MEMBERS__();\nreturn __MEMBER_TYPES__;\n");
		body.append("}\n");
		body.append("}\n");
	}
	private void genrateSetterGetter(StringBuilder body,DataType type){

		String name = type.__NAME__();
		int arraycount = type.__ARRAYCOUNT__();		
		char first = name.charAt(0);
		String methodname = Character.toUpperCase(first) + name.substring(1);
		String classname = type.get_targetType();
		body.append("private " );
		if (arraycount>0){
			body.append("final ");
		}
		body.append(classname);
		body.append(" ");
		body.append(name);
		if (arraycount>0){
			body.append("[]=new "+classname+"["+arraycount+"];\n");
		}else{
			body.append(";\n");
		}
		body.append("public void set" + methodname + "(" + classname + " args"+(arraycount>0?"[]":"")+"){ ");
		if (arraycount>0){
			body.append("System.arraycopy(args,0,this." + name+",0,this."+name+".length);");
		}else{
			body.append("this." + name + "=args; ");
		}
		body.append("}\n");
		body.append("public " + classname+ (arraycount>0?"[]":"") + " get" + methodname + "(){ ");
		body.append("return this." + name+";");
		body.append(" }\n");
	}
 

}
