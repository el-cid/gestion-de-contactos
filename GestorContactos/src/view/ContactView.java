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
   private int numTelefonos = 2;
   private int numEmails = 2;
   private int numDirecciones = 2;
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
   private ArrayList<JPanel> telephoneTopList = new ArrayList<JPanel>();
   private ArrayList<JPanel> telephoneMiddleList = new ArrayList<JPanel>();
   private ArrayList<JPanel> telephoneBottomList = new ArrayList<JPanel>();
   private ArrayList<JPanel> emailTopList = new ArrayList<JPanel>();
   private ArrayList<JPanel> emailMiddleList = new ArrayList<JPanel>();
   private ArrayList<JPanel> emailBottomList = new ArrayList<JPanel>();
   private ArrayList<JPanel> addressTopList = new ArrayList<JPanel>();
   private ArrayList<JPanel> addressMiddleList = new ArrayList<JPanel>();
   private ArrayList<JPanel> addressBottomList = new ArrayList<JPanel>();
   private ArrayList<JPanel> bottomList = new ArrayList<JPanel>();
   private ArrayList<ArrayList<JPanel>> wholeList = new ArrayList<ArrayList<JPanel>>();
   // Constructor to setup the GUI components and event handlers
   public ContactView() {
         
        wholeList.add(topList);
        wholeList.add(telephoneTopList);
        wholeList.add(telephoneMiddleList);
        wholeList.add(telephoneBottomList);
        wholeList.add(emailTopList);
        wholeList.add(emailMiddleList);
        wholeList.add(emailBottomList);
        wholeList.add(addressTopList);
        wholeList.add(addressMiddleList);
        wholeList.add(addressBottomList);
        wholeList.add(bottomList);
        
        JPanel panelY = new JPanel(new FlowLayout());
        panelY.add(labelHeader);
        topList.add(panelY);
        
        JPanel panelZ = new JPanel(new FlowLayout());
        panelZ.add(contactPhoto);
        topList.add(panelZ);
        
        formattedNameBlock.getTitleLabel().setText("Formatted Name:");
        formattedNameBlock.getTextArea().setText("formatted name");
        topList.add(formattedNameBlock);
        
        initNameBlock();    
        JPanel panelZX = new JPanel(new FlowLayout());
        for ( InteractiveBlock block : nameBlock ){
            panelZX.add(block);
        }
        topList.add(panelZX);
        
        JPanel panel3F1 = new JPanel(new FlowLayout());
        panel3F1.add(labelFecha);
        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        datePicker = new JDatePickerImpl(datePanel);
        panel3F1.add(datePicker);
        topList.add(panel3F1);
        
        JPanel panel3K1 = new JPanel(new FlowLayout());
        panel3K1.add(labelTelefono);
        telephoneTopList.add(panel3K1);
        
        String[] phoneTags = {"Type:","type","Number:","number"};
        initMultiBlock(telephoneBlock,phoneTags);
        configureTelephoneMiddleList();
        
        JPanel panel3W1 = new JPanel(new FlowLayout());
        panel3W1.add(addTelButton);
        telephoneBottomList.add(panel3W1);
        
        JPanel panel3V1 = new JPanel(new FlowLayout());
        panel3V1.add(labelEmail);
        emailTopList.add(panel3V1);
        
        String[] emailTags = {"Type:","type","Email:","email"};
        initMultiBlock(emailBlock, emailTags);
        configureEmailMiddleList();
        
        JPanel panel3N1 = new JPanel(new FlowLayout());
        panel3N1.add(addEmailButton);
        emailBottomList.add(panel3N1);
        
        JPanel panel3X1 = new JPanel(new FlowLayout());
        panel3X1.add(labelAddress);
        addressTopList.add(panel3X1);
                
        initAddressBlock(addressBlock);
        configureAddressMiddleList();
    
        JPanel panel3U1 = new JPanel(new FlowLayout());
        panel3U1.add(addAddressButton);
        addressBottomList.add(panel3U1);    
        
        JPanel panel3C2 = new JPanel(new FlowLayout());
        panel3C2.add(button);
        bottomList.add(panel3C2);
        
        configureLayout();
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
   
   private void configureTelephoneMiddleList(){
        //telephoneMiddleList = new ArrayList<JPanel>();
        for ( int i = 0; i < telephoneBlock.length; i++ ){
            InteractiveBlock[] blockPair = telephoneBlock[i];
            deleteTelButtons[i] = new JButton("X");
            telephoneMiddleList.add(new JPanel(new FlowLayout()));
            telephoneMiddleList.get(i).add(blockPair[0]);
            telephoneMiddleList.get(i).add(blockPair[1]);
            telephoneMiddleList.get(i).add(deleteTelButtons[i]);
        }
    }
    
   private void configureEmailMiddleList(){
        for ( int i = 0; i < emailBlock.length; i++ ){
            InteractiveBlock[] blockPair = emailBlock[i];
            deleteEmailButtons[i] = new JButton("X");
            emailMiddleList.add(new JPanel(new FlowLayout()));
            emailMiddleList.get(i).add(blockPair[0]);
            emailMiddleList.get(i).add(blockPair[1]);
            emailMiddleList.get(i).add(deleteEmailButtons[i]);
        }
    }
   
   private void configureAddressMiddleList(){
        for ( int i = 0; i < addressBlock.length; i++ ){
            addressMiddleList.add(new JPanel(new FlowLayout()));
            for ( InteractiveBlock block : addressBlock[i] ){
                addressMiddleList.get(i).add(block);
            }
            deleteAddressButtons[i] = new JButton("X");
            addressMiddleList.get(i).add(deleteAddressButtons[i]);
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