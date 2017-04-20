package vue;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JProgressBar;

public class PanneauDroitHomeView extends JPanel {
	private JTable tableDesComptes;

	/**
	 * Create the panel.
	 */
	public PanneauDroitHomeView() {
		setBackground(Color.PINK);
		setLayout(new BorderLayout(0, 0));
		
		JPanel panComptes = new JPanel();
		panComptes.setLayout(null);
		
		
		
		tableDesComptes = new JTable();
		tableDesComptes.setBorder(new LineBorder(Color.RED, 2));
		tableDesComptes.setBounds(49, 53, 360, 59);
		
		tableDesComptes.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{"TOTAL", null},
			},
			new String[] {
				"Nom du compte", "Solde"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableDesComptes.getColumnModel().getColumn(0).setPreferredWidth(514);
		tableDesComptes.getColumnModel().getColumn(1).setPreferredWidth(300);
		
		JScrollPane spComptes = new JScrollPane(tableDesComptes);
		spComptes.setBounds(23, 43, 275, 193);
		panComptes.add(spComptes);
		
		add(panComptes, BorderLayout.CENTER);
		
		JScrollPane spDepRev = new JScrollPane();
		spDepRev.setBounds(374, 43, 254, 193);
		panComptes.add(spDepRev);
		
		JPanel panDepRev = new JPanel();
		spDepRev.setViewportView(panDepRev);
		panDepRev.setLayout(null);
		
		JProgressBar pBarDep = new JProgressBar();
		pBarDep.setBounds(57, 56, 146, 14);
		panDepRev.add(pBarDep);
		
		JProgressBar pBarRevenus = new JProgressBar();
		pBarRevenus.setBounds(57, 98, 146, 14);
		panDepRev.add(pBarRevenus);
		
		JLabel lblDepenses = new JLabel("D\u00E9penses");
		lblDepenses.setBounds(10, 28, 81, 14);
		panDepRev.add(lblDepenses);
		
		JLabel lblRevenus = new JLabel("Revenus");
		lblRevenus.setBounds(10, 73, 46, 14);
		panDepRev.add(lblRevenus);

		JLabel lblDateDaujourdhui = new JLabel("Date d'aujourd'hui");
		lblDateDaujourdhui.setFont(new Font("Tahoma", Font.PLAIN, 26));
		add(lblDateDaujourdhui, BorderLayout.NORTH);

	}
}
