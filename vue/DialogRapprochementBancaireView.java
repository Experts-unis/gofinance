/**
 * 
 */
package vue;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import model.DataRefLibelle;
import javax.swing.DefaultComboBoxModel;



/**
 * @author test
 *
 */
public class DialogRapprochementBancaireView extends DialogBase {
	private JTable tblLigneBanque;
	public DialogRapprochementBancaireView() {
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 115, 537, 439);
		getContentPane().add(scrollPane);
		
		tblLigneExistantes = new JTable();
		scrollPane.setViewportView(tblLigneExistantes);
		tblLigneExistantes.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Num", "Date", "Montant", "Commentaire", "Lettrage"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				true, false, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		JScrollPane scrollPaneBanque = new JScrollPane();
		scrollPaneBanque.setBounds(565, 115, 537, 440);
		getContentPane().add(scrollPaneBanque);
		
		tblLigneBanque = new JTable();
		scrollPaneBanque.setViewportView(tblLigneBanque);
		tblLigneBanque.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Num", "Date", "Montant", "Commentaire", "Lettrage"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				true, false, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		JButton btnValider = new JButton("Valider");
		btnValider.setBounds(914, 566, 89, 23);
		getContentPane().add(btnValider);
		
		JLabel lblListeDescritures = new JLabel("Liste des \u00E9critures de la base");
		lblListeDescritures.setBounds(10, 90, 234, 14);
		getContentPane().add(lblListeDescritures);
		
		JLabel lblListeDescriture = new JLabel("Liste des \u00E9critures du fichier");
		lblListeDescriture.setBounds(565, 91, 244, 14);
		getContentPane().add(lblListeDescriture);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBounds(1013, 566, 89, 23);
		getContentPane().add(btnAnnuler);
		
		txtFileName = new JTextField();
		txtFileName.setBounds(10, 11, 289, 20);
		getContentPane().add(txtFileName);
		txtFileName.setColumns(10);
		
		JButton btnRechercherFichier = new JButton("Charger le fichier ");
		btnRechercherFichier.setBounds(427, 11, 120, 23);
		getContentPane().add(btnRechercherFichier);
		
		JComboBox<DataRefLibelle> cmbFormat = new JComboBox();
		cmbFormat.setModel(new DefaultComboBoxModel(new String[] {"QIF", "CSV"}));
		cmbFormat.setBounds(331, 12, 80, 20);
		getContentPane().add(cmbFormat);
		
		JButton btnRapprochement = new JButton("Rapprocher les \u00E9critures");
		btnRapprochement.setBounds(906, 10, 196, 23);
		getContentPane().add(btnRapprochement);
		tblLigneExistantes.getColumnModel().getColumn(1).setResizable(false);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tblLigneExistantes;
	private JTextField txtFileName;
}
