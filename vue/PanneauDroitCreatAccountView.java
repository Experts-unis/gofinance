package vue;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import java.awt.Font;

public class PanneauDroitCreatAccountView extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanneauDroitCreatAccountView() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelButton = new JPanel();
		panelButton.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		//panelButton.setBackground(new Color(245, 222, 179));
		add(panelButton, BorderLayout.CENTER);
		panelButton.setLayout(new BorderLayout(0, 0));
		//panelButton.setLayout(null);
		
		JButton btnCreerUnNouveau = new JButton("Cliquer ici pour cr\u00E9er un nouveau compte");
		btnCreerUnNouveau.setFont(new Font("Tahoma", Font.PLAIN, 30));
		//btnCrerUnNouveau.setBounds(109, 121, 218, 49);
		panelButton.add(btnCreerUnNouveau, BorderLayout.CENTER);
		


	}
}
