package model;

import java.util.Vector;

import javax.swing.DefaultListModel;

import controleur.ControlManager;

public class modelManagerTiers extends ManagerRefLibelle {

	public modelManagerTiers(ControlManager controlManager) {
		super(controlManager,"Gestion des tiers","Entrer le tiers :");
	//	, "Editer",
	//	"Modifier le tiers"
	}



	/* (non-Javadoc)
	 * @see model.ManagerRefLibelle#add(java.lang.String)
	 */
	@Override
	public DataRefLibelle add(String text) {
		 
		return controlManager.getModelManager().addTiers(text);
	}



	@Override
	public void del(DataRefLibelle element) {
		controlManager.getModelManager().delTiers(element);

	}

	@Override
	public void maj(DataRefLibelle element) {
		controlManager.getModelManager().majTiers(element);

	}

	@Override
	public Vector<DataRefLibelle> load(DefaultListModel<DataRefLibelle> listeModel) {
		
		return controlManager.getModelManager().loadTiers(listeModel);
	}

}
