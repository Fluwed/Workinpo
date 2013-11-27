package interface_graphique;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Fenêtre Administrateur accessible uniquement par Bob
 * @author Katelyne
 *
 */
public class FenetreAdministrateur extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JButton collecter = new JButton(new GetActionAdministrateur(this,"Collecter"));
	private JLabel etat_collecte = new JLabel();
	private JLabel nb_article_initial = new JLabel();
	private JLabel nb_article_final = new JLabel();
	
	//Définition des get pour accès dans GetActionAdministrateur
	public JButton getCollecter() {
		return collecter;
	}

	public JLabel getEtat_collecte() {
		return etat_collecte;
	}

	public JLabel getNb_article_initial() {
		return nb_article_initial;
	}

	public JLabel getNb_article_final() {
		return nb_article_final;
	}

	/**
	 * Constructeur de la classe
	 */
	public FenetreAdministrateur(){
		super();// on appelle le constructeur de la classe JFrame ?
 
		build();//On initialise notre fenêtre
	}
	
	/**
	 * Méthode de définition de la forme de la fenêtre
	 */
	private void build(){
		setTitle("CheshireRSS_modeAdmin");//Nom application
		setSize(500,100); //Taille fenêtre (horizontal,vertical)
		setLocationRelativeTo(null); //Position
		setResizable(true); //On permet le redimensionnement de la fenêtre
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix
		setContentPane(buildContentPane());
		setVisible(true);
		setAlwaysOnTop(true);//Toujours au premiers plan
	}
	
	/**
	 * Création des éléments de la fenêtre administrateur
	 * Cette méthode permet de créer tous les éléments de notre fenêtre, 
	 * à savoir les boutons collecter et déconnexion
	 * @return JPanel
	 */
	private JPanel buildContentPane() {
		
		JPanel panelBouton = new JPanel();
		JPanel panelInfo_collecte = new JPanel();
		
		panelBouton.setLayout(new FlowLayout());
		panelInfo_collecte.setLayout(new GridLayout(3,1));
		
		rootPane.setDefaultButton(collecter); //Rend le bouton connexion accessible via la touche Entree
		panelBouton.add(collecter);
		panelInfo_collecte.add(etat_collecte);
		panelInfo_collecte.add(nb_article_initial);
		panelInfo_collecte.add(nb_article_final);
		
		panelBouton.add(panelInfo_collecte);
		
		return panelBouton;
	}

	
	public FenetreAdministrateur getFenetre(){
		return this;
	}
	
//	public static void main(String[] args){
//		SwingUtilities.invokeLater(new Runnable(){
//			public void run(){
//				//On crée une nouvelle instance de notre JDialog
//				FenetreAdministrateur fAdmin = new FenetreAdministrateur();
//				fAdmin.setVisible(true);//On la rend visible
//			}
//		});
//	}

}
