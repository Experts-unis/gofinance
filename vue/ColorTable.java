/**
 * 
 */
package vue;

import java.awt.Color;

/**
 * @author test
 *
 */
public class ColorTable {
	


	public Color getForeground(int row, int column) {
		// colorie l'écriture des colonnes choisies
		
		return Color.BLACK;
					
	}
		 
	public Color getBackground(int row, int column) {
		if ((row % 2) == 0) {
			return Color.WHITE;
		}
		else 
			return new Color(204, 229, 255);
		
			//return Color.CYAN;
	}

}
