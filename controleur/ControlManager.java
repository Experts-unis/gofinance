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
		
		// Charge le fichier du paramétrage de l'application
		config = ResourceBundle.getBundle("domaine.properties.config"); 
		
		// Charge le fichier des strings en fonction du paramétrage de l'application
		String prefLangue = config.getString("langue.preference"); //"domaine.properties.langue";
		fichierDesChainesTraduites =  ResourceBundle.getBundle(prefLangue);



		// Création du model principal
		model = new ModelManager(this);
		
		// Création de la fenêtre principale
		main = new MainView(this);
		
		// Lance la fenêtre principal		
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
		
		// Traitement sur les données métiers
		infoCompte.setLibelle(infoCompte.getLibelle().toUpperCase());
		
		// Informer le model qu'un nouveau compte a été créé
		model.creatAccount(infoCompte);
		
	}
	public void updateAccount(DataAccount infoCompte) {
		// Traitement sur les données métiers
		infoCompte.setLibelle(infoCompte.getLibelle().toUpperCase());

		// Informer le model qu'un compte est à modifier
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
