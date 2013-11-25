package BDD_Reader;

/**
 * FONCTION DES BIBLIOTHEQUES IMPORTEES :
 * java.net.URL permet de récupérer et travailler sur des objects de type URL
 * DOM = Document Object Modele
 * javax.xml.parsers.DocumentBuilder & javax.xml.parsers.DocumentBuilderFactory permettent d'avoir accès aux classes qui fabrique des DOM
 * org.w3c.dom permet de hiérarchiser, parcourir, modéliser, manipuler un document XML
 * Les .Document + .NodeList + .Node + .Element sont des interfaces de DOM
 */

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

//import com.mysql.jdbc.MySQLConnection;

/**
 * Parser un flux RSS
 * @author The white Walkers - 2013
 */

public class RSSReader {
	
	/**
     * Analyser (parser) le fichier XML
     * @param monFluxRSS URL du flux RSS (.xml) de type String
     * 
     * Description des méthodes utilisées :
     * 		DocumentBuilderFactory.newDocumentBuilder() permet d'obtenir une instance de la classe DocumentBuilder
     * 		=> Stockage dans la variable builder de type DOM (Document Object Model)
     * 
     * 		builder.parse(url.openStream()) analyse de l'objet DocumentBuilder appliquée ici à une URL
     * 			=> url.openStream() effectue la connection et la lecture de l'url correspondant à monFLuxRSS
     */
	
	private static GestionDB base = new GestionDB();
	
    public void parse(String monFluxRss) throws SQLException {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            URL url = new URL(monFluxRss);
            Document doc = builder.parse(url.openStream());
            NodeList noeudsCourants = null;
            Element element = null;
            /**
             * Titre et date du flux
             * 
             * la variable noeudsCourants stocke une liste d'éléments dont le nom est fournit en paramètre 
             * 
             * noeudsCourants = doc.getElementsByTagName("title") 
             * la variable noeudsCourants stocke une liste d'éléments dont le nom est fournit en paramètre, ici ces éléments sont les balises title qui correspondent aux titres des flux RSS
             * 
             * Node notreRacine = doc.getDocumentElement() nous renvoie l'élément racine du DOM
             * 
             * Affichage des éléments précédents pour tester
             */
            noeudsCourants = doc.getElementsByTagName("title");
            Node notreRacine = doc.getDocumentElement();
            //System.out.println("Flux RSS: " + this.readNode(notreRacine, "channel|title"));
            //System.out.println("Date de publication: " + GMTDateToFrench(this.readNode(notreRacine, "channel|lastBuildDate")));
            //System.out.println();
            /**
             * Elements du flux RSS
             * 
             * La liste de noeudsCourant contient maintenant une liste d'item (qui correspondent aux articles : titre + date + lien + description)
             **/ 
            noeudsCourants = doc.getElementsByTagName("item");
            
            base.coDB();
            String requeteTableUsers = "CREATE TABLE IF NOT EXISTS Flux (Titre VARCHAR(256) PRIMARY KEY, Date VARCHAR(256), Description VARCHAR(256),Lien VARCHAR(256),Tflux VARCHAR(256))";
    		try {
				base.executeUpdate(requeteTableUsers);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
            for (int i = 0; i < noeudsCourants.getLength(); i++) 
            {
                element = (Element) noeudsCourants.item(i);
                String rTitre = (readNode(element, "title"));
                String rDate = (GMTDateToFrench(readNode(element, "pubDate")));
                String rDesc = (readNode(element, "description"));
                String rLink = (readNode(element, "link"));
                String rFlux = (readNode(notreRacine, "channel|title"));
                base.EntreeBase(rTitre, rDate, rDesc, rLink,rFlux);
            
            
            } //for
            //for
        } catch (SAXException ex) {
            Logger.getLogger(RSSReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RSSReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(RSSReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
		base.arretDB();
        
    }

    /**
     * Méthode permettant de retourner ce que contient un noeud
     * @param monArticle le noeud principal
     * @param chemin suite des noms des noeud sans espace séparer par des "|"
     * @return un string contenant le valeur du noeud voulut
     */
    public String readNode(Node monArticle, String chemin) {

        String[] paths = chemin.split("\\|");
        Node notreRacine = null;

        if (paths != null && paths.length > 0) {
            notreRacine = monArticle;

            for (int i = 0; i < paths.length; i++) {
                notreRacine = getChildByName(notreRacine, paths[i].trim());
            }
        }

        if (notreRacine != null) {
            return notreRacine.getTextContent();
        } else {
            return "";
        }
    }

    /**
     * renvoye le nom d'un noeud fils a partir de son nom
     * @param noeudPere noeud pricipal
     * @param nomNoeudFils nom du noeud fils
     * @return le noeud fils
     */
    public Node getChildByName(Node noeudPere, String nomNoeudFils) {
        if (noeudPere == null) {
            return null;
        }
        NodeList listChild = noeudPere.getChildNodes();

        if (listChild != null) {
            for (int i = 0; i < listChild.getLength(); i++) {
                Node fils = listChild.item(i);
                if (fils != null) {
                    if ((fils.getNodeName() != null && (nomNoeudFils.equals(fils.getNodeName()))) || (fils.getLocalName() != null && (nomNoeudFils.equals(fils.getLocalName())))) {
                        return fils;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Afficher une Date GML au format francais
     * @param gmtDate
     * @return
     */
    public String GMTDateToFrench(String gmtDate) {
        try {
            SimpleDateFormat dfGMT = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);
            dfGMT.parse(gmtDate);
            SimpleDateFormat dfFrench = new SimpleDateFormat("EEEE, d MMMM yyyy HH:mm:ss", Locale.FRANCE);
            return dfFrench.format(dfGMT.getCalendar().getTime());
        } catch (ParseException ex) {
            Logger.getLogger(RSSReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    /**
     * Exemple
     * @param args
     * @throws SQLException 
     */
    public static void main(String[] args) throws SQLException {
        /*
        RSSReader reader = new RSSReader();
        long time_fin=System.currentTimeMillis() +5000;
        System.out.println("==================================================================================================");
        System.out.println("Avant Collecte de données");
        System.out.println("==================================================================================================");
        base.Affiche();
        System.out.println("");
        while(System.currentTimeMillis() <= time_fin){
        reader.parse("http://www.lemonde.fr/rss/tag/afrique.xml");
        reader.parse("http://www.lemonde.fr/rss/tag/videos.xml");
        reader.parse("http://www.lemonde.fr/rss/tag/ameriques.xml");
        }
        System.out.println("==================================================================================================");
        System.out.println("Apres Collecte de données");
        System.out.println("==================================================================================================");
        base.Affiche();
        */
    }
}