/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author mizar
 */
public class ExportView extends JPanel{
    private StaticBlock fileName = new StaticBlock();
    private StaticBlock fileDirectory = new StaticBlock();
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
        fileName.getTitleLabel().setText("Nombre del archivo:");
        fileName.getContentLabel().setText("");
        filePanel.add( fileDirectory );
        filePanel.add( fileName );
        
        JPanel bottomPanel = new JPanel( new FlowLayout() );
        bottomPanel.add( buttonExportar );
        
        buttonSeleccionar.addActionListener(new SaveL());
        
        this.setLayout( new BoxLayout(this, BoxLayout.Y_AXIS) );
        this.add( headerPanel );
        this.add( secondPanel );
        this.add( filePanel );
        this.add( bottomPanel );
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
