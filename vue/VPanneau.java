/**
 * 
 */
package vue;

import java.awt.LayoutManager;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
//import javax.swing.JPanel;

/**
 * @author AGD
 *
 */
public class VPanneau extends JPanel {

	/**
	 * 
	 */


	public void paintComponent(Graphics g){
		// Ajouter une image pour le fond d'écran
	    try {
	      Image img = ImageIO.read(new File("src/images/images.jpg"));
	     // g.drawImage(img, 0, 0, this);
	      //Pour une image de fond
	      g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	    } catch (IOException e) {
	      e.printStackTrace();
	    }                
	  }

}

