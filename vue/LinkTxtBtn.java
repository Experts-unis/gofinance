/**
 * 
 */
package vue;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JTextField;

/**
 * @author test
 *
 */
public class LinkTxtBtn {
	protected JComponent txt;
	protected JButton btn;
	Point pDialParent;
	/**
	 * @param txtDate
	 * @param btnCalDate
	 */
	public LinkTxtBtn(Point p, JComponent txt, JButton btn) {
		super();
		this.txt = txt;
		this.btn = btn;
		this.pDialParent = p;
		

		
		this.btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 showDial();
			}
		});
	}
	
	/**
	 * 
	 */
	public void showDial() {
		//txtDate.setBounds(25, 36, 174, 20);
		try {
			
			
			Point ptxt = txt.getLocation();
			//Point p=;
			int x = pDialParent.x+ptxt.x;
			int y = pDialParent.y+30+ptxt.y+20;
			
			CallDial(x,y);

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void CallDial(int x, int y) {
		// A redéfinir pour créer la boite de dialog
		
	}

}
