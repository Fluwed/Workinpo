package Flux;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import Rss.JDBC;

public class ImgWidth implements Nom {
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
				int ind = test.indexOf("<img width");
				if (ind>0){
					test=test.substring(0,ind);
					base.ChangeCol(test,Table,"Description",Titre); }
			}
		}
		catch (SQLException | ClassNotFoundException e) {
		} 
	}
}
