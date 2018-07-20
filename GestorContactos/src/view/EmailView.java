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
public class EmailView extends JPanel{
   private JPanel emailTopPanel = new JPanel(new FlowLayout());
   private JPanel emailMiddlePanel = new JPanel();
   private JPanel emailBottomPanel = new JPanel(new FlowLayout());
   private JLabel header = new JLabel("Email:");
   private JButton addEmailButton = new JButton("+");
   private ArrayList<Block[]> emailBlock = new ArrayList<Block[]>();
   private ArrayList<JButton> deleteEmailButtons = new ArrayList<JButton>();
   private ArrayList<JPanel> emailMiddleList = new ArrayList<JPanel>();
   // Constructor to setup the GUI components and event handlers
    public EmailView() {
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        emailTopPanel.add( header );
        this.add( emailTopPanel );
        emailMiddlePanel.setLayout(new BoxLayout(emailMiddlePanel,BoxLayout.Y_AXIS));
        this.add(emailMiddlePanel);
     
        emailBottomPanel.add(addEmailButton);
        addEmailButton.addActionListener(new AddEmailListener());
        this.add(emailBottomPanel);
        emailBottomPanel.setVisible(false);
        
    }
    
    public void makeStatic(boolean b){
        for ( Block[] blocks : emailBlock ){
            for ( Block block : blocks ){
                block.makeStatic( b );
            }
        }
        for ( JButton button : deleteEmailButtons ){
            button.setVisible( !b );
        }
        emailBottomPanel.setVisible( !b );
    }

    private void addToEmailBlock(){
        String[] attributes = {"Type:", "Email:"};
        String[] values = {"type", "email"};
        int top = emailBlock.size();
        int numberOfBlocks = 2;
        emailBlock.add(new Block[ numberOfBlocks ]);
        for (int j = 0; j < numberOfBlocks; j++){
             emailBlock.get(top)[j] = new Block();
             emailBlock.get(top)[j].getTitleLabel().setText(attributes[j]);
             emailBlock.get(top)[j].setContent(values[j]);
             emailBlock.get(top)[j].updateBlock();
             if (j == 0){
                 emailBlock.get(top)[j].getTextArea().setColumns(3);
             }
             else{
                 emailBlock.get(top)[j].getTextArea().setColumns(5);
             }
        }
    }
    
    private void addToEmailMiddleList(){
        int size = emailMiddleList.size();
        emailMiddleList.add(new JPanel(new FlowLayout()));
        for ( Block blockPair : emailBlock.get(size) ){
            emailMiddleList.get(size).add(blockPair);
        }
        
        deleteEmailButtons.add(new JButton("X"));
        deleteEmailButtons.get(size).addActionListener(new DeleteEmailListener());
        //deleteEmailButtons.get(size).setVisible(false);
        emailMiddleList.get(size).add(deleteEmailButtons.get(size));
        
    }
        
    public void addEmail(){
        addToEmailBlock();
        addToEmailMiddleList();
        emailMiddlePanel.add(emailMiddleList.get( emailMiddleList.size() - 1 ));
        revalidate();
        repaint();
    }
    
    private void removeEmail(int index){
        deleteEmailButtons.remove( deleteEmailButtons.get(index) );
        emailMiddlePanel.remove( emailMiddleList.get(index) );
        emailMiddleList.remove( emailMiddleList.get(index) );
        emailBlock.remove( emailBlock.get(index) );
        revalidate();
        repaint();
    }
    
    private class AddEmailListener implements ActionListener {

        public AddEmailListener() {
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            addEmail();
        }
    }
    
    private class DeleteEmailListener implements ActionListener {

        public DeleteEmailListener() {
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            int index = deleteEmailButtons.indexOf( ae.getSource() );
            removeEmail(index);
        }
    }
    
}
