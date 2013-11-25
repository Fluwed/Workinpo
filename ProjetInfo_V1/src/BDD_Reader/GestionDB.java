package BDD_Reader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class GestionDB 
{
	
	/** Service Connexion. */
	private Connection connexion;
	/** driver JDBC. */
	private String jdbc = "org.hsqldb.jdbcDriver";
	/** mode mémoire. */
	private String database = "jdbc:hsqldb:file:database";
 
	/** utilisateur qui se connecte à la base de données. */
	private String user = "sa";
 
	/** mot de passe pour se connecter à la base de données. */
	private String password = "";
	
	
	public void coDB()
	{
		try {
			// On commence par charger le driver JDBC d'HSQLDB
			Class.forName(jdbc).newInstance();
			} 
		catch (InstantiationException e) 
			{
			// Auto-generated catch block
				e.printStackTrace();
			} 
		catch (IllegalAccessException e) 
			{
			// Auto-generated catch block
				e.printStackTrace();
			}
		catch(ClassNotFoundException e)
			{
		    System.out.println("La classe "+jdbc+"n'a pas été trouvée");
		    e.printStackTrace();
			}
	
		try {
			connexion = DriverManager.getConnection(database, user, password);
			}
		catch (SQLException e) 
			{
			e.printStackTrace();
			}	
	}	
	
	public void arretDB() throws SQLException 
	{
		Statement st = connexion.createStatement();
		// On envoie l'instruction pour arreter proprement HSQLDB
		st.execute("SHUTDOWN");
		// On ferme la connexion
		connexion.close();
	}
	
	/**
	 * Execute la requete passée en paramètre.
	 * @param requete contient la requête SQL
	 * @throws SQLException SQL exception
	 */
	public void executeUpdate(String requete) throws SQLException 
	{
		Statement statement = connexion.createStatement();
		statement.executeUpdate(requete);
	}
	
	/**
	 * Execute la requete passée en paramètre.
	 * @param requete contient la requête SQL
	 * @throws SQLException
	 */
	public ResultSet executeQuery(String requete) throws SQLException 
	{
		Statement statement = connexion.createStatement();
		ResultSet resultat = statement.executeQuery(requete);
		return resultat;
	}

	// Entrée de variable dans la base de données "Flux"
	public void EntreeBase(String Titre,String Date, String Description,String Lien,String Tflux) throws SQLException {
		try {
		    PreparedStatement pstmt = connexion.prepareStatement("INSERT INTO Flux (Titre,Date,Description,Lien,Tflux) VALUES (?,?,?,?,?) ");
		    pstmt.setString(1, Titre );
		    pstmt.setString(2, Date);
		    pstmt.setString(3, Description);
		    pstmt.setString(4, Lien);
		    pstmt.setString(5, Tflux);
		    pstmt.executeUpdate();
			}
		catch (SQLException e){}
	}
	
	public void Affiche() throws SQLException{
	    coDB();
	    String requeteTableUsers = "CREATE TABLE IF NOT EXISTS Flux (Titre VARCHAR(256) PRIMARY KEY, Date VARCHAR(256), Description VARCHAR(256),Lien VARCHAR(256),Tflux VARCHAR(256))";
		try {
			executeUpdate(requeteTableUsers);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
	    ResultSet resultat = executeQuery("SELECT * FROM Flux");
		// On parcours le résultat et on l'affiche
	    System.out.format("%-103s%-41s%-250s%-199s%s","Titre","Date","Description","Lien","Tflux");
	    while (resultat.next()) {
	    	System.out.println("");
	    	System.out.format("%-100s%-40s%-250s%-200s%s", resultat.getString("Titre"),"   " + resultat.getString("Date"), "   " + resultat.getString("Description"),"   " + resultat.getString("Lien"), "   " + resultat.getString("Tflux") );
	    }
	    System.out.println("");
		// On arrete la Bdd
		arretDB();}
}



