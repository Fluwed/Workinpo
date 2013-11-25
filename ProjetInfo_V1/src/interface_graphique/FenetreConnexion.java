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
 * Fenetre de connexion � l'application 
 * @author Katelyne then Fluwed
 * Cette fen�tre permet � l'utilisateur (Bob ou Alice) de s'authentifier pour acceder � son profil
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
		build();//Initialisation des param�tres de la fen�tre
	}
	
	/**
	 * M�thode de d�finition de la forme de la fen�tre de connexion
	 * @throws SQLException 
	 */
	public void build() throws SQLException{
		setSize(250,200);//Taille de la fen�tre
		setTitle("Connexion");//Titre de la fen�tre
		setVisible(true);//Fen�tre visible car True
		setLocationRelativeTo(null);//Placement de la fen�tre au centre de l'�cran
		setResizable(false);//On interdit le redimensionnement
		setContentPane(buildContentPane());
		setAlwaysOnTop(true);//Toujours au premiers plan
	}
	
	/**
	 * M�thode pour ajouter les composants de la fen�tre
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
		connexion = new JButton(new GetActionConnexion(this,"GO"));//this correspond � la FenetreConnexion
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
	 * Methodes qui r�cup�re le texte entr� dans les JTextField
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
