/**
 * 
 */
package controleur;

import java.sql.SQLException;
import java.util.ResourceBundle;

import model.DataAccount;
import model.ModelManager;
import vue.MainView;

/**
 * @author AGD
 * 
 */
public class ControlManager {

	static MainView main ;
	static public ResourceBundle config;
	static public ResourceBundle fichierDesChainesTraduites ;
	static ModelManager model;
	/**
	 * @throws SQLException 
	 * 
	 */
	public ControlManager() throws SQLException {
		
		// Charge le fichier du param�trage de l'application
		config = ResourceBundle.getBundle("domaine.properties.config"); 
		
		// Charge le fichier des strings en fonction du param�trage de l'application
		String prefLangue = config.getString("langue.preference"); //"domaine.properties.langue";
		fichierDesChainesTraduites =  ResourceBundle.getBundle(prefLangue);



		// Cr�ation du model principal
		model = new ModelManager(this);
		
		// Cr�ation de la fen�tre principale
		main = new MainView(this);
		
		// Lance la fen�tre principal		
		main.Show();
		
	}
	public ResourceBundle getLangue() {
		
		return fichierDesChainesTraduites;
	}
	public ModelManager getModelManager() {
		
		return model;
	}
	public void creatNewAccount(DataAccount infoCompte) {
		
		System.out.println("setNewAccount dans ctrlManager");
		
		// Traitement sur les donn�es m�tiers
		infoCompte.setLibelle(infoCompte.getLibelle().toUpperCase());
		
		// Informer le model qu'un nouveau compte a �t� cr��
		model.creatAccount(infoCompte);
		
	}
	public void updateAccount(DataAccount infoCompte) {
		// Traitement sur les donn�es m�tiers
		infoCompte.setLibelle(infoCompte.getLibelle().toUpperCase());

		// Informer le model qu'un compte est � modifier
		model.updateAccount(infoCompte);
		
	}
	public void deleteAccount(DataAccount info) {
		
		// Demande au modelManager la suppression des informations du compte
		model.deleteAccount(info);
	}
	public MainView getMainView() {
		
		return main;
	}



}
