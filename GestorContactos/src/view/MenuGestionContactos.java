/*  
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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
   private InteractiveBlock nameBlock = new InteractiveBlock();
   private InteractiveBlock lastNameBlock = new InteractiveBlock();
   private ContactView contact = new ContactView("static");
   // Constructor to setup the GUI components and event handlers
   public MenuGestionContactos() {
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
                        
        JPanel panelBusqueda = new JPanel();
        panelBusqueda.setLayout(new GridLayout(2,1));
        nameBlock.getTitleLabel().setText("Nombre:");
        nameBlock.getTextArea().setText("nombre");
        panelBusqueda.add(nameBlock);
        lastNameBlock.getTitleLabel().setText("Apellido:");
        lastNameBlock.getTextArea().setText("apellido");
        panelBusqueda.add(lastNameBlock);
        add(panelBusqueda);
        
        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new FlowLayout());
        panelButtons.add(buttonAnterior);
        panelButtons.add(buttonSiguiente);
        add(panelButtons);
        
        add(contact);
        
        JPanel bottomPanel = new JPanel(new GridLayout(1,3));
        
        JPanel panelButtonAdd = new JPanel(new FlowLayout());
        panelButtonAdd.add(buttonAdd);
        bottomPanel.add(panelButtonAdd);
        
        JPanel panelButtonMod = new JPanel(new FlowLayout());
        panelButtonMod.add(buttonModificar);
        bottomPanel.add(panelButtonMod);
        
        JPanel panelButtonEliminar = new JPanel(new FlowLayout());
        panelButtonEliminar.add(buttonEliminar);
        bottomPanel.add(panelButtonEliminar);
        
        add(bottomPanel);
        
    }
}