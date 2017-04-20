/**
 * Lanceur de l'application
 */
package controleur;

import java.awt.HeadlessException;
import java.sql.*;
 



/**
 * @author AGD
 *
 */
public class Principale {

	
	static public ControlManager controlManager;

	//private String prefLangue;
	/**
	 * @param args
	 * @throws HeadlessException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws HeadlessException, SQLException  {
		
	 
		//Cr�ation du contr�leur principal 
		
		try {
			controlManager = new ControlManager();
		} catch (HeadlessException e) {
			
			e.printStackTrace();
		}

		//TODO Corrige les warnings ..
		//TODO Revoir comment remonter les exceptions sur la base de donn�e
		//TODO Revoir commment g�n�rer des traces sur un fichier....
		
	}

	public void test()
	{
		Connection conn;
		// TODO Mise � jour des langues Anglais, Allemand, Espagnol, Italien, Grec, Russes 

		try {
			Class.forName("org.postgresql.Driver");
			System.out.println("Driver O.K.");

			String url = "jdbc:postgresql://localhost:5432/mescomptes";
			String user = "postgres";
			String passwd = "superutilisateur";

			conn = DriverManager.getConnection(url, user, passwd);
			System.out.println("Connexion effective !");   
			
			
			//Cr�ation d'un objet Statement
			Statement state = conn.createStatement();

			//L'objet ResultSet contient le r�sultat de la requ�te SQL
			ResultSet result = state.executeQuery("SELECT * FROM tcompte");

			//On r�cup�re les MetaData
			ResultSetMetaData resultMeta = result.getMetaData();

			System.out.println("\n**********************************");

			//On affiche le nom des colonnes
			for(int i = 1; i <= resultMeta.getColumnCount(); i++)
				System.out.print("\t" + resultMeta.getColumnName(i).toUpperCase() + "\t *");

			System.out.println("\n**********************************");


			// On affiche les donn�es
			while(result.next()){         

				for(int i = 1; i <= resultMeta.getColumnCount(); i++)
					System.out.print("\t" + result.getObject(i).toString() + "\t |");

				System.out.println("\n---------------------------------");
		        
			}


		    result.close();

		    state.close();			

		} catch (Exception e) {
			System.out.println("Erreur ! ");
			e.printStackTrace();
			System.exit(0);	
		} 
	}

};
