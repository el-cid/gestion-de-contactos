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
import view.ViewController;

public class Controller {
    private ViewController mainView;
    private ArrayList<Contact> contactList = new ArrayList<Contact>();
    
    public void parseFile(String filePath){
        try {
            File fileToParse = new File( filePath );
            InputStream is = new FileInputStream( fileToParse );
            if ( is != null ){
                SyntaxChecker scheck = new SyntaxChecker( is );
                contactList = new ArrayList<Contact>();
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
    
    public void setMainView(ViewController mainView){
        this.mainView = mainView;
    }
    
    public void contactsToContactViews(){
        for ( Contact c : contactList ){
           
            ContactView contactoVisual = new ContactView();
    
            contactoVisual.setFormattedName( c.getFormattedName() );

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
    
    public void contactViewsToContacts( ArrayList<ContactView> contactViews ){
        contactList = new ArrayList<Contact>();
        for ( int i = 0; i < contactViews.size(); i++ ){
            contactList.add( new Contact()  );
            ContactView contactView = contactViews.get(i);
            ArrayList<Block[]> addresses = contactView.getAddressView().getAddressesBlocks();
            for ( Block[] address : addresses ){
                contactList.get(i).addAddress( address[0].getContent(),
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
            contactList.get(i).setBirthday( year + "-" + String.format("%02d", month) + "-" + String.format("%02d", day) );
            ArrayList<Block[]> emails = contactView.getEmailView().getEmailsBlocks();
            for ( Block[] email : emails ){
                String[] types = email[0].getContent().split("\\s+");
                contactList.get(i).addEmail( email[1].getContent() , new ArrayList<>(Arrays.asList( types )));
            }
            contactList.get(i).setFormattedName( contactView.getFormattedNameView().getFormattedName() );
            Block[] name = contactView.getNameView().getBlocks();
            contactList.get(i).getName().setGivenName( name[0].getContent() );
            contactList.get(i).getName().setFamilyName( name[1].getContent() );
            contactList.get(i).getName().setAdditionalName( name[2].getContent() );
            contactList.get(i).getName().setHonorificPreffix( name[3].getContent() );
            contactList.get(i).getName().setHonorificSuffix( name[4].getContent() );
            ArrayList<Block[]> telephones = contactView.getTelephoneView().getTelephonesBlocks();
            for ( Block[] telephone : telephones ){
                String[] types = telephone[0].getContent().split("\\s+");
                contactList.get(i).addTelephone( telephone[1].getContent() , new ArrayList<>(Arrays.asList( types )));
            }
        }    
    }
    
    public void saveChanges( ArrayList<ContactView> contactViews ){
        //Aquí se podría reiniciar la base de datos
        
        //Aquí se podría reiniciar la base de datos
        contactViewsToContacts( contactViews );
        for ( Contact contact : contactList ){
            //Escribir contact a BD
        }
    }
    
    /*
    public void loadUserContacts( String userID ){
        contactList = new ArrayList<Contact>();
        // Extraer los Contactos del usuario y añadirlos a la lista contactList
        // o
        // Extraer los Contactos del usuario como un ArrayList y asignarlo a contactList
    }
    */
    
    /*
    public boolean verifyAccount( String userID, String password ){
        boolean userExist = false;
        //Revisar en la BD si existe ese usuario y si la contraseña es correcta
        //Registrar el resulta de la busqueda en la BD en userExist
        //Si el usuarioExiste, asignar true a userExist
        if ( userExist ){
            return true;
        }
        else{
            return false;
        }
    }
    */
    
}
