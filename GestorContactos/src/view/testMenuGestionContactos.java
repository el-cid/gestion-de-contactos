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
import javax.swing.JTextArea;

/**
 *
 * @author mizar
 */
public class testMenuGestionContactos extends JPanel {      
   private JButton buttonAnterior = new JButton("Anterior");
   private JButton buttonSiguiente = new JButton("Siguiente");
   private JButton buttonModificar = new JButton("Modificar contacto");
   private JButton buttonAdd = new JButton("Añadir contacto");
   private JButton buttonEliminar = new JButton("Eliminar contacto");
   private InteractiveBlock nameBlock = new InteractiveBlock();
   private InteractiveBlock lastNameBlock = new InteractiveBlock();
   private ContactView contact = new ContactView();
   // Constructor to setup the GUI components and event handlers
   public testMenuGestionContactos() {
        
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
        
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new BoxLayout(middlePanel,BoxLayout.X_AXIS));

        JPanel panelButtonAnterior = new JPanel();
        panelButtonAnterior.setLayout(new BoxLayout(panelButtonAnterior, BoxLayout.Y_AXIS));
        panelButtonAnterior.add(buttonAnterior);
        middlePanel.add(panelButtonAnterior);
        
        middlePanel.add(contact);
        
        JPanel panelButtonSiguiente = new JPanel();
        panelButtonSiguiente.setLayout(new BoxLayout(panelButtonSiguiente, BoxLayout.Y_AXIS));
        panelButtonSiguiente.add(buttonSiguiente);
        middlePanel.add(panelButtonSiguiente);
                
        add(middlePanel);
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel,BoxLayout.X_AXIS));
        
        JPanel panelButtonModificar = new JPanel(new FlowLayout());
        panelButtonModificar.add(buttonModificar);
        bottomPanel.add(panelButtonModificar);
        
        JPanel panelButtonAdd = new JPanel(new FlowLayout());
        panelButtonAdd.add(buttonAdd);
        bottomPanel.add(panelButtonAdd);
        
        JPanel panelButtonEliminar = new JPanel(new FlowLayout());
        panelButtonEliminar.add(buttonEliminar);
        bottomPanel.add(panelButtonEliminar);
        
        add(bottomPanel);
        
    }
}