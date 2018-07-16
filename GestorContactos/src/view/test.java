/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author mizar
 */
public class test extends JFrame{
   private JPanel mainPanel = new JPanel(new GridLayout(2,1));
   private JPanel addressMiddlePanel = new JPanel();
   private JPanel addressBottomPanel = new JPanel(new FlowLayout());
   private JButton addAddressButton = new JButton("+");
   private ArrayList<InteractiveBlock[]> addressBlock = new ArrayList<InteractiveBlock[]>();
   private ArrayList<JButton> deleteAddressButtons = new ArrayList<JButton>();
   private ArrayList<JPanel> addressMiddleList = new ArrayList<JPanel>();
   // Constructor to setup the GUI components and event handlers
    public test() {

        mainPanel.add(addressMiddlePanel);
        addressBottomPanel.add(addAddressButton);
        addAddressButton.addActionListener(new AddAddressListener());
        mainPanel.add(addressBottomPanel);
        
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Exit program if close-window button clicked
        setTitle("Mis contactos"); // "super" JFrame sets title
        setSize(850, 150);         // "super" JFrame sets initial size
        setVisible(true);          // "super" JFrame shows
    }
    
    private void addToAddressBlock(){
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
       
           int top = addressBlock.size();
           addressBlock.add(new InteractiveBlock[8]);
           for (int j = 0; j < 8; j++){
                addressBlock.get(top)[j] = new InteractiveBlock();
                addressBlock.get(top)[j].getTitleLabel().setText(attributes[j]);
                if (j == 0){
                    addressBlock.get(top)[j].getTextArea().setColumns(3);
                }
                else{
                    addressBlock.get(top)[j].getTextArea().setColumns(5);
                }
                
                addressBlock.get(top)[j].getTextArea().setText(values[j]);
           }
       }
    
    private void addToAddressMiddleList(){
        int size = addressMiddleList.size();
        addressMiddleList.add(new JPanel(new FlowLayout()));
        for ( InteractiveBlock blockPair : addressBlock.get(size) ){
            addressMiddleList.get(size).add(blockPair);
        }
        deleteAddressButtons.add(new JButton("X"));
        deleteAddressButtons.get(size).addActionListener(new DeleteAddressListener());
        addressMiddleList.get(size).add(deleteAddressButtons.get(size));
    }
        
    private void addAddress(){
        addToAddressBlock();
        addToAddressMiddleList();
        addressMiddlePanel.add(addressMiddleList.get( addressMiddleList.size() - 1 ));
        revalidate();
        repaint();
    }
    
    private void removeAddress(int index){
        deleteAddressButtons.remove( deleteAddressButtons.get(index) );
        addressMiddlePanel.remove( addressMiddleList.get(index) );
        addressMiddleList.remove( addressMiddleList.get(index) );
        addressBlock.remove( addressBlock.get(index) );
        revalidate();
        repaint();
    }
    
    private class AddAddressListener implements ActionListener {

        public AddAddressListener() {
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            addAddress();
        }
    }
    
    private class DeleteAddressListener implements ActionListener {

        public DeleteAddressListener() {
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            int index = deleteAddressButtons.indexOf( ae.getSource() );
            removeAddress(index);
        }
    }
    
    public static void main(String[] args) {
        new test();
    }
}
