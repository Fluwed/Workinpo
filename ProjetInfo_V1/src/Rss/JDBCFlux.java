package Rss;

import Flux.Flux;
import Flux.Img;
import Flux.NoChange;
import Flux.Rue89;

public class JDBCFlux {

	public void collecter() throws Exception {

		Flux Fluxx[]= new Flux[10]; // Tableau regroupant les flux avec leur méthode d'Edition propre
		
		Fluxx[0]=new Img("http://feeds.lefigaro.fr/c/32266/f/438191/index.rss","LeFigaro",null) ;
		Fluxx[1]=new Img("http://flux.20minutes.fr/c/32497/f/479493/index.rss","Minutes",null) ;
		Fluxx[2]=new Rue89("http://liberation.fr.feedsportal.com/c/32268/fe.ed/rss.liberation.fr/rss/58/","liberation",null) ;
		Fluxx[3]=new Rue89("http://rue89.feedsportal.com/c/33822/f/608948/index.rss","rue89",null) ;
		Fluxx[4]=new NoChange("https://news.google.fr/news/feeds?pz=1&cf=all&ned=fr&hl=fr&topic=t&output=rss","google",null);
		Fluxx[5]=new NoChange("http://www.lemonde.fr/rss/tag/enseignement-superieur.xml","LeMonde",null);
		Fluxx[6]=new NoChange("http://syndication.lesechos.fr/rss/rss_grande-consommation.xml","lesechos",null); //méthode classic
		Fluxx[7]=new NoChange("http://www.lequipe.fr/rss/actu_rss.xml","equipe",null);
		Fluxx[8]=new NoChange("http://www.humanite.fr/rss/actu.rss","Humanite",null);
		Fluxx[9]=new Img("http://rss.nytimes.com/services/xml/rss/nyt/World.xml","nytimes",null) ;
		
		RssThread Th0 = new RssThread(Fluxx[0]);
		RssThread Th1 = new RssThread(Fluxx[1]);
		RssThread Th2 = new RssThread(Fluxx[2]);
		RssThread Th3 = new RssThread(Fluxx[3]);
		Th1.t.join();
		RssThread Th4 = new RssThread(Fluxx[4]);
		RssThread Th5 = new RssThread(Fluxx[5]);
		Th3.t.join();
		RssThread Th6 = new RssThread(Fluxx[6]);
		RssThread Th7 = new RssThread(Fluxx[7]);
		Th5.t.join();
		RssThread Th8 = new RssThread(Fluxx[8]);
		RssThread Th9 = new RssThread(Fluxx[9]);
		}
	

public static void main(String[] args) throws Exception {
	JDBCFlux Flux = new JDBCFlux();
	Flux.collecter();
}

}