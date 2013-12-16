package Rss;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.ParsingFeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;


public class FluxRSS {
	private static Connection conn;
	
	public void parse(String monfluxRSS,String Table) throws ClassNotFoundException, Exception, NullPointerException{
		
		int i=0;
		JDBC base = new JDBC();
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
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO "+Table+" VALUES (?,?,?,?,null) "); // on ajoute les donnees du flux dans la base
				pstmt.setString(1, entry.getTitle()); // Titre
				if ((monfluxRSS=="http://syndication.lesechos.fr/rss/rss_grande-consommation.xml") || (monfluxRSS=="http://www.lequipe.fr/rss/actu_rss.xml")) //Rome ne gere pas tout les types de date, on appelle une autre methode ici
				{
					pstmt.setString(2,GetDate(monfluxRSS,i)); // Date
					i++;
				}
				else
				{
					pstmt.setString(2,(entry.getPublishedDate()).toString()); //Date
				}
				pstmt.setString(3, entry.getDescription().getValue()); //Description
				pstmt.setString(4, entry.getLink()); //Lien vers article
				//pstmt.setString(5, ReadArticle(entry.getLink()));
				
				pstmt.executeUpdate();
			}
			catch (SQLException e){

			}
		}

	}
	
	public String GetDate(String monfluxRSS,int i) throws ClassNotFoundException, Exception
	{
		String url = monfluxRSS; 
		String result = null; 
		try
		{
			DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
			DocumentBuilder b = f.newDocumentBuilder();
			Document doc = b.parse(url); //Debut du parsage de l'Url

			doc.getDocumentElement().normalize();


			NodeList items = doc.getElementsByTagName("item"); // On definit la balise <item> comme etant la balise à l'intérieur de laquelle on va parcourir le flux
			if (i < items.getLength()) // Parcours de chaque item present
			{
				Node n = items.item(i);
				Element e = (Element) n;
				NodeList DateList = e.getElementsByTagName("pubDate"); // Pointe le contenu de la balise <pubDate>
				Element DateElem = (Element) DateList.item(0);

				Node titleNode = DateElem.getChildNodes().item(0);
				result = titleNode.getNodeValue(); // On recupere le texte contenu dans la balise
				if (monfluxRSS == "http://syndication.lesechos.fr/rss/rss_grande-consommation.xml")  // On determine de quel flux il s'agit (a chacun sa methode)
				{
					result=ChgDate(result,""); // 
				}     
				else {
					result=ChgDate(result,"Equipe");
				}

			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	
	public String ChgDate(String result,String Flux){ // Pour adapter la date a celle de la majorite des flux
		if (Flux=="Equipe"){
			DateTimeFormatter format = DateTimeFormat.forPattern("EEE, dd  MMM yyyy HH:mm:ss Z").withLocale(Locale.ENGLISH); // Définition de la forme de la string en entrée et a quoi correspond quoi
			DateTime dateTime = format.parseDateTime(result); // On transforme la string en date selon le format plus haut
			DateTimeFormatter format2 =	DateTimeFormat.forPattern("EEE, dd MMM HH:mm:ss zzz yyyy").withLocale(Locale.ENGLISH); // Défini sous quelle forme la date doit être donné en sortie
			return dateTime.toString(format2); // On redonne une string sous le format plus haut
		}
		else // Pour la date des echos
		{
			DateTimeFormatter format = DateTimeFormat.forPattern("EEE, dd MMM yyyy HH:mm:ss Z").withLocale(Locale.ENGLISH);
			DateTime dateTime = format.parseDateTime(result);
			DateTimeFormatter format2 =	DateTimeFormat.forPattern("EEE, dd MMM HH:mm:ss zzz yyyy").withLocale(Locale.ENGLISH);
			return dateTime.toString(format2);
		}
	}




	public String ReadArticle(String MonHtml) throws IOException, IllegalArgumentException, FeedException{ // Utilise Jsoup.jar
	
		String result = null;
		try { // certains article du nytimes bug, faire attention pour l'affichage de l'article 
			StringBuffer Result = null;
			String monArticle ="http://ftr.fivefilters.org/makefulltextfeed.php?url="+MonHtml; // Utilisation d'un site parsant des sites html
			URL url = new URL(monArticle); // Url du flux RSS
			HttpURLConnection httpcon = (HttpURLConnection)url.openConnection();
			SyndFeedInput input = new SyndFeedInput(); // Mise en place de la lecture du flux rss
			SyndFeed feed = input.build(new XmlReader(httpcon));
			@SuppressWarnings("unchecked")
			List<SyndEntry> entries = feed.getEntries(); // Ici entries correspond à toute les balises <item> dans le flux
			Iterator<SyndEntry> itEntries = entries.iterator(); // On définit un itérateur qui permet de parcourir les balise <item>
			SyndEntry entry = itEntries.next();
			Result = new StringBuffer(entry.getDescription().getValue()); // On prend le contenu de la balise description
			int ind = Result.lastIndexOf("<p><em>"); // Donne la position de la derniere occurence de la string "This entry passed"
			result=Result.substring(0, ind); 
		}
		catch (ParsingFeedException e)
		{
		}
		return result;
	}

	
	public void UpdateFlux(String Table) throws SQLException, ClassNotFoundException{
		JDBC base = new JDBC();

		conn = base.ConnexionDB();
		base.executeQuery("SELECT * FROM "+Table+"");
	}
}