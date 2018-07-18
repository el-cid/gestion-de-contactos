/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author mizar
 */
public class ContactView extends JPanel{
    
    private AddressView addressPanel = new AddressView();
    private BirthdayView birthdayPanel = new BirthdayView();
    private EmailView emailPanel = new EmailView();
    private FormattedNameView formattedNamePanel = new FormattedNameView();
    private NameView namePanel = new NameView();
    private PhotoView photoPanel = new PhotoView();
    private TelephoneView telephonePanel = new TelephoneView();
    private JLabel headerLabel = new JLabel("Contacto");
    private JButton returnButton = new JButton("Regresar");
    private JPanel bottomPanel = new JPanel(new GridLayout(1,2));
    public ContactView(){
        configureLayout();
    }
    
    public void configureLayout(){
        JPanel headerPanel = new JPanel(new FlowLayout());
        headerPanel.add(headerLabel);
        this.add(headerPanel);
        this.add(photoPanel);
        this.add(formattedNamePanel);
        this.add(namePanel);
        this.add(birthdayPanel);
        this.add(telephonePanel);
        this.add(addressPanel);
        this.add(emailPanel);
                
        
        JPanel secondButtonPanel = new JPanel(new FlowLayout());
        secondButtonPanel.add(returnButton);
        
        bottomPanel.add(secondButtonPanel);
        bottomPanel.setVisible(false);
        this.add(bottomPanel);
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));       
    }
    
    public void makeStatic(boolean b){
        addressPanel.makeStatic(b);
        //birthdayPanel.makeStatic(b);
        emailPanel.makeStatic(b);
        formattedNamePanel.makeStatic(b);
        namePanel.makeStatic(b);
        //photoPanel.makeStatic(b);
        telephonePanel.makeStatic(b);
        bottomPanel.setVisible(!b);
    }
    
    public JButton getReturnButton(){
        return this.returnButton;
    }

    public void addAddress( String[] values ){
        addressPanel.addAddress( values );
    }
    
    public void addEmail( String[] values ){
        emailPanel.addEmail( values );
    }
    
    public void setFormattedName( String value ){
        formattedNamePanel.setFormattedName(value);
    }
    
    public void setName( String[] values ){
        namePanel.setName(values);
    }
    
    public void addTelephone( String[] values ){
        telephonePanel.addTelephone(values);
    }
    
    public AddressView getAddressView(){ return this.addressPanel; }
    public BirthdayView getBirthdayView(){ return this.birthdayPanel; }
    public EmailView getEmailView(){ return this.emailPanel; }
    public FormattedNameView getFormattedNameView(){ return this.formattedNamePanel; }
    public NameView getNameView(){ return this.namePanel; }
    public PhotoView getPhotoView(){ return this.photoPanel; }
    public TelephoneView getTelephoneView(){ return this.telephonePanel; }
    
    public void setAddressView( AddressView av ){ this.addressPanel = av; }
    public void setBirthdayView( BirthdayView bv ){ this.birthdayPanel = bv; }
    public void setEmailView( EmailView ev ){ this.emailPanel = ev; }
    public void setFormattedNameView( FormattedNameView fn ){ this.formattedNamePanel = fn; }
    public void setNameView( NameView nv ){ this.namePanel = nv; }
    public void setPhotoView( PhotoView pv ){ this.photoPanel = pv; }
    public void setTelephoneView( TelephoneView tv ){ this.telephonePanel = tv; }

}
