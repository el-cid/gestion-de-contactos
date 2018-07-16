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
    private AddressView addressPanel  = new AddressView();
    private BirthdayView birthdayPanel = new BirthdayView();
    private EmailView emailPanel = new EmailView();
    private FormattedNameView formattedNamePanel = new FormattedNameView();
    private NameView namePanel = new NameView();
    private PhotoView photoPanel = new PhotoView();
    private TelephoneView telephonePanel = new TelephoneView();
    private JLabel headerLabel = new JLabel("Contacto");
    private JButton bottomButton = new JButton("Guardar cambios");
    public ContactView(){
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
        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.add(bottomButton);
        this.add(bottomPanel);
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));       
    }

}
