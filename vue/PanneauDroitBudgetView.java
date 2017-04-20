package vue;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JTree;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.LineBorder;

public class PanneauDroitBudgetView extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanneauDroitBudgetView() {
		setBackground(new Color(184, 134, 11));
		setLayout(new BorderLayout(0, 0));
		
		JTree budget = new JTree();
		budget.setBorder(new LineBorder(new Color(0, 0, 0)));
		budget.setEnabled(false);
		add(budget, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 100, 100);

		add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnValider = new JButton("Valider");
		panel.add(btnValider);
		
		JButton btnAnnuler = new JButton("Annuler");
		panel.add(btnAnnuler);
		
		JLabel lblGestionDuBudget = new JLabel("Gestion du budget");
		lblGestionDuBudget.setFont(new Font("Tahoma", Font.PLAIN, 30));
		add(lblGestionDuBudget, BorderLayout.NORTH);

	}

}
