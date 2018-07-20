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
   private JButton buttonAnterior = new JButton("Anterior");
   private JButton buttonSiguiente = new JButton("Siguiente");
   private JButton buttonModificar = new JButton("Modificar contacto");
   private JButton buttonAdd = new JButton("Añadir contacto");
   private JButton buttonEliminar = new JButton("Eliminar contacto");
   private JButton returnButton = new JButton("Regresar");
   private Block nameBlock = new Block();
   private Block lastNameBlock = new Block();
   private JPanel contact = new JPanel(new CardLayout());
   private ArrayList<String> contactViewIds = new ArrayList<String>();
   private ArrayList<ContactView> contacts = new ArrayList<ContactView>();
   private int currentContact = 0;
   // Constructor to setup the GUI components and event handlers
   public MenuGestionContactos() {
        
        //_setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setLayout(new BorderLayout());
                        
        JPanel panelBusqueda = new JPanel();
        panelBusqueda.setLayout(new GridLayout(2,1));
        nameBlock.getTitleLabel().setText("Nombre:");
        nameBlock.getTextArea().setText("nombre");
        panelBusqueda.add(nameBlock);
        lastNameBlock.getTitleLabel().setText("Apellido:");
        lastNameBlock.getTextArea().setText("apellido");
        panelBusqueda.add(lastNameBlock);
        //-add(panelBusqueda);
        add(panelBusqueda, BorderLayout.NORTH);
        
        JPanel bodyPanel = new JPanel( new BorderLayout() );//-
        
        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new FlowLayout());
        panelButtons.add(buttonAnterior);
        panelButtons.add(buttonSiguiente);
        //-add(panelButtons);
        bodyPanel.add(panelButtons, BorderLayout.NORTH);//
        
        //contact.makeStatic( true );
        //-add(contact);
        bodyPanel.add(contact, BorderLayout.CENTER);
        add(bodyPanel, BorderLayout.CENTER );//-
        buttonSiguiente.addActionListener(new RightL());
        buttonAnterior.addActionListener(new LeftL());
        
        JPanel bottomPanel = new JPanel(new GridLayout(1,4));
        
        JPanel panelButtonAdd = new JPanel(new FlowLayout());
        panelButtonAdd.add(buttonAdd);
        bottomPanel.add(panelButtonAdd);
        
        JPanel panelButtonMod = new JPanel(new FlowLayout());
        panelButtonMod.add(buttonModificar);
        bottomPanel.add(panelButtonMod);
        
        JPanel panelButtonEliminar = new JPanel(new FlowLayout());
        panelButtonEliminar.add(buttonEliminar);
        bottomPanel.add(panelButtonEliminar);
        
        JPanel panelButtonRegresar = new JPanel(new FlowLayout());
        panelButtonRegresar.add(returnButton);
        bottomPanel.add(panelButtonRegresar);
        
        //-add(bottomPanel);
         add(bottomPanel, BorderLayout.SOUTH);
        //-bodyPanel.add(bottomPanel, BorderLayout.SOUTH);
        
    }
   
   public JButton getReturnButton(){
       return this.returnButton;
   }
   
   public JButton getSelectionButton(){
       return this.buttonModificar;
   }
   
   public void addContactView(ContactView contactView){
       int size = contacts.size();
       contactView.makeStatic(true);
       this.contacts.add(contactView);
       contactViewIds.add( size + "" );
       contact.add( contactView, size + "" );
   } 
  
   private class RightL implements ActionListener {
            
            public RightL(){
                
            }
            
            public void actionPerformed(ActionEvent e) {
                currentContact++;
                currentContact %= contacts.size();
                CardLayout cl = (CardLayout)(contact.getLayout());
                cl.show(contact, currentContact + "");
            }
    }
    
   private class LeftL implements ActionListener {
            
            public LeftL(){
                
            }
            
            public void actionPerformed(ActionEvent e) {
                currentContact--;
                //(x % N + N) %N
                currentContact = (currentContact % contacts.size() + contacts.size()) % contacts.size();
                CardLayout cl = (CardLayout)(contact.getLayout());
                cl.show(contact, currentContact + "");
            }
    }
    
    public ContactView getCurrentContact(){
        return contacts.get( currentContact );
    }
}