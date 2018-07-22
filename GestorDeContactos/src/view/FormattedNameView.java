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
public class FormattedNameView extends JPanel{
    private Block formattedNameBlock = new Block();
    public FormattedNameView(){
        formattedNameBlock.getTitleLabel().setText("Nombre con formato:");
        formattedNameBlock.setContent("");
        formattedNameBlock.updateBlock();
        
        this.add(formattedNameBlock);
        this.setLayout(new FlowLayout());
    }
    public void makeStatic(boolean b){
        formattedNameBlock.makeStatic( b );
    }
    public void setFormattedName( String name ){
        formattedNameBlock.setContent(name);
        formattedNameBlock.updateBlock();
    }
    public void update(){
        String newContent = formattedNameBlock.getTextArea().getText();
        formattedNameBlock.setContent( newContent );
        formattedNameBlock.updateBlock();
    }
    public String getFormattedName(){
        return formattedNameBlock.getContent();
    }
}
