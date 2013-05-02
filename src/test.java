import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
 

 

public class test {
	
	public static void main(String args[]) {
		TestCommand command = new TestCommand();
		short head=10;
		command.setHeader(head);
		command.setSize(0xFFFFFFFF);
		String[] name=command.getName();
		for(int i =0;i<name.length;i++){
			name[i]="test_name_"+i;
		}
		
		command.setPassword("password");
		TestCommand.Properties[] Properties=command.getProperties();
		for(int i=0;i<Properties.length;i++){
			Properties[i]=new TestCommand.Properties();
			Properties[i].setCount(50);
			Properties[i].setPosision(60);
			TestCommand.Properties.Evn env[] = Properties[i].getEvn();
			for(int j=0;j<env.length;j++){
				env[j]=new TestCommand.Properties.Evn();
				env[j].setAa(100.0f+j);
			}
		}

		TestCommand.Evn  e[]= command.getEvn();
		for(int i =0;i<e.length;i++){
			e[i] = new TestCommand.Evn();
			e[i].setAa(i+10.0f);			
		}
		
		
		ByteArrayOutputStream out =new ByteArrayOutputStream();
		try {
			command.Encode(out);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		byte[] b= out.toByteArray();
		for(byte bb:b)
		System.out.print(bb+",");
	    System.out.println();
		System.out.println(b.length);
		TestCommand tt =new TestCommand();
		ByteArrayInputStream source=new ByteArrayInputStream(b);
		try {
			tt.decode(source);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		
		System.out.println("header:"+tt.getHeader());
		System.out.println(Integer.toHexString(tt.getSize()));
		name=tt.getName();
		for(int i =0;i<name.length;i++){
			System.out.println(name[i]);
		}
		
		System.out.println(tt.getPassword());
		Properties=tt.getProperties();
		for(int i=0;i<Properties.length;i++){
			System.out.println(Properties[i].getCount());
			System.out.println(Properties[i].getPosision());
			TestCommand.Properties.Evn env[] = Properties[i].getEvn();
			for(int j=0;j<env.length;j++){
				System.out.println(env[j].getAa());
			}
		}

		e = tt.getEvn();
		for(int i =0;i<e.length;i++){
			System.out.println(e[i].getAa());			
		}
		
	}
	
 
}
