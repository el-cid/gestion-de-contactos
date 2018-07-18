/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author mizar
 */
public class MainMenu extends JPanel{
    private JButton button1 = new JButton("Importar Contactos");
    private JButton button2 = new JButton("Gestionar Contactos");
    private JButton button3 = new JButton("Exportar Contactos");
    private JButton button4 = new JButton("Terminar sesión");
    public MainMenu(){
         this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
         
         JPanel panel1 = new JPanel(new FlowLayout());
         panel1.add(button1);
         this.add(panel1);
         
         JPanel panel2 = new JPanel(new FlowLayout());
         panel2.add(button2);
         this.add(panel2);
         
         JPanel panel3 = new JPanel(new FlowLayout());
         panel3.add(button3);
         this.add(panel3);
         
         JPanel panel4 = new JPanel(new FlowLayout());
         panel4.add(button4);
         this.add(panel4);
    }
    public JButton getImportButton(){
        return this.button1;
    }
    public JButton getContactsButton(){
        return this.button2;
    }
    public JButton getExportButton(){
        return this.button3;
    }
    public JButton getLogoutButton(){
        return this.button4;
    }
}
