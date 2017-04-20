package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class TCompteModel extends DBTable{
	

	protected String queryUpdate2;
	protected PreparedStatement dbPrepUpdate2;
	

	public TCompteModel(DB driver) {
		super("TCOMPTE","IDCOMPTE",driver);
	}

	/* (non-Javadoc)
	 * @see model.TableModel#prepareUpdate()
	 */
	@Override
	public void prepareUpdate() {
		super.prepareUpdate();
		
		// Prépare la modification du nom du compte et du solde initial du compte.
		// On crée l'objet avec la requête en paramètre
		System.out.println("Appel de prepareUpdate dans TCompteModel");
		queryUpdate="UPDATE TCOMPTE SET NAME = ?, SOLDE= ? WHERE IDCOMPTE=?";
		this.dbPrepUpdate = prepareQuery(queryUpdate);
		
		
		
		// Prépare la modification du status du compte Clos = 1 open = null.
		// On crée l'objet avec la requête en paramètre
		System.out.println("Appel de prepareUpdate ");
		queryUpdate2="UPDATE TCOMPTE SET CLOS = ? WHERE IDCOMPTE=?";
		this.dbPrepUpdate2 = prepareQuery(queryUpdate2);

	}

	
	public void prepareArchive() {
		//Permettre la clotûre d'un compte 
	}

	/* (non-Javadoc)
	 * @see model.TableModel#prepareInsert()
	 */
	@Override
	public void prepareInsert() {
		super.prepareInsert();
		
		//On crée l'objet avec la requête en paramètre
		System.out.println("Appel de prepareInsert dans TCompte");
		queryInsert ="INSERT INTO TCOMPTE (IDCOMPTE,NAME,SOLDE, CLOS) VALUES (?,?,?,false)";
		this.dbPrepInsert = prepareQuery(queryInsert);
				
	}

	/* (non-Javadoc)
	 * @see model.TableModel#prepareDelete()
	 */
	@Override
	public void prepareDelete() {
		//On crée l'objet avec la requête en paramètre
		System.out.println("Appel de prepareDelete");
		queryDelete ="DELETE  FROM TCOMPTE WHERE IDCOMPTE=?";
		this.dbPrepDelete = prepareQuery(queryDelete);
			    
	}

	public Vector<DataAccount> getList() {

		// Créer une liste d'info à partir de la table tcomptes de la base de données;
		Vector<DataAccount> lesComptes = new Vector<DataAccount>();
		
		ResultSet result =null;
		String query ="SELECT IDCOMPTE AS ID ,NAME AS LIBELLE ,SOLDE FROM TCOMPTE WHERE CLOS = false";
		System.out.println("Contruite la liste des comptes" +query);
		//L'objet ResultSet contient le résultat de la requête SQL
		try {
			result = DB.dbStatement.executeQuery(query);
			System.out.println("Rq OK" +query);
			while(result.next()){ 
				
				lesComptes.add(new DataAccount(result.getString("LIBELLE"),result.getBigDecimal("SOLDE"),result.getInt("ID")));
			}

		} catch (SQLException e) {
			
			System.out.println("Rq KO "+query );
			e.printStackTrace();
			return null;
		}
		return lesComptes ;
	}
	
	public int add(String name,String solde)
	{
		//Ajouter une référence de compte dans la table TCOMPTE
		
		try 
		{
			System.out.println("Rq sur tcompte OK "+queryInsert );
			//On paramètre notre requête préparée
			dbPrepInsert.setInt(1, this.getNextID());
			dbPrepInsert.setString(2, name);
			dbPrepInsert.setString(3, solde);
   			//On exécute
			dbPrepInsert.executeUpdate();	
			System.out.println("Rq sur tcompte OK "+dbPrepInsert.toString() );

			return getCurrentID();
		} catch (SQLException e) {
			System.out.println("Rq sur tcompte KO "+dbPrepInsert.toString()  );
			e.printStackTrace();
			return -1;
		}
		
		
		
	}

	public void maj(int id, String name, String solde) {
		//Modifier une ligne dans la table TCOMPTE
		
		try 
		{
			System.out.println("Rq sur tcompte OK "+queryUpdate );
			//On paramètre notre requête préparée
			
			dbPrepUpdate.setString(1, name);
			dbPrepUpdate.setString(2, solde);
			dbPrepUpdate.setInt(3, id);
			
			//On exécute
			dbPrepUpdate.executeUpdate();	
			System.out.println("Rq sur tcompte OK "+dbPrepUpdate.toString() );

		} catch (SQLException e) {
			System.out.println("Rq sur tcompte KO "+dbPrepUpdate.toString()  );
			e.printStackTrace();
		}
		
	}

	public void del(int idToDel) {
		try 
		{
			System.out.println("Rq sur tcompte OK "+queryDelete);
			
			//On paramètre notre requête préparée
			dbPrepDelete.setInt(1, idToDel);
			
			//On exécute
			dbPrepDelete.executeUpdate();	
			System.out.println("Rq sur tcompte OK "+dbPrepDelete.toString() );

		} catch (SQLException e) {
			System.out.println("Rq sur tcompte KO "+dbPrepDelete.toString()  );
			e.printStackTrace();
		}
		
	}

}
