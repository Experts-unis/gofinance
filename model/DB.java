package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;


public abstract class DB {

	String url ;
	String user ;
	String passwd;
	static public Connection cnx;
	static public Statement dbStatement; 
	
	
	public DB() {
		
		url = "jdbc:postgresql://localhost:5432/mescomptes";
		user = "postgres";
		passwd = "superutilisateur";
		cnx = null;
		dbStatement=null;
		
	}
	public abstract Connection getConnexion();
	public abstract Statement  getCurentDb();

}
