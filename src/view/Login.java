/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
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
    private JButton button2 = new JButton("Crear cuenta");
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
        JPanel buttonPanel = new JPanel( new GridLayout(1,2) );
        JPanel button1Panel = new JPanel( new FlowLayout() );
        button1Panel.add( button );
        JPanel button2Panel = new JPanel( new FlowLayout() );
        button2Panel.add( button2 );
        buttonPanel.add( button1Panel );
        buttonPanel.add( button2Panel );
        add( buttonPanel );
    }
    public JButton getLoginButton(){
        return this.button;
    }
    
    public JButton getSigninButton(){
        return this.button2;
    }
    
    public Block getUserNameBlock(){
        return this.userName;
    }
    
    public Block getUserPasswordBlock(){
        return this.userPassword;
    }
}
