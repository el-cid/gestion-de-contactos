/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Address;
import model.Contact;
import model.Email;
import model.Telephone;

/**
 *
 * @author mizar
 */
public class FileExporter {
    PrintWriter writer;
    
    public FileExporter( String filePath ){
        try {
            writer = new PrintWriter( filePath , "UTF-8" );
        } catch (Exception ex) {
            Logger.getLogger(FileExporter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public void writeQuotedPrintable( String stringToEncode ){
        
        byte[] stringBytes = stringToEncode.getBytes();
        StringBuilder builder = new StringBuilder();
        for ( byte bb : stringBytes ) {
            builder.append("=");
            builder.append(String.format("%02X", bb));
        }
        String encodedString = builder.toString();   
        int stringLength = encodedString.length();
        if ( stringLength < 76 ){
            writer.print( encodedString );
        }
        else{
            String lineToWrite;
            int lowEnd = 0;
            int highEnd = 75;
            do{
                lineToWrite = encodedString.substring( lowEnd , highEnd ) + "=";
                writer.print( lineToWrite + "\r\n");
                stringLength -= 75;
                lowEnd = highEnd;
                if ( stringLength >= 76 ){
                    highEnd += 75;
                }    
            } while ( stringLength >= 76 );
            stringLength = encodedString.length();
            lineToWrite = encodedString.substring( highEnd , stringLength);
            writer.print( lineToWrite );
        }
        //writer.close();
    }
        
    public void writeContact( Contact contact ){
        writer.print("BEGIN:VCARD\r\n");
        writer.print("VERSION:2.1\r\n");
        writer.print("N;CHARSET=UTF-8;ENCODING=QUOTED-PRINTABLE:");
        writeQuotedPrintable( contact.getName().getGivenName());
        writer.print(";");
        writeQuotedPrintable( contact.getName().getFamilyName());
        writer.print(";");
        writeQuotedPrintable( contact.getName().getAdditionalName());
        writer.print(";");
        writeQuotedPrintable( contact.getName().getHonorificPreffix());
        writer.print(";");
        writeQuotedPrintable( contact.getName().getHonorificSuffix());
        writer.print("\r\n");
        writer.print("FN;CHARSET=UTF-8;ENCODING=QUOTED-PRINTABLE:");
        writeQuotedPrintable( contact.getFormattedName() );
        writer.print("\r\n");
        for ( Telephone tel : contact.getTelephones() ){
            writer.print( "TEL" );
            String[] types = tel.getTypes().split("\\s+");
            for ( int i = 0; i < types.length; i++ ){
                writer.print(";" + "TYPE=" + types[i]);
            }
            writer.print(":");
            writer.print( tel.getValue() );
            writer.print( "\r\n" );
        }
        for ( Email email : contact.getEmails() ){
            writer.print( "EMAIL" );
            String[] types = email.getTypes().split("\\s+");
            for ( int i = 0; i < types.length; i++ ){
                writer.print(";" + "TYPE=" + types[i]);
            }
            writer.print(":");
            writer.print( email.getValue() );
            writer.print( "\r\n" );
        }
        for ( Address address : contact.getAddresses() ){
            writer.print("ADR");
            String[] types = address.getType().split("\\s+");
            for ( int i = 0; i < types.length; i++ ){
                writer.print(";" + "TYPE=" + types[i]);
            }
            writer.print(";");
            writer.print("CHARSET=UTF-8;ENCODING=QUOTED-PRINTABLE:");
            writeQuotedPrintable( address.getPostOfficeAddress() );
            writer.print(";");
            //writeQuotedPrintable( address.getExtendedAddress() );
            writer.print(";");
            writeQuotedPrintable( address.getStreet() );
            writer.print(";");
            writeQuotedPrintable( address.getLocality() );
            writer.print(";");
            writeQuotedPrintable( address.getRegion() );
            writer.print(";");
            writeQuotedPrintable( address.getPostalCode() );
            writer.print(";");
            writeQuotedPrintable( address.getCountry() );
            writer.print("\r\n");
        }
        writer.print("END:VCARD\r\n");
    }
    
    public void writeContacts( ArrayList<Contact> contacts ){
        for ( Contact contact : contacts ){
            writeContact( contact );
        }
        writer.close();
    }
}
