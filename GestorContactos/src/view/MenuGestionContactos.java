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
/**
 *
 * @author mizar
 */
public class MenuGestionContactos extends JPanel {      
    private JPanel panelButtons = new JPanel();
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
    private PhotoView noContacts = new PhotoView();
    private ArrayList<ContactView> contacts = new ArrayList<ContactView>();
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
        panelBusqueda.add(nameBlock);
        lastNameBlock.getTitleLabel().setText("Apellido:");
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
        BorderLayout layout = (BorderLayout) bodyPanel.getLayout();
        bodyPanel.remove(layout.getLayoutComponent(BorderLayout.CENTER));
        bodyPanel.add(contacts.get( contacts.size() - 1 ), BorderLayout.CENTER);
        buttonAnterior.setEnabled(true);
        buttonSiguiente.setEnabled(true);
        buttonModificar.setEnabled(true);
        buttonEliminar.setEnabled(true);
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
            size = contacts.size();
            if ( size == 0 ){    
                bodyPanel.add( noContacts , BorderLayout.CENTER);
                currentContact = 0;//
            }
            else{
                currentContact--;
                currentContact = (currentContact % contacts.size() + contacts.size()) % contacts.size();            
                bodyPanel.add(contacts.get( currentContact ), BorderLayout.CENTER);
            }
            revalidate();
            repaint();
        }
    }
    
    
}