package interface_graphique;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.AbstractAction;


/**
 * Class permettant d'effectuer des actions sur les boutons des différentes fenêtres
 * @author Fluwed
 *
 */
public class GetActionNewUser extends AbstractAction{


	private static final long serialVersionUID = 1L;

	private boolean test_id = false;

	private FenetreNewUser fConnexion;
	private static Connection conn;

	/**
	 * Constructeur de la classe get Action Connexion
	 * @param fConnex
	 * @param texte
	 * @throws SQLException 
	 */
	public GetActionNewUser(FenetreNewUser fConnex, String texte) throws SQLException{
		super(texte);//Appel du constructeur de la classe parente ici: AbstractAction
		this.fConnexion = fConnex;
		conn = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root");
	}


	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if(source == fConnexion.getEnregistrer()){
			//récupération des données entrées par l'utilisateur
			String login_lu = fConnexion.getLogin().getText();
			String mdp1_lu = fConnexion.getMdp1().getText();
			String mdp2_lu = fConnexion.getMdp2().getText();
			System.out.println("Vous venez de cliquer sur enregistrer!");
			try {
				Enregistrement(login_lu, mdp1_lu, mdp2_lu);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		else if(source == fConnexion.getannuler()){
			System.out.println("Retour au Menu de Connexion");
			fConnexion.dispose();
		}

	}


	private void newAlice(String login_lu, String mdp1_lu) throws SQLException{

		Statement stmt = conn.createStatement();
		stmt.executeUpdate("CREATE TABLE IF NOT EXISTS utilisateursalice (login VARCHAR(256) PRIMARY KEY, mdp VARCHAR(256))");

		String myRequete = "INSERT INTO utilisateursalice VALUES (?,?)";
		PreparedStatement prstmt = conn.prepareStatement(myRequete);

		prstmt.setString(1, login_lu);
		prstmt.setString(2, mdp1_lu);
		prstmt.execute();
		System.out.println("Ajout Effectué");
	}

	private boolean CheckAliceLogin(String login_lu) throws SQLException{

		boolean CheckAliceLoginReturn = false;
		//requete d'échange avec la BDD
		Statement stmt = conn.createStatement();

		stmt.executeUpdate("CREATE TABLE IF NOT EXISTS utilisateursalice (login VARCHAR(256) PRIMARY KEY, mdp VARCHAR(256))");
		String SELECT_LOGIN = "SELECT login FROM utilisateursalice";
		ResultSet rs_log = stmt.executeQuery(SELECT_LOGIN);
		//Stockage des logins dans une liste
		List <String> listLogin = new ArrayList <String> ();
		while(rs_log.next()){
			String login = rs_log.getString("login");
			listLogin.add(login);
		}

		int numIncrement = -1;
		Iterator<String> itLogin = listLogin.iterator();
		
		boolean check = itLogin.hasNext();
		if(check == false){
			
			System.out.println("Login Disponible");
			CheckAliceLoginReturn = true;
			return CheckAliceLoginReturn;
		}
		
		while(itLogin.hasNext()){
			numIncrement+=1;
			String log_table = itLogin.next();
			System.out.println("Login table:"+log_table);
			System.out.println("Login comparé:"+login_lu);
			if((log_table).equals(login_lu)){
				System.out.println("Login déjà existant");
				CheckAliceLoginReturn = false;

			}
			else {
				System.out.println("Login Disponible");
				CheckAliceLoginReturn = true;
			}

		}
		//		conn.close();
		rs_log.close();
		return CheckAliceLoginReturn;
	}

	private boolean CheckAliceMdp(String mdp1_lu, String mdp2_lu) throws SQLException{
		boolean CheckAliceMdpReturn = false;

		if((mdp1_lu).equals(mdp2_lu)){
			System.out.println("Les Mdp sont Identiques");
			CheckAliceMdpReturn = true;
		}
		else{
			System.out.println("Mdp Differents");
			CheckAliceMdpReturn = false;
			//return false;						
		}

		return CheckAliceMdpReturn;

	}


	private boolean Enregistrement(String login_lu, String mdp1_lu, String mdp2_lu) throws SQLException, ClassNotFoundException{

		while(test_id == false){

			//Si les JtextField sont vides:
			if((login_lu.isEmpty()==true) || (mdp1_lu.isEmpty()==true) || (mdp2_lu.isEmpty()==true)){
				System.out.println("Les champs JtextField sont vides");
				fConnexion.getMessage1().setText("               Login OU Mot de");
				fConnexion.getMessage2().setText("Passe non renseignés");
				test_id = false;
				break;
			}

			//Si les JtextField ne sont pas vides:
			else if((login_lu.isEmpty()!=true) && (mdp1_lu.isEmpty()!=true) && (mdp2_lu.isEmpty()!=true)){

				//L'Utilisateur s'enregistre
				if(CheckAliceLogin(login_lu)==true){

					if(CheckAliceMdp(mdp1_lu,mdp2_lu)==true){

						newAlice(login_lu, mdp1_lu);
						System.out.println("Utilisateur "+login_lu +" enregistré");
						test_id = true;
						fConnexion.dispose(); // fermer la fenetre de connexion
					}
					else{
						System.out.println("Check Mdp Failed");
						fConnexion.getMessage1().setText("        Les Mots de Passes");			
						fConnexion.getMessage2().setText("ne correspondent pas");
						test_id = false;
						break;

					}
				}

				else{
					System.out.println("Check Login Failed");
					fConnexion.getMessage1().setText("                           Identifiant");			
					fConnexion.getMessage2().setText("non disponible");
					test_id = false;
					break;
				}

			}//fin else if (les champs sont plein)
		}//fin while authentification

		return test_id;
	}
}



