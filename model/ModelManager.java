/**
 * 
 */
package model;

import java.sql.SQLException;

import java.util.List;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;


import controleur.ControlManager;


/**
 * @author test
 *
 */
public class ModelManager {

	ControlManager ctrlManager;
	DB driver;
	TCompteModel tcompte;
	private int nbrCompteBancaires;
	private TRefLibelleModel trefType;
	private TRefLibelleModel trefMoyenPaiement;
	private TBudgetRubriqueModel tBudgetRubriques;
	private TRefLibelleModel trefRevenu;
	private TRefLibelleModel trefTiers;
	private TEcrituresModel tEcritures;
	

	/**
	 * @throws SQLException 
	 * 
	 */
	public ModelManager(ControlManager ctrlManager) throws SQLException {
		
		driver = new DBProgres();
		tcompte = new TCompteModel(driver);
		tEcritures = new TEcrituresModel(driver);
		
		tBudgetRubriques = new TBudgetRubriqueModel(driver);
		trefMoyenPaiement = new TRefLibelleModel("TREF_MOYENPAIEMENT", driver);
		trefType = new TRefLibelleModel("TREF_TYPE", driver);
		trefRevenu = new TRefLibelleModel("TREF_REVENU", driver);
		trefTiers = new TRefLibelleModel("TREF_TIERS", driver);
		
		this.ctrlManager = ctrlManager;
		nbrCompteBancaires=0;
	}
	/**
	 * @return la liste des comptes bancaires
	 */
	public List<DataAccount> getAccountList() {
		
		List<DataAccount> liste = tcompte.getList();
		
		setNbrCompteBancaires(liste.size());

		return liste;
		
	}
	/**
	 * @return the nbrCompteBancaires
	 */
	public int getNbrCompteBancaires() {
		return nbrCompteBancaires;
	}
	/**
	 * @param nbrCompteBancaires the nbrCompteBancaires to set
	 */
	private void setNbrCompteBancaires(int nbrCompteBancaires) {
		this.nbrCompteBancaires = nbrCompteBancaires;
	}
	
	public void creatAccount(DataAccount infoCompte) {
		System.out.println("setNewAccount dans le modelManager");
		
		// Insertion dans la table tcompte de la base de données
		infoCompte.setId(tcompte.add(infoCompte.getLibelle(),  infoCompte.getSolde().toString()));
		++nbrCompteBancaires;
		
	
	}
	public void updateAccount(DataAccount infoCompte) {
		
		// Modification dans la table tcompte de la base de données
		tcompte.maj(infoCompte.getId(),infoCompte.getLibelle(),  infoCompte.getSolde().toString());
		
	}
	public void deleteAccount(DataAccount infoCompte) {
		
		// Demande à la table de supprimer les informations liées au compte
		tcompte.del(infoCompte.getId());
		--nbrCompteBancaires;
		
	}

	public void loadRefType(JComboBox<DataRefLibelle> cmbType) {

		Vector<DataRefLibelle> liste ;

		liste = trefType.getList();
		copyVectorInComboBox(cmbType,liste);
		
	}
	private void copyVectorInComboBox(JComboBox<DataRefLibelle> cmb, Vector<DataRefLibelle> liste) {
		
		for (int i=0;i<liste.size();i++)
			cmb.addItem(liste.get(i));	
		
	}
	private  void copyVectorInListModel(DefaultListModel<DataRefLibelle> listeModel, Vector<DataRefLibelle> liste) {
		
		listeModel.clear();
		for (int i=0;i<liste.size();i++)
			listeModel.addElement(liste.get(i));
	}
	
	public void loadRefMoyenPaiement(JComboBox<DataRefLibelle> cmb) {

		Vector<DataRefLibelle> liste ;

		liste = trefMoyenPaiement.getList();
		copyVectorInComboBox(cmb,liste);
		
	}
	public Vector<DataBudget> getBudgetRubriques(int idBudget) {
		
		return tBudgetRubriques.getList(idBudget);

	}
	
	public Vector<DataBudget> loadBudgetRubriques() {
		
		return tBudgetRubriques.getList();

	}

	
	public void loadRefRevenu(JComboBox<DataRefLibelle> cmbRevenu) {

		Vector<DataRefLibelle> liste ;

		liste = trefRevenu.getList();
		copyVectorInComboBox(cmbRevenu,liste);
		
	}
	public Vector<DataRefLibelle> loadTiers(DefaultListModel<DataRefLibelle> listeModel) {
		Vector<DataRefLibelle> liste ;

		liste = trefTiers.getList();
		copyVectorInListModel(listeModel,liste);
		
		return liste;
		
	}
	
	public DataRefLibelle addTiers(String text) {
		
		return   trefTiers.add(text);
		
	}
	
	public void delTiers(DataRefLibelle value) {
		
		 trefTiers.del(value.getId());
		
	}
	
	public void majTiers(DataRefLibelle elementModif) {
		
		 trefTiers.maj(elementModif.getId(), elementModif.getLibelle());
	}
	
	public Vector<DataRefLibelle> loadAccount(DefaultListModel<DataRefLibelle> listeModel) {

		Vector<DataAccount> listeAccount = tcompte.getList();
		Vector<DataRefLibelle> listeRefLib = new Vector<DataRefLibelle>();
		
		DataRefLibelle donnee;
		
		
		listeModel.clear();
		for (int i=0;i<listeAccount.size();i++){
			donnee = (DataRefLibelle)listeAccount.get(i);
			listeRefLib.add(donnee); 
			listeModel.addElement(donnee);
		}
		

		
		return listeRefLib;
	}
	
	public Vector<DataWrinting> loadDataWritting() {
		
		//"Num","Date","Débit","Budget","Crédit","Revenu","Type","Tiers","Note","Rapprochée"
		//TODO Lire la table Ecritures de la base de données
		
		Vector<DataWrinting> liste =  tEcritures.getList();
		
		// Charge les libellés
		for (DataWrinting element : liste){
			majLibDataWritting(element);
			
			
		}
		
		return liste;
	}
	/**
	 * @param element
	 */
	public void majLibDataWritting(DataWrinting element) {
		element.setLibBudget(tBudgetRubriques.search(element.getIdBudget()));
		element.setLibRevenu(trefRevenu.search(element.getIdRevenu()));
		element.setLibType((trefType.search(element.getIdType())));
		element.setLibTiers((trefTiers.search(element.getIdTiers())));	
		element.setLibMoyenPaiement(trefMoyenPaiement.search(element.getIdMoyenPaiement()));
	}
	public void addWrittings(DataWrinting value) {
		
		tEcritures.add(value);
		majLibDataWritting(value);
	}


	
}
