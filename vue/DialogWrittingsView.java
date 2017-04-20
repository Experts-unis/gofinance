package vue;

import java.awt.EventQueue;



import javax.swing.JLabel;



import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


import javax.swing.ImageIcon;


import controleur.ControlManager;

import model.DataWrinting;
import model.TableModelWritingsList;
import model.DataRefLibelle;

import model.DataAccount;


//import java.util.ResourceBundle;

import javax.swing.JTextField;

import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JCheckBox;


public class DialogWrittingsView extends DialogBase  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private ResourceBundle fl;
	
	private JTextField txtDateSaisie;
	private LinkTxtBtnCalendar linkDate1;
	private DataWrinting infoDataWrittings;
	
	
	
	String selectCompte;
	int selectType;
	private JTextField txtMontant;
	
	private JComboBox<DataRefLibelle> cmbType;
	private JComboBox<DataRefLibelle> cmbMoyen;
	private JComboBox<DataRefLibelle> cmbRevenu;

	private JTextField txtNum;
	private JButton btnBudget;
	private LinkTxtBtnTreeBudget lkTxtBudget;
	

	
	private JTextField txtNumCheque;
	private JLabel lblNumroDuChque;
	
	private JButton btnTiers;
	private DataRefLibelle dataTiers;
	private JButton btnCompteDestinataireVirement;
	private DataRefLibelle dataDestCompteVirement;
	
	private JCheckBox chckbxRapproche;
	private JTextArea textArea;
	private TableModelWritingsList modelTableau;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DialogWrittingsView frame = new DialogWrittingsView(null,true,"test ecriture",null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * Remmarque pour la maintenance de cet écran
	 * Mettre en commentaire les composants qui occupent le même espace dans le fenêtre
	 * permet de travailler sur le design de chacun d'eux l'un après l'autre
	 */
	
	public DialogWrittingsView(ControlManager controlManager, boolean bcreat,String title,DataAccount infoCompte) {		
		super(controlManager, null,title + "  sur " +infoCompte.getLibelle(), true, 310, 516);
		
		//setBounds(0, 0, 309, 516);
		//fl = controlManager.getLangue();
		
		
		infoDataWrittings = new DataWrinting();
		infoDataWrittings.setIdCompte(infoCompte.getId());
		
		dataTiers=null;
		dataDestCompteVirement=null;
		
		setResizable(false);


		getContentPane().setLayout(null);
		
		
		initComponentDate();	
		chckbxRapproche = new JCheckBox("Rapproch\u00E9e");
		chckbxRapproche.setBounds(205, 27, 97, 23);
		getContentPane().add(chckbxRapproche);
		chckbxRapproche.setSelected(false);
		
		initComponentType();
		initMontant();	
		initTiers();
		
		// Ces deux composants partagent alternativement le même espace dans la fenêtre
		initBudget(controlManager);	
		initRevenu();
		
		// Idem
		initCompteDest();
		initNumCheque();
		
		
		initNote();				
		initButton();
		
		loadDataReference() ;
		ecouter=true;

	}

	/**
	 * 
	 */
	private void initTiers() {
	    btnTiers = new JButton("Choix du tiers");
		btnTiers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionChoixTiers();
			}
		});
		btnTiers.setToolTipText("B\u00E9n\u00E9ficiaire");
		btnTiers.setBounds(12, 160, 281, 23);
		getContentPane().add(btnTiers);
	}

	protected void actionChoixTiers() {
		DialogSelectTiersView dialTiers = new DialogSelectTiersView(controlManager);
		dataTiers = dialTiers.showDialog2();
		if (dataTiers != null)
			btnTiers.setText(dataTiers.getLibelle());
	}

	/**
	 * 
	 */
	private void initButton() {
		JButton btnEnreg = new JButton("Enregistrer");
		btnEnreg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionEnregistrer();
			}
		});
		btnEnreg.setMnemonic('E');
		btnEnreg.setBounds(83, 456, 113, 23);
		getContentPane().add(btnEnreg);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setMnemonic('A');
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionQuitter();
			}
		});

		btnAnnuler.setBounds(202, 456, 89, 23);
		getContentPane().add(btnAnnuler);
		

	}

	/**
	 * 
	 */
	private void initCompteDest() {
		btnCompteDestinataireVirement = new JButton("Selectionner le compte destinataire");
		btnCompteDestinataireVirement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionChoixDestinataireVirement();
			}
		});
		btnCompteDestinataireVirement.setVisible(false);
		btnCompteDestinataireVirement.setBounds(12, 228, 281, 23);
		getContentPane().add(btnCompteDestinataireVirement);
	}

	protected void actionChoixDestinataireVirement() {

		DialogSelectAccountView dial = new DialogSelectAccountView(controlManager);
		dataDestCompteVirement = dial.showDialog2();
		if (dataDestCompteVirement != null)
			btnCompteDestinataireVirement.setText(dataDestCompteVirement.getLibelle());
		
		
	}

	/**
	 * 
	 */
	private void initNumCheque() {
		lblNumroDuChque = new JLabel("Num\u00E9ro du ch\u00E8que : ");
		lblNumroDuChque.setBounds(14, 228, 120, 14);
		
		getContentPane().add(lblNumroDuChque);
		
		txtNumCheque = new JTextField();
		txtNumCheque.setBounds(133, 228, 160, 20);
		getContentPane().add(txtNumCheque);
		txtNumCheque.setColumns(10);
		
		setVisibleCheque(false);
	}

	/**
	 * @param b 
	 * 
	 */
	private void setVisibleCheque(boolean b) {
		lblNumroDuChque.setVisible(b);
		txtNumCheque.setVisible(b);
	}

	/**
	 * 
	 */
	private void initNote() {
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 262, 281, 2);
		getContentPane().add(separator);
		
		JLabel lblNumro = new JLabel("Num\u00E9ro ligne  :");
		lblNumro.setBounds(13, 282, 212, 14);
		getContentPane().add(lblNumro);
		
		txtNum = new JTextField();
		txtNum.setBounds(12, 301, 223, 20);
		getContentPane().add(txtNum);
		txtNum.setColumns(10);
		
				
		JButton btnNum = new JButton("...");
		btnNum.setBounds(245, 300, 46, 23);
		getContentPane().add(btnNum);
		
			
		JLabel lblNote = new JLabel("Note :");
		lblNote.setBounds(13, 329, 46, 14);
		getContentPane().add(lblNote);
		
		textArea = new JTextArea();
		
		textArea.setBounds(12, 347, 280, 98);
		getContentPane().add(textArea);
	}
	
	/**
	 * @param controlManager
	 */
	private void initBudget(ControlManager controlManager) {
		
		btnBudget = new JButton("Ligne budgétaire");
		btnBudget.setBounds(12, 194, 281, 23);
		getContentPane().add(btnBudget);
		
	//	JLabel lblBudgetRubrique = new JLabel("rubrique");
	//	lblBudgetRubrique.setBounds(103, 11, 179, 20);
	//	add(lblBudgetRubrique);
		

		
		lkTxtBudget = new LinkTxtBtnTreeBudget(this.getLocation(), btnBudget, btnBudget, controlManager);
		
	}

	/**
	 * 
	 */
	private void initRevenu() {
	
		cmbRevenu = new JComboBox<DataRefLibelle>();
		cmbRevenu.setBounds(12, 194, 279, 23);
		getContentPane().add(cmbRevenu);
		
		
	}
	protected void actionChangeType() {
		System.out.println("Changer de type");
		
		
		if (!ecouter) return;
		
		int index = cmbType.getSelectedIndex();
		
		
		DataRefLibelle data = cmbType.getItemAt(index);
		selectType = data.getId();
		switch (selectType){
		case 1 : // DEBIT
			setAlternerVisibiliteBudgetRevenu(true);

			break;
		case 2 : // CREDIT
			setAlternerVisibiliteBudgetRevenu(false);
			break;
		case 3:
		case 4:
			setVisibleRevenu(false);
			setVisibleBudget(true);
			
		}
		 actionChangeMoyenPaiement();
		
	}

	private void setAlternerVisibiliteBudgetRevenu(boolean b) {
		
		btnBudget.setVisible(b);
		cmbRevenu.setVisible(!b);		
	}
	private void setVisibleRevenu(boolean b) {
		
		cmbRevenu.setVisible(b);		
	}
	private void setVisibleBudget(boolean b) {
		
		btnBudget.setVisible(b);
		
	}
	
	protected void actionEnregistrer() {
		
		System.out.println("actionEnregistrer");
		if (testField()){
			
			System.out.println("actionEnregistrer test ok");

			//IDECR, DTSAISIE, DTECR, IDCOMPTE, NUM, MONTANT, IDTYPE, IDREVENU, IDBUDGET, IDTIERS, FLAGLETTRAGE, IDVIREMENT,  NOTE
			DataRefLibelle data;

			infoDataWrittings.setDtEcr(linkDate1.getDate());
			infoDataWrittings.setNum(new Integer(txtNum.getText()));
			infoDataWrittings.setMontant(new Double(txtMontant.getText()));

			data =  (DataRefLibelle) cmbMoyen.getSelectedItem();
			infoDataWrittings.setIdMoyenPaiement(data.getId());

			data = (DataRefLibelle) cmbType.getSelectedItem();
			infoDataWrittings.setIdType(data.getId());

			infoDataWrittings.setIdVirement(0);
			infoDataWrittings.setIdRevenu(0);
			infoDataWrittings.setIdBudget(0);
			infoDataWrittings.setIdBudget(0);
			infoDataWrittings.setIdTiers(0);
			infoDataWrittings.setNumCheque(txtNumCheque.getText());
			infoDataWrittings.setStatut(chckbxRapproche.isSelected());
			infoDataWrittings.setNote(textArea.getText());


			switch(infoDataWrittings.getIdType())
			{
			case 1 : // débit
				infoDataWrittings.setIdBudget(lkTxtBudget.getBudgetSelection().getId());
				if (infoDataWrittings.getIdMoyenPaiement()==5)
					infoDataWrittings.setIdVirement(dataDestCompteVirement.getId());
				break;	
			case 2 : // crédit
				data = (DataRefLibelle) cmbRevenu.getSelectedItem();
				infoDataWrittings.setIdRevenu(data.getId());
				break;	
			case 3 : // désir

				break;	


			}


			infoDataWrittings.setIdTiers(dataTiers.getId());
			//infoDataWrittings.setStatut(false);

			controlManager.getModelManager().addWrittings(infoDataWrittings);
			modelTableau.addRow(infoDataWrittings);
			clear();
		}
		
		
	}



	private boolean testField() {
		
//		txtDateSaisie.setText("");
//		chckbxRapproche.setSelected(false);
//		cmbType.setSelectedIndex(0);
//		txtMontant.setText("");
//		cmbMoyen.setSelectedIndex(0);
//		btnTiers.setText("Choix du tiers");
		
//		btnBudget.setText("Ligne budgétaire");
//		cmbRevenu.setSelectedIndex(0);
//		btnCompteDestinataireVirement.setText("Selectionner le compte destinataire");
//		txtNumCheque.setText("");
//		txtNum.setText("");
//		textArea.setText("");
		
		if (txtDateSaisie.getText() =="") return false;
		if (txtMontant.getText() =="") return false;
		if (dataTiers != null) 
			if (dataTiers.getId()==-1) return false;
		
		return true;
	}

	/**
	 * 
	 */
	private void initMontant() {
		// Montant
		JLabel lblMontant = new JLabel("Montant :");
		lblMontant.setBounds(14, 104, 69, 14);
		getContentPane().add(lblMontant);
		
		txtMontant = new JTextField();
		txtMontant.setBounds(14, 128, 120, 20);
		getContentPane().add(txtMontant);
		txtMontant.setColumns(10);
		
		JLabel lblModeDePaiement = new JLabel("Moyen de paiement :");
		lblModeDePaiement.setBounds(144, 105, 149, 14);
		getContentPane().add(lblModeDePaiement);

		cmbMoyen = new JComboBox<DataRefLibelle>();
		cmbMoyen.setActionCommand("MOYEN_PAIEMENT");
		cmbMoyen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionChangeMoyenPaiement();
			}
		});
		//cmbMoyen.setModel(new DefaultComboBoxModel(new String[] {"", "Carte bleu", "Liquide", "Pr\u00E9l\u00E8vement", "Virement"}));
		cmbMoyen.setToolTipText("Mode de paiement");
		cmbMoyen.setBounds(144, 128, 149, 20);
		getContentPane().add(cmbMoyen);
	}

	protected void actionChangeMoyenPaiement() {
		System.out.println("Changer de moyen de paiement ecouter " + ecouter);
		
		if (!ecouter) return;
		int index = cmbMoyen.getSelectedIndex();
		
		
		DataRefLibelle data = cmbMoyen.getItemAt(index);

		if (data.getId()==5 && selectType==1) // VIREMENT & DEBIT
		{
			btnCompteDestinataireVirement.setVisible(true);
		}else{
			btnCompteDestinataireVirement.setVisible(false);
		}
		
		if (data.getId()==2){ // CHEQUE
			setVisibleCheque(true);
		}else{
			setVisibleCheque(false);
		}
		
	}



	/**
	 * 
	 */
	private void initComponentType() {
		
		// Type
		JLabel lblType = new JLabel("Type :");
		lblType.setBounds(14, 57, 46, 14);
		getContentPane().add(lblType);
		
		cmbType = new JComboBox<DataRefLibelle>();
		cmbType.setActionCommand("TYPE");
		cmbType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionChangeType();
			}
		});
		cmbType.setBounds(14, 74, 279, 20);
		getContentPane().add(cmbType);
	}





	/**
	 * 
	 */
	private void initComponentDate() {
		// Date
		JLabel lblDate = new JLabel("Date :");
		lblDate.setBounds(14, 11, 160, 14);
		getContentPane().add(lblDate);
		
		txtDateSaisie = new JTextField();
		txtDateSaisie.setDisabledTextColor(new Color(255, 255, 255));
		txtDateSaisie.setForeground(Color.WHITE);
		txtDateSaisie.setBackground(Color.LIGHT_GRAY);
		txtDateSaisie.setEnabled(false);
		txtDateSaisie.setEditable(false);
		txtDateSaisie.setBounds(14, 28, 160, 20);
		getContentPane().add(txtDateSaisie);
		txtDateSaisie.setColumns(10);
		
		JButton btnCalendrier = new JButton(new ImageIcon("src/images/cal2-24.png"));
		//btnCalendrier.setIcon();
		btnCalendrier.setToolTipText("Calendrier");
		btnCalendrier.setBounds(175, 25, 24, 24);
		getContentPane().add(btnCalendrier);
				
		linkDate1 = new LinkTxtBtnCalendar(this.getLocation(), txtDateSaisie, btnCalendrier);
	}


	
	public DataWrinting showDialog(boolean bCreat,TableModelWritingsList pmodelTableau){

		this.sendData = false;
		modelTableau = pmodelTableau ;
		
		//Début du dialogue
		this.setVisible(true);
		
		
		//Le dialogue prend fin
		//Si on a clicqué sur OK, on envoie, sinon on envoie null !
		return (this.sendData)?infoDataWrittings: null;

	}

	private void loadDataReference() {
		
		// Charge les combobox
		controlManager.getModelManager().loadRefType(cmbType);
		selectType = cmbType.getItemAt(0).getId();
		controlManager.getModelManager().loadRefRevenu(cmbRevenu);
		  
	    controlManager.getModelManager().loadRefMoyenPaiement(cmbMoyen);
	    
		
	}

	public void clear() {
		
		System.out.println("Clear data");
		
		//txtDateSaisie.setText("");
		chckbxRapproche.setSelected(false);
		cmbType.setSelectedIndex(0);
		txtMontant.setText("");
		cmbMoyen.setSelectedIndex(0);
		btnTiers.setText("Choix du tiers");
		if (dataTiers != null) dataTiers.clear();
		btnBudget.setText("Ligne budgétaire");
		cmbRevenu.setSelectedIndex(0);
		btnCompteDestinataireVirement.setText("Selectionner le compte destinataire");
		txtNumCheque.setText("");
		txtNum.setText("");
		textArea.setText("");
	}
	public void load(DataWrinting data) {
		// TODO remplis les champs de saisie avec des données
		
	}
}
