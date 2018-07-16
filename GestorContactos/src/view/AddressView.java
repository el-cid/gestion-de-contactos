/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author mizar
 */
public class AddressView extends JPanel{
   private String type = "";
   private JPanel addressTopPanel = new JPanel(new FlowLayout());
   private JPanel addressMiddlePanel = new JPanel();
   private JPanel addressBottomPanel = new JPanel(new FlowLayout());
   private JLabel header = new JLabel("Dirección:");
   private JButton addAddressButton = new JButton("+");
   private ArrayList<Block[]> addressBlock = new ArrayList<Block[]>();
   private ArrayList<JButton> deleteAddressButtons = new ArrayList<JButton>();
   private ArrayList<JPanel> addressMiddleList = new ArrayList<JPanel>();
   // Constructor to setup the GUI components and event handlers
    public AddressView(String type) {
        this.type = type;
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        addressTopPanel.add( header );
        this.add(addressTopPanel);
        addressMiddlePanel.setLayout(new BoxLayout(addressMiddlePanel,BoxLayout.Y_AXIS));
        this.add(addressMiddlePanel);
        if ( type.equalsIgnoreCase("interactive") ){
            addressBottomPanel.add(addAddressButton);
            addAddressButton.addActionListener(new AddAddressListener());
            this.add(addressBottomPanel);
        }
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
           if ( this.type.equalsIgnoreCase("interactive") ){
                addressBlock.add(new InteractiveBlock[8]);
                for (int j = 0; j < 8; j++){
                     addressBlock.get(top)[j] = new InteractiveBlock();
                     addressBlock.get(top)[j].getTitleLabel().setText(attributes[j]);
                     InteractiveBlock newBlock = (InteractiveBlock) addressBlock.get(top)[j];
                     if (j == 0){
                         newBlock.getTextArea().setColumns(3);
                     }
                     else{
                         newBlock.getTextArea().setColumns(5);
                     }
                     newBlock.getTextArea().setText(values[j]);
                }
           }
           else if ( this.type.equalsIgnoreCase("static") ){
                addressBlock.add(new StaticBlock[8]);
                for (int j = 0; j < 8; j++){
                     addressBlock.get(top)[j] = new StaticBlock();
                     addressBlock.get(top)[j].getTitleLabel().setText(attributes[j]);
                     StaticBlock newBlock = (StaticBlock) addressBlock.get(top)[j];
                     newBlock.getContentLabel().setText(values[j]);
                }
           }
    }
    
    private void addToAddressMiddleList(){
        int size = addressMiddleList.size();
        addressMiddleList.add(new JPanel(new FlowLayout()));
        for ( Block blockPair : addressBlock.get(size) ){
            addressMiddleList.get(size).add(blockPair);
        }
        if ( this.type.equalsIgnoreCase("interactive") ){
            deleteAddressButtons.add(new JButton("X"));
            deleteAddressButtons.get(size).addActionListener(new DeleteAddressListener());
            addressMiddleList.get(size).add(deleteAddressButtons.get(size));
        }
    }
        
    public void addAddress(){
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
    
}
