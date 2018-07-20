/*  
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author mizar
 */
public class MenuGestionContactos extends JPanel {      
    private JPanel bottomPanel = new JPanel(new GridLayout(1,4));
    private JPanel panelBusqueda = new JPanel();
    private JPanel photoPanel = new JPanel();
    private JPanel bodyPanel = new JPanel( new BorderLayout() );
    private JButton buttonAnterior = new JButton("Anterior");
    private JButton buttonSiguiente = new JButton("Siguiente");
    private JButton buttonModificar = new JButton("Modificar contacto");
    private JButton buttonAdd = new JButton("Añadir contacto");
    private JButton buttonEliminar = new JButton("Eliminar contacto");
    private JButton returnButton = new JButton("Regresar");
    private Block nameBlock = new Block();
    private Block lastNameBlock = new Block();
    private ContactView contactBackup;
    private ArrayList<ContactView> contacts = new ArrayList<ContactView>();
    final static String MAINPANEL = "Pantalla raíz, con barra filtradora y botones.";
    final static String EDITPANEL = "Pantalla secundaria, para modificar un contacto.";
    private int currentContact = 0;
    // Constructor to setup the GUI components and event handlers
    public MenuGestionContactos() {
       
        setLayout( new BorderLayout() );
        
        buttonModificar.addActionListener( new ModifyL() );
        
        panelBusqueda.setLayout(new GridLayout(2,1));
        nameBlock.getTitleLabel().setText("Nombre:");
        panelBusqueda.add(nameBlock);
        lastNameBlock.getTitleLabel().setText("Apellido:");
        panelBusqueda.add(lastNameBlock);
        add(panelBusqueda, BorderLayout.NORTH);
        
        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new FlowLayout());
        panelButtons.add(buttonAnterior);
        panelButtons.add(buttonSiguiente);
        buttonSiguiente.addActionListener(new RightL());
        buttonAnterior.addActionListener(new LeftL());
        buttonSiguiente.setEnabled(false);//-
        buttonAnterior.setEnabled(false);//-
        bodyPanel.add(panelButtons, BorderLayout.NORTH);
        
        photoPanel.setLayout(new BoxLayout(photoPanel, BoxLayout.Y_AXIS));
        photoPanel.add(new JPanel());
        PhotoView noContacts = new PhotoView();
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
        if ( contacts.size() == 1 ){
            BorderLayout layout = (BorderLayout) bodyPanel.getLayout();
            bodyPanel.remove(layout.getLayoutComponent(BorderLayout.CENTER));
            bodyPanel.add(contacts.get( 0 ), BorderLayout.CENTER);
            revalidate();
            repaint();
            buttonAnterior.setEnabled(true);
            buttonSiguiente.setEnabled(true);
            buttonModificar.setEnabled(true);
            buttonEliminar.setEnabled(true);
        }
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
                currentContact++;
                currentContact %= contacts.size();
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
                currentContact--;
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
            //contactBackup = new ContactView(contacts.get(currentContact));
            contacts.get(currentContact).getReturnButton().addActionListener(new ReturnL());
            contacts.get(currentContact).makeStatic(false);
            bottomPanel.setVisible(false);
            panelBusqueda.setVisible(false);
        }
    }

    private class ReturnL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            
            contacts.get(currentContact).makeStatic(true);
            bottomPanel.setVisible(true);
            panelBusqueda.setVisible(true);
            revalidate();
            repaint();
        }
    }
}