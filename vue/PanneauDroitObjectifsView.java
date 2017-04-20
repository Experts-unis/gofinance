package vue;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;

public class PanneauDroitObjectifsView extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public PanneauDroitObjectifsView() {
		setBackground(new Color(0, 255, 0));
		setLayout(new BorderLayout(0, 0));
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Date", "Libell\u00E9", "Photo"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(1).setPreferredWidth(250);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		add(table, BorderLayout.CENTER);
		
		JLabel lblObjectifs = new JLabel("Objectifs");
		lblObjectifs.setFont(new Font("Tahoma", Font.PLAIN, 30));
		add(lblObjectifs, BorderLayout.NORTH);

	}

}
