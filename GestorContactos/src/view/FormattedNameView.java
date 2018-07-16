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
    private String type = "";
    private Block formattedNameBlock;
    public FormattedNameView(String type){
        this.type = type;
        if ( this.type.equalsIgnoreCase("interactive") ){    
            InteractiveBlock newBlock = new InteractiveBlock();
            newBlock.getTitleLabel().setText("Formatted Name:");
            newBlock.getTextArea().setText("formatted name");
            formattedNameBlock = newBlock;
        }
        else if ( this.type.equalsIgnoreCase("static") ){    
            StaticBlock newBlock = new StaticBlock();
            newBlock.getTitleLabel().setText("Formatted Name:");
            newBlock.getContentLabel().setText("formatted name");
            formattedNameBlock = newBlock;
        }
        this.add(formattedNameBlock);
        this.setLayout(new FlowLayout());
    }
}
