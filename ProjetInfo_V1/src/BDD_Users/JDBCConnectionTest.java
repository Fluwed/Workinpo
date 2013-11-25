package BDD_Users;

import static org.junit.Assert.*;

import java.sql.ResultSet;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.Iterator;
//import java.util.Set;
//import java.sql.DriverManager;
//import java.sql.SQLException;

import java.sql.Statement;

import org.junit.Before;
import org.junit.Test;

public class JDBCConnectionTest {
	
	//private static Connection conn;
	//private final static int NOMBRE_USERS = 4;
	JDBCConnection bdd_users;
	UtilisateursAlice user_test1;
	UtilisateursAlice user_test2;
	UtilisateursAlice user_testcurrent;
	
	/**
	 * Ajout d'utilisateurs g�n�r�s al�atoirement
	 * @param args
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	/*public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.jdbc.Driver");
		// Setup the connection with the DB
		conn = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root"); // Nom de la BDD dans phpmyadmin

		// Ajout des utilisateurs g�n�r� al�atoirement
		Set<UtilisateursAlice> listAlice = UtilisateursAlice.usersAleatoire(NOMBRE_USERS);
		Iterator<UtilisateursAlice> ite = listAlice.iterator();
		System.out.println("Liste d'utilisateur al�atoires initialis�e");
		// Boucle d'insertion des nouveaux utilisateurs dans la BDD
		while (ite.hasNext()) {
			UtilisateursAlice user = (UtilisateursAlice) ite.next();
			String myRequest = "INSERT INTO utilisateursalice VALUES (?,?,?)";
			PreparedStatement preparestat = conn.prepareStatement(myRequest);
			preparestat.setString(1, null);
			preparestat.setString(2, user.getLogin());
			preparestat.setString(3, user.getMdp());
			preparestat.execute();
			System.out.println("user al�atoire ajout�");
		}
	}*/
	
	@Before
	public void initialisation_varTest(){
		bdd_users = new JDBCConnection();
		user_test1 = new UtilisateursAlice("Toto","123456");
		user_test2 = new UtilisateursAlice("Arnaud","123456");
	}
	
	@Test
	public void test_add_userBDD() throws ClassNotFoundException, SQLException {
		System.out.println("Test de la m�thode add_userBDD()");
		assertTrue(!(bdd_users.add_userBDD(user_test1))); // !(...) si user d�j� ajout� (...) sinon
	}

}
