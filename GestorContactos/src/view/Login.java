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
public class Login extends JPanel{
    private Block userName = new Block();
    private Block userPassword = new Block();
    private JButton button = new JButton("Ingresar");
    public Login(){
        setLayout( new BoxLayout(this, BoxLayout.Y_AXIS) );
        JPanel namePanel = new JPanel( new FlowLayout() );
        userName.getTitleLabel().setText("Usuario:");
        userName.getTextArea().setText("");
        namePanel.add( userName );
        JPanel passwordPanel = new JPanel( new FlowLayout() );
        userPassword.getTitleLabel().setText("Contraseña:");
        userPassword.getTextArea().setText("");
        passwordPanel.add( userPassword );
        add( namePanel );
        add( passwordPanel );
        JPanel buttonPanel = new JPanel( new FlowLayout() );
        buttonPanel.add( button );
        add( buttonPanel );
    }
    public JButton getLoginButton(){
        return this.button;
    }
    
    public Block getUserNameBlock(){
        return this.userName;
    }
    
    public Block getUserPasswordBlock(){
        return this.userPassword;
    }
}
