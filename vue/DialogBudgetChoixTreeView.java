package vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import controleur.ControlManager;
import controleur.Navigation;
import controleur.NavigationMenu;
import controleur.NavigationMenuAccount;
import model.DataBudget;
import model.DataAccount;

import javax.swing.JTree;
import javax.swing.JScrollPane;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;

import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogBudgetChoixTreeView extends JDialog implements TreeSelectionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ControlManager controlManager;
	private final JPanel contentPanel = new JPanel();
	private DefaultMutableTreeNode racine;
	private boolean sendData;
	private DataBudget budgetSelection;
	JTree  arbre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogBudgetChoixTreeView dialog = new DialogBudgetChoixTreeView(null,0,0);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogBudgetChoixTreeView(ControlManager controlManager,int x, int y) {
		
		this.controlManager=controlManager;
		//setUndecorated(true);
		setResizable(false);
		setModal(true);
		setBounds(x, y, 269, 435);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		budgetSelection=null;
		
		
		initTree();
		initButton();
	}

	/**
	 * 
	 */
	private void initButton() {
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	/**
	 * 
	 */
	private void initTree() {
		
		JScrollPane scrollPane = new JScrollPane();
		contentPanel.add(scrollPane);
		
		racine = new DefaultMutableTreeNode("BUDGET");
		//racine.setUserObject(new NavigationMenu(fl.getString("mnuAccueil"),Navigation.ACCUEIL));
		
		loadTree(racine,0);
		DefaultTreeModel modelTree = new DefaultTreeModel(racine);
		
		
		
		arbre = new JTree(modelTree);
		arbre.setRootVisible(false);
		scrollPane.setViewportView(arbre);
		
		//arbre.addMouseListener(new NavigMousseListener(this));
		arbre.addTreeSelectionListener(this);

		arbre.setRootVisible(true);
		
		arbre.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		
		initPopUp(arbre);
	}

	/**
	 * @param idbudget 
	 * @param node 
	 * 
	 */
	private void loadTree(DefaultMutableTreeNode node, int idbudget) {
		Vector<DataBudget> listDesRubriques;
		listDesRubriques = controlManager.getModelManager().getBudgetRubriques(idbudget);
		DataBudget data;
		for(int i=0;i<listDesRubriques.size();i++){
			data = listDesRubriques.get(i);
			DefaultMutableTreeNode newNode =new DefaultMutableTreeNode(data.getLibelle());
			newNode.setUserObject(data);
			
			node.add(newNode);
			loadTree(newNode,data.getId());
		}
	}

	
	/**
	 * 
	 */
	public DataBudget showDialog() {

		this.sendData = false;
		
		//Début du dialogue
		this.setVisible(true);
		
		
		
		//Le dialogue prend fin
		//Si on a cliqué sur OK, on envoie, sinon on envoie une chaîne vide !
		return (this.sendData)? budgetSelection : null;
		
		
	}
	
	/**
	 * @param arbre
	 */
	private void initPopUp(JTree arbre) {
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(arbre, popupMenu);
		{
			JMenuItem mntmAjouter = new JMenuItem("Ajouter");
			popupMenu.add(mntmAjouter);
		}
		{
			JMenuItem mntmEditer = new JMenuItem("Editer");
			popupMenu.add(mntmEditer);
		}
		{
			JMenuItem mntmSupprimer = new JMenuItem("Supprimer");
			popupMenu.add(mntmSupprimer);
		}
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	@Override
	public void valueChanged(TreeSelectionEvent arg0) {

		if(arbre.getLastSelectedPathComponent() != null){
        	

        	TreePath path = arbre.getSelectionPath();

        	System.out.println(path);
        	DefaultMutableTreeNode node = (DefaultMutableTreeNode) arbre.getLastSelectedPathComponent(); 
        	 
        	budgetSelection = (DataBudget)node.getUserObject();
        	this.sendData = true;
        	System.out.println("budgetselection = "+budgetSelection.getLibelle());
        	 
        	setVisible(false);

        }
		
	}
}
