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
    private InteractiveBlock formattedNameBlock = new InteractiveBlock();
    public FormattedNameView(){
        formattedNameBlock.getTitleLabel().setText("Formatted Name:");
        formattedNameBlock.getTextArea().setText("formatted name");
        this.add(formattedNameBlock);
        this.setLayout(new FlowLayout());
    }
}
