/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author mizar
 */
public class Block extends JPanel{
    private JLabel titleLabel = new JLabel();
        
    public Block(){
        this.setLayout(new FlowLayout());
        JPanel firstPanel = new JPanel(new FlowLayout());
        firstPanel.add(titleLabel);
        this.add(firstPanel);
    }
    
    public JLabel getTitleLabel(){
        return this.titleLabel;
    }
    
}
