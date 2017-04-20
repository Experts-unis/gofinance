package vue;


import java.awt.EventQueue;


import javax.swing.JTable;

import javax.swing.JScrollPane;
import javax.swing.JButton;


import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import controleur.ControlManager;
import model.DataAccount;
import model.DataWrinting;
import model.TableModelWritingsList;

import javax.swing.ListSelectionModel;


import javax.swing.border.EmptyBorder;
import java.awt.Font;

import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ResourceBundle;

import javax.swing.JMenuItem;

public class PanneauDroitWritingsListView extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tableau;
	private JButton btnNouveau;
	private JButton btnEcrProg;
	private JButton btnFiltre;
	private JButton btnRecherche;
	private JLabel lblTitreCompte;
	private JLabel lblSoldeValeur;
	private JLabel soldeEffectif;
	ControlManager controlManager;
	private ColorTable myColor;
	private DialogWrittingsView fDialogWrittings;
	private ResourceBundle fl;

	private static JMenuItem mntmSupprimer;
	private static JMenuItem mntmEditer;
	private JButton btnDateSaisie;

	private DataAccount dataAccountinfo;
	private TableModelWritingsList modelTableau;
	private DialogRapprochementBancaireView fRapprochementBancaire;

	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PanneauDroitWritingsListView frame = new PanneauDroitWritingsListView(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PanneauDroitWritingsListView(ControlManager controlManager) {
		
		this.controlManager = controlManager;
		
		// langue utilisée pour les messages
		fl = this.controlManager.getLangue(); 
				
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setName("Liste des d\u00E9penses du compte");
		//setResizable(true);
		setBounds(301, 0, 1200, 900);
		//getContentPane().setLayout(null);
		
		
		
		
		//JScrollBar scrollBar = new JScrollBar();
		//scrollBar.setBounds(657, 70, 17, 505);
		//getContentPane().add(scrollBar);
		
		initBtn();
		
		
		initTableau();
		

		

	}

	/**
	 * 
	 */
	private void initTableau() {
		
		myColor = new ColorTable();
		modelTableau = new TableModelWritingsList(controlManager);
		tableau = new JTable(modelTableau);
		//tableau.setDefaultRenderer(JComponent.class, new TableComponentWritingsList());
		//tableau.setDefaultRenderer(String.class, new TableComponentWritingsList());
		registerRendererForClass(tableau, String.class);
		registerRendererForClass(tableau, Boolean.class);
	     

		tableau.setFillsViewportHeight(true);
		tableau.setBorder(new EmptyBorder(1, 0, 1, 0));
		
		tableau.setToolTipText("Liste des \u00E9critures du compte");
		tableau.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableau.setAutoscrolls(true);
		
	
		
		JScrollPane scrollPane = new JScrollPane(tableau);
		scrollPane.setBounds(15, 131, 1350, 700);
		scrollPane.setViewportView(tableau);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(tableau, popupMenu);
		
		mntmEditer = new JMenuItem("Editer");
		mntmEditer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionEditerEcriture() ;
			}
		});
		popupMenu.add(mntmEditer);
		
		mntmSupprimer = new JMenuItem("Supprimer");
		mntmSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionSupprimerEcriture();
			}
		});
		//mntmSupprimer.setEnabled(false);
		popupMenu.add(mntmSupprimer);
		
		setEnablePopupMenu(false);
		add(scrollPane);
		
		
		
		 //On récupère le modèle de la colonne

		TableColumn col;
		// "Num","Date","Débit","Budget","Crédit","Revenu","Moyen de paiement","Tiers","Note","Rapprochée"
		
		col= tableau.getColumnModel().getColumn(0);
        col.setPreferredWidth(3);
		
        col= tableau.getColumnModel().getColumn(8);
        col.setPreferredWidth(400);


        col= tableau.getColumnModel().getColumn(9);
        col.setPreferredWidth(35);
//        col= tableau.getColumnModel().getColumn(9);
//        col.setPreferredWidth(3);
//        col= tableau.getColumnModel().getColumn(10);
//        col.setPreferredWidth(3);
        
       
        
        
       
//        tableau.getColumn("Supp").setCellRenderer(new ButtonRenderer("src/images/supp_24.png"));
//        this.tableau.getColumn("Supp").setCellEditor(new ButtonEditor(new JCheckBox(),"src/images/supp_24.png"));
//        
//        
//        tableau.getColumn("Edit").setCellRenderer(new ButtonRenderer("src/images/edit_24.png"));
        
        
        
      
          
	}


    
	private void registerRendererForClass(JTable table, Class<?> classe) {
		
		TableCellRenderer colorRenderer;
		
		if (classe == Boolean.class){
			TableCellRenderer defaultRenderer;
			defaultRenderer = tableau.getDefaultRenderer(Boolean.class);
			 colorRenderer = new TableComponentWritingsList(defaultRenderer, myColor);
			
		} else{

		    // Get Default Renderer from the table
		    DefaultTableCellRenderer defaultRenderer; 
		    defaultRenderer = (DefaultTableCellRenderer) table.getDefaultRenderer(classe);
		    colorRenderer = new TableComponentWritingsList(defaultRenderer, myColor);

		}
	    // Wrap(Decorate) the color renderer around the default renderer
	   
	    // Register the color Renderer with the JTable
	    table.setDefaultRenderer(classe, colorRenderer);
	  }

	/**
	 * 
	 */
	private static void setEnablePopupMenu(boolean b) {
		mntmEditer.setEnabled(b);
		mntmSupprimer.setEnabled(b);
	}

	/**
	 * 
	 */
	private void initBtn() {
		
		lblTitreCompte = new JLabel("Compte :");
		lblTitreCompte.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblTitreCompte.setBounds(10, 11, 816, 35);
		add(lblTitreCompte);
		
		btnNouveau = new JButton("Nouvelle \u00E9criture");
		//setDefaultButton(btnNouveau);
		btnNouveau.setBounds(15, 57, 136, 23);
		btnNouveau.addActionListener(this);
		
		btnEcrProg = new JButton("Ecritures programm\u00E9es");
		btnEcrProg.setBounds(161, 57, 186, 23);
		btnEcrProg.addActionListener(this);
		
		btnFiltre = new JButton("Filtre");
		btnFiltre.setBounds(938, 57, 121, 23);
		btnFiltre.addActionListener(this);
		
		btnRecherche = new JButton("Rechercher");
		btnRecherche.setBounds(1069, 57, 121, 23);
		btnRecherche.addActionListener(this);
		
		btnDateSaisie = new JButton("Date Saisie :");
		btnDateSaisie.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnDateSaisie.setBounds(938, 11, 252, 35);
		add(btnDateSaisie);
		
		JButton btnLettrage = new JButton("Rapprochement bancaire");
		btnLettrage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionRapprochementBancaire();
			}
		});
		btnLettrage.setBounds(357, 57, 207, 23);
		add(btnLettrage);
		
		JLabel lblSolde = new JLabel("Solde  :");
		lblSolde.setBounds(15, 106, 70, 14);
		
		JLabel lblSoldeReconcilie = new JLabel("Solde rapproch\u00E9 :");
		lblSoldeReconcilie.setBounds(185, 106, 120, 14);
		
		lblSoldeValeur = new JLabel("580,68");
		lblSoldeValeur.setBounds(88, 106, 87, 14);
		
		soldeEffectif = new JLabel("58 000,65");
		soldeEffectif.setBounds(315, 106, 111, 14);
		setLayout(null);
		add(btnFiltre);
		add(btnRecherche);
		add(btnNouveau);
		add(btnEcrProg);
		add(lblSolde);
		add(lblSoldeValeur);
		add(lblSoldeReconcilie);
		add(soldeEffectif);
	}

	protected void actionRapprochementBancaire() {
		// TODO Stub de la méthode généré automatiquement

		if(fRapprochementBancaire == null)
			fRapprochementBancaire	 = new DialogRapprochementBancaireView();
		fRapprochementBancaire.showDialog();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// les actions des boutons
		
		Object obj = e.getSource();
    	
    	//System.out.println("actionPerformed " + e.getActionCommand() + " ");
		//JOptionPane.showMessageDialog(this, "actionPerformed", "Information", JOptionPane.INFORMATION_MESSAGE);

    	if (obj == btnNouveau) {
    		//System.out.println("actionPerformed Nouveau débit" );
    		actionNouvelleEcriture();
    	} /*else if (obj == btnModifier) {
    		
    		//System.out.println("actionPerformed Modifier écriture" );
    		actionModifierEcriture();
    		
    	} else if (obj == btnSupprimer) {
    		
    		actionSupprimerEcriture();
    		
    	}*/ else if (obj == btnEcrProg ) {
    		
    		actionEcritureProgrammee();
    		
    	} else if (obj == btnFiltre) {
    		
    		actionFiltre();
    		
    	} else if (obj == btnRecherche) {
    		
    		actionRecherche();
    		
    		 
    	} else{
    		System.out.println("ACTION AUTRE " + e.getActionCommand() + " ");
    	}
    	
	}

	private void actionRecherche() {
		// TODO créer la fenêtre Rechercher une écriture
		JOptionPane.showMessageDialog(null, "Recherche", "Information", JOptionPane.INFORMATION_MESSAGE);
	}

	private void actionFiltre() {
		// TODO Filtrer les écritures suivant des critères préétablie
		JOptionPane.showMessageDialog(null, "Filtre", "Information", JOptionPane.INFORMATION_MESSAGE);
		
	}

	private void actionEcritureProgrammee() {
		// Créer un popup menu pour sélectionner Virement compte à compte, virement externe, prélèvement automatique 
		JOptionPane.showMessageDialog(null, "Virement", "Information", JOptionPane.INFORMATION_MESSAGE);
	}

	private void actionSupprimerEcriture() {
		
		int rowSel = tableau.getSelectedRow();
		
		((TableModelWritingsList) tableau.getModel()).removeRow(rowSel);
		setEnablePopupMenu(false);
		//JOptionPane.showMessageDialog(null, "Supprimer écriture oui/non " + rowSel, "Information", JOptionPane.INFORMATION_MESSAGE);
		
	}

	private void actionEditerEcriture() {
		// TODO Appel fenêtre modification écriture
		
		//int rowSel = tableau.getSelectedRow();
		
		JOptionPane.showMessageDialog(null, "Editer dépense", "Information", JOptionPane.INFORMATION_MESSAGE);
	}

	private void actionNouvelleEcriture() {
		
		System.out.println("Création d'un nouveau compte bancaire");
		
		
		// Fenêtre de création des comptes
		
		if (fDialogWrittings == null){
			fDialogWrittings = new DialogWrittingsView(controlManager,true,fl.getString("dialogWrittingsTitle"),this.dataAccountinfo);					
		} 
		fDialogWrittings.clear();
		
		fDialogWrittings.showDialog(true,modelTableau);
		
		
	}


	/**
	 * @return the lblTitreCompte
	 */
	public JLabel getLblTitreCompte() {
		return lblTitreCompte;
	}

	/**
	 * @param dataAccount 
	 * @param lblTitreCompte the lblTitreCompte to set
	 */
	public void setCompte(DataAccount dataAccountinfo) {

		this.dataAccountinfo = dataAccountinfo;
		this.lblTitreCompte.setText("Compte : " + dataAccountinfo.getLibelle());
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
				
				int row = ((JTable)e.getComponent()).getSelectedRow();
				//System.out.println(" row selected " + row);
				if (row !=-1){
					 setEnablePopupMenu(true);
				}
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
