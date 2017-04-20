/**
 * 
 */
package vue;


import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import controleur.ControlManager;
import controleur.Navigation;
import controleur.NavigationMenu;
import controleur.NavigationMenuAccount;
import model.DataAccount;
import model.ModelManager;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;



/**
 * @author AGD
 * 
 * Cette classe définit les éléments graphiques et les évènements
 * de la fenêtre principale.
 *
 */


public class MainView extends JFrame implements WindowListener, ActionListener {

	// Fichier de langue
	private ResourceBundle fl ; 

	// Déclaration du menu et de ses items
	private JMenuBar menuBar;

	// FICHIER
	private JMenu mnuFichier;

	private JMenuItem mnuFichierQuitter;

	// COMPTE
	private JMenu nmuCompte;

	private JMenuItem mnuCompteNew;
	private JMenuItem mnuCompteListe;

	private JMenuItem mnuCompteModif;
	private JMenuItem mnuCompteClose;

	private JMenu mnuCompteVirement;
	private JMenuItem mnuCompteVirementPrelevement;
	private JMenuItem mnuCompteVirementCompteACompte;
	private JMenuItem mnuCompteVirementCompteExterne;

	// OBJECTIFS
	private JMenu mnuObjectifs;
	private JMenuItem mnuObjectifsNew;
	private JMenuItem mnuObjectifsListe;

	// BUDGETS
	private JMenu mnuBudget;
	private JMenuItem mnuBudgetNew;
	private JMenuItem mnuBudgetArchive;


	// PARAMETRAGE
	private JMenu mnuParamPreferences;
	private JMenuItem mnuParamGeneral;

	// AIDE
	private JMenu mnuAide;
	private JMenuItem mnuAideAPropos;

	// DIALOGUE ALERTE
	private JOptionPane dialogAlert ;
	
	// PANNEAU
	private JScrollPane panneaugauche;
	private JScrollPane panneaudroit ;
	private JSplitPane splitPane ;
	PanneauDroitWritingsListView theExpend; 
	PanneauDroitCreatAccountView panCreatAccount;
	private JScrollPane panneauExpend;
	private JScrollPane panneauCreatAccount;
	private JScrollPane panneauObjectifs;
	private JScrollPane panneauAccueil;
	private JScrollPane panneauSolde;
	private JScrollPane panneauBudget;

	
	
	// CTRL Manager
	private ControlManager ctrlManager;

	// GESTION DES COMPTES
	private DialogCreatAccountView fDialogCompte; // Fenêtre de gestion des comptes

	// GESTION DE LA NAVIGATION
	NavigationTreeView navigation;

	
	/**
	 * @throws HeadlessException
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public MainView(ControlManager ctrlManager) throws HeadlessException {
		super();

		this.ctrlManager = ctrlManager;
		
		// langue utilisée pour les messages
		fl = ctrlManager.getLangue(); 



		// fenêtre de navigation
		navigation = null;
		// Init Dialogue Creation compte
		fDialogCompte = null;

		
		initComponent();
		
		// Déclaration des gestionnaires d'évènement de la fenêtre.
		this.addWindowListener(this);


		
	}
	
	private void initComponent() {
		// Definir la taille de la fenêtre à maximum - Elle prend tout l'écran
		setExtendedState(MAXIMIZED_BOTH);

		// Permet d'interdir la redimention  de la fenêtre
		//setResizable(false);

		// Ajouter un titre à la fenêtre
		setTitle(fl.getString("titre"));

		//Termine le processus lorsqu'on clique sur la croix rouge
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Instanciation d'un objet JSplitPane
		panneaugauche = initPanneauGauche();
		panneaudroit = initPanneauDroit();

		
		
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT ,panneaugauche,panneaudroit);
		
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(250);
		
		

		//Provide minimum sizes for the two components in the split pane
		Dimension minimumSize = new Dimension(300, 100);
		panneaugauche.setMinimumSize(minimumSize);
		panneaugauche.setMaximumSize(minimumSize);
		panneaudroit.setMinimumSize(minimumSize);
		
		
		splitPane.setVisible(true);
		add(splitPane);
		
		// Ajouter un menu
		initMenu();

		
	}

	

	private JScrollPane initPanneauGauche() {
		// Navigation
		navigation = new NavigationTreeView(ctrlManager);
		//desktop.add(navigation);
		//pan.add(navigation);
		navigation.setVisible(true);	
		panneaugauche = new JScrollPane(navigation);
		return panneaugauche;
	}
	private JScrollPane initPanneauDroit() {
		
		// Les dépenses
		theExpend = new PanneauDroitWritingsListView (ctrlManager);
		panneauExpend = new JScrollPane(theExpend);
		
		// Création d'un compte obligatoire
		panCreatAccount = new PanneauDroitCreatAccountView();
		panneauCreatAccount = new JScrollPane(panCreatAccount);
	
		
		panneauObjectifs = new JScrollPane(new PanneauDroitObjectifsView());
		panneauAccueil = new JScrollPane(new PanneauDroitHomeView());
		panneauSolde = new JScrollPane(new PanneauDroitSoldeAccountView());
		panneauBudget = new JScrollPane(new PanneauDroitBudgetView());
		
		
		if (ctrlManager.getModelManager().getNbrCompteBancaires() == 0)
			panneaudroit = panneauCreatAccount;
		else{
			panneaudroit = panneauExpend;	
		}
		
		return panneaudroit;
	}


	private JMenu AddMenuFichier()
	{

		// Création du menu fichier

		mnuFichier = new JMenu(fl.getString("mnuFichier"));
		mnuFichier.setMnemonic('F');
		
		mnuFichier.getAccessibleContext().setAccessibleDescription(
                "Blabla...");
		
		//ImageIcon icon = createImage("rc/doc.png");
		//mnuFichierNew = new JMenuItem(fl.getString("mnuFichierNew"),new ImageIcon("src/images/cal-16.png"));
		//mnuFichierNew.setToolTipText( "Ajouter une année au calendrier" ); 

		mnuFichierQuitter = new JMenuItem(fl.getString("mnuFichierQuitter"),new ImageIcon("src/images/sortie-16.png"));
		mnuFichierQuitter.setToolTipText( fl.getString("mnuFichierQuitterBull")); 
		mnuFichierQuitter.setMnemonic('Q');
		
		// Enregistre Le listener pour ce menu
		mnuFichierQuitter.addActionListener(this);
		//mnuFichier.add(mnuFichierNew);
		//mnuFichier.addSeparator();
		mnuFichier.add(mnuFichierQuitter);

		
		return mnuFichier;
	}
	
	private void actionMnuFichierQuitter()
	{
		   System.out.println("Cloture de la fenêtre principale vMain");
		   System.exit(0);	
	}
	
	private void  actionMnuCreatAccount()
	{
		System.out.println("Création d'un nouveau compte bancaire");
		
						
		// Fenêtre de création des comptes
		
		if (fDialogCompte == null){
			fDialogCompte = new DialogCreatAccountView(this,true,fl.getString("dialogCompteTitre"));
		} 
		
		fDialogCompte.refreshDial(true);
		DataAccount infoCompte = fDialogCompte.showDialogAccount(true);
		
		if (infoCompte != null){
			System.out.println("Info Compte transmis "+ infoCompte.toString());
			// Informer le contrôleur de la saisie d'un nouveau compte
			ctrlManager.creatNewAccount(infoCompte);
			AddAccountNavigation(infoCompte);
		}
		else{
			System.out.println("Annulation ");

		}
		//dialogAlert.showMessageDialog(this, "Créer un nouveau compte", "Information", JOptionPane.INFORMATION_MESSAGE);

	}
	public void actionMnuCompteEdit(NavigationMenuAccount infoNode, int index) {
		
		System.out.println("Edite compte bancaire dans MainView " + infoNode.getName());
		
		
		// Fenêtre de création des comptes
		
		if (fDialogCompte == null){
			System.out.println("Edite compte creat Dialog ");
			fDialogCompte = new DialogCreatAccountView(this,false,fl.getString("dialogCompteTitre"));
		} 
		
		DataAccount infoCompte = infoNode.getDataAccount();
		System.out.println("infoCompte " + infoCompte);
		fDialogCompte.loadInfoDial(infoCompte);
		
		 infoCompte = fDialogCompte.showDialogAccount(false);
		
		if (infoCompte != null){
			System.out.println("Info Compte transmis "+ infoCompte.toString());
			// Informer le contrôleur de la modification d'un compte
			ctrlManager.updateAccount(infoCompte);
			infoNode.setDataAccount(infoCompte);
			navigation.setMajAccount(index,infoNode);
		}
		else{
			System.out.println("Annulation ");

		}
		
	}
	private void actionMnuCompteCloture(){
		
	}

	private void actionMnuCompteListe(){
		
		System.out.println("Liste des comptes");
		//TODO Créer une fenètre de synthèse des comptes pour édition d'un rapport
		
		//JOptionPane.showMessageDialog(null, "La liste des comptes", "Information", JOptionPane.INFORMATION_MESSAGE);
	}
	private void actionMnuObjectifsListe(){
		System.out.println("Liste des Objectifs");
		//TODO Créer une fenêtre de synthèse des objectfs
		JOptionPane.showMessageDialog(this, "Liste des Objectifs", "Information", JOptionPane.INFORMATION_MESSAGE);
	}
	private void actionMnuBudgetNew(){
		
		System.out.println("Nouveau Budgets");
		//TODO Créer une fenêtre de synthèse du budget (Liste de rubrique lié à un montant où à un %des revenus)
		JOptionPane.showMessageDialog(this, "Nouveau Budget", "Information", JOptionPane.INFORMATION_MESSAGE);
	}
	private void actionMnuBudgetList(){
		
		System.out.println("Archive des Budgets");
		//TODO Créer une fenêtre de synthèse du budget (Liste de rubrique lié à un montant où à un %des revenus)
		JOptionPane.showMessageDialog(this, "Archive Budget", "Information", JOptionPane.INFORMATION_MESSAGE);
	}
	private void actionMnuParamGeneral(){
		System.out.println("Preferences");
		//TODO Créer une fenêtre des préférences	
		JOptionPane.showMessageDialog(this, "Préférences générales", "Information", JOptionPane.INFORMATION_MESSAGE);
	}
	private void actionMnuAideAPropos(){
		
		System.out.println("Aide");
		//TODO Créer une fenêtre de présentation de l'équipe de développement
		JOptionPane.showMessageDialog(this, "A Propos", "Information", JOptionPane.INFORMATION_MESSAGE);
	}
	    
	
	


	
	private JMenu AddMenuCompte()
	{
		  // Création du menu COMPTE
		  
		  nmuCompte = new JMenu(fl.getString("nmuCompte"));
		  nmuCompte.setMnemonic('C');
		  
		  
		  mnuCompteNew= new JMenuItem(fl.getString("mnuCompteNew"),new ImageIcon("src/images/compte-16.png"));
		  mnuCompteNew.setToolTipText( fl.getString("mnuCompteNewBull")); 
		  mnuCompteNew.setMnemonic('N');
//		  mnuCompteListe = new JMenuItem(fl.getString("mnuCompteListe"),new ImageIcon("src/images/liste-compte-16.png"));
//		  mnuCompteListe.setToolTipText( fl.getString("mnuCompteListeBull")); 
//		  mnuCompteListe.setMnemonic('L');
		  
		 // mnuCompteModif = new JMenuItem(fl.getString("mnuCompteModif"));
		 // mnuCompteClose = new JMenuItem(fl.getString("mnuCompteClose"));

		  /*
		  mnuCompteVirement= new JMenu(fl.getString("mnuCompteVirement"));
		  mnuCompteVirementPrelevement= new JMenuItem(fl.getString("mnuCompteVirementPrelevement"));
		  mnuCompteVirementCompteACompte= new JMenuItem(fl.getString("mnuCompteVirementCompteACompte"));
		  mnuCompteVirementCompteExterne= new JMenuItem(fl.getString("mnuCompteVirementCompteExterne"));
		   */
		  
		  // CONSTRUIRE
		  nmuCompte.add(mnuCompteNew);
	//	  nmuCompte.add(mnuCompteListe);
		  //nmuCompte.add(mnuCompteModif);
		  //nmuCompte.add(mnuCompteClose);
		  
		  /*
		   * nmuCompte.addSeparator();		  
		   
		  mnuCompteVirement.add(mnuCompteVirementPrelevement);
		  mnuCompteVirement.add(mnuCompteVirementCompteACompte);
		  mnuCompteVirement.add(mnuCompteVirementCompteExterne);		  
		  nmuCompte.add(mnuCompteVirement);
		  */
		// Enregistre Le listener pour ce menu
		  mnuCompteNew.addActionListener(this);
		//  mnuCompteListe.addActionListener(this);
			
		return nmuCompte;
	}
	
	private JMenu AddMenuObjectif()
	{
		// CREER
		mnuObjectifs = new JMenu(fl.getString("mnuObjectifs"));
		mnuObjectifs.setMnemonic('O');

		mnuObjectifsNew = new JMenuItem(fl.getString("mnuObjectifsNew"),new ImageIcon("src/images/objectif-creation-16.png"));
		mnuObjectifsNew.setToolTipText( fl.getString("mnuObjectifsNewBull")); 

		mnuObjectifsListe = new JMenuItem(fl.getString("mnuObjectifsView"),new ImageIcon("src/images/objectif-16.png"));
		mnuObjectifsListe.setToolTipText( fl.getString("mnuObjectifsListeBull")); 

		
		// CONSTRUIRE
		mnuObjectifs.add(mnuObjectifsNew);
		mnuObjectifs.add(mnuObjectifsListe);
		

		// Enregistre Le listener pour ce menu
		mnuObjectifsListe.addActionListener(this);
		mnuObjectifsNew.addActionListener(this);
		
		return mnuObjectifs;
	}

	private JMenu AddMenuBudget()
	{
		// CREER
		mnuBudget = new JMenu(fl.getString("mnuBudget"));
		mnuBudget.setMnemonic('B');

		mnuBudgetNew = new JMenuItem(fl.getString("mnuBudgetNew"),new ImageIcon("src/images/budget-16.png"));
		mnuBudgetNew.setToolTipText( fl.getString("mnuBudgetBull")); 
		
		mnuBudgetArchive = new JMenuItem(fl.getString("mnuBudgetArchive"),new ImageIcon("src/images/budget-16.png"));
		mnuBudgetArchive.setToolTipText( fl.getString("mnuBudgetArchiveBull")); 

		// CONSTRUIRE
		mnuBudget.add(mnuBudgetNew);
		mnuBudget.add(mnuBudgetArchive);
		

		// Enregistre Le listener pour ce menu
		mnuBudgetNew.addActionListener(this);
		mnuBudgetArchive.addActionListener(this);
		
		return mnuBudget;

	}
	
	
	private JMenu AddMenuPreferences()
	{
		mnuParamPreferences = new JMenu(fl.getString("mnuParamPreferences"));
		mnuParamPreferences.setMnemonic('P');
		mnuParamGeneral= new JMenuItem(fl.getString("mnuParamGeneral"),new ImageIcon("src/images/pref-16.png"));
		mnuParamPreferences.add(mnuParamGeneral);

		// Enregistre Le listener pour ce menu
		mnuParamGeneral.addActionListener(this);

		return mnuParamPreferences;
	}
	
	private JMenu AddMenuAide()
	{
		//TODO Revoir les accélérateurs dans les menus Mettre une lettre dans le fichier des termes à traduire
		mnuAide = new JMenu(fl.getString("mnuAide"));
		mnuAide.setMnemonic('A');

		mnuAideAPropos = new JMenuItem(fl.getString("mnuAideAPropos"),new ImageIcon("src/images/apropos-16.png"));
		mnuAide.add(mnuAideAPropos);
		// Enregistre Le listener pour ce menu
		mnuAideAPropos.addActionListener(this);

		return mnuAide;
	}
	
	private void initMenu()  {
		// MENU
		menuBar = new JMenuBar();
		
		// FICHIER
		menuBar.add(AddMenuFichier());
		
		// DEPENSE
		//menuBar.add(AddMenuDepense());
		
		// COMPTE
		menuBar.add(AddMenuCompte());
 
		// OBJECTIFS
		menuBar.add(AddMenuObjectif());
		
		// BUDGET
		menuBar.add(AddMenuBudget());
		  
		// PARAMETRAGE
		menuBar.add(AddMenuPreferences());
		  
		// AIDE
		menuBar.add(AddMenuAide());
		  
	    setJMenuBar(menuBar);

	   
	}
	
	// Listener pour les menus
	@Override
    public void actionPerformed(ActionEvent e) {
    
    	Object obj = e.getSource();
    	
    	//System.out.println("actionPerformed " + e.getActionCommand() + " ");

    	if (obj == mnuFichierQuitter) {
    		//System.out.println("actionPerformed QUITTER " );
    		actionMnuFichierQuitter();
    	} else if (obj == mnuCompteNew) {
    		
    		//System.out.println("actionPerformed NEW COMPTE " );
    		actionMnuCreatAccount();
    		
    	} else if (obj == mnuCompteListe) {
    		
    		actionMnuCompteListe();
    		
    	} else if (obj == mnuObjectifsListe) {
    		
    		actionMnuObjectifsListe();
    		
    	} else if (obj == mnuBudgetNew) {
    		
    		actionMnuBudgetNew();
    		
    	} else if (obj == mnuBudgetArchive) {
    		
    		actionMnuBudgetList();
    		
    	}else if (obj == mnuParamGeneral) {
    		
    		actionMnuParamGeneral();
    	} else if (obj == mnuAideAPropos) {
    		
    		actionMnuAideAPropos();
    	} else{
    		System.out.println("ACTION AUTRE " + e.getActionCommand() + " ");
    	}
    	
    	
    }
    
   
    
    
	  public void windowClosing(WindowEvent e)
	  {
		  actionMnuFichierQuitter();

	  }
	   

	  public void windowOpened(WindowEvent e)
	  {
		   System.out.println("Ouverture de la fenêtre principale vMain");
		  
	  }


	  public void windowClosed(WindowEvent e)
	  {
		   System.out.println("Cloture 2 de la fenêtre principale vMain");
		  
	  }

	  public void windowIconified(WindowEvent e)
	  {
		   System.out.println("vMain est mis en icone");
		   
	  }

	  
	  @Override            
	  public void windowDeiconified(WindowEvent e) {
			
		  System.out.println("la fenêtre principale est remise en max");
		   
	  }
	  public void windowActivated(WindowEvent e)
	  {
		   System.out.println("vMain est active");
		   
	  }
	  

	  public void windowDeactivated(WindowEvent e)
	  {
		   System.out.println(" vMain est inactive");
		   
	  }
	public void Show() {

		// On montre la fenêtre
		if (ctrlManager.getModelManager().getNbrCompteBancaires() != 0)
			navigation.selectFirstAccount();
		setVisible(true);
		
	}

	public void SelectPanneauDroite(String menu,NavigationMenu info) {
		
				
		// Aucun compte n'a été créé
		if (ctrlManager.getModelManager().getNbrCompteBancaires() == 0){
			panneaudroit = panneauCreatAccount;
			splitPane.setRightComponent(panneaudroit);
			System.out.println("rapport Creation compte est actif");
			
		}else if (info instanceof NavigationMenuAccount ){
			// On est sur un compte  bancaires 
			theExpend.setCompte(((NavigationMenuAccount) info).getDataAccount());
			panneaudroit = panneauExpend;
			splitPane.setRightComponent(panneaudroit);
			System.out.println("theExpend est actif");
		}else if (info.getNumero() == Navigation.OBJECTIFS){
			panneaudroit = panneauObjectifs;
			splitPane.setRightComponent(panneaudroit);
			System.out.println("Objectifs est actif");
		}else if (info.getNumero() == Navigation.ACCUEIL){
			panneaudroit = panneauAccueil;
			splitPane.setRightComponent(panneaudroit);
			System.out.println("Accueil est actif");
		}else if  (info.getNumero() == Navigation.BUDGET){
			panneaudroit = panneauBudget;
			splitPane.setRightComponent(panneaudroit);
			System.out.println("Budget est actif");
		}

/*		if (panneaudroit == panneauAccueil){
			panneaudroit = panneauSolde;
			splitPane.setRightComponent(panneaudroit);
			System.out.println("rapport Solde est actif");
			
		}*/
		 
			

	}

	/**
	 * @return the fl
	 */
	public ResourceBundle getFichierLangue() {
		return fl;
	}

	/**
	 * @param fl the fl to set
	 */
	public void setFichierLangue(ResourceBundle fl) {
		this.fl = fl;
	}

	/**
	 * @return the ctrlManager
	 */
	public ControlManager getCtrlManager() {
		return ctrlManager;
	}

	/**
	 * @param ctrlManager the ctrlManager to set
	 */
	public void setCtrlManager(ControlManager ctrlManager) {
		this.ctrlManager = ctrlManager;
	}

	public void AddAccountNavigation(DataAccount infoCompte) {
		
		System.out.println("Dans la mainView appel refreshAccount de la navigation");
		// Revoir les noeux comptes de la navigation
		navigation.AddAccount(infoCompte);
	}

	


	

}
