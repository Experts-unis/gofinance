/**
 * 
 */
package vue;



import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controleur.ControlManager;
import model.DataBudget;

/**
 * @author test
 *
 */
public class LinkTxtBtnTreeBudget extends LinkTxtBtn {

	private ControlManager controlManager;
	private DataBudget budgetSelection;
	JLabel txtRubrique;
	/**
	 * @param dialParent
	 * @param txt
	 * @param btn
	 */
	public LinkTxtBtnTreeBudget(Point location, JButton txt, JButton btn,ControlManager controlManager) {
		super(location, txt, btn);
		this.controlManager = controlManager;
	}

	/* (non-Javadoc)
	 * @see vue.LinkTxtBtn#CallDial(int, int)
	 */
	@Override
	protected void CallDial(int x, int y) {
		
		DialogBudgetChoixTreeView dialog = new DialogBudgetChoixTreeView(controlManager,x, y);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		budgetSelection = dialog.showDialog();
		
		if (budgetSelection !=null){
			
			((JButton)txt).setText(budgetSelection.getLibelle());
			//((JButton)txt).repaint();
			
		}
	}

	public DataBudget getBudgetSelection() {
		return budgetSelection;
	}

	public void setBudgetSelection(DataBudget budgetSelection) {
		this.budgetSelection = budgetSelection;
	}
	

}
