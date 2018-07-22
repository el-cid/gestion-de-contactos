/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;


/**
 *
 * @author auzer
 */

import com.mysql.cj.jdbc.MysqlDataSource;
import java.io.IOException;
import java.io.InputStream;
import model.*;
import java.util.ArrayList;
import java.sql.*;
import java.util.Properties;
import java.util.StringTokenizer;

public class databaseQuery {
    
    private static final MysqlDataSource DATASOURCE = new MysqlDataSource();
    private Connection conn;
    
    public databaseQuery() throws SQLException, IOException {
        Properties configuration = new Properties();
        InputStream is = databaseQuery.class.getClassLoader().getResourceAsStream("resources/mysql.properties");
        configuration.load(is);     
        String user = configuration.getProperty("USER");
        String user_password = configuration.getProperty("USER_PASSWORD");
        String db_name = configuration.getProperty("DB_NAME");
        String time_zone = configuration.getProperty("TIME_ZONE");
        DATASOURCE.setUser(user);
        DATASOURCE.setPassword(user_password);
        DATASOURCE.setDatabaseName(db_name);
        DATASOURCE.setServerTimezone(time_zone);
        this.conn = DATASOURCE.getConnection();
    }        
    
    public void insertRegisterContact(Contact newContact, int usuarioPK) throws SQLException{
        this.conn = DATASOURCE.getConnection();
        String query;
        ResultSet rs;
        Statement stmt = conn.createStatement();
        int namePK;
        int contactPK;
        
        query = "INSERT INTO Name (name_givenName, name_familyName, name_additionalName, name_honorificPrefix, name_honorificSuffix)"
                + " VALUES ('"+newContact.getName().getGivenName()+"','"+ newContact.getName().getFamilyName()+"','"+ newContact.getName().getAdditionalName()
                +"','"+newContact.getName().getHonorificPreffix()+"','"+newContact.getName().getHonorificSuffix()+"');";
        //System.out.println(query);
        
        stmt.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
        rs = stmt.getGeneratedKeys();
        
        if(!rs.next())
            System.out.println("YOU FUCKED UP NOW");
        
        namePK = rs.getInt(1);
        
        //System.out.println(namePK);
        
        query = "INSERT INTO Contact (contact_formattedName, contact_birthday, name_ID, usuario_ID)"
                + " VALUES ('"+newContact.getFormattedName()+"','"+ newContact.getBirthday()+"',"+ namePK+",'"+usuarioPK+"');";
        //System.out.println(query);
        stmt.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
        rs = stmt.getGeneratedKeys();
        
        if(!rs.next())
            System.out.println("YOU FUCKED UP NOW");
        
        contactPK = rs.getInt(1);
        
        //System.out.print("CPK:"+contactPK);
        
        
        ArrayList<Email> emails = newContact.getEmails();
        for(int i=0;i<emails.size();++i){
            Email newE = emails.get(i);            
            query = "INSERT INTO Email"
                + " VALUES (null,'"+newE.getValue()+"','"+newE.getTypes()+"',"+contactPK+");";
            stmt.executeUpdate(query);
        }
        
        ArrayList<Telephone> telephones = newContact.getTelephones();
        for(int i=0;i<emails.size();++i){
            Telephone newTel = telephones.get(i);
            query = "INSERT INTO Telephone"
                + " VALUES (null,'"+newTel.getValue()+"','"+newTel.getTypes()+"',"+contactPK+");";
            stmt.executeUpdate(query);
        }
        
        ArrayList<Address> adrs = newContact.getAddresses();
        for(int i=0;i<emails.size();++i){
            Address adr = adrs.get(i);
            query = "INSERT INTO Adress"
                + " VALUES (null,'"+adr.getType()+"','"+adr.getPostOfficeAddress()+"','"+adr.getExtendedAddress()+"','"+adr.getStreet()+"','"
            + adr.getLocality()+"','"+adr.getRegion()+"','"+adr.getPostalCode()+"','"+adr.getCountry()+"',"+contactPK+");";
            stmt.executeUpdate(query);
        }
        
    }
    
    //Regresa todos los contactos de un usuario en forma de lista
    public ArrayList<Contact> getUsersContacts(int usID) throws SQLException{
        this.conn = DATASOURCE.getConnection();
        ArrayList<Contact> contacts = new ArrayList<>();
        
        String query;
        ResultSet rs;
        Statement stmt = conn.createStatement();
        Statement stmt0 = conn.createStatement();
        query="SELECT * FROM Contact WHERE usuario_ID="+usID+";";
        stmt.execute(query);
        rs=stmt.getResultSet();
        
        while(rs.next()){
            
            
            int conID=rs.getInt("contact_ID");
            int nID=rs.getInt("name_ID");
            
            Name name = new Name();
            String formattedName = "";
            String birthday = "";
            
            ArrayList<Telephone> telephones = new ArrayList<Telephone>();
            ArrayList<Email> emails = new ArrayList<Email>();
            ArrayList<Address> addresses = new ArrayList<Address>();
            
            Integer contactID=conID;
            Integer nameID=nID;
            Integer photoID=-1;
    
            ArrayList<Integer> addressesID = new ArrayList<>();
            ArrayList<Integer> emailsID = new ArrayList<>();
            ArrayList<Integer> telsID = new ArrayList<>();
            
            
            //Obtener clase contacto

            query="SELECT * FROM Contact WHERE contact_ID = "+ conID +";";
            stmt0.execute(query);
            ResultSet rs0=stmt0.getResultSet();
            
            if(rs0.isAfterLast()){
                return new ArrayList<>();
            }
            
            while(rs0.next()){
                formattedName = rs0.getString("contact_formattedName");
                birthday = rs0.getString("contact_birthday");
                nID = rs0.getInt("name_ID");
                usID = rs0.getInt("usuario_ID");
            }
            
            //Obtener clase nombre
            
            String givenName = "";
            String familyName = "";
            String additionalName = "";
            String honorificPreffix = "";
            String honorificSuffix = "";
            
            query="SELECT * FROM Name WHERE name_ID = "+ nID +";";
            stmt0.execute(query);
            rs0=stmt0.getResultSet();
            while(rs0.next()){
                givenName = rs0.getString("name_givenName");
                familyName = rs0.getString("name_familyName");
                additionalName = rs0.getString("name_additionalName");
                honorificPreffix = rs0.getString("name_honorificPrefix");
                honorificSuffix = rs0.getString("name_honorificSuffix");
            }
            
            name = new Name(givenName,familyName,additionalName,honorificPreffix,honorificSuffix);
            
            //Obtener telefonos
            
            query="SELECT * FROM Telephone WHERE contact_ID = "+conID+";";
            stmt0.execute(query);
            rs0=stmt0.getResultSet();
            
            while(rs0.next()){
                ArrayList<String> types = new ArrayList<String>();
                String value = "";
                String typesRaw;
                
                typesRaw = rs0.getString("telephone_type");
                StringTokenizer st = new StringTokenizer(typesRaw,",");
                while(st.hasMoreTokens()){
                    types.add(st.nextToken());
                }
                value=rs0.getString("telephone_value");
                Telephone nt = new Telephone(types,value);
                telephones.add(nt);
                telsID.add(rs0.getInt("telephone_ID"));
            }
            
            //Obtener emails
            query="SELECT * FROM Email WHERE contact_ID = "+conID+";";
            stmt0.execute(query);
            rs0=stmt0.getResultSet();
            
            while(rs0.next()){
                ArrayList<String> types = new ArrayList<String>();
                String value = "";
                String typesRaw="";
                
                typesRaw = rs0.getString("email_type");
                StringTokenizer st = new StringTokenizer(typesRaw,",");
                while(st.hasMoreTokens()){
                    types.add(st.nextToken());
                }
                value=rs0.getString("email_value");
                
                emailsID.add(rs0.getInt("email_ID"));
                
                Email ne = new Email(types,value);
                emails.add(ne);
            }
            
            //Obtener direcciones
            
            
    
            query="SELECT * FROM Adress WHERE contact_ID = "+conID+";";
            stmt0.execute(query);
            rs0=stmt0.getResultSet();
            
            while(rs0.next()){
                String type = "";
                String postOfficeAddress = "";
                String extendedAddress = "";
                String street = "";
                String locality = "";
                String region = "";
                String postalCode = "";
                String country = "";
                
                type = rs0.getString("adress_type");
                postOfficeAddress = rs0.getString("adress_postOffice");
                extendedAddress = rs0.getString("address_extendedAdress");
                street = rs0.getString("address_street");
                locality = rs0.getString("address_locality");
                region = rs0.getString("address_region");
                postalCode = rs0.getString("address_postalCode");
                country = rs0.getString("address_country");
                addressesID.add(rs0.getInt("adress_ID"));
                
                Address na = new Address(type,postOfficeAddress,extendedAddress,street,locality,region,postalCode,country);
                addresses.add(na);
            }
            
            //Obtener foto
            
            
            //Agregar al arraylist de contactos
            Contact contact = new Contact(name,formattedName,birthday,telephones,emails,addresses);
            contacts.add(contact);
            
            //ContactRelatedIDs ncri= new ContactRelatedIDs(conID,nID,addressesID,emailsID,telsID);
            
        }
        
        System.out.println("Éxito al importar contactos de usuario");
        
        return contacts;
        
    }
    
    public void insertUser(String user, String pass) throws SQLException{
        this.conn = DATASOURCE.getConnection();
        String query;
        ResultSet rs;
        Statement stmt = conn.createStatement();
        
        query="INSERT INTO Usuario (usuario_name,usuario_password) VALUES ('"+user+"','"+pass+"');";
        stmt.executeUpdate(query);
        
        return;
        
    }
    
    public Integer getUserID(String user, String pass) throws SQLException{
        this.conn = DATASOURCE.getConnection();
        int r=-1;
        
        String query;
        ResultSet rs;
        Statement stmt = conn.createStatement();
        
        query="SELECT * FROM Usuario WHERE usuario_name = '"+user+"';";
        System.out.println(query);
        stmt.executeQuery(query);
        rs=stmt.getResultSet();
        
        while(rs.next()){
            String p;
            Integer id;
            
            p=rs.getString("usuario_password");
            id=rs.getInt("usuario_ID");
            
            if(p.equals(pass)){
                return id;
            }
            
        }
        
        return r;
    }
    
    public boolean userNameExists(String user) throws SQLException{
        this.conn = DATASOURCE.getConnection();
        int r=-1;
        
        String query;
        ResultSet rs;
        Statement stmt = conn.createStatement();
        
        query="SELECT COUNT(*) FROM Usuario WHERE usuario_name = '"+user+"';";
        System.out.println(query);
        stmt.executeQuery(query);
        rs=stmt.getResultSet();
        
        rs.next();
            
        if ( rs.getInt("COUNT(*)") == 1 ){
            return true;
        }
        else{
            return false;
        }    
        
    }
    
    public void deleteAndBuildDatabase() throws SQLException{
        this.conn = DATASOURCE.getConnection();
        int r=-1;
        
        String query;
        ResultSet rs;
        Statement stmt = conn.createStatement();
     
        query = "DELETE FROM Name;";
        stmt.executeUpdate(query);
        
    }
     
}
