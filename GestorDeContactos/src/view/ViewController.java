/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import control.Controller;
import static javax.swing.JOptionPane.YES_OPTION;
/**
 *
 * @author mizar
 */
public class ViewController extends JFrame {   
    private Controller control;
    private JPanel cards = new JPanel(new CardLayout());
    private Login login = new Login();
    private MainMenu mainMenu = new MainMenu();
    private ImportView importView = new ImportView();
    private ExportView exportView = new ExportView();
    private MenuGestionContactos menuContactos = new MenuGestionContactos();
    //private ArrayList<ContactView> userContacts = new ArrayList<ContactView>();
  
    final static String LOGINPANEL = "Pantalla para ingresar.";
    final static String MAINPANEL = "Pantalla con el menú principal.";
    final static String IMPORTPANEL = "Pantalla para importar un archivo vcf.";
    final static String EXPORTPANEL = "Pantalla para exportar un archivo vcf..";
    final static String CONTACTPANEL = "Pantalla para gestionar un contacto.";
    final static String CONTACTSPANEL = "Pantalla para gestionar los contactos de un usuario.";
    // Constructor to setup the GUI components and event handlers
    public ViewController() {
        
        login.getLoginButton().addActionListener(new LoginL());
        login.getSigninButton().addActionListener(new SigninL());
        mainMenu.getImportButton().addActionListener(new ReturnL(IMPORTPANEL));
        mainMenu.getContactsButton().addActionListener(new ReturnL(CONTACTSPANEL));
        mainMenu.getExportButton().addActionListener(new ReturnL(EXPORTPANEL));
        mainMenu.getLogoutButton().addActionListener(new LogoutL(LOGINPANEL));
        importView.getReturnButton().addActionListener(new ReturnL(MAINPANEL));
        importView.getImportButton().addActionListener(new ImportL());
        exportView.getReturnButton().addActionListener(new ReturnL(MAINPANEL));
        exportView.getExportButton().addActionListener(new ExportL());
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
                String user = login.getUserNameBlock().getTextArea().getText();
                String password = login.getUserPasswordBlock().getTextArea().getText();
                if ( ( ( user.matches(".*\\w.*") ) & ( password.matches(".*\\w.*") ) ) ){
                    
                    boolean validAccount = control.verifyAccount( user, password );
                    if ( validAccount ){
                        control.importFromDatabase();
                        control.contactsToContactViews();
                        CardLayout cl = (CardLayout)(cards.getLayout());
                        cl.show(cards, MAINPANEL);
                    }
                    else{ 
                        String title = "Datos incorrectos";
                        String message = "Verifica tus datos, por favor.";
                        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);                
                    }
                    
                }
                else{
                    String title = "Entrada invalida";
                    String message = "Inserta un identificador de usuario y una contraseña, por favor.";
                    JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);                
                }
                
            }
    }
    
    private class SigninL implements ActionListener {
            
            public void actionPerformed(ActionEvent e) {
                String user = login.getUserNameBlock().getTextArea().getText();
                String password = login.getUserPasswordBlock().getTextArea().getText();
                if ( ( ( user.matches(".*\\w.*") ) & ( password.matches(".*\\w.*") ) ) ){
                   
                    if ( control.userNameIsAvailable( user ) ){
                        control.userNameIsAvailable(user);
                        control.createNewAccount(user, password);
                        CardLayout cl = (CardLayout)(cards.getLayout());
                        cl.show(cards, MAINPANEL);
                    }
                    else{ 
                        String title = "Cuenta en uso";
                        String message = "Ya existe una cuenta con ese nombre de usuario, ingresa uno diferente por favor.";
                        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);                
                    }
                    
                }
                else{
                    String title = "Entrada invalida";
                    String message = "Inserta un identificador de usuario y una contraseña, por favor.";
                    JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);                
                }
                
            }
    }
    
    private class ExportL implements ActionListener {
            
        public void actionPerformed(ActionEvent e) {
            String filePath = exportView.getFilePath();
            if ( filePath.equalsIgnoreCase("") ){
                String title = "Error";
                String message = "Necesitas proporcionar una ruta y nombre de archivo.";
                JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
            }
            else{
                control.contactViewsToContacts( menuContactos.getContactViews() );
                control.exportContactsToFile(filePath);
            }
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
    
    private class LogoutL implements ActionListener {
        private String panelID = "";
        public LogoutL(String panelStr){
            this.panelID = panelStr;
        }

        public void actionPerformed(ActionEvent e) {
            boolean logoutConfirmed = logout();
            if ( logoutConfirmed ){
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, panelID);
            }
            else{
            
            }
        }
    }
     
    public boolean logout(){
        Object[] options = {"Si",
                            "No"};
        int n = JOptionPane.showOptionDialog(new JFrame(),
        "¿Deseas guardar los cambios realizados?",
        "Notificación",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null,     
        options,  
        options[0]); 
        if ( n == YES_OPTION){
            control.saveChanges( menuContactos.getContactViews() );
            return true;
        }
        else {
            return false;
        }
    }
    
    public void addContact(ContactView contact){
        menuContactos.addContactView( contact );
    }
    
    public void setController(Controller control){
        this.control = control;
    }
}
