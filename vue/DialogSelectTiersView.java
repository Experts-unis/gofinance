package vue;



import controleur.ControlManager;
import model.modelManagerTiers;


public class DialogSelectTiersView extends ListeSearchView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ControlManager controlManager;

	/**
	 * 
	 */
	public DialogSelectTiersView(ControlManager controlManager) {
		super(new modelManagerTiers(controlManager),null);
		
		load();
		
	}



}
