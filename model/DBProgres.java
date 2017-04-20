package model;

 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DBProgres extends DB  {
	
	

	public DBProgres() throws  SQLException {
		super();
		
		
		try {
			Class.forName("org.postgresql.Driver");
			System.out.println("Driver O.K.");
	
			//TODO Utiliser le fichier config
			// Charge le fichier du paramétrage de l'application
//			config = ResourceBundle.getBundle("domaine.properties.config"); 			
//			// Charge le fichier des strings en fonction du paramétrage de l'application
//			String prefLangue = config.getString("langue.preference"); //"domaine.properties.langue";
			
			String url = "jdbc:postgresql://localhost:5432/mescomptes";
			String user = "postgres";
			String passwd = "superutilisateur";
	
			cnx = DriverManager.getConnection(url, user, passwd);
			System.out.println("Connexion effective à DBProgres !");   
			
			dbStatement = cnx.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			System.out.println("création object db statement Ok !" );

		} catch (Exception e) {
			System.out.println("Erreur connexion database DBProgress ! ");
			e.printStackTrace();
			System.exit(0);	
		}  

	}

	@Override
	public Connection getConnexion() {
		
		return cnx;
	}

	@Override
	public Statement getCurentDb() {
		
		return dbStatement;
	}

}
