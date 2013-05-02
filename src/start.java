import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import yyang.translate.core.FileParser;
import yyang.translate.core.GenerateRunCode;
import yyang.translate.core.type.DataType;
import yyang.translate.core.type.MESSAGE;
import yyang.translate.core.type.STRUCT;

public class start {

	public static void main(String args[]) {
		File f = new File("src/simple.txt");
		StringBuilder filebody = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(f));
			String line = null;
			while ((line = reader.readLine()) != null) {
				filebody.append(line);
				filebody.append("\n");
			}
			List list = new FileParser().parseToTreeNode(filebody.toString());
//			print(list,0);
			String body = new GenerateRunCode().GenerateToJava(list);
			PrintStream fileout=new PrintStream(new File("src/s.java"));
			fileout.println(body);
			fileout.flush();
			fileout.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void print(List<DataType> list,int n){
		String space="";
		for(int i =0;i<n;i++)space += " ";
		for(DataType d:list){
			System.out.println(space+"Type:"+d.getClass().getSimpleName());
			System.out.println(space+"Name:"+d.__NAME__());
			System.out.println(space+"Count:"+d.__ARRAYCOUNT__());
			if(d instanceof STRUCT){
				print(((STRUCT) d).__CHILDS__(),n+1);
			}
			
		}
	}
}
