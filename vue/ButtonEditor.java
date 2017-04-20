/**
 * 
 */
package vue;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import model.TableModelWritingsList;

/**
 * @author test
 *
 */
public class ButtonEditor extends DefaultCellEditor {
    
	   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JButton button;
	   private ButtonListener bListener = new ButtonListener();
	    
	   public ButtonEditor(JCheckBox checkBox,String nameFileIcone) {
		   //Par défaut, ce type d'objet travaille avec un JCheckBox
		   super(checkBox);
		   //On crée à nouveau notre bouton
		   button = new JButton();
		   button.setIcon(new ImageIcon(nameFileIcone));
		   button.setOpaque(true);
		   //On lui attribue un listener
		   button.addActionListener(bListener);
	   }
	 
	   public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column){
	     
		  
		   System.out.println(" ButtonEditor.getTableCellEditorComponent " +((TableModelWritingsList) table.getModel()).getRowCount());
		   
		   //On définit le numéro de ligne à notre listener
	      bListener.setRow(row);
	      //Idem pour le numéro de colonne
	      bListener.setColumn(column);
	      //On passe aussi en paramètre le tableau pour des actions potentielles
	      bListener.setTable(table);
	      //on reaffecte l'icone du boutton 
	    //On réaffecte le libellé au bouton
	  //    button.setText( (value ==null) ? "" : value.toString() );
	      //On renvoie le bouton
	       return button;
	   }
	    
	   class ButtonListener implements ActionListener {
	      
	     private int column, row;
	     private JTable table;
	   
	     private JButton button;
	      
	     public void setColumn(int col){this.column = col;}
	     public void setRow(int row){this.row = row;}
	     public void setTable(JTable table){this.table = table;}
	     public JButton getButton(){return this.button;}
	      
	     public void actionPerformed(ActionEvent event) {
	      //On affiche un message mais vous pourriez faire ce que vous voulez
	      System.out.println("coucou du bouton row "+row+" , col "+column );
	      //On affecte un nouveau libellé à une celulle de la ligne
//	      ((AbstractTableModel)table.getModel()).setValueAt("New Value " + (++nbre), this.row, (this.column -1));  
	      //Permet de dire à notre tableau qu'une valeur a changé
	      //à l'emplacement déterminé par les valeurs passées en paramètres
	//      ((AbstractTableModel)table.getModel()).fireTableCellUpdated(this.row, this.column - 1);
	     // this.button = ((JButton)event.getSource());
	 
	      
	      
	      //((TableModelWritingsList) table.getModel()).removeRow(row);
	      //((AbstractTableModel)table.getModel()).fireTableCellUpdated(this.row, this.column);
	     }
	  }
	}
