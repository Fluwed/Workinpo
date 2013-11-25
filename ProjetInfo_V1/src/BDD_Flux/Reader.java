package BDD_Flux;

import java.util.Locale;
import javax.xml.parsers.*;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.w3c.dom.*;

public class Reader //Parsing manuelle pour cas defavorable à la biblioteque ROME 
{ 
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
                if (monfluxRSS == "http://emploi.lesechos.fr/rss.php")  // On determine de quel flux il s'agit (a chacun sa methode)
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
			DateTimeFormatter format = DateTimeFormat.forPattern("EEE, dd MMMM yyyy HH:mm:ss").withLocale(Locale.ENGLISH);
			DateTime dateTime = format.parseDateTime(result);
			DateTimeFormatter format2 =	DateTimeFormat.forPattern("EEE, dd MMM HH:mm:ss zzz yyyy").withLocale(Locale.ENGLISH);
			return dateTime.toString(format2);
		}
	}
}

