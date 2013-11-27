package Flux;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import Rss.JDBC;

public class StopHtml implements Nom {

	public void Edit(String Table) {
		try{
			String Titre = new String();
			JDBC base = new JDBC();
			@SuppressWarnings("unused")
			Connection conn;
			conn= base.ConnexionDB();
			ResultSet resultat = base.executeQuery("SELECT * FROM "+Table+"");
			String test=new String();
			while(resultat.next()){
				test=resultat.getString("Description");
				Titre=resultat.getString("Titre");
					while (test.lastIndexOf("<")!=-1)
					{
					int first = test.indexOf("<");
					int end = test.indexOf(">");
					test=test.replace(test.substring(first, end+1),"");
					} 
					if (test.startsWith(" ")==true)
						test="Rendez-vous sur La page de l'article pour plus de contenu !";
					base.ChangeCol(test,Table,"Description",Titre); }
			
		}
		catch (SQLException | ClassNotFoundException e) {
		} 
	}
}


