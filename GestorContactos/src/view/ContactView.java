/*  
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComponent;
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
   private int rows = 12;
   private int row = 0;
   private int numTelefonos = 0;
   private int numEmails = 0;
   private int numDirecciones = 0;
   private JPanel[] panelHolder = new JPanel[rows];    
   private JButton button = new JButton("Guardar cambios");
   private JLabel labelHeader = new JLabel("Contacto");
   private JLabel labelFecha = new JLabel("Fecha de nacimiento:");
   private JDatePickerImpl datePicker;
   private JLabel labelTelefono = new JLabel("Teléfono:");
   private JLabel labelEmail = new JLabel("Email:");
   private JLabel labelAddress = new JLabel("Dirección:");
   private Image contactPhoto = new Image();
   private InteractiveBlock formattedNameBlock = new InteractiveBlock();
   private InteractiveBlock[] nameBlock = new InteractiveBlock[5];
   private InteractiveBlock[][] addressBlock = new InteractiveBlock[numDirecciones][8];
   private JButton[] deleteAddressButtons = new JButton[numDirecciones];
   private JButton addAddressButton = new JButton("+");
   private InteractiveBlock[][] telephoneBlock = new InteractiveBlock[numTelefonos][2];
   private JButton[] deleteTelButtons = new JButton[numTelefonos];
   private JButton addTelButton = new JButton("+");
   private InteractiveBlock[][] emailBlock = new InteractiveBlock[numEmails][2];
   private JButton[] deleteEmailButtons = new JButton[numEmails];
   private JButton addEmailButton = new JButton("+");   
   private ArrayList<JPanel> topList = new ArrayList<JPanel>();
   private ArrayList<JPanel> telephoneList = new ArrayList<JPanel>();
   private ArrayList<JPanel> emailList = new ArrayList<JPanel>();
   private ArrayList<JPanel> addressList = new ArrayList<JPanel>();
   private ArrayList<JPanel> bottomList = new ArrayList<JPanel>();
   private ArrayList<ArrayList<JPanel>> wholeList = new ArrayList<ArrayList<JPanel>>();
   // Constructor to setup the GUI components and event handlers
   public ContactView() {
        
        setLayout(new GridLayout(rows,1));
       
        for(int m = 0; m < rows; m++) {           
              panelHolder[m] = new JPanel();
              add(panelHolder[m]);
        }
   
        wholeList.add(topList);
        wholeList.add(telephoneList);
        wholeList.add(emailList);
        wholeList.add(addressList);
        wholeList.add(bottomList);
        
        JPanel panelY = new JPanel(new FlowLayout());
        panelY.add(labelHeader);
        panelHolder[row++].add(panelY);//
        topList.add(panelY);
        
        JPanel panelZ = new JPanel(new FlowLayout());
        panelZ.add(contactPhoto);
        panelHolder[row++].add(panelZ);//
        topList.add(panelZ);
        
        formattedNameBlock.getTitleLabel().setText("Formatted Name:");
        formattedNameBlock.getTextArea().setText("formatted name");
        panelHolder[row++].add(formattedNameBlock);//
        topList.add(formattedNameBlock);
        
        initNameBlock();    
        JPanel panelZX = new JPanel(new FlowLayout());
        for ( InteractiveBlock block : nameBlock ){
            panelHolder[row].add(block);//
            //?panelZX.add(block);
        }
        //?topList.add(panelZX);
        ++row;
        
        JPanel panel3F1 = new JPanel(new FlowLayout());
        panel3F1.add(labelFecha);
        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        datePicker = new JDatePickerImpl(datePanel);
        panel3F1.add(datePicker);
        panelHolder[row++].add(panel3F1);//
        topList.add(panel3F1);
        
        JPanel panel3K1 = new JPanel(new FlowLayout());
        panel3K1.add(labelTelefono);
        panelHolder[row++].add(panel3K1);//
        telephoneList.add(panel3K1);
        
        String[] phoneTags = {"Type:","type","Number:","number"};
        initMultiBlock(telephoneBlock,phoneTags);
        for ( int i = 0; i < telephoneBlock.length; i++ ){
            InteractiveBlock[] blockPair = telephoneBlock[i];
            panelHolder[row].add(blockPair[0]);
            panelHolder[row].add(blockPair[1]);
            deleteTelButtons[i] = new JButton("X");
            panelHolder[row++].add(deleteTelButtons[i++]);
        }
        
        JPanel panel3W1 = new JPanel(new FlowLayout());
        panel3W1.add(addTelButton);
        telephoneList.add(panel3W1);
        panelHolder[row++].add(addTelButton);//
        
        JPanel panel3V1 = new JPanel(new FlowLayout());
        panel3V1.add(labelEmail);
        panelHolder[row++].add(panel3V1);//
        emailList.add(panel3V1);
        
        String[] emailTags = {"Type:","type","Email:","email"};
        initMultiBlock(emailBlock, emailTags);
        for ( int i = 0; i < emailBlock.length; i++ ){
            InteractiveBlock[] blockPair = emailBlock[i];
            panelHolder[row].add(blockPair[0]);
            panelHolder[row].add(blockPair[1]);
            deleteEmailButtons[i] = new JButton("X");
            panelHolder[row++].add(deleteEmailButtons[i]);
        }
        
        JPanel panel3N1 = new JPanel(new FlowLayout());
        panel3N1.add(addEmailButton);
        emailList.add(panel3N1);
        panelHolder[row++].add(addEmailButton);//
        
        JPanel panel3X1 = new JPanel(new FlowLayout());
        panel3X1.add(labelAddress);
        addressList.add(panel3X1);
        panelHolder[row++].add(panel3X1);//
        
        initAddressBlock(addressBlock);
        for ( int i = 0; i < addressBlock.length; i++ ){
            for ( InteractiveBlock block : addressBlock[i] ){
                panelHolder[row].add(block);
            }
            deleteAddressButtons[i] = new JButton("X");
            panelHolder[row++].add(deleteAddressButtons[i]);
        }
    
        JPanel panel3U1 = new JPanel(new FlowLayout());
        panel3U1.add(addAddressButton);
        addressList.add(panel3U1);    
        panelHolder[row++].add(addAddressButton);//
        
        JPanel panel3C2 = new JPanel(new FlowLayout());
        panel3C2.add(button);
        panelHolder[ rows-1 ].add(panel3C2);//
        bottomList.add(panel3C2);
    }
   
   private void initNameBlock(){
       String[] attributes = {"Given Name:", "Family Name:",
                              "Additional Name:", "Honorific Preffix:",
                              "Honorific Suffix"};
       String[] values = {"given name", "family name",
                          "additional name" , "honorific preffix",
                          "honorific suffix"};
       
       for (int i = 0; i < nameBlock.length; i++){
           InteractiveBlock block = new InteractiveBlock();
           block.getTitleLabel().setText(attributes[i]);
           block.getTextArea().setColumns(8);
           block.getTextArea().setText(values[i]);
           nameBlock[i] = block;
       }
   }

   private void initAddressBlock(InteractiveBlock[][] blocks){
       String[] attributes = {"Type:",
                                "Post-Office:",
                                "Extended:",
                                "Street:",
                                "Locality:",
                                "Region:",
                                "Postal Code:",
                                "Country:"};
       String[] values = {"type",
                                "postOffice",
                                "extendedAd",
                                "street",
                                "locality",
                                "region",
                                "postalCode",
                                "country"};
       
       for (int i = 0; i < blocks.length; i++){
           for (int j = 0; j < blocks[i].length; j++){
                InteractiveBlock block = new InteractiveBlock();
                block.getTitleLabel().setText(attributes[j]);
                if (j == 0){
                    block.getTextArea().setColumns(3);
                }
                else{
                    block.getTextArea().setColumns(5);
                }
                
                block.getTextArea().setText(values[j]);
                blocks[i][j] = block;
           }
       }
   }

   private void initMultiBlock(InteractiveBlock[][] multiBlock, String[] tags){
       for (int i = 0; i < multiBlock.length; i++){        
           InteractiveBlock upperBlock = new InteractiveBlock();
           upperBlock.getTitleLabel().setText(tags[0]);
           upperBlock.getTextArea().setColumns(3);
           upperBlock.getTextArea().setText(tags[1]);
           multiBlock[i][0] = upperBlock;
           InteractiveBlock lowerBlock = new InteractiveBlock();
           lowerBlock.getTitleLabel().setText(tags[2]);
           upperBlock.getTextArea().setColumns(5);
           lowerBlock.getTextArea().setText(tags[3]);
           multiBlock[i][1] = lowerBlock;
       }
   }
   
   private void configureLayout(){
        int elements = 0;
        for ( ArrayList<JPanel> list : wholeList ){
           elements += list.size();
        }
        setLayout(new GridLayout(elements,1));

        for ( ArrayList<JPanel> list : wholeList ) {           
            for ( JPanel panel : list ){  
                add(panel);
            }
        }
   }
}