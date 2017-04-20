/**
 * 
 */
package vue;


import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;

import controleur.ControlManager;

/**
 * @author test
 *
 */
public class DialogBase extends JDialog {

	protected boolean ecouter;
	protected boolean sendData;
	protected ControlManager controlManager;
	/**
	 * 
	 */
	public DialogBase() {
		super();
		sendData = false;
	}

	/**
	 * @param owner
	 * @param title
	 * @param modal
	 */
	public DialogBase(ControlManager controlManager,JFrame owner, String title, boolean modal,int w,int h) {
		super(owner, title, modal);

		ecouter=false;
		this.controlManager = controlManager;

		// Centre la Dialogue
		Dimension dimScreen = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, w, h);
		int x = (dimScreen.width / 2) - w/2 ;
		int y = (dimScreen.height / 2) - h/2 -100; 
		Point centreEcran = new Point(x,y);
		this.setLocation(centreEcran);

		//
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				actionQuitter();
			}
		});
		
		sendData = false;
	}

	/**
	 * 
	 */
	protected void actionQuitter() {
	
		setVisible(false);
		
	}
	
	public void showDialog(){

		
		//Début du dialogue
		this.setVisible(true);
		

	}

	/**
	 * @return the controlManager
	 */
	public ControlManager getControlManager() {
		return controlManager;
	}

	/**
	 * @param controlManager the controlManager to set
	 */
	public void setControlManager(ControlManager controlManager) {
		this.controlManager = controlManager;
	}
	

}
