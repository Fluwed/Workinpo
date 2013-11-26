package interface_graphique;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import BDD_Flux.JDBC;

public class FenetrePrincipale extends JFrame {
	
	private static Connection conn;
	private static JDBC base = new JDBC();
	private String [] nomTableFlux = {"LeMonde", "LeFigaro","Minutes","google","liberation","rue89","lesechos","equipe","Humanite","NYTimes"};
	private static final long serialVersionUID = 1L;
    private JPanel panel = new JPanel();
    private JLabel label = new JLabel("Cliquez sur le journal de votre choix pour afficher ses news");


	
    //Construction du panel général
    Container contenu = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
    
    // Boutons des flux
	JButton lemonde = new JButton(new GetActionPrincipale(this,"Le Monde"));			
	JButton lefigaro = new JButton(new GetActionPrincipale(this,"Le Figaro"));			
	JButton google = new JButton(new GetActionPrincipale(this,"Google"));			
	JButton rue89 = new JButton(new GetActionPrincipale(this,"Rue 89"));		
	JButton minutes = new JButton(new GetActionPrincipale(this,"Minutes"));			
	JButton liberation = new JButton(new GetActionPrincipale(this,"Libération"));			
	JButton echos = new JButton(new GetActionPrincipale(this,"Les échos"));			
	JButton equipe = new JButton(new GetActionPrincipale(this,"L'Equipe"));	
	JButton humanite = new JButton(new GetActionPrincipale(this,"L'Humanité"));		
	JButton nytimes = new JButton(new GetActionPrincipale(this,"New York Times"));			

	// Constructeur sans paramètre
	public FenetrePrincipale() throws ClassNotFoundException, SQLException {		
		
		this.setSize(1000, 600);	    	
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
               
        //Construction du panel des flux ('menu')
        this.buildPanelFlux();    
        
        // COnstruction du panel accueillant les titres d'articles
        panel.add(label);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(100, 22, 800, 500);
        contenu.add(scrollPane);
        scrollPane.setViewportView(panel);
        panel.setLayout(new GridLayout(30, 1, 0, 2));
	}
	
	// Constructeur avec nom de la table en paramètre	
	public FenetrePrincipale(String nomTable) throws ClassNotFoundException, SQLException {		
		
		this.setSize(1000, 600);	    	
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        
        //Construction du panel des flux ('menu')
        this.buildPanelFlux();    
        
        //Construction du panel accueillant les titres d'articles
        panel.remove(label);
        contenu.remove(panel);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(100, 22, 800, 500);
        contenu.add(scrollPane);
        scrollPane.setViewportView(panel);
        
        //Remplissage du panel des articles en fonction du bouton
        switch (nomTable) {
        case "LeMonde" : this.buildPanelArticles("LeMonde");
        break;
        case "LeFigaro" : this.buildPanelArticles("LeFigaro");
        break;
        case "Minutes" : this.buildPanelArticles("Minutes");
        break;
        case "google" : this.buildPanelArticles("google");
        break;
        case "liberation" : this.buildPanelArticles("liberation");
        break;
        case "rue89" : this.buildPanelArticles("rue89");
        break;
        case "lesechos" : this.buildPanelArticles("lesechos");
        break;
        case "equipe" : this.buildPanelArticles("equipe");
        break;
        case "Humanite" : this.buildPanelArticles("Humanite");
        break;
        case "NYTimes" : this.buildPanelArticles("NYTimes");
        break;
        }      	
	}
	
	public JButton getLemonde() {
		return lemonde;
	}

	public JButton getLefigaro() {
		return lefigaro;
	}

	public JButton getGoogle() {
		return google;
	}

	public JButton getRue89() {
		return rue89;
	}

	public JButton getMinutes() {
		return minutes;
	}

	public JButton getLiberation() {
		return liberation;
	}

	public JButton getEchos() {
		return echos;
	}

	public JButton getEquipe() {
		return equipe;
	}

	public JButton getHumanite() {
		return humanite;
	}

	public JButton getNytimes() {
		return nytimes;
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
	
	private void addListener(JLabel lb_url, final String url, final String nomTable) throws SQLException, ClassNotFoundException {
	    	lb_url.addMouseListener(new MouseAdapter() {
	        	
	            //Click sur le lien
	            public void mouseClicked(MouseEvent e) {
	                JLabel label=(JLabel)e.getSource();
	                String titre = label.getText().replaceAll("\\<.*?\\>'", "");
	                System.out.println(titre+"\n");
	               // String url = "";
																	
					try {
	                    Desktop.getDesktop().browse(new URI(url));
	                } catch (URISyntaxException ex) {
	                    Logger.getLogger(FenetrePrincipale.class.getName()).log(Level.SEVERE, null, ex);
	                    System.out.println("URL introuvable");
	                } catch (IOException ex) {
	                    Logger.getLogger(FenetrePrincipale.class.getName()).log(Level.SEVERE, null, ex);
	                    System.out.println("URL introuvable");
	                }
	              
	            }
	 
	            //Survol sur le lien
	            public void mouseEntered(MouseEvent e) {
	                JLabel label=(JLabel)e.getSource();
	                String plainText = label.getText().replaceAll("\\<.*?\\>", "");
	                //Sousligner le texte 
	                String urlText="<html><u>"+plainText+"</u></html>";
	                label.setText(urlText);
	            }
	            
	            //Quitte la zone du lien    
	            public void mouseExited(MouseEvent e) {
	                JLabel label=(JLabel)e.getSource();
	                String plainText = label.getText().replaceAll("\\<.*?\\>", "");
	                //Texte non souligné
	                String urlText="<html>"+plainText+"</html>";
	                label.setText(urlText);
	            }
	        });
	    }
	
	public void buildPanelArticles(String nomTable) throws ClassNotFoundException, SQLException{
		
        panel.setLayout(new GridLayout(get_nbArticle(nomTable), 1, 0, 2));

        //Remplissage du panel des articles
        List <String> listeTitres = afficheArticle(nomTable);
        Iterator<String> it = listeTitres.iterator();

		while(it.hasNext()){
				
			JPanel petitPanel = new JPanel();
			petitPanel.setBackground(Color.LIGHT_GRAY);
			JLabel label_url = new JLabel();
			label_url.setText(it.next());
			label_url.setCursor(new Cursor(Cursor.HAND_CURSOR));
			
			String plainText ="SELECT Lien FROM "+nomTable+" WHERE Titre=\""+label_url.getText().replaceAll("\\<.*?\\>", "")+"\"";
			//System.out.println(plainText);
			Connection conn = base.ConnexionDB();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(plainText);
			rs.next();
			String url = rs.getString("Lien"); 
			//System.out.println(url);
			
			addListener(label_url, url, nomTable);
			petitPanel.add(label_url);
			panel.add(petitPanel);
			conn.close();			
		}
		
	}
	
	public void buildPanelFlux(){
        setContentPane(contenu);
        contenu.setSize(100, 800);
        JPanel panelFlux = new JPanel();		
		panelFlux.setLayout(new GridLayout(10,1));
		panelFlux.add(lemonde); 
		panelFlux.add(lefigaro);
		panelFlux.add(google);
		panelFlux.add(rue89);
		panelFlux.add(minutes);
		panelFlux.add(liberation);
		panelFlux.add(echos);
		panelFlux.add(equipe);
		panelFlux.add(humanite);
		panelFlux.add(nytimes);
		contenu.add(panelFlux);
	}
	 
   public static void main(String args[]) throws ClassNotFoundException, SQLException {
			SwingUtilities.invokeLater(new Runnable(){
				public void run(){
					FenetrePrincipale fp;
					try {
						fp = new FenetrePrincipale();
						fp.setVisible(true);
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			
			});

	   }
	   
	   
	   
}
