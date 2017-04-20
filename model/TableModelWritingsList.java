/**
 * 
 */
package model;


import java.util.Vector;

import javax.swing.table.AbstractTableModel;


import controleur.ControlManager;

/**
 * @author test
 *
 */
public class TableModelWritingsList extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String[] entete;
	Boolean[] columnEditables;
	Vector <DataWrinting> data;
	
	ControlManager controlManager;
	Class<?>[] columnTypes;
	String supp="supp";
	String edit="Edit";
	
	

	/**
	 * 
	 */
	public TableModelWritingsList(ControlManager controlManager) {
		super();
		
		entete = new String[] {"Num","Date","Débit","Budget","Crédit","Revenu","Moyen de paiement","Tiers","Note","Rapprochée"};
		columnEditables = new Boolean[] {false,false,false,false,false,false,false,false,false,true};
		columnTypes = new Class[] {
				String.class, // num
				String.class, // date
				String.class, // Débit
				String.class, // Budget				
				String.class, // Crédit
				String.class, // Revenu				
				String.class, // Moyen de paiement
				String.class, // Tiers				
				String.class, // Note
				Boolean.class // Rapprochée 

			};

		// Chargement des lignes
		this.controlManager = controlManager;
		
		data = controlManager.getModelManager().loadDataWritting();
		

			
		
	}
	public void Refresh()
	{
		data = controlManager.getModelManager().loadDataWritting();
		this.fireTableDataChanged();
	}
	
	public  DataWrinting addRow(DataWrinting value )
	{
		data.addElement(value);
		this.fireTableDataChanged();
		return value;
	}
	 public void removeRow(int position){
	       
	      
		 
		 //TODO Supprmer  les données tecritures de la base
		 
		 data.removeElementAt(position);
		 
		// this.fireTableStructureChanged();
		 //this.fireTableRowsDeleted(position, position);
	      //Cette méthode permet d'avertir le tableau que les données
	      //ont été modifiées, ce qui permet une mise à jour complète du tableau
	     this.fireTableDataChanged();
	      
	   }

	/* (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 */
	@Override
	public String getColumnName(int column) {
		return entete[column];
		//return super.getColumnName(column);
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() {
		return data.size();
		
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount() {
		return entete.length; 
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
	 */
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		
		return columnTypes[columnIndex];
		//return this.data.get(columnIndex).getClass();
	}

	//Retourne vrai si la cellule est éditable : celle-ci sera donc éditable

	public boolean isCellEditable(int row, int col){

	  return columnEditables[col];

	}
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		//"Num","Date","Débit","Budget","Crédit","Revenu","Moyen de paiement","Tiers","Note","Rapprochée"
		DataWrinting row = data.get(rowIndex);
		switch(columnIndex){
		case 0 : // num
			return (Object)row.getNumStr();
		case 1 : // Date
			return (Object)row.getDtEcr().toString();
		case 2 : // Débit
			return (Object)row.getDebitStr();
			
		case 3 : // Budget
			return (Object)row.getLibBudget();
		case 4 : // Credit
			return (Object)row.getCreditStr();
			
		case 5 : // Revenu
			return (Object)row.getLibRevenu();

		case 6 : // Moyen de paiement
			return (Object)row.getLibMoyenPaiement();
		case 7 : // Tiers
			return (Object)row.getLibTiers();

		case 8 : // Note
			
			return (Object)(row.getNumCheque() + " " + row.getNote());
		case 9 : // Status
			return (Object)row.isStatut();

			

		}
		return "";
	}
	

	/* (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#setValueAt(java.lang.Object, int, int)
	 */
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

		
		if (columnIndex == 9){ // La colonne Status est la seule à être éditable
			DataWrinting row = data.get(rowIndex);
			if (row.isStatut())
				row.setStatut(false);
			else
				row.setStatut(true);
			
		}
		

			
		//super.setValueAt(aValue, rowIndex, columnIndex);
	}
	

}
