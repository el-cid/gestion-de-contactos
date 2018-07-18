package model;

public class Photo{
    private String encodedContent = "";
    private String picExtension = "";
    
    public void setEncodedContent( String enc ) { encodedContent = enc; } 
    public void setPicExtension( String ext ) { picExtension = ext; }
    
    public String getEncodedContent() { return encodedContent;  } 
    public String getPicExtension() { return picExtension;  }
    
    public String toString(){
    	String content = "";
	content += "Pic extension: " + picExtension + "\n";
	content += "Encoded content:\n" + encodedContent;
	return content;
    } 
}
