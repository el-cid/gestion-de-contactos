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
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class Image extends JLabel{
    private ImageIcon icon = new ImageIcon();
    private BufferedImage img;
    
    public Image(){
        configure("default.png");
    }
    
    public void configure(String value){
        try {
            this.img = ImageIO.read(getClass().getResource("/resources/" + value));
            this.icon.setImage(img);
            this.setIcon(this.icon);
            } catch (Exception ex) { System.out.println(ex);}
    }
}
