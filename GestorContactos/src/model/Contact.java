package model;

import java.util.ArrayList;

public class Contact{
    private Name name = new Name();
    private String formattedName = "";
    private String birthday = "";
    private Photo photo = new Photo();
    private ArrayList<Telephone> telephones = new ArrayList<Telephone>();
    private ArrayList<Email> emails = new ArrayList<Email>();
    private ArrayList<Address> addresses = new ArrayList<Address>();
    
    public Name getName() { return name; }
    
    public ArrayList<Telephone> getTelephones() { return telephones; }
    
    public ArrayList<Email> getEmails() { return emails; }
    
    public ArrayList<Address> getAddresses() { return addresses; }
    
    public Photo getPhoto() { return photo; }
    
    public String getFormattedName() { return formattedName; }
    
    public String getBirthday() { return birthday; }
    
    public void addTelephone(String value, ArrayList<String> types){
    	 Telephone tel = new Telephone();
	 for (String type: types){
	 	tel.addType(type);
	 }
	 tel.setValue(value);
	 telephones.add(tel);
    }
    
    public void addEmail(String value, ArrayList<String> types){
    	 Email em = new Email();
	 for (String type: types){
	 	em.addType(type);
	 }
	 em.setValue(value);
	 emails.add(em);
    }
    
    public void addAddress(String type, String poa, String ext, String street, String loc, String reg, String cp, String country){
    	Address address = new Address();
	address.setType(type);
	address.setPostOfficeAddress(poa);
	address.setExtendedAddress(ext);
	address.setStreet(street);
	address.setLocality(loc);
	address.setRegion(reg);
	address.setPostalCode(cp);
	address.setCountry(country);
	addresses.add(address);
    }
    
    public void setFormattedName( String fname ) { formattedName = fname; }
    
    public void setBirthday( String bday ) { birthday = bday; }
    
    public String toString(){
        String contactInfo  = "";
	contactInfo += "FN:\n" + formattedName + "\n";
	contactInfo += "N:\n" + name.toString() + "\n";
	contactInfo += "BDAY:\n";
	if (!birthday.equalsIgnoreCase("")) contactInfo += birthday + "\n";
	contactInfo += "TEL:\n";
	for (Telephone tel : telephones){
                contactInfo +=  tel.toString() + "\n";
        }
	contactInfo += "EMAIL:\n";
        for (Email em : emails){
                contactInfo += em.toString() + "\n";
        }
	contactInfo += "ADR:\n";
        for (Address adr : addresses){
                contactInfo += adr.toString() + "\n";
        }
	contactInfo += "Photo:\n" + photo.toString();
        return contactInfo;
    }
    
}
