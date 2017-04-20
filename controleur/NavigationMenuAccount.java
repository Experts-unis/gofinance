/**
 * 
 */
package controleur;

import model.DataAccount;

/**
 * @author test
 *
 */
public class NavigationMenuAccount extends NavigationMenu {

	private DataAccount dataAccount;
	/**
	 * @param name
	 * @param numero
	 */
	
	public NavigationMenuAccount(String name, Navigation numero,DataAccount obj) {
		super(name, numero);
		
		this.dataAccount = obj;
	}
	/**
	 * @return the obj
	 */
	public DataAccount getDataAccount() {
		return dataAccount;
	}
	/**
	 * @param obj the obj to set
	 */
	public void setDataAccount(DataAccount obj) {
		this.dataAccount = obj;
		this.name = obj.getLibelle();
	}

}
