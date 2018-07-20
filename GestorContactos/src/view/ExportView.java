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
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author mizar
 */
public class ExportView extends JPanel{
    private Block fileName = new Block();
    private Block fileDirectory = new Block();
    private JButton returnButton = new JButton("Regresar");
    private JButton buttonExportar = new JButton("Exportar");
    private JButton buttonSeleccionar = new JButton("Seleccionar ruta y nombre del archivo");
    public ExportView(){
        JPanel headerPanel = new JPanel( new FlowLayout() );
        JLabel headerLabel = new JLabel( "Exportar archivo" );
        headerPanel.add( headerLabel );
        
        JPanel secondPanel = new JPanel( new FlowLayout() );
        secondPanel.add( buttonSeleccionar );
        
        JPanel filePanel = new JPanel(new GridLayout(1,2));
        fileDirectory.getTitleLabel().setText("Ruta del archivo:");
        fileDirectory.getContentLabel().setText("");
        fileDirectory.makeStatic(true);
        fileName.getTitleLabel().setText("Nombre del archivo:");
        fileName.getContentLabel().setText("");
        fileName.makeStatic(true);
        filePanel.add( fileDirectory );
        filePanel.add( fileName );
        
        JPanel bottomPanel = new JPanel( new GridLayout(1,2) );
        JPanel firstButtonPanel = new JPanel( new FlowLayout() );
        firstButtonPanel.add(buttonExportar);
        JPanel secondButtonPanel = new JPanel( new FlowLayout() );
        secondButtonPanel.add(returnButton);
        bottomPanel.add( firstButtonPanel );
        bottomPanel.add( secondButtonPanel );
        
        buttonSeleccionar.addActionListener(new SaveL());
        
        this.setLayout( new BoxLayout(this, BoxLayout.Y_AXIS) );
        this.add( headerPanel );
        this.add( secondPanel );
        this.add( filePanel );
        this.add( bottomPanel );
    }
    
    public JButton getReturnButton(){
        return this.returnButton;
    }
    
    class SaveL implements ActionListener {
    
    public void actionPerformed(ActionEvent e) {
      JFileChooser c = new JFileChooser();
      // Demonstrate "Save" dialog:
      int rVal = c.showSaveDialog( null );
      if (rVal == JFileChooser.APPROVE_OPTION) {
        fileName.getContentLabel().setText(c.getSelectedFile().getName());
        fileDirectory.getContentLabel().setText(c.getCurrentDirectory().toString());
      }
      if (rVal == JFileChooser.CANCEL_OPTION) {
        fileName.getContentLabel().setText("You pressed cancel");
        fileDirectory.getContentLabel().setText("");
      }
    }
  }
}
