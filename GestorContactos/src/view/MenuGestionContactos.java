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
   private JButton button1 = new JButton("Left");
   private JButton button2 = new JButton("Right");
   private JButton button3 = new JButton("Modificar contacto");
   private JButton button4 = new JButton("Añadir contacto");
   private JButton button5 = new JButton("Eliminar contacto");
   private JLabel label1 = new JLabel("   Nombre: ");
   private JLabel label2 = new JLabel(" Apellido: ");
   private JLabel label3 = new JLabel("  Contacto ");
   private JLabel label3A1 = new JLabel(" Nombre: ");
   private JLabel label3A2 = new JLabel("   nombre ");
   private JLabel label3B1 = new JLabel("Apellido:");
   private JLabel label3B2 = new JLabel("  apellido ");
   private JTextArea textArea1 = new JTextArea(1, 30);
   private JTextArea textArea2 = new JTextArea(1, 30);
   
   // Constructor to setup the GUI components and event handlers
   public MenuGestionContactos() {
        
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
                
        JPanel panelT = new JPanel();
        panelT.setLayout(new FlowLayout());
        
        JPanel panel0 = new JPanel();
        panel0.setLayout(new GridLayout(2,1));
        JPanel panel1 = new JPanel(new FlowLayout());
        panel1.add(label1);
        panel1.add(textArea1);
        panel0.add(panel1);
        
        JPanel panel2 = new JPanel(new FlowLayout());
        panel2.add(label2);
        panel2.add(textArea2);
        panel0.add(panel2);
        
        panelT.add(panel0);
        this.add(panelT);
        
        JPanel panel3 = new JPanel(new GridLayout(1,3));
        //button1.setContentAreaFilled(false);
        //-button1.setFocusPainted(false);
        //button1.setBorderPainted(false);
        JPanel panel3C1 = new JPanel(new FlowLayout());
        panel3C1.add(button1);
        panel3.add(panel3C1);
        
        JPanel panel3A = new JPanel();
        panel3A.setLayout(new BoxLayout(panel3A, BoxLayout.Y_AXIS));
        JPanel panel3A0 = new JPanel(new FlowLayout());
        panel3A0.add(label3);
        panel3A.add(panel3A0);
        
        JPanel panel3Z0 = new JPanel(new FlowLayout());
        panel3Z0.add(new JTextArea(20,20));
        panel3A.add(panel3Z0);
        
        JPanel panel3A1 = new JPanel(new FlowLayout());
        panel3A1.add(label3A1);
        panel3A1.add(label3A2);
        panel3A.add(panel3A1);
        JPanel panel3B1 = new JPanel(new FlowLayout());
        panel3B1.add(label3B1);
        panel3B1.add(label3B2);
        panel3A.add(panel3B1);
        panel3.add(panel3A);
        //button2.setContentAreaFilled(false);
        //-button1.setFocusPainted(false);
        //button2.setBorderPainted(false);
        JPanel panel3C2 = new JPanel(new FlowLayout());
        panel3C2.add(button2);
        panel3.add(panel3C2);
        
        this.add(panel3);       
        
        JPanel panel4 = new JPanel(new GridLayout(1,3));
        JPanel panel4A = new JPanel(new FlowLayout());
        panel4A.add(button3);
        panel4.add(panel4A);
        JPanel panel4B = new JPanel(new FlowLayout());
        panel4B.add(button4);
        panel4.add(panel4B);
        JPanel panel4C = new JPanel(new FlowLayout());
        panel4C.add(button5);
        panel4.add(panel4C);
        
        this.add(panel4);
    }
}