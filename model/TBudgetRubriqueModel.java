/**
 * 
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * @author test
 *
 */
public class TBudgetRubriqueModel extends DBTable {

	private Vector<DataBudget> listeRubrique;
	private String[] tableRubriqueLib;
	private int[] tableRubriqueId;
	private int[] tableRef;
	



	/**
	 * @param nameTable
	 * @param nameID
	 * @param driver
	 */
	public TBudgetRubriqueModel(DB driver) {
		super("TBUDGET_RUBRIQUES", "ID", driver);
		listeRubrique = null;
	}
	
	public Vector<DataBudget> getList() {


		if (listeRubrique == null){
			// Créer une liste d'info à partir de la table tcomptes de la base de données;
			listeRubrique = new Vector<DataBudget>();
			//tableRubriqueLib = new String() ;
			//tableRubriqueId = new Integer[]() ;

			ResultSet result =null;


			String query ="SELECT ID, LIBELLE, IDBUDGET FROM TBUDGET_RUBRIQUES  " ;
			System.out.println("Contruite la liste des Rubriques " +query);
			//L'objet ResultSet contient le résultat de la requête SQL
			try {
				result = DB.dbStatement.executeQuery(query);
				System.out.println("Rq OK" +query);
				while(result.next()){ 

					listeRubrique.add(new DataBudget(result.getInt("ID"),result.getString("LIBELLE"),result.getInt("IDBUDGET")));
					
				}

			} catch (SQLException e) {

				System.out.println("Rq KO "+query );
				e.printStackTrace();
				return null;
			}
		}
		return listeRubrique ;
		
	}
	
	
	public Vector<DataBudget> getList(int idBudget) {

		// Créer une liste d'info à partir de la table tcomptes de la base de données;
		Vector<DataBudget> liste = new Vector<DataBudget>();
		
		ResultSet result =null;
		String idBudgetStr;
		if (idBudget==0) idBudgetStr="is NULL"; else idBudgetStr = "=" + Integer.toString(idBudget);
		String query ="SELECT ID, LIBELLE, IDBUDGET FROM TBUDGET_RUBRIQUES WHERE IDBUDGET " + idBudgetStr ;
		System.out.println("Contruite la liste des Rubriques " +query);
		//L'objet ResultSet contient le résultat de la requête SQL
		try {
			result = DB.dbStatement.executeQuery(query);
			System.out.println("Rq OK" +query);
			while(result.next()){ 
				
				liste.add(new DataBudget(result.getInt("ID"),result.getString("LIBELLE"),result.getInt("IDBUDGET")));
			}

		} catch (SQLException e) {
			
			System.out.println("Rq KO "+query );
			e.printStackTrace();
			return null;
		}
		return liste ;
		
	}


	/**
	 * @return the listeRubrique
	 */
	public Vector<DataBudget> getListeRubrique() {
		return listeRubrique;
	}

	/**
	 * @param listeRubrique the listeRubrique to set
	 */
	public void setListeRubrique(Vector<DataBudget> listeRubrique) {
		this.listeRubrique = listeRubrique;
	}
	
	/**
	 * @param id : id dans la table
	 * @return le libelle correspondant à l'id
	 */
	public String search(int id){
		
		String libelle ="";
		;
		
		if (listeRubrique == null )  getList() ;
		
	
		for (DataBudget element : listeRubrique){
			if (element.getId()==id){
				libelle =element.getLibelle();
				break;
			}
		}
		
		return libelle;
		
		
	}
}
