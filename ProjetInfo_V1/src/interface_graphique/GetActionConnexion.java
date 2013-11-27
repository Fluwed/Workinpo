package interface_graphique;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
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
			try {
				new FenetreNewUser();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	private boolean cestBob(String login_lu, String mdp_lu){
		if(((login_lu).equals("bob")) && ((mdp_lu).equals("bob"))){
			System.out.println("Administrateur Bob authentifié");
			test_Bob = true;
		}
		else{
			test_Bob = false;
		}

		return test_Bob;
	}

	private boolean cestAlice(String login_lu, String mdp_lu) throws SQLException{
		
		boolean cestAliceReturn = false;
		//requete d'échange avec la BDD
		Statement stmt = conn.createStatement();

		stmt.executeUpdate("CREATE TABLE IF NOT EXISTS utilisateursalice (login VARCHAR(256) PRIMARY KEY, mdp VARCHAR(256))");
		String SELECT_LOGIN = "SELECT login FROM utilisateursalice";
		ResultSet rs_log = stmt.executeQuery(SELECT_LOGIN);
		String SELECT_MDP = "SELECT mdp FROM utilisateursalice";
		//Stockage des logins dans une liste
		List <String> listLogin = new ArrayList <String> ();
		while(rs_log.next()){
			String login = rs_log.getString("login");
			listLogin.add(login);
		}
		ResultSet rs_mdp = stmt.executeQuery(SELECT_MDP);
		//Stockage des mdp dans une liste
		List <String> listMdp = new ArrayList <String> ();
		while(rs_mdp.next()){
			String mdp = rs_mdp.getString("mdp");
			listMdp.add(mdp);
		}

		int numIncrement = -1;

		Iterator<String> itLogin = listLogin.iterator();
		while(itLogin.hasNext() && cestAliceReturn == false){
			numIncrement+=1;
			String log_table = itLogin.next();
			System.out.println("Login table:"+log_table);
			System.out.println("Login comparé:"+login_lu);
			if((log_table).equals(login_lu)){
				System.out.println("Login reconnu");

				String mdp_table = listMdp.get(numIncrement);
				System.out.println("Mot de passe correspondant:"+mdp_table);
				System.out.println("Mot de passe comparé:"+mdp_lu);

				if((mdp_table).equals(mdp_lu)){
					System.out.println("Mdp reconnu");
					cestAliceReturn = true;
					return true;
				}
				else{
					System.out.println("Login Ok mais Mdp non trouvé dans la BDD");
					cestAliceReturn = false;
					//return false;						
				}
			}
			else {
			//	System.out.println("Mauvais Login je ne testerai meme pas le mdp! NIQUE TOUT");
				cestAliceReturn = false;
			}
		}

	//conn.close();
	rs_log.close();
	rs_mdp.close();
	
	return cestAliceReturn;
}

private boolean authentification(String login_lu, String mdp_lu) throws SQLException, ClassNotFoundException{

	while(test_id == false){

		//Si les JtextField sont vides:
		if((login_lu.isEmpty()==true) || (mdp_lu.isEmpty()==true)){
			System.out.println("Les deux champs JtextField sont vides");
			fConnexion.getMessage().setText("Login OU Mot de Passe non renseignés");
			test_id = false;
			break;
		}

		//Si les JtextField ne sont pas vides:
		else if((login_lu.isEmpty()!=true) && (mdp_lu.isEmpty()!=true)){

			//L'Administrateur s'authentifie:
			if(cestBob(login_lu,mdp_lu)==true){
				fConnexion.dispose(); // fermer la fenetre de connexion
				new FenetreAdministrateur();
				System.out.println("Administrateur Bob authentifié");
				test_id = true;
			}

			//Une Alice s'authentifie:
			else if(cestAlice(login_lu,mdp_lu)==true){
				new FenetrePrincipale();
				System.out.println("Utilisateur "+login_lu +" authentifié");
				test_id = true;
				fConnexion.dispose(); // fermer la fenetre de connexion
				
			}

			//C'est ni Alice ni Bob
			else{
				System.out.println("Authentification Failed -> LOOOOSER");
				fConnexion.getMessage().setText("Login ou Mot de Passe incorrect");
				test_id = false;
				break;
			}

		}//fin else if (les champs sont plein)
	}//fin while authentification

	return test_id;
}
}



