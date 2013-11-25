package BDD_Flux;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC {

	private static Connection conn;
	
	public void arretDB() throws SQLException 
	{
		Statement st = conn.createStatement();
		// On envoie l'instruction pour arreter proprement HSQLDB
		st.execute("SHUTDOWN");
		// On ferme la connexion
		conn.close();
	}
	
	public Connection ConnexionDB() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost/Test?user=root"); //Nom de la BDD dans phpmyAdmin
		return conn;
		}
	
	public void NewTable(String NomTable) throws SQLException{ // Creation des tables a partir de java sans passer par wamp

		Statement statement = conn.createStatement();
		statement.executeUpdate("CREATE TABLE IF NOT EXISTS "+NomTable+" (Titre VARCHAR(256) PRIMARY KEY, Date VARCHAR(256), Description text,Lien VARCHAR(512))");
	}
	
	 /* Execute la requete passée en paramètre.*/
	 
		public ResultSet executeQuery(String NomTable) throws SQLException {
			Statement statement = conn.createStatement();
			ResultSet resultat = statement.executeQuery(NomTable);
			return resultat;
		}
		
		

}
