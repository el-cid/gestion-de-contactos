/*  
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
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
   private JLabel label1 = new JLabel("   Nombre: ");
   private JLabel label2 = new JLabel(" Apellido: ");
   private JLabel label3 = new JLabel("  Contacto ");
   private JLabel labelN = new JLabel(" Nombre: ");
   private JLabel labelNombre = new JLabel("   nombre ");
   private JLabel labelA = new JLabel("Apellido:");
   private JLabel labelApellido = new JLabel("  apellido ");
   private JLabel labelFecha = new JLabel("Fecha de nacimiento: ");
   private JTextArea textAreaNombre = new JTextArea(1, 30);
   private JTextArea textAreaApellido = new JTextArea(1, 30);
   private PhotoView contactPhoto = new PhotoView();
   // Constructor to setup the GUI components and event handlers
   public MenuGestionContactos() {
        
        int i = 6;
        int j = 3;
        JPanel[][] panelHolder = new JPanel[i][j];    
        setLayout(new GridLayout(i,j));

        for(int m = 0; m < i; m++) {
           for(int n = 0; n < j; n++) {
              panelHolder[m][n] = new JPanel();
              add(panelHolder[m][n]);
           }
        }
                        
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
        
        panelHolder[0][1].add(panelBusqueda);
        
        JPanel panelButtonAnterior = new JPanel(new FlowLayout());
        panelButtonAnterior.add(buttonAnterior);
        panelHolder[3][0].add(panelButtonAnterior);
        
        JPanel panelY = new JPanel(new FlowLayout());
        panelY.add(label3);
        panelHolder[1][1].add(panelY);
        
        panelHolder[2][1].add(contactPhoto);
        
        JPanel panel3A1 = new JPanel(new FlowLayout());
        panel3A1.add(labelN);
        panel3A1.add(labelNombre);
        panelHolder[3][1].add(panel3A1);
        JPanel panel3B1 = new JPanel(new FlowLayout());
        panel3B1.add(labelA);
        panel3B1.add(labelApellido);
        panelHolder[4][1].add(panel3B1);
        

        JPanel panel3C2 = new JPanel(new FlowLayout());
        panel3C2.add(buttonSiguiente);
        panelHolder[3][2].add(panel3C2);
                
        JPanel panel4A = new JPanel(new FlowLayout());
        panel4A.add(buttonModificar);
        panelHolder[5][0].add(panel4A);
        JPanel panel4B = new JPanel(new FlowLayout());
        panel4B.add(buttonAdd);
        panelHolder[5][1].add(panel4B);
        JPanel panel4C = new JPanel(new FlowLayout());
        panel4C.add(buttonEliminar);
        panelHolder[5][2].add(panel4C);
        
        
    }
}