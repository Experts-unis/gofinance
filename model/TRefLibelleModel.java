package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;



public class TRefLibelleModel extends DBTable{

	protected String query;
	private Vector<DataRefLibelle> liste;



	public TRefLibelleModel(String nameTable,  DB driver) {
		super(nameTable, "ID", driver);
		
		liste = null;
		
		query ="SELECT ID, LIBELLE FROM " + nameTable;
		
	}

	/* (non-Javadoc)
	 * @see model.TableModel#prepareInsert()
	 */
	@Override
	public void prepareInsert() {
		super.prepareInsert();
		
		//On crée l'objet avec la requête en paramètre
		System.out.println("Appel de prepareInsert dans " + nameTable);
		queryInsert ="INSERT INTO " + nameTable + " (ID,LIBELLE) VALUES (?,?)";
		this.dbPrepInsert = prepareQuery(queryInsert);
				
	}
	
	

	/* (non-Javadoc)
	 * @see model.TableModelDB#prepareUpdate()
	 */
	@Override
	public void prepareUpdate() {
		super.prepareUpdate();
		
		System.out.println("Appel de prepareUpdate de " + nameTable);
		queryUpdate="UPDATE " + nameTable + " SET LIBELLE = ? WHERE ID=?";
		this.dbPrepUpdate = prepareQuery(queryUpdate);
		
	}

	/* (non-Javadoc)
	 * @see model.TableModelDB#prepareDelete()
	 */
	@Override
	public void prepareDelete() {
		super.prepareDelete();
		
		System.out.println("Appel de prepareDelete de "+nameTable);
		queryDelete ="DELETE  FROM " + nameTable +" WHERE ID=?";
		this.dbPrepDelete = prepareQuery(queryDelete);
		
	}

	public DataRefLibelle add(String libelle)
	{
		

		try 
		{
			System.out.println("Rq sur  "+nameTable + " : " + queryInsert );
			//On paramètre notre requête préparée
			dbPrepInsert.setInt(1, this.getNextID());
			dbPrepInsert.setString(2, libelle);
		
   			//On exécute
			dbPrepInsert.executeUpdate();	
			System.out.println("Rq OK "+dbPrepInsert.toString() );

			DataRefLibelle element = new DataRefLibelle (getCurrentID(),libelle);
			
			return element;
		} catch (SQLException e) {
			System.out.println("Rq  KO "+dbPrepInsert.toString()  );
			e.printStackTrace();
			return null;
		}
			
		
	}
	
	public void maj(int id, String libelle)
	{
		try 
		{
			System.out.println("Rq sur "+nameTable+" OK "+queryUpdate );
			//On paramètre notre requête préparée
			
			dbPrepUpdate.setString(1, libelle);
			dbPrepUpdate.setInt(2, id);
			
			//On exécute
			dbPrepUpdate.executeUpdate();	
			System.out.println("Rq  OK "+dbPrepUpdate.toString() );

		} catch (SQLException e) {
			System.out.println("Rq  KO "+dbPrepUpdate.toString()  );
			e.printStackTrace();
		}
		
		
	}
	
	public void del(int idToDel) {
		try 
		{
			System.out.println("Rq  OK "+queryDelete);
			
			//On paramètre notre requête préparée
			dbPrepDelete.setInt(1, idToDel);
			
			//On exécute
			dbPrepDelete.executeUpdate();	
			System.out.println("Rq sur  OK "+dbPrepDelete.toString() );

		} catch (SQLException e) {
			System.out.println("Rq sur  KO "+dbPrepDelete.toString()  );
			e.printStackTrace();
		}
		
	}
	
	public Vector<DataRefLibelle>  getList() {

		// Créer une liste d'info à partir de la table tcomptes de la base de données;
		//JComboBox<DataRefLibelle> cmb = new JComboBox<DataRefLibelle>();
		
		
		ResultSet result =null;
		liste =new Vector<DataRefLibelle>();
	
		
		System.out.println("Contruite la liste " +query);
		//L'objet ResultSet contient le résultat de la requête SQL
		try {
			result = DB.dbStatement.executeQuery(query);
			System.out.println("Rq OK" +query);
			while(result.next()){ 
				liste.addElement(new DataRefLibelle(result.getInt("ID"),result.getString("LIBELLE")));
				
			}

		} catch (SQLException e) {
			
			System.out.println("Rq KO "+query );
			e.printStackTrace();
			return null;
		}
		return liste ;
	}
	
	/**
	 * @param id : id dans la table
	 * @return le libelle correspondant à l'id
	 */
	public String search(int id){
		
		String libelle ="";
		
		
		if (liste == null )  getList() ;
		
	
		for (DataRefLibelle element : liste){
			if (element.getId()==id){
				libelle = element.getLibelle();
				break;
			}
		}
		
		return libelle;
		
		
	}

}
