import java.util.HashMap;
import yyang.translate.core.type.*;

class TestCommand extends STRUCT {
private short Header;
public void setHeader(short args){ this.Header=args; }
public short getHeader(){ return this.Header; }
private int Size;
public void setSize(int args){ this.Size=args; }
public int getSize(){ return this.Size; }
private String Name[]=new String[8];
public void setName(String args[]){ this.Name=args; }
public String[] getName(){ return this.Name; }
private String Password;
public void setPassword(String args){ this.Password=args; }
public String getPassword(){ return this.Password; }
public static class Properties extends STRUCT {
private int Count;
public void setCount(int args){ this.Count=args; }
public int getCount(){ return this.Count; }
private int Posision;
public void setPosision(int args){ this.Posision=args; }
public int getPosision(){ return this.Posision; }
public static class Evn extends STRUCT {
private float aa;
public void setAa(float args){ this.aa=args; }
public float getAa(){ return this.aa; }
private static HashMap<String,String> __MEMBERS__= null;
protected HashMap<String, String> __MEMBERS__(){
if(__MEMBERS__==null){
__MEMBERS__= new HashMap<String,String>();
__MEMBERS__.put("Aa","FLOAT");
}
return __MEMBERS__;}
}
private Evn evn[]=new Evn[12];
public void setEvn(Evn args[]){ this.evn=args; }
public Evn[] getEvn(){ return this.evn; }
private static HashMap<String,String> __MEMBERS__= null;
protected HashMap<String, String> __MEMBERS__(){
if(__MEMBERS__==null){
__MEMBERS__= new HashMap<String,String>();
__MEMBERS__.put("Count","INT32");
__MEMBERS__.put("Evn","STRUCT");
__MEMBERS__.put("Posision","INT32");
}
return __MEMBERS__;}
}
private Properties Properties[]=new Properties[12];
public void setProperties(Properties args[]){ this.Properties=args; }
public Properties[] getProperties(){ return this.Properties; }
public static class Evn extends STRUCT {
private float aa;
public void setAa(float args){ this.aa=args; }
public float getAa(){ return this.aa; }
private static HashMap<String,String> __MEMBERS__= null;
protected HashMap<String, String> __MEMBERS__(){
if(__MEMBERS__==null){
__MEMBERS__= new HashMap<String,String>();
__MEMBERS__.put("Aa","FLOAT");
}
return __MEMBERS__;}
}
private Evn evn[]=new Evn[12];
public void setEvn(Evn args[]){ this.evn=args; }
public Evn[] getEvn(){ return this.evn; }
private static HashMap<String,String> __MEMBERS__= null;
protected HashMap<String, String> __MEMBERS__(){
if(__MEMBERS__==null){
__MEMBERS__= new HashMap<String,String>();
__MEMBERS__.put("Name","STRING");
__MEMBERS__.put("Evn","STRUCT");
__MEMBERS__.put("Header","INT16");
__MEMBERS__.put("Password","STRING");
__MEMBERS__.put("Properties","STRUCT");
__MEMBERS__.put("Size","INT32");
}
return __MEMBERS__;}
}


