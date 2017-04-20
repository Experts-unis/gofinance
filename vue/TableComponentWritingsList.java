package vue;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class TableComponentWritingsList extends DefaultTableCellRenderer {

	protected TableCellRenderer renderer;
	protected ColorTable provider;
	JLabel label = new JLabel();



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TableComponentWritingsList(TableCellRenderer aRenderer, 	ColorTable provider) {
		this.renderer = aRenderer;
		this.provider = provider;
	}
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

		//Si la valeur de la cellule est un JButton, on transtype cette valeur

//		if (value instanceof JButton)
//
//			return (JButton) value;
//
//		else
//
//			return this;
		
		
		Color bgrd = null;
	    Color fgrd = null;
	    if (isSelected) {
	      fgrd = table.getSelectionForeground();
	      bgrd = table.getSelectionBackground();
	    }
	    else {
	      // Adjust for columns moving around
	      int mcol = table.convertColumnIndexToModel(column);
	      // Get the colors from the provider
	      fgrd = provider.getForeground(row, mcol);
	      bgrd = provider.getBackground(row, mcol);
	    }
	    Component c =
	        renderer.getTableCellRendererComponent(
	        table,
	        value,
	        isSelected,
	        hasFocus,
	        row,
	        column);
	    // Set the component colours
	    c.setBackground(bgrd);
	    c.setForeground(fgrd);
	    
	    return c;
		
//		if (value instanceof JCombobox)
//
//			 return (JCombobox)value
	}
	
	
		  
		  

}
