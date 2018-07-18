package control;

import java.io.InputStream;
import java.util.ArrayList;
import model.Contact;
import parser.SyntaxChecker;

public class Controller {
    public static void main(String[] args) {
        try {
            InputStream is = Controller.class.getClassLoader().getResourceAsStream("resources/test5.vcf");
            if ( is != null ){
                SyntaxChecker scheck = new SyntaxChecker( is );
                ArrayList<Contact> contactList = new ArrayList<Contact>();
                scheck.S( contactList );
                for ( Contact c : contactList ){
                    System.out.println( c );
                }
            }
            else{
                System.out.println("Error durante la apertura del archivo.");
            }            
        } catch (Throwable e) {
            System.out.println("Syntax check failed: " + e.getMessage());
        }
    }    
}
