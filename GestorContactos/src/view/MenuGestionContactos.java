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
public class MenuGestionContactos extends JPanel {      
   private JButton buttonAnterior = new JButton("Anterior");
   private JButton buttonSiguiente = new JButton("Siguiente");
   private JButton buttonModificar = new JButton("Modificar contacto");
   private JButton buttonAdd = new JButton("Añadir contacto");
   private JButton buttonEliminar = new JButton("Eliminar contacto");
   private JLabel label1 = new JLabel("   Nombre: ");
   private JLabel label2 = new JLabel(" Apellido: ");
   private JLabel label3 = new JLabel("  Contacto ");
   private JLabel labelN = new JLabel(" Nombre: ");
   private JLabel labelNombre = new JLabel("   nombre ");
   private JLabel labelA = new JLabel("Apellido:");
   private JLabel labelApellido = new JLabel("  apellido ");
   private JTextArea textAreaNombre = new JTextArea(1, 30);
   private JTextArea textAreaApellido = new JTextArea(1, 30);
   
   // Constructor to setup the GUI components and event handlers
   public MenuGestionContactos() {
        
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
                
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        
        JPanel panelBusqueda = new JPanel();
        panelBusqueda.setLayout(new GridLayout(2,1));
        JPanel panelNombre = new JPanel(new FlowLayout());
        panelNombre.add(label1);
        panelNombre.add(textAreaNombre);
        panelBusqueda.add(panelNombre);
        
        JPanel panelApellido = new JPanel(new FlowLayout());
        panelApellido.add(label2);
        panelApellido.add(textAreaApellido);
        panelBusqueda.add(panelApellido);
        
        topPanel.add(panelBusqueda);
        this.add(topPanel);
        
        JPanel middlePanel = new JPanel(new GridLayout(1,3));

        JPanel panelButtonAnterior = new JPanel(new FlowLayout());
        panelButtonAnterior.add(buttonAnterior);
        middlePanel.add(panelButtonAnterior);
        
        JPanel panelContacto = new JPanel();
        panelContacto.setLayout(new BoxLayout(panelContacto, BoxLayout.Y_AXIS));
        JPanel panelY = new JPanel(new FlowLayout());
        panelY.add(label3);
        panelContacto.add(panelY);
        
        JPanel panelX = new JPanel(new FlowLayout());
        panelX.add(new JTextArea(20,20));
        panelContacto.add(panelX);
        
        JPanel panel3A1 = new JPanel(new FlowLayout());
        panel3A1.add(labelN);
        panel3A1.add(labelNombre);
        panelContacto.add(panel3A1);
        JPanel panel3B1 = new JPanel(new FlowLayout());
        panel3B1.add(labelA);
        panel3B1.add(labelApellido);
        panelContacto.add(panel3B1);
        middlePanel.add(panelContacto);

        JPanel panel3C2 = new JPanel(new FlowLayout());
        panel3C2.add(buttonSiguiente);
        middlePanel.add(panel3C2);
        
        this.add(middlePanel);       
        
        JPanel bottomPanel = new JPanel(new GridLayout(1,3));
        JPanel panel4A = new JPanel(new FlowLayout());
        panel4A.add(buttonModificar);
        bottomPanel.add(panel4A);
        JPanel panel4B = new JPanel(new FlowLayout());
        panel4B.add(buttonAdd);
        bottomPanel.add(panel4B);
        JPanel panel4C = new JPanel(new FlowLayout());
        panel4C.add(buttonEliminar);
        bottomPanel.add(panel4C);
        
        this.add(bottomPanel);
    }
}