/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author mizar
 */
public class NameView extends JPanel{
    private Block[] nameBlock = new Block[5];
    
    public NameView(){
        initNameBlock();    
        JPanel panel = new JPanel(new FlowLayout());
        for ( Block block : nameBlock ){
            panel.add(block);
        }
        this.add(panel);
        this.setLayout(new FlowLayout());
    }
    
    private void initNameBlock(){
        String[] attributes = {"Nombre:", "Apellido:",
                              "Segundo Nombre:", "Título:",
                              "Sufijo:"};
        String[] values = {"", "", "" , "", ""};       
        
        for (int i = 0; i < nameBlock.length; i++){
            nameBlock[i] = new Block();
            nameBlock[i].getTitleLabel().setText(attributes[i]);
            nameBlock[i].getTextArea().setColumns(8);
            nameBlock[i].setContent(values[i]);
            nameBlock[i].updateBlock();
        }        
    }
    
    public void setName(String[] values){
        for (int i = 0; i < nameBlock.length; i++){
            nameBlock[i].setContent(values[i]);
            nameBlock[i].updateBlock();
        }  
    }
    
    public void makeStatic(boolean b){  
        for ( Block block : nameBlock ){
            block.makeStatic( b );
        }
    }
    
    public String getFirstName(){
        return nameBlock[0].getContent();
    }
    
    public String getSecondName(){
        return nameBlock[2].getContent();
    }
    
    public String getFamilyName(){
        return nameBlock[1].getContent();
    }
}
