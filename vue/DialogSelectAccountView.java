package vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;


import javax.swing.JDialog;
import javax.swing.JPanel;


import controleur.ControlManager;
import model.modelManagerAccount;


public class DialogSelectAccountView extends ListeSearchView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogSelectAccountView dialog = new DialogSelectAccountView(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogSelectAccountView(ControlManager controlManager) {
		super(new modelManagerAccount(controlManager),null);
		
		setVisibleEdit(false);
		setVisibleSelect(false);
		setVisibleSupp(false);
		setVisibleAjouter(false);
		load();

	}

}
