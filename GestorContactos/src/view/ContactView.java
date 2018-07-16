/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author mizar
 */
public class ContactView extends JPanel{
    
    private String type = "";
    private AddressView addressPanel;
    private BirthdayView birthdayPanel;
    private EmailView emailPanel;
    private FormattedNameView formattedNamePanel;
    private NameView namePanel;
    private PhotoView photoPanel;
    private TelephoneView telephonePanel;
    private JLabel headerLabel = new JLabel("Contacto");
    private JButton bottomButton = new JButton("Guardar cambios");
    
    public ContactView(String type){
        
        this.type = type;
        addressPanel  = new AddressView(this.type);
        birthdayPanel = new BirthdayView();
        emailPanel = new EmailView(this.type);
        formattedNamePanel = new FormattedNameView(this.type);
        namePanel = new NameView(this.type);
        photoPanel = new PhotoView();
        telephonePanel = new TelephoneView(this.type);
        
        if ( this.type.equalsIgnoreCase("static") ){
            addressPanel.addAddress();
            emailPanel.addEmail();
            telephonePanel.addTelephone();
        }
        
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
        
        if ( this.type.equalsIgnoreCase("interactive") ){
            JPanel bottomPanel = new JPanel(new FlowLayout());
            bottomPanel.add(bottomButton);
            this.add(bottomPanel);
        }
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));       
    }

}
