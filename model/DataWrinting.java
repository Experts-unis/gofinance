/**
 * 
 */
package model;

import java.util.Date;



/**
 * @author test
 *
 */
public class DataWrinting {
	
	//IDECR, DTSAISIE, DTECR, IDCOMPTE, NUM, MONTANT, IDTYPE, IDREVENU, IDBUDGET, IDTIERS, FLAGLETTRAGE, IDVIREMENT,  NOTE
	
	private int id;
	private Date dtSaisie;
	private Date dtEcr;
	private int idCompte;
	private int num;
	private Double montant;
	private Double debit;
	private Double credit;
	
	private int idMoyenPaiement;
	private String libMoyenPaiement;
	private int idType;
	private String libType;
	private int idRevenu;
	private String libRevenu;
	private int idBudget;
	private  String libBudget;
	private  String numCheque;


	
	
	private int idTiers;
	private String libTiers;
	boolean statut;
	private int idVirement;
	private String note;

	
	

	public DataWrinting() {
		
		this.dtSaisie = new Date();
		this.dtEcr =null;
		this.idCompte =-1;
		this.num = -1;
		this.montant= this.debit = this.credit =0d;
		this.idMoyenPaiement=-1;
		this.libMoyenPaiement="";		
		this.idType = -1;
		this.libType = "";
		this.idRevenu = -1;
		this.libRevenu ="";
		this.idBudget = -1;
		this.libBudget = "";
		this.idTiers = -1;
		this.libTiers = "";
		this.statut = false;
		this.idVirement=-1;
		this.numCheque="";
		this.note = "";
		
		
	}


	
	/**
	 * @return the libBudget
	 */
	public String getLibBudget() {
		return libBudget;
	}

	/**
	 * @param libBudget the libBudget to set
	 */
	public void setLibBudget(String libBudget) {
		this.libBudget = libBudget;
	}

	/**
	 * @return the libType
	 */
	public String getLibType() {
		return libType;
	}

	/**
	 * @param libType the libType to set
	 */
	public void setLibType(String libType) {
		this.libType = libType;
	}

	/**
	 * @return the libTiers
	 */
	public String getLibTiers() {
		return libTiers;
	}

	/**
	 * @param libTiers the libTiers to set
	 */
	public void setLibTiers(String libTiers) {
		this.libTiers = libTiers;
	}

	/**
	 * @return the num
	 */
	public int getNum() {
		return num;
	}
	public String getNumStr() {
		return Integer.toString(num);
	}
	/**
	 * @param num the num to set
	 */
	public void setNum(int num) {
		this.num = num;
	}



	
	/**
	 * @return the dtEcr
	 */
	public Date getDtEcr() {
		return dtEcr;
	}
	public java.sql.Date getSQLDtEcr() {
		
		return new java.sql.Date(dtEcr.getTime());
	}
	
	
	/**
	 * @param dtEcr the dtEcr to set
	 */
	public void setDtEcr(Date dtEcr) {
		this.dtEcr = dtEcr;
	}

	/**
	 * @return the idCompte
	 */
	public int getIdCompte() {
		return idCompte;
	}

	/**
	 * @param idCompte the idCompte to set
	 */
	public void setIdCompte(int idCompte) {
		this.idCompte = idCompte;
	}

	/**
	 * @return the montant
	 */
	public Double getMontant() {
		return montant;
	}

	/**
	 * @param montant the montant to set
	 */
	public void setMontant(Double montant) {
		this.montant = montant;
		initDebitCredit();
		
	}


	/**
	 * @return the debit
	 */
	public Double getDebit() {
		return debit;
	}
	public String getDebitStr() {
		if (idType==2 || idType==-1) return ""; // écriture en crédit ou indéfini
		else return debit.toString();
	}
	/**
	 * @param debit the debit to set
	 */
	public void setDebit(Double debit) {
		this.debit = debit;
	}

	/**
	 * @return the credit
	 */
	public Double getCredit() {
		return credit;
	}
	public String getCreditStr() {
		if (idType!=2 || idType==-1) return ""; // écriture en débit ou indéfini
		else return credit.toString();
	}
	/**
	 * @param credit the credit to set
	 */
	public void setCredit(Double credit) {
		this.credit = credit;
	}

	/**
	 * @return the idType
	 */
	public int getIdType() {
		return idType;
	}

	/**
	 * @param idType the idType to set
	 */
	public void setIdType(int idType) {
		this.idType = idType;
		initDebitCredit();
	}



	/**
	 */
	private void initDebitCredit() {
		if (idType!=-1){
			if (idType==1 || idType==3) // Débit ou Désir
			{
				setCredit(0d);
				setDebit(montant);
			}else{
				setDebit(0d);
				setCredit(montant);
			}
		}else{
			setCredit(0d);
			setDebit(0d);
		}
	}
	
	/**
	 * @return the idRevenu
	 */
	public int getIdRevenu() {
		return idRevenu;
	}

	/**
	 * @param idRevenu the idRevenu to set
	 */
	public void setIdRevenu(int idRevenu) {
		this.idRevenu = idRevenu;
	}

	/**
	 * @return the libRevenu
	 */
	public String getLibRevenu() {
		return libRevenu;
	}

	/**
	 * @param libRevenu the libRevenu to set
	 */
	public void setLibRevenu(String libRevenu) {
		this.libRevenu = libRevenu;
	}

	/**
	 * @return the idVirement
	 */
	public int getIdVirement() {
		return idVirement;
	}

	/**
	 * @param idVirement the idVirement to set
	 */
	public void setIdVirement(int idVirement) {
		this.idVirement = idVirement;
	}
	
	

	/**
	 * @return the dtSaisie
	 */
	public Date getDtSaisie() {
		return dtSaisie;
	}
	
	public java.sql.Date getSQLDtSaisie() {
		return new java.sql.Date(dtSaisie.getTime());
	}
	
	

	/**
	 * @param dtSaisie the dtSaisie to set
	 */
	public void setDtSaisie(Date dtSaisie) {
		this.dtSaisie = dtSaisie;
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
	
	public void setBudget(DataBudget data){
		idBudget = data.getIdBudget();
		
	}


	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		if (note!=null)
			this.note = note;
	}

	/**
	 * @return the numCheque
	 */
	public String getNumCheque() {
		return numCheque;
	}



	/**
	 * @param numCheque the numCheque to set
	 */
	public void setNumCheque(String numCheque) {
		if (numCheque!=null)
			this.numCheque = numCheque;
	}



	/**
	 * @return the idTiers
	 */
	public int getIdTiers() {
		return idTiers;
	}

	/**
	 * @param idTiers the idTiers to set
	 */
	public void setIdTiers(int idTiers) {
		this.idTiers = idTiers;
	}

	/**
	 * @return the statut
	 */
	public boolean isStatut() {
		return statut;
	}

	/**
	 * @param statut the statut to set
	 */
	public void setStatut(boolean statut) {
		this.statut = statut;
		//TODO Mise à jour de la base !
	}

	/**
	 * @return the idMoyenPaiement
	 */
	public int getIdMoyenPaiement() {
		return idMoyenPaiement;
	}



	/**
	 * @param idMoyenPaiement the idMoyenPaiement to set
	 */
	public void setIdMoyenPaiement(int idMoyenPaiement) {
		this.idMoyenPaiement = idMoyenPaiement;
	}



	/**
	 * @return the libMoyenPaiement
	 */
	public String getLibMoyenPaiement() {
		return libMoyenPaiement;
	}



	/**
	 * @param libMoyenPaiement the libMoyenPaiement to set
	 */
	public void setLibMoyenPaiement(String libMoyenPaiement) {
		this.libMoyenPaiement = libMoyenPaiement;
	}



	public void setId(int id) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	
	
	
	
	

}
