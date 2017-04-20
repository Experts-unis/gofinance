package vue;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;



public class PanneauDroitSoldeAccountView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @wbp.nonvisual location=70,-21
	 */
	private final JPanel panel = new JPanel();

	/**
	 * Create the panel.
	 */
	public PanneauDroitSoldeAccountView() {
		setLayout(new BorderLayout(0, 0));
		panel.setBackground(new Color(255, 250, 205));
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));

		panel.setBounds(0, 0, 500, 500);

		String titreColonne[]={"Num","Nom du Compte","Solde"};
		Object data[][]={
				{1,"LCL","1500.58"},
				{2,"DIM","100.58"}
		};

		JTable tableau = new JTable(data,titreColonne);
		tableau.setFillsViewportHeight(true);
		tableau.setBorder(new EmptyBorder(1, 0, 1, 0));

		tableau.setToolTipText("Liste des \u00E9critures du compte");
		tableau.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//tableau.setAutoscrolls(true);

		JScrollPane scrollPane = new JScrollPane(tableau);
		add(scrollPane, BorderLayout.CENTER);

		JLabel lblTitre = new JLabel("Relevé des comptes");
		lblTitre.setFont(new Font("Tahoma", Font.PLAIN, 30));
		JLabel lblDate = new JLabel("Date des relevés : ");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDate.setAlignmentY(Component.TOP_ALIGNMENT);
		panel.setLayout(new GridLayout(2, 2, 0, 0));
		panel.add(lblTitre);
		panel.add(lblDate);
		add(panel, BorderLayout.NORTH);
		//panel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblTitre, lblDate}));

	}

}
