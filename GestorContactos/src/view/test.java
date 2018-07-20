/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.JFrame;

/**
 *
 * @author mizar
 */
public class test extends JFrame{
    public test(){
        ContactView contacto = new ContactView();
        //contacto.makeStatic(false);
        
        String[] direccion = {"Tipo", "DHL", "Exx", "Mar de japón", "DF", "Popotla" ,"11400", "México"};
        contacto.addAddress( direccion );
        String[] correo = {"Personal", "cosa@gmail.com"};
        contacto.addEmail( correo );
        String[] telefono = {"Personal", "+52 1 55 39 34 12 87"};
        contacto.addTelephone(telefono);
        String[] nombre = {"Mizar", "Martínez", "Eduardo", "Don", ""};
        contacto.setName( nombre );
        String formattedName = "Eduardo Mizar Martínez Alcántara";
        contacto.setFormattedName( formattedName );
        
        setContentPane(contacto);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Exit program if close-window button clicked
        setTitle("Mis contactos"); // "super" JFrame sets title
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setVisible(true); 
    }
    public static void main(String[] args) {
        new test();
    }
}
