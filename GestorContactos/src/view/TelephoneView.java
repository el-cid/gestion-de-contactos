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
public class TelephoneView extends JPanel{
   private JPanel telephoneTopPanel = new JPanel(new FlowLayout());
   private JPanel telephoneMiddlePanel = new JPanel();
   private JPanel telephoneBottomPanel = new JPanel(new FlowLayout());
   private JLabel header = new JLabel("Teléfono:");
   private JButton addTelephoneButton = new JButton("+");
   private ArrayList<InteractiveBlock[]> telephoneBlock = new ArrayList<InteractiveBlock[]>();
   private ArrayList<JButton> deleteTelephoneButtons = new ArrayList<JButton>();
   private ArrayList<JPanel> telephoneMiddleList = new ArrayList<JPanel>();
   // Constructor to setup the GUI components and event handlers
    public TelephoneView() {

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        telephoneTopPanel.add( header );
        this.add( telephoneTopPanel );
        telephoneMiddlePanel.setLayout(new BoxLayout(telephoneMiddlePanel,BoxLayout.Y_AXIS));
        this.add(telephoneMiddlePanel);
        telephoneBottomPanel.add(addTelephoneButton);
        addTelephoneButton.addActionListener(new AddTelephoneListener());
        this.add(telephoneBottomPanel);
        
    }
    
    private void addToTelephoneBlock(){
       String[] attributes = {"Type:", "Número:"};
       String[] values = {"type", "número"};
       
           int top = telephoneBlock.size();
           telephoneBlock.add(new InteractiveBlock[2]);
           for (int j = 0; j < 2; j++){
                telephoneBlock.get(top)[j] = new InteractiveBlock();
                telephoneBlock.get(top)[j].getTitleLabel().setText(attributes[j]);
                if (j == 0){
                    telephoneBlock.get(top)[j].getTextArea().setColumns(3);
                }
                else{
                    telephoneBlock.get(top)[j].getTextArea().setColumns(5);
                }
                
                telephoneBlock.get(top)[j].getTextArea().setText(values[j]);
           }
       }
    
    private void addToTelephoneMiddleList(){
        int size = telephoneMiddleList.size();
        telephoneMiddleList.add(new JPanel(new FlowLayout()));
        for ( InteractiveBlock blockPair : telephoneBlock.get(size) ){
            telephoneMiddleList.get(size).add(blockPair);
        }
        deleteTelephoneButtons.add(new JButton("X"));
        deleteTelephoneButtons.get(size).addActionListener(new DeleteTelephoneListener());
        telephoneMiddleList.get(size).add(deleteTelephoneButtons.get(size));
    }
        
    public void addTelephone(){
        addToTelephoneBlock();
        addToTelephoneMiddleList();
        telephoneMiddlePanel.add(telephoneMiddleList.get( telephoneMiddleList.size() - 1 ));
        revalidate();
        repaint();
    }
    
    private void removeTelephone(int index){
        deleteTelephoneButtons.remove( deleteTelephoneButtons.get(index) );
        telephoneMiddlePanel.remove( telephoneMiddleList.get(index) );
        telephoneMiddleList.remove( telephoneMiddleList.get(index) );
        telephoneBlock.remove( telephoneBlock.get(index) );
        revalidate();
        repaint();
    }
    
    private class AddTelephoneListener implements ActionListener {

        public AddTelephoneListener() {
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            addTelephone();
        }
    }
    
    private class DeleteTelephoneListener implements ActionListener {

        public DeleteTelephoneListener() {
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            int index = deleteTelephoneButtons.indexOf( ae.getSource() );
            removeTelephone(index);
        }
    }
    
}
