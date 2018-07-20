package control;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import model.Address;
import model.Contact;
import model.Email;
import model.Telephone;
import parser.SyntaxChecker;
import view.Block;
import view.ContactView;
import view.Main;

public class Controller {
    private Main mainView;
    ArrayList<Contact> contactList = new ArrayList<Contact>();
    
    public void parseFile(String filePath){
        try {
            File fileToParse = new File( filePath );
            InputStream is = new FileInputStream( fileToParse );
            if ( is != null ){
                SyntaxChecker scheck = new SyntaxChecker( is );
                contactList = new ArrayList<Contact>();
                scheck.S( contactList );
                //La siguiente linea debera modificarse porque el contacto podría tener 
                //contactos registrados en la BD
                int id = 0;
                for ( Contact c : contactList ){
                   
                    ContactView contactoVisual = new ContactView();
                    //
                    c.setContactID( id + "" );
                    contactoVisual.setContactViewID( id + "" );
                    contactoVisual.setState("old");//debería ser new
                    // 
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
                    id++;
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
    
    public Contact contactViewToContact(ContactView contactView ){
        Contact modifiedContact = new Contact();
        ArrayList<Block[]> addresses = contactView.getAddressView().getAddressesBlocks();
        for ( Block[] address : addresses ){
            modifiedContact.addAddress( address[0].getContent(),
                                        address[1].getContent(), 
                                        address[2].getContent(), 
                                        address[3].getContent(), 
                                        address[4].getContent(), 
                                        address[5].getContent(), 
                                        address[6].getContent(), 
                                        address[7].getContent()
                                      );
        }
        int day = contactView.getBirthdayView().getDateModel().getDay();
        int month = contactView.getBirthdayView().getDateModel().getMonth();
        int year = contactView.getBirthdayView().getDateModel().getYear();
        modifiedContact.setBirthday( year + "-" + String.format("%02d", month) + "-" + String.format("%02d", day) );
        ArrayList<Block[]> emails = contactView.getEmailView().getEmailsBlocks();
        for ( Block[] email : emails ){
            String[] types = email[0].getContent().split("\\s+");
            modifiedContact.addEmail( email[1].getContent() , new ArrayList<>(Arrays.asList( types )));
        }
        modifiedContact.setFormattedName( contactView.getFormattedNameView().getFormattedName() );
        Block[] name = contactView.getNameView().getBlocks();
        modifiedContact.getName().setGivenName( name[0].getContent() );
        modifiedContact.getName().setFamilyName( name[1].getContent() );
        modifiedContact.getName().setAdditionalName( name[2].getContent() );
        modifiedContact.getName().setHonorificPreffix( name[3].getContent() );
        modifiedContact.getName().setHonorificSuffix( name[4].getContent() );
        ArrayList<Block[]> telephones = contactView.getTelephoneView().getTelephonesBlocks();
        for ( Block[] telephone : telephones ){
            String[] types = telephone[0].getContent().split("\\s+");
            modifiedContact.addTelephone( telephone[1].getContent() , new ArrayList<>(Arrays.asList( types )));
        }
        //System.out.println( modifiedContact.toString() );
        return modifiedContact;
    }
    
    public void saveChanges( ArrayList<ContactView> contactViews ){
        for ( ContactView contactView : contactViews ){
            String contactViewID = contactView.getContactViewID();
            String state = contactView.getState();
            System.out.println( "ID: " + contactViewID + " State: " + state );
            if ( state.equalsIgnoreCase("new") ){
                Contact newContact = contactViewToContact( contactView );//crear nueva tupla en la BD (escribir)
            }
            else{
                for ( Contact contact : contactList ){
                    String contactID = contact.getContactID();
                    if ( contactViewID.equalsIgnoreCase( contactID ) ){
                        if ( state.equalsIgnoreCase("modified") ){
                            Contact newContact = contactViewToContact( contactView );
                            //UPDATE contactos WHERE ID = contactID, sobreescribir con newContact
                        }
                        else if ( state.equalsIgnoreCase("Deleted") ){
                            //eliminar contacto de la BD WHERE ID = contactID
                        }
                    }
                }
            }
        }
    }
    
    public static void main(String[] args) {
           Controller control = new Controller();
           Main main = new Main();
           main.setController(control);
           control.setMainView(main);
    } 
}
