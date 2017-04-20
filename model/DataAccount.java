package model;

import java.math.BigDecimal;

public class DataAccount extends DataRefLibelle {




	protected BigDecimal solde;
	//private int id;
	
	
	public DataAccount () 
	{
		super(-1,"Pas de nom");
		solde = new BigDecimal ("0.00");
			
	}
	public DataAccount(String name, BigDecimal  solde, int id) {
		super(id,name);

		this.solde = solde;

	}

	/**
	 * @return the name
	 */
//	public String getName() {
//		return libelle;
//	}
//	/**
//	 * @param name the name to set
//	 */
//	public void setName(String name) {
//		this.name = name;
//	}
	/**
	 * @return the solde
	 */
	public BigDecimal getSolde() {
		return solde;
	}
	/**
	 * @param solde the solde to set
	 */
	public void setSolde(BigDecimal solde) {
		this.solde = solde;
	}
	/**
	 * @return the id
	 */
//	public int getId() {
//		return id;
//	}
//	/**
//	 * @param id the id to set
//	 */
//	public void setId(int id) {
//		this.id = id;
//	}

	public void setData(String name,BigDecimal solde){
		this.libelle=name;
		this.solde = solde;			
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return libelle;
		//return "tostring InfoNewAccount [id="+id+" name=" + libelle + ", solde=" + solde + "]";
	}

}