package model;

public class Address{
    private String type = "";
    private String postOfficeAddress = "";
    private String extendedAddress = "";
    private String street = "";
    private String locality = "";
    private String region = "";
    private String postalCode = "";
    private String country = "";
    
    public Address(){
        
    }
    
    public Address(String type,String postOfficeAddress,String extendedAddress,
            String street,String locality,String region,
            String postalCode,String country)
    {
        this.type=type;
        this.postOfficeAddress=postOfficeAddress;
        this.extendedAddress=extendedAddress;
        this.street=street;
        this.locality=locality;
        this.region=region;
        this.postalCode=postalCode;
        this.country=country;
    }

    public void setType( String ty ) { type = ty; }
    public void setPostOfficeAddress( String poa ) { postOfficeAddress = poa; }
    public void setExtendedAddress( String ea ) { extendedAddress = ea; }
    public void setStreet( String st ) { street = st; }
    public void setLocality( String loc ) { locality = loc; }
    public void setRegion( String reg ) { region = reg; }
    public void setPostalCode( String pc ) { postalCode = pc; }
    public void setCountry( String co ) { country = co; }
    
    public String getType() { return type; }
    public String getPostOfficeAddress() { return postOfficeAddress; }
    public String getExtendedAddress() { return extendedAddress; }
    public String getStreet() { return street; }
    public String getLocality() { return locality; }
    public String getRegion() { return region; }
    public String getPostalCode() { return postalCode; }
    public String getCountry() { return country; }
    
    public String toString(){
    	String content = "";
	content += "Type: " + type + ", ";
        content += "PostOffice Address: " + postOfficeAddress + ", ";
        content += "Extended Address: " + extendedAddress + ", ";
        content += "Steet: " + street + ", ";
        content += "Locality: " + locality + ", ";
        content += "Region: " + region + ", ";
        content += "Postal Code: " + postalCode + ", ";
        content += "Country: " + country;
	return content;
    }
}
