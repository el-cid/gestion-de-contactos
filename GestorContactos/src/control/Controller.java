package control;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import model.Address;
import model.Contact;
import model.Email;
import model.Telephone;
import parser.SyntaxChecker;
import view.ContactView;
import view.Main;

public class Controller {
    private Main mainView;
   
    public void parseFile(String filePath){
        try {
            File fileToParse = new File( filePath );
            InputStream is = new FileInputStream( fileToParse );
            if ( is != null ){
                SyntaxChecker scheck = new SyntaxChecker( is );
                ArrayList<Contact> contactList = new ArrayList<Contact>();
                scheck.S( contactList );
                
                for ( Contact c : contactList ){
                    ContactView contactoVisual = new ContactView();

                    contactoVisual.setFormattedName(c.getFormattedName());

                    String[] name = new String[5];
                    name[0] = c.getName().getFamilyName();//
                    name[1] = c.getName().getGivenName();//
                    name[2] = c.getName().getAdditionalName();
                    name[3] = c.getName().getHonorificPreffix();
                    name[4] = c.getName().getHonorificSuffix();
                    contactoVisual.setName( name );

                    for ( Address address : c.getAddresses() ){
                        String[] newAddress = new String[8];
                        newAddress[0] = address.getType();
                        newAddress[1] = address.getPostOfficeAddress();
                        newAddress[2] = address.getExtendedAddress();
                        newAddress[3] = address.getStreet();
                        newAddress[4] = address.getLocality();
                        newAddress[5] = address.getRegion();
                        newAddress[6] = address.getPostalCode();
                        newAddress[7] = address.getCountry();
                        contactoVisual.addAddress( newAddress );
                    }

                    for ( Email email : c.getEmails() ){
                        String[] newEmail = new String[2];
                        newEmail[0] = email.getTypes();
                        newEmail[1] = email.getValue();
                        contactoVisual.addEmail( newEmail );
                    }

                    for ( Telephone tel : c.getTelephones() ){
                        String[] newTel = new String[2];
                        newTel[0] = tel.getTypes();
                        newTel[1] = tel.getValue();
                        contactoVisual.addTelephone( newTel );
                    }
                    String birthday = c.getBirthday();
                    if ( !birthday.equalsIgnoreCase("") ){
                        String[] parts = birthday.split("-",-1);
                        int year = Integer.parseInt( parts[0] );
                        int month = Integer.parseInt( parts[1] );
                        int day = Integer.parseInt( parts[2] );
                        contactoVisual.getBirthdayView().getDateModel().setDate(year, month, day);
                        contactoVisual.getBirthdayView().getDateModel().setSelected(true);
                    }
                    
                    mainView.addContact(contactoVisual);
                }
            }
            else{
                System.out.println("Error durante la apertura del archivo.");
            }            
        } catch (Throwable e) {
            System.out.println("Syntax check failed: " + e.getMessage());
        }
    }
    
    public void setMainView(Main mainView){
        this.mainView = mainView;
    }
    
    public static void main(String[] args) {
           Controller control = new Controller();
           Main main = new Main();
           main.setController(control);
           control.setMainView(main);
    } 
}
