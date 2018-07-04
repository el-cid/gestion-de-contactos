package model;

public class Name{
    private String givenName = "";
    private String familyName = "";
    private String additionalName = "";
    private String honorificPreffix = "";
    private String honorificSuffix = "";

    public void setGivenName( String gn ) { givenName = gn; }
    public void setFamilyName( String fn ) { familyName = fn; }
    public void setAdditionalName( String an ) { additionalName = an; }
    public void setHonorificPreffix( String hp ) { honorificPreffix = hp; }
    public void setHonorificSuffix( String hs ) { honorificSuffix = hs; }
    
    public String getGivenName() { return givenName; }
    public String getFamilyName() { return familyName; }
    public String getAdditionalName() { return additionalName; }
    public String getHonorificPreffix() { return honorificPreffix; }
    public String getHonorificSuffix() { return honorificSuffix; }
    
    public String toString(){
    	String content = "";
        content += "Given Name: " + givenName + ", ";
        content += "Family Name: " + familyName + ", ";
        content += "Additional Name: " + additionalName + ", ";
        content += "Honorific Preffix: " + honorificPreffix + ", ";
        content += "Honorific Suffix: " + honorificSuffix;
	return content;
    }
}
