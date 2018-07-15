/*  
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author mizar
 */
public class ContactView extends JPanel {      
   private int rows = 15;
   private int columns = 1;
   private int row = 0;
   private int column = 0;
   private int numTelefonos = 2;
   private int numEmails = 2;
   private int numDirecciones = 2;
   private JPanel[][] panelHolder = new JPanel[rows][columns];    
   private JButton button = new JButton("¿Aceptar?");
   private JLabel labelHeader = new JLabel("Contacto");
   private JLabel labelFecha = new JLabel("Fecha de nacimiento:");
   private JDatePickerImpl datePicker;
   private JLabel labelTelefono = new JLabel("Teléfono:");
   private JLabel labelEmail = new JLabel("Email:");
   private JLabel labelAddress = new JLabel("Dirección:");
   private Image contactPhoto = new Image();
   private StaticBlock formattedNameBlock = new StaticBlock();
   private StaticBlock[] nameBlock = new StaticBlock[5];
   private StaticBlock[][] addressBlock = new StaticBlock[numDirecciones][8];
   private StaticBlock[][] telephoneBlock = new StaticBlock[numTelefonos][2];
   private StaticBlock[][] emailBlock = new StaticBlock[numEmails][2];
   // Constructor to setup the GUI components and event handlers
   public ContactView() {
        
        setLayout(new GridLayout(rows,columns));

        for(int m = 0; m < rows; m++) {
           for(int n = 0; n < columns; n++) {
              panelHolder[m][n] = new JPanel();
              add(panelHolder[m][n]);
           }
        }
        
        JPanel panelY = new JPanel(new FlowLayout());
        panelY.add(labelHeader);
        panelHolder[row++][0].add(panelY);
        
        panelHolder[row++][0].add(contactPhoto);
        
        formattedNameBlock.getTitleLabel().setText("Formatted Name:");
        formattedNameBlock.getContentLabel().setText("formatted name");
        panelHolder[row++][0].add(formattedNameBlock);
        
        initNameBlock();
        /*for ( int i = 0; i < nameBlock.length; i++){
            if ( i < 3 ){
                panelHolder[row][0].add(nameBlock[i]);
            }
            else{
                panelHolder[ row + 1 ][0].add(nameBlock[i]);
            }
        }
        row += 2;*/
        
        for ( StaticBlock block : nameBlock ){
            panelHolder[row][0].add(block);
        }
        ++row;
        
        JPanel panel3F1 = new JPanel(new FlowLayout());
        panel3F1.add(labelFecha);
        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        datePicker = new JDatePickerImpl(datePanel);
        panel3F1.add(datePicker);
        panelHolder[row++][0].add(panel3F1);
        
        JPanel panel3K1 = new JPanel(new FlowLayout());
        panel3K1.add(labelTelefono);
        panelHolder[row++][0].add(panel3K1);
        
        String[] phoneTags = {"Type:","type","Number:","number"};
        initMultiBlock(telephoneBlock,phoneTags);
        for ( StaticBlock[] blockPair : telephoneBlock ){
            panelHolder[row][0].add(blockPair[0]);
            panelHolder[row++][0].add(blockPair[1]);
        }
        
        
        JPanel panel3V1 = new JPanel(new FlowLayout());
        panel3V1.add(labelEmail);
        panelHolder[row++][0].add(panel3V1);
        
        String[] emailTags = {"Type:","type","Email:","email"};
        initMultiBlock(emailBlock, emailTags);
        for ( StaticBlock[] blockPair : emailBlock ){
            panelHolder[row][0].add(blockPair[0]);
            panelHolder[row++][0].add(blockPair[1]);
        }
        
        JPanel panel3X1 = new JPanel(new FlowLayout());
        panel3X1.add(labelAddress);
        panelHolder[row++][0].add(panel3X1);
        
        //
        initAddressBlock(addressBlock);
        for ( StaticBlock[] address : addressBlock ){
            for ( StaticBlock block : address ){
                panelHolder[row][0].add(block);
            }
            ++row;
        }
        
        //
        
        JPanel panel3C2 = new JPanel(new FlowLayout());
        panel3C2.add(button);
        panelHolder[ rows-1 ][0].add(panel3C2);
                        
    }
   
   private void initNameBlock(){
       String[] attributes = {"Given Name:", "Family Name:",
                              "Additional Name:", "Honorific Preffix:",
                              "Honorific Suffix"};
       String[] values = {"given name", "family name",
                          "additional name" , "honorific preffix",
                          "honorific suffix"};
       
       for (int i = 0; i < nameBlock.length; i++){
           StaticBlock block = new StaticBlock();
           block.getTitleLabel().setText(attributes[i]);
           block.getContentLabel().setText(values[i]);
           nameBlock[i] = block;
       }
   }
   private void initAddressBlock(StaticBlock[][] blocks){
       String[] attributes = {"Type:",
                                "Post-Office-Address:",
                                "Extended Address:",
                                "Street:",
                                "Locality:",
                                "Region:",
                                "Postal Code:",
                                "Country:"};
       String[] values = {"type",
                                "postOfficeAddress",
                                "extendedAddress",
                                "street",
                                "locality",
                                "region",
                                "postalCode",
                                "country"};
       
       for (int i = 0; i < blocks.length; i++){
           for (int j = 0; j < blocks[i].length; j++){
                StaticBlock block = new StaticBlock();
                block.getTitleLabel().setText(attributes[j]);
                block.getContentLabel().setText(values[j]);
                blocks[i][j] = block;
           }
       }
   }

   private void initMultiBlock(StaticBlock[][] multiBlock, String[] tags){
       for (int i = 0; i < multiBlock.length; i++){        
           StaticBlock upperBlock = new StaticBlock();
           upperBlock.getTitleLabel().setText(tags[0]);
           upperBlock.getContentLabel().setText(tags[1]);
           multiBlock[i][0] = upperBlock;
           StaticBlock lowerBlock = new StaticBlock();
           lowerBlock.getTitleLabel().setText(tags[2]);
           lowerBlock.getContentLabel().setText(tags[3]);
           multiBlock[i][1] = lowerBlock;
       }
   }
}