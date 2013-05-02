import java.util.List;
import java.util.ArrayList;
import yyang.translate.core.type.*;

public class TestCommand extends STRUCT {
private short Header;
public void setHeader(short args){ this.Header=args; }
public short getHeader(){ return this.Header; }
private int Size;
public void setSize(int args){ this.Size=args; }
public int getSize(){ return this.Size; }
private final String Name[]=new String[8];
public void setName(String args[]){ System.arraycopy(args,0,this.Name,0,this.Name.length);}
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
private static List<String> __MEMBERS__= null;
private static List<String> __MEMBER_TYPES__= null;
protected List<String> __MEMBERS__(){
if(__MEMBERS__==null){
__MEMBERS__= new ArrayList<String>();
__MEMBER_TYPES__= new ArrayList<String>();
__MEMBERS__.add("Aa");
__MEMBER_TYPES__.add("FLOAT");
}
return __MEMBERS__;}
protected List<String> __MEMBER_TYPES__(){
__MEMBERS__();
return __MEMBER_TYPES__;
}
}
private final Evn evn[]=new Evn[12];
public void setEvn(Evn args[]){ System.arraycopy(args,0,this.evn,0,this.evn.length);}
public Evn[] getEvn(){ return this.evn; }
private static List<String> __MEMBERS__= null;
private static List<String> __MEMBER_TYPES__= null;
protected List<String> __MEMBERS__(){
if(__MEMBERS__==null){
__MEMBERS__= new ArrayList<String>();
__MEMBER_TYPES__= new ArrayList<String>();
__MEMBERS__.add("Count");
__MEMBER_TYPES__.add("INT32");
__MEMBERS__.add("Posision");
__MEMBER_TYPES__.add("INT32");
__MEMBERS__.add("Evn");
__MEMBER_TYPES__.add("STRUCT");
}
return __MEMBERS__;}
protected List<String> __MEMBER_TYPES__(){
__MEMBERS__();
return __MEMBER_TYPES__;
}
}
private final Properties Properties[]=new Properties[12];
public void setProperties(Properties args[]){ System.arraycopy(args,0,this.Properties,0,this.Properties.length);}
public Properties[] getProperties(){ return this.Properties; }
public static class Evn extends STRUCT {
private float aa;
public void setAa(float args){ this.aa=args; }
public float getAa(){ return this.aa; }
private static List<String> __MEMBERS__= null;
private static List<String> __MEMBER_TYPES__= null;
protected List<String> __MEMBERS__(){
if(__MEMBERS__==null){
__MEMBERS__= new ArrayList<String>();
__MEMBER_TYPES__= new ArrayList<String>();
__MEMBERS__.add("Aa");
__MEMBER_TYPES__.add("FLOAT");
}
return __MEMBERS__;}
protected List<String> __MEMBER_TYPES__(){
__MEMBERS__();
return __MEMBER_TYPES__;
}
}
private final Evn evn[]=new Evn[12];
public void setEvn(Evn args[]){ System.arraycopy(args,0,this.evn,0,this.evn.length);}
public Evn[] getEvn(){ return this.evn; }
private static List<String> __MEMBERS__= null;
private static List<String> __MEMBER_TYPES__= null;
protected List<String> __MEMBERS__(){
if(__MEMBERS__==null){
__MEMBERS__= new ArrayList<String>();
__MEMBER_TYPES__= new ArrayList<String>();
__MEMBERS__.add("Header");
__MEMBER_TYPES__.add("INT16");
__MEMBERS__.add("Size");
__MEMBER_TYPES__.add("INT32");
__MEMBERS__.add("Name");
__MEMBER_TYPES__.add("STRING");
__MEMBERS__.add("Password");
__MEMBER_TYPES__.add("STRING");
__MEMBERS__.add("Properties");
__MEMBER_TYPES__.add("STRUCT");
__MEMBERS__.add("Evn");
__MEMBER_TYPES__.add("STRUCT");
}
return __MEMBERS__;}
protected List<String> __MEMBER_TYPES__(){
__MEMBERS__();
return __MEMBER_TYPES__;
}
}


