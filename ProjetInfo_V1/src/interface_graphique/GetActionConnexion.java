package interface_graphique;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.AbstractAction;

import BDD_Users.JDBCConnection;
import BDD_Users.UtilisateursAlice;

/**
 * Class permettant d'effectuer des actions sur les boutons des différentes fenêtres
 * @author Katelyne
 *
 */
public class GetActionConnexion extends AbstractAction{


	private static final long serialVersionUID = 1L;

	private FenetreConnexion fConnexion;
	private static Connection conn;
	private boolean test_id = false;
	private boolean test_Alice = false;
	private boolean test_Bob = false;
	
	/**
	 * Constructeur de la classe get Action Connexion
	 * @param fConnex
	 * @param texte
	 * @throws SQLException 
	 */
	public GetActionConnexion(FenetreConnexion fConnex, String texte) throws SQLException{
		super(texte);//Appel du constructeur de la classe parente ici: AbstractAction
		this.fConnexion = fConnex;
		conn = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root");
	}
		

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if(source == fConnexion.getButtonConnexion()){
			//récupération des données entrées par l'utilisateur
			String login_lu = fConnexion.getLogin().getText();
			String mdp_lu = fConnexion.getMdp().getText();
			System.out.println("Vous venez de cliquer sur connecter!");
			try {
				authentification(login_lu, mdp_lu);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}
		}
		
		else if(source == fConnexion.getNouvelAlice()){
			System.out.println("Vous venez de cliquer sur nouvel utilisateur");
			//bdd_user.add_userBDD(haha); //Nouvelle fenêtre ?
		}
	}
	
	private boolean cestBob(String login_lu, String mdp_lu){
		if(((login_lu).equals("bob")) && ((mdp_lu).equals("bob"))){
			new FenetreAdministrateur();
			System.out.println("Administrateur Bob authentifié");
			test_Bob = true;
		}
		else{
			test_Bob = false;
		}
		
		return test_Bob;
	}
	
	private boolean cestAlice(String login_lu, String mdp_lu) throws SQLException{
		
		//requete d'échange avec la BDD
		Statement stmt = conn.createStatement();
		
		String SELECT_LOGIN = "SELECT login FROM utilisateursalice";
		ResultSet rs_log = stmt.executeQuery(SELECT_LOGIN);
		String SELECT_MDP = "SELECT mdp FROM utilisateursalice";
		ResultSet rs_mdp = stmt.executeQuery(SELECT_MDP);
		String SELECT_ID = "SELECT identifiant FROM utilisateursalice";
		ResultSet rs_id = stmt.executeQuery(SELECT_ID);
		
		//boolean test_idAlice = false;
		boolean test_mdpAlice = false;
		//int identifiant;
		//PreparedStatement CORRESPONDANCE_MDP = conn.prepareStatement("SELECT mdp FROM utilisateursalice WHERE identifiant=?");

		int ligne_bdd = 0;
		
		while(test_Alice == false){
			//Parcours des Logins de la table:
			/*while(test_idAlice == false){
				// Test des logins
				rs_log.next();
				String log_table = rs_log.getString("login");
				if((log_table).equals(login_lu)){
					System.out.println("Login reconnu");
					//Récupération de l'ID de la ligne dans la BDD:
					ligne_bdd = rs_idbdd.getInt("identifiant");
					test_idAlice = true;
				}
			}*/
			while(rs_log.next()){
				String log_table = rs_log.getString("login");
				System.out.println("Login table:"+log_table);
				System.out.println("Login comparé:"+login_lu);
				if((log_table).equals(login_lu)){
					System.out.println("Login reconnu");
					//Récupération de l'ID de la ligne dans la BDD:
					ligne_bdd = rs_id.getInt("identifiant");
					break;
				}
			}
			//Parcours des Mdp de la table:
			while(test_mdpAlice == false){
				//CORRESPONDANCE_MDP.setInt(1, ligne_bdd);
				//CORRESPONDANCE_MDP.executeQuery();
				String mdp_table = rs_mdp.getString(ligne_bdd);
				System.out.println("Mot de passe correspondant:"+mdp_table);
				System.out.println("Mot de passe comparé:"+mdp_lu);
				
				if((mdp_table).equals(mdp_lu)){
					System.out.println("Mdp reconnu");
					test_mdpAlice = true;
					test_Alice = true;
				}
				else{
					System.out.println("Mdp non trouvé dans la BDD");
					fConnexion.getMessage().setText("Mdp incorrect");
					test_mdpAlice = false;
				}	
			}
		}
		
		conn.close();
		rs_log.close();
		rs_mdp.close();
		rs_id.close();
		
		return test_Alice;
	}
	
	private boolean authentification(String login_lu, String mdp_lu) throws SQLException, ClassNotFoundException{
		
		while(test_id == false){
			
			//Si les JtextField sont vides:
			if((login_lu.isEmpty()==true) | (mdp_lu.isEmpty()==true)){
				System.out.println("Les deux champs JtextField sont vides");
				fConnexion.getMessage().setText("Login OU Mot de Passe non renseignés");
				test_id = false;
				break;
			}
			
			//Si les JtextField ne sont pas vides:
			else if((login_lu.isEmpty()!=true) && (mdp_lu.isEmpty()!=true)){
		
				//L'Administrateur s'authentifie:
				if(cestBob(login_lu,mdp_lu)==true){
					new FenetreAdministrateur();
					System.out.println("Administrateur Bob authentifié");
					test_id = true;
				}
				
				//Une Alice s'authentifie:
				if(cestAlice(login_lu,mdp_lu)==true){
					new FenetrePrincipale();
					System.out.println("Utilisateur Alice authentifié");
					test_id = true;
				}
				
				//C'est ni Alice ni Bob
				else{
					System.out.println("C'est ni Alice ni Bob");
					fConnexion.getMessage().setText("Login ou Mot de Passe incorrect");
					test_id = false;
					break;
				}
						
			}//fin else if (les champs sont plein)
		}//fin while authentification
		
		return test_id;
	}
}



