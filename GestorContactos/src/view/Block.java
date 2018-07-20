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
    private String content = "";
    private JLabel contentLabel = new JLabel();
    private JTextArea contentTextArea = new JTextArea(1,15);
    
    public Block(){
        this.setLayout(new FlowLayout());
        JPanel firstPanel = new JPanel(new FlowLayout());
        firstPanel.add(titleLabel);
        JPanel secondPanel = new JPanel(new FlowLayout());
        
        secondPanel.add(contentLabel);
        secondPanel.add(contentTextArea);
        
        this.add(firstPanel);
        this.add(secondPanel);
        makeStatic(false);
    }
    
    public JLabel getTitleLabel(){
        return this.titleLabel;
    }
    
    public JTextArea getTextArea(){
        return this.contentTextArea;
    }
    
    public JLabel getContentLabel(){
        return this.contentLabel;
    }
    
    public void makeStatic(boolean label){
        if ( label ){
            contentLabel.setVisible(true);
            contentTextArea.setVisible(false);
            contentLabel.setText( content );
        }
        else{
            contentTextArea.setVisible(true);
            contentLabel.setVisible(false);
            contentTextArea.setText( content );
        }
    }
    
    public void setContent( String content ){
        this.content = content;
    }
    
    public void updateBlock(){
        this.contentLabel.setText( content );
        this.contentTextArea.setText( content );
    }
}
