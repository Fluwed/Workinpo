package interface_graphique;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.SQLException;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * Fenetre de connexion à l'application 
 * @author Katelyne then Fluwed
 * Cette fenêtre permet à l'utilisateur (Bob ou Alice) de s'authentifier pour acceder à son profil
 */
public class FenetreConnexion extends JDialog{

	private static final long serialVersionUID = 1L;
	
	private JTextField loginText;
	private JPasswordField mdpText;
	private JLabel message;
	private JButton connexion;
	private JButton nouvelAlice;
	
	/**
	 * Constructeur de la classe FenetreConnexion
	 * @throws SQLException 
	 */
	public FenetreConnexion() throws SQLException{
		super();
		build();//Initialisation des paramètres de la fenêtre
	}
	
	/**
	 * Méthode de définition de la forme de la fenêtre de connexion
	 * @throws SQLException 
	 */
	public void build() throws SQLException{
		setSize(250,200);//Taille de la fenêtre
		setTitle("Connexion");//Titre de la fenêtre
		setVisible(true);//Fenêtre visible car True
		setLocationRelativeTo(null);//Placement de la fenêtre au centre de l'écran
		setResizable(false);//On interdit le redimensionnement
		setContentPane(buildContentPane());
		setAlwaysOnTop(true);//Toujours au premiers plan
	}
	
	/**
	 * Méthode pour ajouter les composants de la fenêtre
	 * @return
	 * @throws SQLException 
	 */
	private Container buildContentPane() throws SQLException {
		JPanel panelgeneral = new JPanel();
		JPanel panelid = new JPanel();
		JPanel panelmessage = new JPanel();
		
		panelgeneral.setLayout(new BorderLayout());
		panelid.setLayout(new GridLayout(3,2,5,5));

		loginText = new JTextField();
		Font police = new Font("Arial", Font.BOLD, 14);
		loginText.setFont(police);
		mdpText = new JPasswordField();
		connexion = new JButton(new GetActionConnexion(this,"GO"));//this correspond à la FenetreConnexion
		message = new JLabel();
		nouvelAlice = new JButton(new GetActionConnexion(this,"New User"));
		
		panelid.add(new JLabel("Login"));
		panelid.add(loginText);
		panelid.add(new JLabel("Mot de passe"));
		panelid.add(mdpText);
		panelid.add(connexion);
		panelid.add(nouvelAlice);
		
		panelmessage.add(message);
		
		panelgeneral.add(panelid,BorderLayout.NORTH);
		panelgeneral.add(panelmessage,BorderLayout.SOUTH);

		return panelgeneral;
	}
	
	/**
	 * Methodes qui récupère le texte entré dans les JTextField
	 * @return
	 */
	
	public JTextField getLogin(){
		return loginText;
	}
	public JTextField getMdp(){
		return mdpText;
	}
	public JLabel getMessage(){
		return message;
	}
	
	public JButton getNouvelAlice(){
		return nouvelAlice;
	}
	
	public JButton getButtonConnexion(){
		return connexion;
	}

	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				FenetreConnexion fTest = null;
				try {
					fTest = new FenetreConnexion();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				fTest.setVisible(true);
			}
		});
		
		/*
		try{
			Thread.sleep(10000);
		} catch (InterruptedException e) {}
		
		System.exit(0);
		*/
	}

}
