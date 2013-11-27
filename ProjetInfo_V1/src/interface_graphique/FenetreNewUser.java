package interface_graphique;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.sql.SQLException;





import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * Fenetre de connexion à l'application 
 * @author Fluwed
 * Cette fenêtre permet à l'utilisateurAlice de senregistrer
 */
public class FenetreNewUser extends JDialog{

	private static final long serialVersionUID = 1L;
	
	
	private JTextField newLogin;
	private JPasswordField newMdp1;
	private JPasswordField newMdp2;
	private JLabel message1;
	private JLabel message2;
	private JButton enregistrer;
	private JButton annuler;
	
	/**
	 * Constructeur de la classe FenetreConnexion
	 * @throws SQLException 
	 */
	public FenetreNewUser() throws SQLException{
		build();//Initialisation des paramètres de la fenêtre
	}
	
	/**
	 * Méthode de définition de la forme de la fenêtre de connexion
	 * @throws SQLException 
	 */
	public void build() throws SQLException{
		setSize(300,220);//Taille de la fenêtre
		setTitle("Nouvel Utilisateur");//Titre de la fenêtre
		setVisible(true);//Fenêtre visible car True
		setLocationRelativeTo(null);//Placement de la fenêtre au centre de l'écran
		setResizable(true);//On interdit le redimensionnement
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
		JRootPane rootPane = getRootPane(); // utilité?
		
		panelgeneral.setLayout(new BorderLayout());
		panelid.setLayout(new GridLayout(4,2,5,5));

		
		newLogin = new JTextField();
		Font police = new Font("Arial", Font.BOLD, 14);
		newLogin.setFont(police);
		newMdp1 = new JPasswordField();
		newMdp2 = new JPasswordField();
		enregistrer = new JButton(new GetActionNewUser(this,"enregistrer")); //this correspond à la FenetreNewUser
		rootPane.setDefaultButton(enregistrer); //Rend le bouton connexion accessible via la touche Entree
		message1 = new JLabel();
		message2 = new JLabel();
		annuler = new JButton(new GetActionNewUser(this,"annuler"));
		
		panelid.add(new JLabel("                                Login"));
		panelid.add(newLogin);
		panelid.add(new JLabel("                 Mot de passe"));
		panelid.add(newMdp1);
		panelid.add(new JLabel(" Retaper Mot de passe"));
		panelid.add(newMdp2);
		panelid.add(message1);
		panelid.add(message2);
		
		panelmessage.add(annuler);
		panelmessage.add(enregistrer);
		
		
		
		panelgeneral.add(panelid,BorderLayout.CENTER);
		//panelgeneral.add(message,BorderLayout.NORTH);
		panelgeneral.add(panelmessage,BorderLayout.SOUTH);

		return panelgeneral;
	}
	
	/**
	 * Methodes qui récupère le texte entré dans les JTextField
	 * @return
	 */
	
	public JTextField getLogin(){
		return newLogin;
	}
	public JTextField getMdp1(){
		return newMdp1;
	}
	public JTextField getMdp2(){
		return newMdp2;
	}
	public JLabel getMessage1(){
		return message1;
	}
	public JLabel getMessage2(){
		return message2;
	}
	public JButton getEnregistrer(){
		return enregistrer;
	}
	
	public JButton getannuler(){
		return annuler;
	}

	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				FenetreNewUser fTest = null;
				try {
					fTest = new FenetreNewUser();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				fTest.setVisible(true);
			}
		});
		
		
//		try{
//			Thread.sleep(10000);
//		} catch (InterruptedException e) {}
//		
		//System.exit(0);
		
	}

}
