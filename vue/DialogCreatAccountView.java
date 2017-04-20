package vue;

import java.awt.Color;

import java.awt.EventQueue;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;

import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.math.BigDecimal;
import java.util.ResourceBundle;



import controleur.ControlManager;
import model.DataAccount;

public class DialogCreatAccountView extends DialogBase implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtName;
	private JTextField txtSolde;
	private JButton btnValider;
	private JButton btnAnnuler;
	
	private DataAccount infoCompte;
	

	private JPanel panAccount;
	private ResourceBundle fl;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DialogCreatAccountView frame = new DialogCreatAccountView(new JFrame());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @wbp.parser.constructor
	 */
	public DialogCreatAccountView(JFrame parent) {
	super(null,parent, "test Compte bancaire", true, 274, 240);
		
		setLocationRelativeTo(parent);
		fl = ControlManager.fichierDesChainesTraduites;

	
		infoCompte = new DataAccount ();
		initDialCompoment(true);
		refreshDial(true);
	
	}
	public DialogCreatAccountView(MainView parent,boolean bcreat,String title) {
		
		super(null,parent, title, true, 274, 240);
		
		fl = ControlManager.fichierDesChainesTraduites;
		infoCompte = new DataAccount ();
		initDialCompoment(bcreat);
		refreshDial(bcreat);
	}

	
	private void initDialCompoment(boolean bCreat)
	{
		
		// TODO Mettre les textes dans le fichier des données internationnales
		//setToolTipText("Cr\u00E9ation d'un nouveau compte bancaire");

		//setTitle("Nouveau compte bancaire");
		//setBounds(0, 0, 274, 240);
		getContentPane().setLayout(null);
		 //La position
	    this.setLocationRelativeTo(null);

	    //La boîte ne devra pas être redimenssionnable
	    this.setResizable(false);

		panAccount = new JPanel();

		panAccount.setBackground(Color.white);
		//panAccount.setPreferredSize(new Dimension(60, 60));
		panAccount.setBounds(10, 11, 248, 154);
		
		if (bCreat)
			panAccount.setBorder(BorderFactory.createTitledBorder(fl.getString("dialogCompteCreaBorder") ));
		else
			panAccount.setBorder(BorderFactory.createTitledBorder(fl.getString("dialogCompteEditBorder") ));
		
		panAccount.setLayout(null);

		JLabel lblNomDuCompte = new JLabel(fl.getString("dialogTxtName"));
		lblNomDuCompte.setBounds(29, 30, 198, 14);
		panAccount.add(lblNomDuCompte);
		
		txtName = new JTextField();
		//txtName.setSelectionColor(new Color(211, 211, 211));
		txtName.setToolTipText(fl.getString("dialogTxtNameTips"));
		txtName.setBounds(28, 48, 199, 20);
		txtName.requestFocus();
		
		//textField.setBounds(26, 47, 187, 20);
		panAccount.add(txtName);
		txtName.setColumns(10);

		JLabel lblSolde = new JLabel(fl.getString("dialogTxtSolde"));
		lblSolde.setBounds(29, 79, 195, 14);
		panAccount.add(lblSolde);
		
		txtSolde = new JTextField();
		//txtSolde.setSelectionColor(new Color(211, 211, 211));
		txtSolde.setBounds(29, 103, 195, 20);
		txtSolde.setToolTipText(fl.getString("dialogTxtSoldeTips"));
		panAccount.add(txtSolde);
		txtSolde.setColumns(10);

		getContentPane().add(panAccount);

		btnValider = new JButton(fl.getString("dialogBtValider"));
		btnValider.setBounds(20, 176, 89, 23);
		btnValider.addActionListener(this);
		getContentPane().add(btnValider);
		
		btnAnnuler = new JButton(fl.getString("dialogBtAnnuler"));
		btnAnnuler.setBounds(167, 176, 89, 23);
		btnAnnuler.addActionListener(this);
		getContentPane().add(btnAnnuler);
	
		// Permet de changer la couleur de la bordure du jtxtfield
		//txtName.setBorder(BorderFactory.createLineBorder(Color.red));
			
	}
	public void refreshDial(boolean bCreat)
	{

		txtName.setText("");
		txtName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		txtSolde.setText("0.00");
		txtSolde.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		if (bCreat)
			panAccount.setBorder(BorderFactory.createTitledBorder(fl.getString("dialogCompteCreaBorder") ));
		else
			panAccount.setBorder(BorderFactory.createTitledBorder(fl.getString("dialogCompteEditBorder") ));
		
		
	}
	private void messageErreur(int num)
	{
		//Boîte du message d'erreur
		String message="";
	
		switch (num) {
		case 1 :
			message = fl.getString("dialogMsgErrNameEmpty");
			break;
		case 2:
			message = fl.getString("dialogMsgErrSoldeFormat");
			break;
			
		}
		JOptionPane.showMessageDialog(null, message, fl.getString("dialogMsgErrTitre"), JOptionPane.ERROR_MESSAGE);
		
	}
	public void loadInfoDial(DataAccount infoCompte)
	{
		
		this.infoCompte = infoCompte;
		System.out.println("Dialog loadInfoDial");
		
		txtName.setText(this.infoCompte.getLibelle());
		txtName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txtName.requestFocus();
		
		txtSolde.setText(this.infoCompte.getSolde().toString());
		txtSolde.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		panAccount.setBorder(BorderFactory.createTitledBorder(fl.getString("dialogCompteEditBorder") ));
	}
	
	@Override
	public void actionPerformed(ActionEvent e)  {
		Object obj = e.getSource();

		//System.out.println("actionPerformed " + e.getActionCommand() + " ");

		if (obj == btnValider) {
			this.sendData = true;
			
			String nameCompte = txtName.getText();
			
			if (nameCompte.equals("")){
				System.out.println("Erreur la saisie du nom est vide" );
				txtName.setBorder(BorderFactory.createLineBorder(Color.red));
				txtName.requestFocus();
				this.sendData = false;
				messageErreur(1);
				return;
				
			}
			
			BigDecimal val;
			try {
				val = new BigDecimal(txtSolde.getText());
				infoCompte.setData(nameCompte,val);
				System.out.println("Saisie Compte OK "+ infoCompte.toString());
				// Stop la fenêtre de dialogue 
				setVisible(false);
			} catch (NumberFormatException  e1) {
				System.out.println("Erreur de saisie du solde" );
				txtSolde.setBorder(BorderFactory.createLineBorder(Color.red));
				txtSolde.requestFocus();
				this.sendData = false;
				messageErreur(2);
				
				//e1.printStackTrace();
			}
			

			
		} else if (obj == btnAnnuler) {
			actionQuitter();
			
		}
		
	}
	public DataAccount showDialogAccount(boolean bCreat){

		this.sendData = false;
		
		//Début du dialogue
		this.setVisible(true);
		
		//Le dialogue prend fin
		//Si on a cliqué sur OK, on envoie, sinon on envoie une chaîne vide !
		return (this.sendData)? infoCompte : null;

	}
}


