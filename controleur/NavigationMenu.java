/**
 * 
 */
package controleur;

/**
 * @author test
 *
 */
public class NavigationMenu  {

	protected Navigation numero;
	protected String name;
	/**
	 * 
	 */
	public NavigationMenu(String name, Navigation numero) {
		this.name=name;
		this.numero=numero;
		
	}
	/**
	 * @return the numero
	 */
	public Navigation getNumero() {
		return numero;
	}
	/**
	 * @param numero the numero to set
	 */
	public void setNumero(Navigation numero) {
		this.numero = numero;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		//return "NavigationMenu [name=" + name + "]";
		return name;
	}
	public Object getId() {
		// TODO Auto-generated method stub
		return null;
	}

}
