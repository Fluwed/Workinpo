package BDD_Flux;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JDBCFlux {
	
	private static FluxRSS flux = new FluxRSS();

	public static void collecter() throws Exception {
		
		flux.parse("http://www.lemonde.fr/rss/tag/enseignement-superieur.xml","LeMonde"); 
		System.out.println("1"); // Pour se reperer en cas d'erreur 
		flux.parse("http://feeds.lefigaro.fr/c/32266/f/438191/index.rss","LeFigaro");
		System.out.println("2");
		flux.parse("http://flux.20minutes.fr/c/32497/f/479493/index.rss","Minutes");
		System.out.println("3");
		flux.parse("https://news.google.fr/news/feeds?pz=1&cf=all&ned=fr&hl=fr&topic=t&output=rss","google");
		System.out.println("4");
		flux.parse("http://liberation.fr.feedsportal.com/c/32268/fe.ed/rss.liberation.fr/rss/58/","liberation");
		System.out.println("5");
		flux.parse("http://rue89.feedsportal.com/c/33822/f/608948/index.rss","rue89");
		System.out.println("6");
		flux.parse("http://emploi.lesechos.fr/rss.php","lesechos"); 
		System.out.println("7");
		flux.parse("http://www.lequipe.fr/rss/actu_rss_Tennis.xml","equipe");  
		System.out.println("8");
		flux.parse("http://www.humanite.fr/filrouge.rss","Humanite");
		System.out.println("9");
		flux.parse("http://rss.nytimes.com/services/xml/rss/nyt/World.xml","NYTimes");
		System.out.println("10");
		}
	
	public String afficher() throws ClassNotFoundException, SQLException{
		String text = "";
		text+=flux.Affiche("LeMonde")+"\n";
		text+=flux.Affiche("LeFigaro")+"\n";
		text+=flux.Affiche("Minutes")+"\n";
		text+=flux.Affiche("google")+"\n";
		text+=flux.Affiche("liberation")+"\n";
		text+=flux.Affiche("rue89")+"\n";
		text+=flux.Affiche("lesechos")+"\n";
		text+=flux.Affiche("Humanite")+"\n";
		text+=flux.Affiche("NYTimes")+"\n";
		return text;
		
	}

	public static int get_nbArticle() throws SQLException{
		String [] nomTableFlux = {"LeMonde", "LeFigaro","Minutes","google","liberation","rue89","lesechos","equipe","Humanite","NYTimes"};
		Statement stmt = flux.getConn().createStatement();
		int nbArticle=0;
		for(int i=0;i<nomTableFlux.length;i++){
			String SELECT_ID =("SELECT * FROM "+nomTableFlux[i]+"");
			ResultSet rs = stmt.executeQuery(SELECT_ID);
			rs.last();
			nbArticle += rs.getRow();
			rs.beforeFirst();
		}
		return nbArticle;
	}
	
}
	
