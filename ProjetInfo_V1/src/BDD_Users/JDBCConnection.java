package BDD_Users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.Iterator;
//import java.util.Set;

/**
 * Class test pour l'ajout d'utilisateurs al�atoire � la BDD
 * 
 * @author Katelyne
 * 
 */
public class JDBCConnection {

	private static Connection conn;
	
	
	public boolean add_userBDD(UtilisateursAlice user) throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		// Setup the connection with the DB
		conn = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root"); // Nom de la BDD dans phpmyadmin
		
		String SELECT_LOGIN = "SELECT login FROM utilisateursalice"; //
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(SELECT_LOGIN); //RS se parcourt it�rativement (stockage des logins de la table)
		boolean exist=false;

			System.out.println("Entr�e dans la boucle while");
			while(rs.next()){
				//R�cup�ration du login des lignes de la table
				String log_current = rs.getString("login");
				System.out.println("valeur table:" +log_current);
				System.out.println("valeur test�e:"+user.getLogin());
				if((log_current).equals(user.getLogin())==true){
					//Le login de l'utilisateur existe d�j�
					System.out.println("L'utilisateur existe d�j�!");
					exist=true;
					break;
				}
				else{
					System.out.println("L'utilisateur peut �tre cr��");
					exist=false;
				}
			System.out.println("Fin de la boucle while");
		}
		if(exist==true){
			System.out.println("exist=true");
			return false;
		}
		else{
			System.out.println("exist=false");
			String myRequete = "INSERT INTO utilisateursalice VALUES (?,?,?)";
			PreparedStatement preparestat = conn.prepareStatement(myRequete);
			preparestat.setString(1, null);
			preparestat.setString(2, user.getLogin());
			preparestat.setString(3, user.getMdp());
			preparestat.execute();
			System.out.println("user console ajout�");
			return true;
		}
	}

}
