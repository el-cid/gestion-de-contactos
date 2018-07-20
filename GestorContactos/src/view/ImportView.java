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
import java.io.File;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author mizar
 */
public class ImportView extends JPanel{
    private Block userInput = new Block();
    private JButton button = new JButton("Importar");
    private JButton returnButton = new JButton("Regresar");
    public ImportView(){
        JPanel headerPanel = new JPanel( new FlowLayout() );
        JLabel headerLabel = new JLabel( "Importar archivo" );
        headerPanel.add( headerLabel );
        userInput.getTitleLabel().setText("Ruta del archivo:");
        userInput.setContent("arrastrar aquí");
        userInput.updateBlock();
        userInput.getTextArea().setColumns(20);
        userInput.getTextArea().setRows(20);
        userInput.makeStatic(false);
        userInput.getTextArea().setDropTarget(new DropTarget() {
            public synchronized void drop(DropTargetDropEvent evt) {
                try {
                    evt.acceptDrop(DnDConstants.ACTION_COPY);
                    List<File> droppedFiles = (List<File>) evt
                            .getTransferable().getTransferData(
                                    DataFlavor.javaFileListFlavor);
                    for (File file : droppedFiles) {
                        userInput.getTextArea().setText(file.getAbsolutePath());
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        
        JPanel bottomPanel = new JPanel( new GridLayout(1,2) );
        JPanel firstButtonPanel = new JPanel( new FlowLayout() );
        firstButtonPanel.add(button);
        JPanel secondButtonPanel = new JPanel( new FlowLayout() );
        secondButtonPanel.add(returnButton);
        bottomPanel.add( firstButtonPanel );
        bottomPanel.add( secondButtonPanel );
        this.setLayout( new BoxLayout(this, BoxLayout.Y_AXIS) );
        this.add( headerPanel );
        this.add( userInput );
        this.add( bottomPanel );
    }
    
    public JButton getReturnButton(){
        return this.returnButton;
    }
}
