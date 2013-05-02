package yyang.translate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import yyang.translate.core.FileParser;
import yyang.translate.core.GenerateRunCode;

public class Tanslator {
	public static void main(String args[]) {
		if (args.length<2){
			System.err.println("Please enter Source file name and Destination Directory(JAVA)\n " +
					"For Example:\n" +
					"\tjava yyang.translate.Translator simple.txt directory\n");
			return;
		}
		
		
		File f = new File(args[0]);
		if(!f.exists()){
			System.err.println("Source file '"+args[0]+"' Does not exists!");
			return;
		}
		File targetDir = new File(args[1]);
		if(!targetDir.exists()){
			System.err.println("Destination Directory '"+args[1]+"' Does not exists!");
			return;
		}
		
		StringBuilder filebody = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(f));
			String line = null;
			while ((line = reader.readLine()) != null) {
				filebody.append(line);
				filebody.append("\n");
			}
			List list = new FileParser().parseToTreeNode(filebody.toString());
			new GenerateRunCode().GenerateToJava(list,args[1]);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Successful");
	}
}
