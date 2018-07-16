/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.FlowLayout;
import javax.swing.JPanel;

/**
 *
 * @author mizar
 */
public class NameView extends JPanel{
    private InteractiveBlock[] nameBlock = new InteractiveBlock[5];
    
    public NameView(){
        initNameBlock();    
        JPanel panel = new JPanel(new FlowLayout());
        for ( InteractiveBlock block : nameBlock ){
            panel.add(block);
        }
        this.add(panel);
        this.setLayout(new FlowLayout());
    }
    
    private void initNameBlock(){
        String[] attributes = {"Given Name:", "Family Name:",
                              "Additional Name:", "Honorific Preffix:",
                              "Honorific Suffix"};
        String[] values = {"given name", "family name",
                          "additional name" , "honorific preffix",
                          "honorific suffix"};
       
        for (int i = 0; i < nameBlock.length; i++){
            InteractiveBlock block = new InteractiveBlock();
            block.getTitleLabel().setText(attributes[i]);
            block.getTextArea().setColumns(8);
            block.getTextArea().setText(values[i]);
            nameBlock[i] = block;
       }
   }
}
