package interface_graphique;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Fen�tre Administrateur accessible uniquement par Bob
 * @author Katelyne
 *
 */
public class FenetreAdministrateur extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JButton collecter = new JButton(new GetActionAdministrateur(this,"Collecter"));
	private JLabel etat_collecte = new JLabel();
	private JLabel nb_article_initial = new JLabel();
	private JLabel nb_article_final = new JLabel();
	private JButton fermer = new JButton(new GetActionAdministrateur(this,"Fermer"));
	
	//D�finition des get pour acc�s dans GetActionAdministrateur
	public JButton getCollecter() {
		return collecter;
	}
	
	public JButton getFermer(){
		return fermer;		
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
 
		build();//On initialise notre fen�tre
	}
	
	/**
	 * M�thode de d�finition de la forme de la fen�tre
	 */
	private void build(){
		setTitle("CheshireRSS_modeAdmin");//Nom application
		setSize(500,100); //Taille fen�tre (horizontal,vertical)
		setLocationRelativeTo(null); //Position
		setResizable(false); //On ne permet pas le redimensionnement de la fen�tre
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit � l'application de se fermer lors du clic sur la croix
		setContentPane(buildContentPane());
		setVisible(true);
		setAlwaysOnTop(true);//Toujours au premiers plan
	}
	
	/**
	 * Cr�ation des �l�ments de la fen�tre administrateur
	 * Cette m�thode permet de cr�er tous les �l�ments de notre fen�tre, 
	 * � savoir les boutons collecter et d�connexion
	 * @return JPanel
	 */
	private JPanel buildContentPane() {
		
		JPanel panelgeneral = new JPanel();
		JPanel panelBouton = new JPanel();
		JPanel panelinfo = new JPanel();
		
		panelgeneral.setLayout(new BorderLayout());
		panelBouton.setLayout(new FlowLayout());
		panelinfo.setLayout(new FlowLayout());
		
		rootPane.setDefaultButton(collecter);//Rend le bouton connexion accessible via la touche Entree
		panelBouton.add(fermer);
		panelBouton.add(collecter);
		panelinfo.add(etat_collecte);
		panelinfo.add(nb_article_initial);
		panelinfo.add(nb_article_final);
		
	
		panelgeneral.add(panelBouton,BorderLayout.NORTH);
		panelgeneral.add(panelinfo,BorderLayout.SOUTH);
		
		return panelgeneral;
	}

	
	public FenetreAdministrateur getFenetre(){
		return this;
	}
	
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				//On cr�e une nouvelle instance de notre JDialog
				FenetreAdministrateur fAdmin = new FenetreAdministrateur();
				fAdmin.setVisible(true);//On la rend visible
			}
		});
	}

}
