/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_OPTION;
import javax.swing.JPanel;
import control.Controller;
/**
 *
 * @author mizar
 */
public class Main extends JFrame {   
    private Controller control;
    private JPanel cards = new JPanel(new CardLayout());
    private Login login = new Login();
    private MainMenu mainMenu = new MainMenu();
    private ImportView importView = new ImportView();
    private ExportView exportView = new ExportView();
    private MenuGestionContactos menuContactos = new MenuGestionContactos();
    final static String LOGINPANEL = "Pantalla para ingresar.";
    final static String MAINPANEL = "Pantalla con el menú principal.";
    final static String IMPORTPANEL = "Pantalla para importar un archivo vcf.";
    final static String EXPORTPANEL = "Pantalla para exportar un archivo vcf..";
    final static String CONTACTPANEL = "Pantalla para gestionar un contacto.";
    final static String CONTACTSPANEL = "Pantalla para gestionar los contactos de un usuario.";
    // Constructor to setup the GUI components and event handlers
    public Main() {
        
        login.getLoginButton().addActionListener(new LoginL());
        mainMenu.getImportButton().addActionListener(new ReturnL(IMPORTPANEL));
        mainMenu.getContactsButton().addActionListener(new ReturnL(CONTACTSPANEL));
        mainMenu.getExportButton().addActionListener(new ReturnL(EXPORTPANEL));
        mainMenu.getLogoutButton().addActionListener(new LogoutL(LOGINPANEL));
        importView.getReturnButton().addActionListener(new ReturnL(MAINPANEL));
        importView.getImportButton().addActionListener(new ImportL());
        exportView.getReturnButton().addActionListener(new ReturnL(MAINPANEL));
        menuContactos.getReturnButton().addActionListener(new ReturnL(MAINPANEL));
                
        cards.add( login, LOGINPANEL );
        cards.add( mainMenu, MAINPANEL );
        cards.add( importView, IMPORTPANEL );
        cards.add( exportView, EXPORTPANEL );
        cards.add( menuContactos, CONTACTSPANEL );      
       
        setContentPane( cards );
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Exit program if close-window button clicked
        setTitle("Mis contactos"); // "super" JFrame sets title
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setVisible(true);          // "super" JFrame shows
 
    }
    
    private class ReturnL implements ActionListener {
            private String panelID = "";
            public ReturnL(String panelStr){
                this.panelID = panelStr;
            }
            
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, panelID);
            }
    }
     
    private class LoginL implements ActionListener {
            
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, MAINPANEL);
            }
    }
    
    private class LogoutL implements ActionListener {
            private String panelID = "";
            public LogoutL(String panelStr){
                this.panelID = panelStr;
            }
            
            public void actionPerformed(ActionEvent e) {
                notificarSolicitud();
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, panelID);
            }
    }
    
    private class ImportL implements ActionListener {
            
        public void actionPerformed(ActionEvent e) {
            String filePath = importView.getFilePath();
            if ( !importView.fileWasSupplied() ){
                String title = "Error";
                String message = "Necesitas proporcionar un archivo.";
                JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
            }
            else{
                control.parseFile(filePath);
            }
        }
    }
    
    public void addContact(ContactView contact){
        menuContactos.addContactView( contact );
    }

    public void notificarSolicitud(){
        Object[] options = {"Si",
                        "No"};
        int n = JOptionPane.showOptionDialog(new JFrame(),
        "¿Deseas guardar los cambios realizados?",
        "Notificación",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null,     //do not use a custom Icon
        options,  //the titles of buttons
        options[0]); //default button title
        if ( n == YES_OPTION){
            System.out.println("YEEESSSSS");
        }
        else {
            System.out.println("NOOOOOOO");
        }
    }

    public void setController(Controller control){
        this.control = control;
    }
}
