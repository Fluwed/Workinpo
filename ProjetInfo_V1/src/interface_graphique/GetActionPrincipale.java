package interface_graphique;
import java.awt.event.ActionEvent;

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
		/*if(source == fPrincipale.getButtonRefresh()){
			System.out.println("Vous venez de cliquer sur Refresh");
		}
		
		else if(source == fPrincipale.getButtonDeconnexion()){
			System.out.println("Vous venez de cliquer sur déconnecter");
		}
		*/

	}
}
