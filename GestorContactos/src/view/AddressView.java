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
   private JPanel addressTopPanel = new JPanel(new FlowLayout());
   private JPanel addressMiddlePanel = new JPanel();
   private JPanel addressBottomPanel = new JPanel(new FlowLayout());
   private JLabel header = new JLabel("Dirección:");
   private JButton addAddressButton = new JButton("+");
   private ArrayList<Block[]> addressBlock = new ArrayList<Block[]>();
   private ArrayList<JButton> deleteAddressButtons = new ArrayList<JButton>();
   private ArrayList<JPanel> addressMiddleList = new ArrayList<JPanel>();
   // Constructor to setup the GUI components and event handlers
    public AddressView() {
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        addressTopPanel.add( header );
        this.add(addressTopPanel);
        addressMiddlePanel.setLayout(new BoxLayout(addressMiddlePanel,BoxLayout.Y_AXIS));
        this.add(addressMiddlePanel);
        
        addressBottomPanel.add(addAddressButton);
        addAddressButton.addActionListener(new AddAddressListener());
        this.add(addressBottomPanel);
        addressBottomPanel.setVisible(false);
    }
    
    public void makeStatic(boolean b){
        for ( Block[] blocks : addressBlock ){
            for ( Block block : blocks ){
                block.makeStatic( b );
            }
        }
        for ( JButton button : deleteAddressButtons ){
            button.setVisible( !b );
        }
        addressBottomPanel.setVisible( !b );
    }
    
    private void addToAddressBlock(String[] values ){
       String[] attributes = {"Tipo:",
                                "Correo:",
                                "D. Extendida:",
                                "Calle:",
                                "Localidad:",
                                "Región:",
                                "C.P:",
                                "País:"};
   
            int top = addressBlock.size();
            int numberOfBlocks = 8;
            addressBlock.add(new Block[ numberOfBlocks ]);
            for (int j = 0; j < numberOfBlocks; j++){
                 addressBlock.get(top)[j] = new Block();
                 addressBlock.get(top)[j].getTitleLabel().setText(attributes[j]);
                 addressBlock.get(top)[j].setContent( values[j] );
                 addressBlock.get(top)[j].updateBlock();
                 if (j == 0){
                     addressBlock.get(top)[j].getTextArea().setColumns(3);
                 }
                 else{
                     addressBlock.get(top)[j].getTextArea().setColumns(5);
                 }
            }
    }
    
    private void addToAddressMiddleList(){
        int size = addressMiddleList.size();
        addressMiddleList.add(new JPanel(new FlowLayout()));
        for ( Block blockPair : addressBlock.get(size) ){
            addressMiddleList.get(size).add(blockPair);
        }
        
        deleteAddressButtons.add(new JButton("X"));
        deleteAddressButtons.get(size).addActionListener(new DeleteAddressListener());
        //deleteAddressButtons.get(size).setVisible(false);
        addressMiddleList.get(size).add(deleteAddressButtons.get(size));
        
    }
        
    public void addAddress(String[] values){
        addToAddressBlock( values );
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
            String[] values = {"","","","","","","",""};
            addAddress(values);
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
