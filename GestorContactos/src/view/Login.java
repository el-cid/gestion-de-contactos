/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class Login extends JPanel {   
   private JButton button = new JButton("Ingresar");
   private JLabel label1 = new JLabel("   Usuario: ");
   private JLabel label2 = new JLabel("Contraseña: ");
   private JTextArea textArea1 = new JTextArea(1, 30);
   private JTextArea textArea2 = new JTextArea(1, 30);
   
   // Constructor to setup the GUI components and event handlers
   public Login() {
        
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
                
        JPanel panel1 = new JPanel(new FlowLayout());
        panel1.add(label1);
        panel1.add(textArea1);
        this.add(panel1);
        
        JPanel panel2 = new JPanel(new FlowLayout());
        panel2.add(label2);
        panel2.add(textArea2);
        this.add(panel2);
        
        JPanel panel3 = new JPanel(new FlowLayout());
        panel3.add(button);        
        this.add(panel3);

        button.addActionListener(new ActionListenerImpl());
                
    }
   private class ActionListenerImpl implements ActionListener {

        public ActionListenerImpl() {
        }

        @Override
        public void actionPerformed(ActionEvent evt) {

        }
    }
   
   public void setNumPomodoros(int p){
       //GestorTareas.updateTareaInterrupcion(p);
       //SwingUtilities.updateComponentTreeUI(this);
   }
   
}