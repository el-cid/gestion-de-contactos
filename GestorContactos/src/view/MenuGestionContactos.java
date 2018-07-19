/*  
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
/**
 *
 * @author mizar
 */
public class MenuGestionContactos extends JPanel {      
    private JPanel panelButtons = new JPanel();
    private JPanel bottomPanel = new JPanel(new GridLayout(1,4));
    private JPanel panelBusqueda = new JPanel();
    private JPanel photoPanel = new JPanel();
    private JPanel resultsPanel = new JPanel();
    private JPanel bodyPanel = new JPanel( new BorderLayout() );
    private JButton buttonAnterior = new JButton("Anterior");
    private JButton buttonSiguiente = new JButton("Siguiente");
    private JButton buttonModificar = new JButton("Modificar contacto");
    private JButton buttonAdd = new JButton("Añadir contacto");
    private JButton buttonEliminar = new JButton("Eliminar contacto");
    private JButton returnButton = new JButton("Regresar");
    private Block nameBlock = new Block();
    private Block lastNameBlock = new Block();
    private ArrayList<ContactView> contacts = new ArrayList<ContactView>();
    private ArrayList<ContactView> unfilteredContacts = new ArrayList<ContactView>();
    private PhotoView noContacts = new PhotoView();
    private PhotoView noResults = new PhotoView();
    final static String MAINPANEL = "Pantalla raíz, con barra filtradora y botones.";
    final static String EDITPANEL = "Pantalla secundaria, para modificar un contacto.";
    private int currentContact = 0;
    // Constructor to setup the GUI components and event handlers
    public MenuGestionContactos() {
       
        setLayout( new BorderLayout() );
        
        buttonModificar.addActionListener( new ModifyL() );
        buttonAdd.addActionListener( new AddL() );
        
        panelBusqueda.setLayout(new GridLayout(2,1));
        nameBlock.getTitleLabel().setText("Nombre:");
        nameBlock.getTextArea().getDocument().addDocumentListener( new NameBlockL () );
        nameBlock.getTextArea().setEnabled(false);
        panelBusqueda.add(nameBlock);
        lastNameBlock.getTitleLabel().setText("Apellido:");
        lastNameBlock.getTextArea().getDocument().addDocumentListener( new NameBlockL () );
        lastNameBlock.getTextArea().setEnabled(false);
        panelBusqueda.add(lastNameBlock);
        add(panelBusqueda, BorderLayout.NORTH);
        
        panelButtons.setLayout(new FlowLayout());
        panelButtons.add(buttonAnterior);
        panelButtons.add(buttonSiguiente);
        buttonSiguiente.addActionListener(new RightL());
        buttonAnterior.addActionListener(new LeftL());
        buttonEliminar.addActionListener(new DeleteL());
        buttonSiguiente.setEnabled(false);//-
        buttonAnterior.setEnabled(false);//-
        bodyPanel.add(panelButtons, BorderLayout.NORTH);
        
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        resultsPanel.add(new JPanel());
        noResults.configure("no-results2.png");
        resultsPanel.add(noResults);
        
        photoPanel.setLayout(new BoxLayout(photoPanel, BoxLayout.Y_AXIS));
        photoPanel.add(new JPanel());
        noContacts.configure("empty.jpg");
        photoPanel.add(noContacts);
        bodyPanel.add(photoPanel, BorderLayout.CENTER);
        add(bodyPanel, BorderLayout.CENTER );
                
        JPanel panelButtonAdd = new JPanel(new FlowLayout());
        panelButtonAdd.add(buttonAdd);
        bottomPanel.add(panelButtonAdd);
        
        JPanel panelButtonMod = new JPanel(new FlowLayout());
        panelButtonMod.add(buttonModificar);
        buttonModificar.setEnabled(false);
        bottomPanel.add(panelButtonMod);
        
        JPanel panelButtonEliminar = new JPanel(new FlowLayout());
        panelButtonEliminar.add(buttonEliminar);
        buttonEliminar.setEnabled(false);
        bottomPanel.add(panelButtonEliminar);
        
        JPanel panelButtonRegresar = new JPanel(new FlowLayout());
        panelButtonRegresar.add(returnButton);
        bottomPanel.add(panelButtonRegresar);
        
        add(bottomPanel, BorderLayout.SOUTH);
        
    }
   
    public void addContactView(ContactView contactView){
        contactView.makeStatic(true);
        this.contacts.add(contactView);
        this.unfilteredContacts.add(contactView);
        BorderLayout layout = (BorderLayout) bodyPanel.getLayout();
        bodyPanel.remove(layout.getLayoutComponent(BorderLayout.CENTER));
        //bodyPanel.add(contacts.get( contacts.size() - 1 ), BorderLayout.CENTER);
        bodyPanel.add(contacts.get( 0 ), BorderLayout.CENTER);
        //currentContact = 0;//*
        buttonAnterior.setEnabled(true);
        buttonSiguiente.setEnabled(true);
        buttonModificar.setEnabled(true);
        buttonEliminar.setEnabled(true);
        nameBlock.getTextArea().setEnabled(true);
        lastNameBlock.getTextArea().setEnabled(true);
        revalidate();
        repaint();
    } 
    
    public ContactView getCurrentContact(){
        return contacts.get( currentContact );
    }
    
    public JButton getReturnButton(){
       return this.returnButton;
   }
   
    public JButton getSelectionButton(){
       return this.buttonModificar;
   }
     
    private class RightL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if ( contacts.size() > 0 ){
                ++currentContact;
                currentContact = (currentContact % contacts.size() + contacts.size()) % contacts.size();            
                BorderLayout layout = (BorderLayout) bodyPanel.getLayout();
                bodyPanel.remove(layout.getLayoutComponent(BorderLayout.CENTER));
                bodyPanel.add(contacts.get( currentContact ), BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        }
    }
    
    private class LeftL implements ActionListener {   
        public void actionPerformed(ActionEvent e) {
            if ( contacts.size() > 0 ){
                --currentContact;
                currentContact = (currentContact % contacts.size() + contacts.size()) % contacts.size(); 
                BorderLayout layout = (BorderLayout) bodyPanel.getLayout();
                bodyPanel.remove(layout.getLayoutComponent(BorderLayout.CENTER));
                bodyPanel.add(contacts.get( currentContact ), BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        }
    }
    
    private class ModifyL implements ActionListener {
        public void actionPerformed(ActionEvent e) {                        
            contacts.get(currentContact).getReturnButton().addActionListener(new ReturnL());
            contacts.get(currentContact).makeStatic(false);
            bottomPanel.setVisible(false);
            panelBusqueda.setVisible(false);
            panelButtons.setVisible(false);
        }
    }

    private class ReturnL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            
            contacts.get(currentContact).makeStatic(true);
            bottomPanel.setVisible(true);
            panelBusqueda.setVisible(true);
            panelButtons.setVisible(true);
           
            revalidate();
            repaint();
        }
    }
    
    private class AddL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
                       
            panelBusqueda.setVisible(false);
            panelButtons.setVisible(false);
            bottomPanel.setVisible(false);
            
            int size = contacts.size();
            
            addContactView(new ContactView() );
            contacts.get(size).makeStatic( false );
            contacts.get(size).getReturnButton().addActionListener( new ReturnL() );
            currentContact = size;
            BorderLayout layout = (BorderLayout) bodyPanel.getLayout();//*
            bodyPanel.remove(layout.getLayoutComponent(BorderLayout.CENTER));//*
            bodyPanel.add(contacts.get(size), BorderLayout.CENTER);
                    
            revalidate();
            repaint();
        }
    }
    
    private class DeleteL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            
            int size = contacts.size();
            BorderLayout layout = (BorderLayout) bodyPanel.getLayout();
            bodyPanel.remove(layout.getLayoutComponent(BorderLayout.CENTER));
            contacts.remove( currentContact );
            unfilteredContacts.remove( currentContact );
            size = contacts.size();
            if ( size == 0 ){    
                bodyPanel.add( photoPanel , BorderLayout.CENTER);
                currentContact = 0;//
                buttonAnterior.setEnabled(false);
                buttonSiguiente.setEnabled(false);
                buttonModificar.setEnabled(false);
                buttonEliminar.setEnabled(false);
                nameBlock.getTextArea().setEnabled(false);
                lastNameBlock.getTextArea().setEnabled(false);
                nameBlock.getTextArea().setText("");
                lastNameBlock.getTextArea().setText("");
            }
            else{
                --currentContact;
                currentContact = (currentContact % contacts.size() + contacts.size()) % contacts.size();            
                bodyPanel.add(contacts.get( currentContact ), BorderLayout.CENTER);
            }
            revalidate();
            repaint();
        }
    }
    
    private class NameBlockL implements DocumentListener{
        public void changedUpdate(DocumentEvent e) {
            filter();
        }
        public void removeUpdate(DocumentEvent e) {
            filter();
        }
        public void insertUpdate(DocumentEvent e) {
            filter();
        }

        public void filter() {
            ArrayList<ContactView> filteredContacts = new ArrayList<ContactView>();
            String namePreffix = nameBlock.getTextArea().getText();
            String familyNamePreffix = lastNameBlock.getTextArea().getText();
            if ( ( namePreffix.matches(".*\\w.*") ) & ( familyNamePreffix.matches(".*\\w.*") ) )
            {
                namePreffix = namePreffix.toLowerCase();
                familyNamePreffix = familyNamePreffix.toLowerCase();
                for ( ContactView contact : unfilteredContacts ){
                    String firstName = contact.getNameView().getFirstName().toLowerCase();
                    String secondName = contact.getNameView().getSecondName().toLowerCase();
                    String familyName = contact.getNameView().getFamilyName().toLowerCase();
                    if ( ( firstName.startsWith( namePreffix ) ) || ( secondName.startsWith( namePreffix ) ) ){
                        if ( familyName.startsWith( familyNamePreffix ) ){
                            filteredContacts.add(contact);
                        }
                    }
                }
                if ( filteredContacts.size() > 0 ){
                    BorderLayout layout = (BorderLayout) bodyPanel.getLayout();
                    bodyPanel.remove(layout.getLayoutComponent(BorderLayout.CENTER));
                    bodyPanel.add( filteredContacts.get( 0 ) , BorderLayout.CENTER);
                    contacts = filteredContacts;
                }
                else{
                    BorderLayout layout = (BorderLayout) bodyPanel.getLayout();
                    bodyPanel.remove(layout.getLayoutComponent(BorderLayout.CENTER));
                    bodyPanel.add( resultsPanel, BorderLayout.CENTER);
                    contacts = unfilteredContacts;
                }
            }
            else if ( namePreffix.matches(".*\\w.*") ){
                namePreffix = namePreffix.toLowerCase();
                for ( ContactView contact : unfilteredContacts ){
                    String firstName = contact.getNameView().getFirstName().toLowerCase();
                    String secondName = contact.getNameView().getSecondName().toLowerCase();
                    if ( ( firstName.startsWith( namePreffix ) ) || ( secondName.startsWith( namePreffix ) ) ){
                            filteredContacts.add(contact);
                    }
                }
                if ( filteredContacts.size() > 0 ){
                    BorderLayout layout = (BorderLayout) bodyPanel.getLayout();
                    bodyPanel.remove(layout.getLayoutComponent(BorderLayout.CENTER));
                    bodyPanel.add( filteredContacts.get( 0 ) , BorderLayout.CENTER);
                    contacts = filteredContacts;
                }
                else{
                    BorderLayout layout = (BorderLayout) bodyPanel.getLayout();
                    bodyPanel.remove(layout.getLayoutComponent(BorderLayout.CENTER));
                    bodyPanel.add( resultsPanel, BorderLayout.CENTER);
                    contacts = unfilteredContacts;
                }
            }
            else if ( familyNamePreffix.matches(".*\\w.*") ){
                familyNamePreffix = familyNamePreffix.toLowerCase();
                for ( ContactView contact : unfilteredContacts ){
                    String familyName = contact.getNameView().getFamilyName().toLowerCase();
                    if ( familyName.startsWith( familyNamePreffix ) ){
                        filteredContacts.add( contact );
                    }
                }
                if ( filteredContacts.size() > 0 ){
                    BorderLayout layout = (BorderLayout) bodyPanel.getLayout();
                    bodyPanel.remove(layout.getLayoutComponent(BorderLayout.CENTER));
                    bodyPanel.add( filteredContacts.get( 0 ) , BorderLayout.CENTER);
                    contacts = filteredContacts;
                }
                else{
                    BorderLayout layout = (BorderLayout) bodyPanel.getLayout();
                    bodyPanel.remove(layout.getLayoutComponent(BorderLayout.CENTER));
                    bodyPanel.add( resultsPanel, BorderLayout.CENTER);
                    contacts = unfilteredContacts;
                }
            }
            else{
                if ( nameBlock.getTextArea().isEnabled() & lastNameBlock.getTextArea().isEnabled() ){
                    BorderLayout layout = (BorderLayout) bodyPanel.getLayout();
                    bodyPanel.remove(layout.getLayoutComponent(BorderLayout.CENTER));
                    bodyPanel.add(contacts.get( 0 ), BorderLayout.CENTER);
                    contacts = unfilteredContacts;
                }
            }
            revalidate();
            repaint();
        }
    }
}