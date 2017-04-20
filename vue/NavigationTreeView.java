package vue;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;


import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
//import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import controleur.ControlManager;
import controleur.Navigation;
import controleur.NavigationMenu;
import controleur.NavigationMenuAccount;
import model.DataAccount;
import model.ModelManager;


import javax.swing.JScrollPane;

public class NavigationTreeView extends JPanel implements TreeSelectionListener, ActionListener {



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Fichier de langue
	private ResourceBundle fl ; 
	
	// ARBRE
	private DefaultMutableTreeNode racine;
	private DefaultMutableTreeNode brancheObjectifs;
	private DefaultMutableTreeNode brancheComptesBancaires;
	private DefaultMutableTreeNode brancheBudgets;
	private DefaultMutableTreeNode brancheRapports;
	protected JTree arbre;
	private DefaultTreeModel modelTree;
	
	// CTRL
	private ControlManager ctrlManager;
	private ModelManager modelManager ;
	
	// POPUP MENU
	// Le popup menu des comptes
	 //La déclaration pour le menu contextuel 
	private JPopupMenu popUpAccount = new JPopupMenu();
	private JMenuItem mnuSupp;   
	private JMenuItem mnuClos; 
	private JMenuItem mnuMaj; 
	
	// DIALOGUE ALERTE
	private JOptionPane dialogAlert ;

	// LISTE DES COMPTES BANACAIRES
	List<DataAccount> listDesComptes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NavigationTreeView frame = new NavigationTreeView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public NavigationTreeView() {
		
	}
	/**
	 * Create the frame.
	 */
	public NavigationTreeView(ControlManager ctrlManager) {
		
		this.ctrlManager = ctrlManager;
		this.modelManager = ctrlManager.getModelManager();
		this.fl = ctrlManager.getLangue();
		

		
		//setName("Navigation");
		//setBounds(0, 0, 300, 500);
		setLayout(null);

	    initTree();
	    
		// init popup		
		initPopUpAccount();

	


		//scrollPane.

	}

	/**
	 * 
	 */
	private void initPopUpAccount() {
		
		mnuClos = new JMenuItem(fl.getString("mnuCloturerCB"));
		mnuClos.addActionListener(this);
		mnuMaj = new JMenuItem(fl.getString("mnuEditerCB"));
		mnuMaj.addActionListener(this);
		mnuSupp = new JMenuItem("Supprimer");      
		mnuSupp.addActionListener(this);
		
		// Construction du popUp menu des comptes
		popUpAccount.add(mnuMaj);
		popUpAccount.add(mnuSupp);
		popUpAccount.add(mnuClos);
	}


	/**
	 * 
	 */
	private void initTreeRapport(DefaultMutableTreeNode racine) {
		brancheRapports = new DefaultMutableTreeNode("Rapports");
		brancheRapports.add(new DefaultMutableTreeNode("Solde des comptes"));
		brancheRapports.add(new DefaultMutableTreeNode("Détail des dépenses"));
		brancheRapports.add(new DefaultMutableTreeNode("Suivi budgétaire"));
		brancheRapports.add(new DefaultMutableTreeNode("Balance"));
		brancheRapports.add(new DefaultMutableTreeNode("Détail des bénéficiaires"));
		// racine.add( brancheRapports);

	}

	/**
	 * 
	 */
	private void initTreeBudget(DefaultMutableTreeNode racine) {
		brancheBudgets = new DefaultMutableTreeNode(fl.getString("mnuBudget"));
		brancheBudgets.setUserObject(new NavigationMenu(fl.getString("mnuBudget"),Navigation.BUDGET));
	    racine.add(brancheBudgets);

	}

	/**
	 * 
	 */
	private void initTreeAccount(DefaultMutableTreeNode racine) {
		
		
		brancheComptesBancaires = new DefaultMutableTreeNode(fl.getString("mnuAccount"));
		brancheComptesBancaires.setUserObject(new NavigationMenu(fl.getString("mnuAccount"),Navigation.COMPTE_BANCAIRE_BRANCHE));
	    
	    listDesComptes = modelManager.getAccountList();
	    DataAccount info;
	    
	    for(int i=0;i<listDesComptes.size();i++){
	    	info = listDesComptes.get(i);
	    	DefaultMutableTreeNode node =new DefaultMutableTreeNode(info.getLibelle());
	    	
	    	node.setUserObject(new NavigationMenuAccount(info.getLibelle(),Navigation.COMPTE_BANCAIRE,info));
	    	brancheComptesBancaires.add(node);
	    
	    }
	    
	    racine.add(brancheComptesBancaires);

	}

	/**
	 * 
	 */
	private void initTreeObjectifs(DefaultMutableTreeNode racine) {
		
		brancheObjectifs =  new DefaultMutableTreeNode(fl.getString("mnuObjectifs"));
		brancheObjectifs.setUserObject(new NavigationMenu(fl.getString("mnuObjectifs"),Navigation.OBJECTIFS));
		racine.add(brancheObjectifs);
	}

	/**
	 * 
	 */
	private void initTree() {

		
		
		racine = new DefaultMutableTreeNode(fl.getString("mnuAccueil"));
		racine.setUserObject(new NavigationMenu(fl.getString("mnuAccueil"),Navigation.ACCUEIL));

		initTreeObjectifs(racine);
		initTreeAccount(racine);
		initTreeBudget(racine);
		initTreeRapport(racine);

		modelTree = new DefaultTreeModel(racine);
		arbre = new JTree(modelTree);
		arbre.addMouseListener(new NavigMousseListener(this));
		arbre.addTreeSelectionListener(this);

		arbre.setRootVisible(true);
		
		arbre.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		// arbre.setBackground(Color.LIGHT_GRAY);
		arbre.setName("Navigation");

		// arbre.setSize(200, 800);

		// int row= arbre.getRowCount();
		//arbre.expandRow(row-1);
		//arbre.expandPath(null);
		// ouvre totalement l'arbre
		expandTree(arbre, true);

		// arbre.collapsePath(null);
		// Cherche le premier compte bancaire et le sélectionner

		JScrollPane scrollPane = new JScrollPane(arbre);
		scrollPane.setBounds(0, 0, 400, 1000);
		add(scrollPane);

		//scrollPane.setViewportView(arbre);
	}
	
	public void valueChanged(TreeSelectionEvent event) {


        selectPanneauDroit();

      }

	/**
	 * 
	 */
	private void selectPanneauDroit() {
		if(arbre.getLastSelectedPathComponent() != null){
        	

        	TreePath path = arbre.getSelectionPath();

        	DefaultMutableTreeNode node = (DefaultMutableTreeNode) arbre.getLastSelectedPathComponent(); 
        	NavigationMenu info = (NavigationMenu)node.getUserObject();

        	String menu ="";
        	menu = arbre.getLastSelectedPathComponent().toString();

        	ctrlManager.getMainView().SelectPanneauDroite(menu,info);

        }
	}
	
	/**
	 * @param popUpAccount the popUpAccount to show
	 */
	public void showPopUpAccount(MouseEvent event) {
		

		int selRow = arbre.getRowForLocation(event.getX(), event.getY());
		TreePath selPath = arbre.getPathForLocation(event.getX(), event.getY());

		if (selRow != -1) {

			arbre.clearSelection(); 
			arbre.setSelectionPath(selPath);
			
        	DefaultMutableTreeNode node = (DefaultMutableTreeNode) arbre.getLastSelectedPathComponent(); 
			Object info = node.getUserObject();

			
			if (info instanceof NavigationMenuAccount ){
				//La méthode qui va afficher le menu
				popUpAccount.show(arbre, event.getX()+10, event.getY());
			}

		}
	}
	
	// Listener pour le popup menus
	@Override
	public void actionPerformed(ActionEvent e) {

		Object obj = e.getSource();

		//System.out.println("actionPerformed " + e.getActionCommand() + " ");

		if (obj == mnuMaj) {
			//System.out.println("actionPerformed QUITTER " );
			actionMnuEdit();
		} else if (obj == mnuClos) {

			//System.out.println("actionPerformed NEW COMPTE " );
			actionMnuClos();

		} else if (obj == mnuSupp) {

			actionMnuSupp();

		}
		
	}
	
	private void actionMnuSupp() {
		
		DataAccount info = remouveNode();
		// Demande au ctrlManager la suppression du compte en base
		ctrlManager.deleteAccount(info);
		
	}

	/**
	 * @return
	 */
	private DataAccount remouveNode() {
		
		// Un noeud a été selectionné pour activer le popup menu
		TreePath selPath = arbre.getSelectionPath();
		
		// Retrouve le noeud selectionné
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) arbre.getLastSelectedPathComponent(); 
		
		// Reprend les informations liées au noeud sélectionné
		NavigationMenuAccount infoNode = (NavigationMenuAccount)node.getUserObject();
		DataAccount info = infoNode.getDataAccount();
		
		// Demande au model du JTree de supprimer le noeud
		modelTree.removeNodeFromParent(node);
		
		return info;
	}

	private void actionMnuClos() {
		// TODO Cloturer un compte
		dialogAlert.showMessageDialog(this, "Clôturer un compte", "Information", JOptionPane.INFORMATION_MESSAGE);
		
	}

	private void actionMnuEdit() {
		
		// Un noeud a été selectionné pour activer le popup menu Editer
		TreePath selPath = arbre.getSelectionPath();
		// Retrouve le noeud selectionné
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) arbre.getLastSelectedPathComponent(); 
		// Reprend l'index du noeud selectionné
		int index = arbre.getRowForPath(selPath);
		// Reprend les informations liées au noeud sélectionné
		NavigationMenuAccount infoNode = (NavigationMenuAccount)node.getUserObject();
		DataAccount info = infoNode.getDataAccount();

		System.out.println("EDIT MENU index = "+index+" infonNde"+infoNode + " infoCompte "+info);

		ctrlManager.getMainView().actionMnuCompteEdit(infoNode,index); 
	//	dialogAlert.showMessageDialog(this, "Editer un compte", "Information", JOptionPane.INFORMATION_MESSAGE);
		
		
	}

	private void expandAll(JTree tree, TreePath path, boolean expand) {
        
		
		TreeNode node = (TreeNode) path.getLastPathComponent();

		// Sur ce noeud il existe des feuilles
        if (node.getChildCount() >= 0) {
        	// Reprend la liste des feuilles
            Enumeration enumeration = node.children();
            // Pour tous les éléments de la liste 
            while (enumeration.hasMoreElements()) {
            	
                TreeNode n = (TreeNode) enumeration.nextElement();
                TreePath p = path.pathByAddingChild(n);
                // Demande d'ouverture / Fermeture du noeud et de ses descendants
                expandAll(tree, p, expand);
            }
        }

        // Execute l'ouverture ou la fermeture du noeud
        if (expand) {
            tree.expandPath(path);
        } else {
            tree.collapsePath(path);
        }
    }

	private void expandTree(JTree tree, boolean expand) {
        
		TreeNode root = (TreeNode) tree.getModel().getRoot();
		
        expandAll(tree, new TreePath(root), expand);
    }

	/**
	 * Mettre à jour le Jtree lorsque l'on ajoute un compte
	 * @param infoCompte
	 */
	public void AddAccount(DataAccount infoCompte) {

		addNode(new NavigationMenuAccount(infoCompte.getLibelle(),Navigation.COMPTE_BANCAIRE,infoCompte),brancheComptesBancaires.getChildCount());
	}

	/**
	 * @param infoCompte
	 */
	private void addNode(NavigationMenuAccount infoNode, int index) {
		System.out.println("Dans la navigationTreeView ajoute un compte");
		
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(infoNode.getName());
		node.setUserObject(infoNode);
		
		modelTree.insertNodeInto(node, brancheComptesBancaires, brancheComptesBancaires.getChildCount());
	}

	public void setMajAccount(int index, NavigationMenuAccount infoNode) {
		// Supprime l'ancien noeud 
		remouveNode();
		// Ajoute un noeud au même endroit
		addNode(infoNode,index);
		
	}

	public void selectFirstAccount() {
		
		
		TreeNode node =brancheComptesBancaires.getFirstChild();
		TreePath selPath = new TreePath(node);
		arbre.setSelectionPath(selPath);
		
		selectPanneauDroit();
		
		
	}


}
