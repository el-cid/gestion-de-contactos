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
import javax.swing.JPanel;

/**
 *
 * @author mizar
 */
public class Main extends JFrame {   
   private JPanel cards = new JPanel(new CardLayout());
   private Login login = new Login();
   private MainMenu mainMenu = new MainMenu();
   private ImportView importView = new ImportView();
   private ExportView exportView = new ExportView();
   private MenuGestionContactos contactsView = new MenuGestionContactos();
   private ContactView contactView = new ContactView();
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
        mainMenu.getLogoutButton().addActionListener(new ReturnL(LOGINPANEL));
        importView.getReturnButton().addActionListener(new ReturnL(MAINPANEL));
        exportView.getReturnButton().addActionListener(new ReturnL(MAINPANEL));
        contactsView.getReturnButton().addActionListener(new ReturnL(MAINPANEL));
        contactsView.getSelectionButton().addActionListener(new SelectL());
        contactView.getReturnButton().addActionListener(new ReturnFromContactL(CONTACTSPANEL));
        
        cards.add( login, LOGINPANEL );
        cards.add( mainMenu, MAINPANEL );
        cards.add( importView, IMPORTPANEL );
        cards.add( exportView, EXPORTPANEL );
        cards.add( contactsView, CONTACTSPANEL );
        //-cards.add( contactView, CONTACTPANEL );

       
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
    
    private class ReturnFromContactL implements ActionListener {
            private String panelID = "";
            public ReturnFromContactL(String panelStr){
                this.panelID = panelStr;
            }
            
            public void actionPerformed(ActionEvent e) {
                contactView.makeStatic(true);//-
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
    
    private class SelectL implements ActionListener {
            
            public void actionPerformed(ActionEvent e) {
          
                //-contactView = contactsView.getCurrentContact();
                //-contactView.makeStatic(false);
                //-contactView.getReturnButton().addActionListener(new ReturnL(CONTACTSPANEL));
                contactsView.getCurrentContact().getReturnButton().addActionListener(new ReturnFromContactL(CONTACTSPANEL));
                contactsView.getCurrentContact().makeStatic(false);
                CardLayout cl = (CardLayout)(cards.getLayout());
                //-cards.add( contactView, CONTACTPANEL );//-
                cards.add( contactsView.getCurrentContact(), CONTACTPANEL );//-
                cl.show(cards, CONTACTPANEL);
            }
    }
   
    /*
    public static void main(String[] args) {
        new Main();
    }
    */
    
    public void addContact(ContactView contact){
        contactsView.addContactView( contact );
    }
}
