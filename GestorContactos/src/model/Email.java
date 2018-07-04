package model;

import java.util.ArrayList;

public class Email{
    private ArrayList<String> types = new ArrayList<String>();
    private String value = "";
    public void addType(String t){
	types.add(t);    
    }
    public void setValue(String v){
	value = v;    
    }
    public String getValue(){
    	return value;
    }
    public String getTypes(){
    	String content = "";
	for ( String type : types ){
	    content += type + " ";
	}
	return content;
    }
    public String toString(){
    	String email = "Type: " + getTypes() + ", " + "Value: " + getValue();
    	return email;
    }
}
