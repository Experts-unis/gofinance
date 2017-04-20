/**
 * 
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 * @author test
 *
 */
public class TEcrituresModel extends DBTable {

	/**
	 * @param nameTable
	 * @param nameID
	 * @param driver
	 */
	public TEcrituresModel( DB driver) {
		super("TECRITURES", "IDECRITURE", driver);
		
	}

	/* (non-Javadoc)
	 * @see model.TableModelDB#prepareUpdate()
	 */
	@Override
	public void prepareUpdate() {
		// TODO Auto-generated method stub
		super.prepareUpdate();
	}

	/* (non-Javadoc)
	 * @see model.TableModelDB#prepareDelete()
	 */
	@Override
	public void prepareDelete() {
		// TODO Auto-generated method stub
		super.prepareDelete();
	}

	/* (non-Javadoc)
	 * @see model.TableModelDB#prepareInsert()
	 */
	@Override
	public void prepareInsert() {
		super.prepareInsert();
		
		//On crée l'objet avec la requête en paramètre
		System.out.println("Appel de prepareInsert dans TEcritures");
		queryInsert ="INSERT INTO TECRITURES "
				+ "(IDECRITURE, DTSAISIE, DTECR, IDCOMPTE, NUM, MONTANT, IDMOYENPAIEMENT, IDTYPE, IDREVENU, IDBUDGET, IDTIERS, FLAGLETTRAGE, IDVIREMENT,NUMCHEQUE,  NOTE)"
				+ "VALUES (?, ?,  ?, ?, ?, ?, ?, ?, ?, ?,  ?, ?, ?, ?,?)";
		
				
		this.dbPrepInsert = prepareQuery(queryInsert);
		
	}
	public int add(DataWrinting value)
	{
		//Ajouter une référence de compte dans la table TCOMPTE
		
		try 
		{
			System.out.println("Rq sur tcompte OK "+queryInsert );
			//On paramètre notre requête préparée
			int n =1;
			dbPrepInsert.setInt(n++, this.getNextID());
			dbPrepInsert.setDate(n++, value.getSQLDtSaisie());
			dbPrepInsert.setDate(n++, value.getSQLDtEcr());
			dbPrepInsert.setInt(n++, value.getIdCompte());
			dbPrepInsert.setInt(n++, value.getNum());
			dbPrepInsert.setDouble(n++, value.getMontant());
			dbPrepInsert.setInt(n++, value.getIdMoyenPaiement());
			dbPrepInsert.setInt(n++, value.getIdType());
			dbPrepInsert.setInt(n++, value.getIdRevenu());
			dbPrepInsert.setInt(n++, value.getIdBudget());
			dbPrepInsert.setInt(n++, value.getIdTiers());
			dbPrepInsert.setBoolean(n++, value.isStatut());
			dbPrepInsert.setInt(n++, value.getIdVirement());
			dbPrepInsert.setString(n++, value.getNumCheque());
			dbPrepInsert.setString(n++, value.getNote());
			
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
	public Vector<DataWrinting> getList() {


		// Créer une liste d'info à partir de la table tcomptes de la base de données;
				Vector<DataWrinting> liste = new Vector<DataWrinting>();
				
				ResultSet result =null;
				String query ="SELECT IDECRITURE, DTSAISIE, DTECR, "
						+ " IDCOMPTE, NUM, MONTANT,IDMOYENPAIEMENT, IDTYPE, IDREVENU, "
						+ " IDBUDGET, IDTIERS, FLAGLETTRAGE, IDVIREMENT, NUMCHEQUE, NOTE "
						+ " FROM TECRITURES ";
				System.out.println("Contruite la liste des écriture " +query);
				//L'objet ResultSet contient le résultat de la requête SQL
				try {
					result = DB.dbStatement.executeQuery(query);
					System.out.println("Rq OK" +query);
					while(result.next()){ 
						
						DataWrinting data = new DataWrinting();
						data.setId(result.getInt("IDECRITURE"));
						data.setDtSaisie(result.getDate("DTSAISIE"));
						data.setDtEcr(result.getDate("DTECR"));
						data.setIdCompte(result.getInt("IDCOMPTE"));
						data.setNum(result.getInt("NUM"));
						data.setMontant(result.getDouble("MONTANT"));
						data.setIdMoyenPaiement(result.getInt("IDMOYENPAIEMENT"));
						data.setIdType(result.getInt("IDTYPE"));
						data.setIdRevenu(result.getInt("IDREVENU"));
						data.setIdBudget(result.getInt("IDBUDGET"));
						data.setIdTiers(result.getInt("IDTIERS"));
						data.setStatut(result.getBoolean("FLAGLETTRAGE"));
						data.setIdVirement(result.getInt("IDVIREMENT"));
						data.setNote(result.getString("NOTE"));
						liste.add(data);
					}

				} catch (SQLException e) {
					
					System.out.println("Rq KO "+query );
					e.printStackTrace();
					return null;
				}
				return liste ;
				
	
	}
	

}
