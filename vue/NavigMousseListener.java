/**
 * 
 */
package vue;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.hamcrest.core.IsInstanceOf;

import model.DataAccount;

/**
 * @author test
 *
 */
public class NavigMousseListener extends MouseAdapter {

	NavigationTreeView nav;
	/**
	 * 
	 */
	public NavigMousseListener(NavigationTreeView nav) {
		this.nav = nav;
	}

	public void mouseReleased(MouseEvent event){

		//Seulement s'il s'agit d'un clic droit

		//if(event.getButton() == MouseEvent.BUTTON3)
		if(event.isPopupTrigger()){       

			nav.showPopUpAccount(event);

		}
	}
}
