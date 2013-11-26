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
				fp.setVisible(true);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}	
		
		else if(source == fPrincipale.getMinutes()){
				try {
					FenetrePrincipale fp = new FenetrePrincipale("Minutes");
					fp.setVisible(true);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
		
		else if(source == fPrincipale.getLiberation()){
			try {
				FenetrePrincipale fp = new FenetrePrincipale("liberation");
				fp.setVisible(true);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		else if(source == fPrincipale.getGoogle()){
			try {
				FenetrePrincipale fp = new FenetrePrincipale("google");
				fp.setVisible(true);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		else if(source == fPrincipale.getRue89()){
			try {
				FenetrePrincipale fp = new FenetrePrincipale("rue89");
				fp.setVisible(true);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		else if(source == fPrincipale.getEchos()){
			try {
				FenetrePrincipale fp = new FenetrePrincipale("lesechos");
				fp.setVisible(true);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		else if(source == fPrincipale.getEquipe()){
			System.out.println("Vous venez de cliquer sur LeFigaro");
			try {
				FenetrePrincipale fp = new FenetrePrincipale("equipe");
				fp.setVisible(true);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		else if(source == fPrincipale.getHumanite()){
			System.out.println("Vous venez de cliquer sur LeFigaro");
			try {
				FenetrePrincipale fp = new FenetrePrincipale("Humanite");
				fp.setVisible(true);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		else if(source == fPrincipale.getNytimes()){
			System.out.println("Vous venez de cliquer sur LeFigaro");
			try {
				FenetrePrincipale fp = new FenetrePrincipale("NYTimes");
				fp.setVisible(true);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
}
}
