/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author mizar
 */
public class StaticBlock extends Block{
    private JLabel contentLabel = new JLabel();
    
    public StaticBlock(){
        JPanel secondPanel = new JPanel(new FlowLayout());
        secondPanel.add(contentLabel);
        this.add(secondPanel);
    }
    
    public JLabel getContentLabel(){
        return this.contentLabel;
    }
}
