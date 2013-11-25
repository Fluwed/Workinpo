package interface_graphique;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import BDD_Flux.JDBC;
 
/**
 * Fenetre principale donnant accès au profil de l'utilisateur
 */
public class FenetrePrincipale extends JFrame{
	
	private static Connection conn;
	private static JDBC base = new JDBC();

	private JButton lemonde = new JButton();
	private JButton lefigaro = new JButton();
	private JButton google = new JButton();
	private JButton rue89 = new JButton();
	private JButton lesechos = new JButton();
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur de la classe FenetrePrincipale
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public FenetrePrincipale(){
		super();
 
		build();//On initialise notre fenêtre
	}
	
	/**
	 * Méthode de définition de la forme de la fenêtre
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	private void build(){
		setTitle("CheshireRSS");//Nom application
		setSize(1100,400); //Taille fenêtre (horizontal,vertical)
		setLocationRelativeTo(null); //Position
		setResizable(true); //On permet le redimensionnement de la fenêtre
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix
		setContentPane(buildContentPane());
	}
	
	/**
	 * Dans la méthode suivante on créée notre JPanel pour ajouter nos composants de fenêtre
	 * (un JLabel est un composant permettant d'afficher du texte)
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	private JPanel buildContentPane(){
		
		JPanel panelGeneral = new JPanel();
		panelGeneral.setLayout(new GridLayout(1,1));
		
		/* Affichage immédiat des news présentes dans la BDD
		 * => 1 texte Area avec autant de texteArea que d'articles
		 */
		String [] nomTableFlux = {"LeMonde", "LeFigaro","Minutes","google","liberation","rue89","lesechos","equipe","Humanite","NYTimes"};
		JTextArea textArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane();
		JPanel panelNews = new JPanel();
		panelFlux(panelNews,"LeMonde");
		panelNews.add(scrollPane);
		
		
		
		/* Affichage du menu avec les différents flux + informations profil Alice
		 * 
		 */
		JPanel panelMenu = new JPanel();
		panelMenu.setLayout(new GridLayout(1,1));
		JMenuBar menu = new JMenuBar();
		
		JLabel test2 = new JLabel("Menu");
		panelMenu.add(test2);

		panelMenu.add(panelNews);
		panelGeneral.add(panelMenu);
		
		return panelGeneral;
	}
	
	public FenetrePrincipale getFenetre(){
		return this;
	}
	
	private List <String> afficheArticle(String nomFlux) throws ClassNotFoundException, SQLException{
		
		List <String> articles = new ArrayList <String> ();
		String texte = "";
		Connection conn = base.ConnexionDB();
		Statement stmt = conn.createStatement();
		String SELECT_ARTICLE = "SELECT Titre FROM "+nomFlux+" ";
		ResultSet rs = stmt.executeQuery(SELECT_ARTICLE);
		while(rs.next()){
			texte = rs.getString("Titre");
			if(!texte.equals(null)){
				articles.add(texte);
			}
		}
		return articles;
	}

	private static int get_nbArticle(String nomTable) throws SQLException, ClassNotFoundException{
		Connection conn = base.ConnexionDB();
		Statement stmt = conn.createStatement();
		int nbArticle=0;
		
		String SELECT_ID =("SELECT * FROM "+nomTable+"");
		ResultSet rs = stmt.executeQuery(SELECT_ID);
		rs.last();
		nbArticle += rs.getRow();
		rs.beforeFirst();
	
		return nbArticle;
	}
	
	private JPanel panelFlux(JPanel panelNews, String nomTable){
		
		try {
			int nbArticleFlux = get_nbArticle(nomTable);
			panelNews.setLayout(new GridLayout(nbArticleFlux+1,1));
			panelNews.setBackground(Color.white);
			
			JLabel intro = new JLabel("Liste Titres du flux"+nomTable);
			panelNews.add(intro);

			//Affichage des articles du flux LeMonde avec JLabel:
			/*List <String> titresArticles = afficheArticle(nomTable);
			List <JLabel> articlesLabel = new ArrayList <JLabel> (0);
			Iterator<String> it = titresArticles.iterator();
			
			while(it.hasNext()){
				String unTitreArticle = it.next();
				articlesLabel.add(new JLabel(unTitreArticle));
				Iterator<JLabel> it2 = articlesLabel.iterator();
				while(it2.hasNext()){
					JLabel pouet = it2.next();
					panelNews.add(pouet);
				}
			}*/
			List <String> titresArticles = afficheArticle(nomTable);
			List <JTextArea> articlesArea = new ArrayList <JTextArea> (0);
			Iterator<String> it = titresArticles.iterator();
			while(it.hasNext()){
				String unTitreArticle = it.next();
				articlesArea.add(new JTextArea(unTitreArticle));
				Iterator<JTextArea> it2 = articlesArea.iterator();
				while(it2.hasNext()){
					JTextArea pouet = (it2.next());
					panelNews.add(pouet);
					
					
				}
			}
			
		} 
		catch (ClassNotFoundException e1) {e1.printStackTrace();} 
		catch (SQLException e1) {e1.printStackTrace();}
		 
		return panelNews;
	}
	
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				//On crée une nouvelle instance de notre JDialog
					FenetrePrincipale fPrincip = new FenetrePrincipale();
					fPrincip.setVisible(true);//On la rend visible

			}
		});
	}
}
