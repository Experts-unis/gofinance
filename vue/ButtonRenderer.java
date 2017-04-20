/**
 * 
 */
package vue;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import model.TableModelWritingsList;

/**
 * @author test
 *
 */
public class ButtonRenderer extends JButton implements TableCellRenderer{

	
	/**
	 * 
	 */
	public ButtonRenderer(String nameFileIcone) {
		super();
		setIcon(new ImageIcon(nameFileIcone));
		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
	 */
	@Override
	public Component getTableCellRendererComponent(
			JTable table, Object value, boolean isSelected, boolean isFocus, int row, int col)
	{
		System.out.println(" ButtonRenderer.getTableCellRendererComponent " +((TableModelWritingsList) table.getModel()).getRowCount());
		return this;
	}

}
