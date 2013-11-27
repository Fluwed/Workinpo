package interface_graphique;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.AbstractAction;

/**
 * Class permettant d'effectuer des actions sur les boutons de la fenêtre Principale
 * @author Katelyne
 *
 */
public class GetActionPrincipale extends AbstractAction {
	
	private static final long serialVersionUID = 1L;
	
//// Fix Le Monde, leFigaro L'humanité
	
	private FenetrePrincipale fPrincipale;// Creation d'une fenêtre de connexion
	/**
	 * Constructeur de la classe
	 * @param fConnex
	 * @param texte
	 */
	public GetActionPrincipale(FenetrePrincipale fPrincip, String texte){
		super(texte);//Appel du constructeur de la classe parente ici: AbstractAction
			
		this.fPrincipale = fPrincip;
	}
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		/**
		 * Actions concernant la fenêtre de connexion
		 */
		if(source == fPrincipale.getLemonde()){
			System.out.println("Vous venez de cliquer sur LeMonde");
			try {
				FenetrePrincipale fp = new FenetrePrincipale("LeMonde");
				fPrincipale.dispose();
				fp.setVisible(true);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		else if(source == fPrincipale.getLefigaro()){
			System.out.println("Vous venez de cliquer sur LeFigaro");
			try {
				FenetrePrincipale fp = new FenetrePrincipale("LeFigaro");
				fPrincipale.dispose();
				fp.setVisible(true);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}	
		
		else if(source == fPrincipale.getMinutes()){
			System.out.println("Vous venez de cliquer sur Le 20 Minutes");
				try {
					FenetrePrincipale fp = new FenetrePrincipale("Minutes");
					fPrincipale.dispose();
					fp.setVisible(true);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
		
		else if(source == fPrincipale.getLiberation()){
			System.out.println("Vous venez de cliquer sur Liberation");
			try {
				FenetrePrincipale fp = new FenetrePrincipale("liberation");
				fPrincipale.dispose();
				fp.setVisible(true);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		else if(source == fPrincipale.getGoogle()){
			System.out.println("Vous venez de cliquer sur GoogleNews");
			try {
				FenetrePrincipale fp = new FenetrePrincipale("google");
				fPrincipale.dispose();
				fp.setVisible(true);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		else if(source == fPrincipale.getRue89()){
			System.out.println("Vous venez de cliquer sur Rue89");
			try {
				FenetrePrincipale fp = new FenetrePrincipale("rue89");
				fPrincipale.dispose();
				fp.setVisible(true);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		else if(source == fPrincipale.getEchos()){
			System.out.println("Vous venez de cliquer sur Les Echos");
			try {
				FenetrePrincipale fp = new FenetrePrincipale("lesechos");
				fPrincipale.dispose();
				fp.setVisible(true);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		else if(source == fPrincipale.getEquipe()){
			System.out.println("Vous venez de cliquer sur L'équipe");
			try {
				FenetrePrincipale fp = new FenetrePrincipale("equipe");
				fPrincipale.dispose();
				fp.setVisible(true);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		else if(source == fPrincipale.getHumanite()){
			System.out.println("Vous venez de cliquer sur L'Humanité");
			try {
				FenetrePrincipale fp = new FenetrePrincipale("Humanite");
				fPrincipale.dispose();
				fp.setVisible(true);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		else if(source == fPrincipale.getNytimes()){
			System.out.println("Vous venez de cliquer sur Le New York Times");
			try {
				FenetrePrincipale fp = new FenetrePrincipale("NYTimes");
				fPrincipale.dispose();
				fp.setVisible(true);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
}
}
