/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author mizar
 */
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PhotoView extends JPanel{
    private JLabel label = new JLabel();
    private ImageIcon icon = new ImageIcon();
    private BufferedImage img;
    
    public PhotoView(){
        configure("default.png");
        this.add(label);
        this.setLayout( new FlowLayout() );
    }
    
    public void configure(String value){
        try {
            this.img = ImageIO.read(getClass().getResource("/resources/" + value));
            this.icon.setImage(img);
            label.setIcon(this.icon);
            } catch (Exception ex) { System.out.println(ex);}
    }
}

