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
import javax.swing.JTextArea;

/**
 *
 * @author mizar
 */
public class Main extends JFrame {   
   private JPanel mainPanel;
   
   // Constructor to setup the GUI components and event handlers
   public Main() {
        
        mainPanel = new MenuGestionContactos();
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Exit program if close-window button clicked
        setTitle("Mis contactos"); // "super" JFrame sets title
        setSize(850, 150);         // "super" JFrame sets initial size
        setVisible(true);          // "super" JFrame shows
    }
   
    public static void main(String[] args) {
        new Main();
    }
}
