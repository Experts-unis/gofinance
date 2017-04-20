/**
 * 
 */
package model;

/**
 * @author test
 *
 */
public class DataBudget {

	private int id;
	private String libelle;
	private int idBudget;
	
	/**
	 * @param id
	 * @param libelle
	 * @param idBudget
	 */
	public DataBudget(int id, String libelle, int idBudget) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.idBudget = idBudget;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		//return libelle + " [id=" + id + ", idBudget=" + idBudget + "]";
		return libelle;
	}

	/**
	 * 
	 */
	public DataBudget() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}
	/**
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	/**
	 * @return the idBudget
	 */
	public int getIdBudget() {
		return idBudget;
	}
	/**
	 * @param idBudget the idBudget to set
	 */
	public void setIdBudget(int idBudget) {
		this.idBudget = idBudget;
	}


}
