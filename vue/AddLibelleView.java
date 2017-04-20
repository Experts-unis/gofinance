package vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controleur.ControlManager;
import model.DataRefLibelle;
import model.ManagerRefLibelle;

import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddLibelleView extends DialogBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtSaisie;
	DefaultListModel<DataRefLibelle> listeModel;
	boolean modif;
	DataRefLibelle elementModif;
	ManagerRefLibelle manager;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddLibelleView dialog = new AddLibelleView(null,null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @param listeModel 
	 */
	public AddLibelleView(ManagerRefLibelle manager,DefaultListModel<DataRefLibelle> listeModel) {
		super(null, null,manager.getTitle() , true, 345, 152);
		
		this.manager = manager;
		this.modif=false;
		elementModif = null;
		this.listeModel = listeModel;
		//setBounds(0, 0, 345, 152);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblInvite = new JLabel(manager.getTextLabel());
		lblInvite.setBounds(10, 11, 309, 14);
		contentPanel.add(lblInvite);
		
		txtSaisie = new JTextField();
		txtSaisie.setBounds(10, 31, 309, 20);
		contentPanel.add(txtSaisie);
		txtSaisie.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 67, 309, 2);
		contentPanel.add(separator);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("Enregistrer");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionEnregistrer();
			}
		});
		okButton.setActionCommand("Enregistrer");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Annuler");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionQuitter();
			}
		});
		cancelButton.setActionCommand("Annuler");
		buttonPane.add(cancelButton);
		
		

	}



	protected void actionEnregistrer() {
		
		
		if (modif){
			
			elementModif.setLibelle(txtSaisie.getText().toUpperCase());
			manager.maj(elementModif);
			
			//this.listeModel.addElement(element);
			this.listeModel.set(elementModif.getIndex(), elementModif);
			clear();	
		}else {
			DataRefLibelle element;
			element = manager.add(txtSaisie.getText().toUpperCase());
			
			this.listeModel.addElement(element);
			clear();
		}
	}

	public void load(DataRefLibelle value) {
		
		clear();
		this.modif=true;
		elementModif = value;
		txtSaisie.setText(value.getLibelle());
		
	}

	private void clear() {
		this.modif=false;
		txtSaisie.setText("");
		txtSaisie.requestFocusInWindow();
	}
}
