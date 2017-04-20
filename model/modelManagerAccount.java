/**
 * 
 */
package model;

import java.util.Vector;

import javax.swing.DefaultListModel;

import controleur.ControlManager;

/**
 * @author test
 *
 */
public class modelManagerAccount extends ManagerRefLibelle {

	/**
	 * @param controlManager
	 * @param title
	 * @param lbt
	 */
	public modelManagerAccount(ControlManager controlManager) {
		super(controlManager, "Selection d'un compte", "");
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see model.ManagerRefLibelle#add(java.lang.String)
	 */
	@Override
	public DataRefLibelle add(String text) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see model.ManagerRefLibelle#del(model.DataRefLibelle)
	 */
	@Override
	public void del(DataRefLibelle element) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see model.ManagerRefLibelle#maj(model.DataRefLibelle)
	 */
	@Override
	public void maj(DataRefLibelle element) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see model.ManagerRefLibelle#load(javax.swing.DefaultListModel)
	 */
	@Override
	public Vector<DataRefLibelle> load(DefaultListModel<DataRefLibelle> listeModel) {
		
		return controlManager.getModelManager().loadAccount(listeModel);
	}

}
