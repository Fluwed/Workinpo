package interface_graphique;

import Rss.JDBC;
import Rss.JDBCFlux;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.AbstractAction;
/**
 * Class permettant d'effectuer des actions sur les boutons de la fenêtre Administrateur
 * @author Katelyne
 *
 */
public class GetActionAdministrateur extends AbstractAction{

	private static final long serialVersionUID = 1L;
	private FenetreAdministrateur fAdministrateur;// Creation d'une fenêtre de connexion
	private static Connection conn;
	private static JDBC base = new JDBC();

	/**
	 * Constructeur de la classe
	 * @param fConnex : une fenêtre de connexion
	 * @param texte
	 */
	public GetActionAdministrateur(FenetreAdministrateur fAdmin, String texte){
		super(texte);//Appel du constructeur de la classe parente ici: AbstractAction

		this.fAdministrateur = fAdmin;
	}

	/** 
	 * Action effectuée lorsqu'on appuie sur le bouton. 
	 * On va décrire dans cette fonction quel programme effectuer
	 * en fonction de l'objet sélectionné.
	 * @param ActionEvent e : evènement : clic sur le bouton
	 */
	public void actionPerformed(ActionEvent e){

		Object source = e.getSource();

		if(source == fAdministrateur.getCollecter()){

			long time_fin=System.currentTimeMillis() +5000;

			//Affichage du nombre initial d'articles
			//Cet Affichage va se faire directement dans la fenêtre avant même qu'on ne clique sur Collecter
			afficherNbArticle("Nombre d'articles initial : ",fAdministrateur);
			new JDBCFlux();
			//Collecte de données de tous les flux
			while(System.currentTimeMillis() <= time_fin){
				try {}
				catch (Exception e1) {e1.printStackTrace();}
			}
			//Affichage du nombre final d'articles 
			afficherNbArticle("Nombre d'articles final : ",fAdministrateur);
			fAdministrateur.getEtat_collecte().setText("\n Collecte terminée ! \n");
		}

		if(source == fAdministrateur.getFermer()){
			fAdministrateur.dispose();
			try {
				new FenetreConnexion();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}


	}

	/*else if (source == fAdministrateur.getButtonDeconnexion()){
			System.out.println("Vous venez de cliquer sur Deconnexion");
		}*/		


public static int get_nbArticle() throws SQLException, ClassNotFoundException{
	Connection conn = base.ConnexionDB();
	String [] nomTableFlux = {"LeMonde", "LeFigaro","Minutes","google","liberation","rue89","lesechos","equipe","Humanite","NYTimes"};
	Statement stmt = conn.createStatement();
	int nbArticle=0;
	for(int i=0;i<nomTableFlux.length;i++){
		String SELECT_ID =("SELECT * FROM "+nomTableFlux[i]+"");
		ResultSet rs = stmt.executeQuery(SELECT_ID);
		rs.last();
		nbArticle += rs.getRow();
		rs.beforeFirst();
	}
	return nbArticle;
}

public static void afficherNbArticle(String texte, FenetreAdministrateur f){
	int nbArticle=0;
	try {
		nbArticle = get_nbArticle();
		String affichage = (texte+nbArticle+"\n");
		System.out.println(nbArticle);
		f.getNb_article_initial().setText(affichage);
	} 
	catch (ClassNotFoundException e2) {e2.printStackTrace();}
	catch (SQLException e2) {e2.printStackTrace();}
}

}
