/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author mizar
 */
public class User {
    private String userID = "";
    private ArrayList<Contact> contacts = new ArrayList<Contact>();
    
    public ArrayList<Contact> getContacts(){
        return this.contacts;
    }
    
    public void addContact( Contact c ){
        contacts.add(c);
    }
    
    public void setContacts( ArrayList<Contact> contacts ){
        this.contacts = contacts;
    }
    
    public String getUserID(){
        return this.userID;
    }
    
    public void setUserID( String id ){
        this.userID = id;
    }
}
