package Rss;

import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;


public class FluxRSS {
	private static Connection conn;
	public void parse(String monfluxRSS,String Table) throws ClassNotFoundException, Exception{
		
		JDBC base= new JDBC();
		Reader rdr = new Reader();
		int i=0;
		
		conn = base.ConnexionDB(); // Connexion à la base de données
		URL url = new URL(monfluxRSS); // Url du flux RSS
		base.NewTable(Table); // On cree la table associer au flux dans la base de données si ce n'est pas deja fait
		HttpURLConnection httpcon = (HttpURLConnection)url.openConnection();
		SyndFeedInput input = new SyndFeedInput(); // Mise en place de la lecture du flux rss
		
		SyndFeed feed = input.build(new XmlReader(httpcon));
		@SuppressWarnings("unchecked")
		List<SyndEntry> entries = feed.getEntries(); // Ici entries correspond à toute les balises <item> dans le flux
		Iterator<SyndEntry> itEntries = entries.iterator(); // On définit un itérateur qui permet de parcourir les balise <item> 
		
		while (itEntries.hasNext()) {
			try {
			SyndEntry entry = itEntries.next(); // entry correspond a toute les balise que contient la balise <item> sur laquel pointe itEntries
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO "+Table+" VALUES (?,?,?,?) "); // on ajoute les donnees du flux dans la base
		    pstmt.setString(1, entry.getTitle()); // Titre
			if ((monfluxRSS=="http://emploi.lesechos.fr/rss.php") || (monfluxRSS=="http://www.lequipe.fr/rss/actu_rss_Tennis.xml")) //Rome ne gere pas tout les types de date, on appelle une autre methode ici
			
			{
				pstmt.setString(2,rdr.GetDate(monfluxRSS,i)); // Date
				i++;
			}
			else
			{
				pstmt.setString(2,(entry.getPublishedDate()).toString()); //Date
			}
		    pstmt.setString(3, entry.getDescription().getValue()); //Description
		    pstmt.setString(4, entry.getLink()); //Lien vers article
		    pstmt.executeUpdate();
		    }
			catch (SQLException e){
				
			}
		}
		
	}
	public void Affiche(String Table) throws SQLException, ClassNotFoundException{   
		   
		JDBC base = new JDBC();
		
		conn = base.ConnexionDB();
	    ResultSet resultat = base.executeQuery("SELECT * FROM "+Table+"");
		// On parcours le résultat et on l'affiche
	    System.out.format("%-130s%-41s%-350s%s","Titre","Date","Description","Lien");
	    while (resultat.next()) {
	    	System.out.println("");
	    	System.out.format("%-130s%-40s%-350s%s", resultat.getString("Titre"),"   " + resultat.getString("Date"), "   " + resultat.getString("Description"),"   " + resultat.getString("Lien") );
	    }
	    System.out.println("");
		// On arrete la Bdd
		/*base.arretDB();*/}
}