/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author mizar
 */
public class InteractiveBlock extends Block{
    private JTextArea textArea = new JTextArea(1,15);
    
    public InteractiveBlock(){
        JPanel secondPanel = new JPanel(new FlowLayout());
        secondPanel.add(textArea);
        this.add(secondPanel);
    }
    
    public JTextArea getTextArea(){
        return this.textArea;
    }
}