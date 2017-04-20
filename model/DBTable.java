/**
 * 
 */
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @author test
 *
 */
public class DBTable {

	protected String nameTable;
	protected String nameID;
	static protected DB  driver;
	protected String queryInsert;
	protected String queryUpdate;
	protected String queryDelete;
	private int id;
	public PreparedStatement dbPrepInsert;
	public PreparedStatement dbPrepUpdate;
	public PreparedStatement dbPrepDelete;
	
	/**
	 * 
	 */
	public DBTable(String nameTable,String nameID,DB driver) {
		this.nameTable=nameTable;
		this.nameID = nameID;
		//this.driver = driver;
		//Recherche de l'identifiant unique
		id = initID();
		prepareInsert();
		prepareDelete();
		prepareUpdate();

		
	}
	protected PreparedStatement  prepareQuery(String query)
	{
		PreparedStatement dbPrepQuery;
		// Prépare la modification de tprojets.libelle.
		System.out.println("Appel de prepareQuery " + nameTable);
		try {
			dbPrepQuery = DB.cnx.prepareStatement(query);
			System.out.println("prepareQuery OK");
			return dbPrepQuery;

		} catch (SQLException e) {
			System.out.println("prepareQuery  KO " +query);
			e.printStackTrace();
			return null;
		}
		
	}
	public void prepareUpdate() {
		// Doit être redefinie dans les classes dérivées
		dbPrepUpdate = null;
		queryUpdate = "";
	}

	public void prepareDelete() {
		// Doit être redefinie dans les classes dérivées
		dbPrepDelete = null;
		queryDelete = "";
	}

	public void prepareInsert() {
		
		System.out.println("TableModel : prepareInsert " + nameTable);
		dbPrepInsert = null;
		queryInsert = "";
	}

	
	public int initID()
	{
	
		
		//Recherche de l'identifiant unique suivant pour cette table
		int unId=-1;
		String query ="SELECT MAX("+nameID+") FROM "+nameTable;
		System.out.println("Recherche de Id de la table " +query);
		//L'objet ResultSet contient le résultat de la requête SQL
		try {
			ResultSet result = DB.dbStatement.executeQuery(query);
			result.first();
			unId = result.getInt(1);
			System.out.println("Rq OK "+query +" res = "+unId );

		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println("Rq K0 "+query );
		}
		
		return unId;
	}
	
	public int getCurrentID(){
		return id;
		
	}
	public int getNextID(){
		return ++id;
		
	}

}
