package Flux;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sun.syndication.io.FeedException;

import Rss.FluxRSS;
import Rss.JDBC;

public abstract class Flux {
	private String Lien;
	private String Table;
	protected Nom nom = new Classic();

	//Constructeur par défault
	public Flux(){
	}

	public Flux(String Lien, String Table, Nom nom ){
		this.nom =new Classic();
		this.Lien=Lien;
		this.Table=Table;
	}

	public void Edit(){
		nom.Edit(Table);
		System.out.println("Flux "+Table+" Edit");
	}

	public void Parse() throws ClassNotFoundException, Exception{
		FluxRSS flux = new FluxRSS();
		flux.parse(Lien, Table);
	}

	public void GetArticle() throws SQLException, ClassNotFoundException, IllegalArgumentException, IOException, FeedException {
		FluxRSS flux = new FluxRSS();
		JDBC base = new JDBC();
		@SuppressWarnings("unused")
		Connection conn;
		conn= base.ConnexionDB();
		ResultSet resultat = base.executeQuery("SELECT * FROM "+Table+"");
		StringBuffer Result = null;
		String Lien;
		String Titre;
		while(resultat.next()){
			Lien=resultat.getString("Lien");
			Titre=resultat.getString("Titre");
			try{
			Result=new StringBuffer(flux.ReadArticle(Lien));
			}
			catch (NullPointerException e){}
			base.ChangeCol(Result.toString(),Table,"FullDescription",Titre);
		}
		System.out.println("Flux "+Table+" Articles Recup");

	}
}
// Le monde = Classic
// Le figaro, rue 89, nytimes, 20 minutes,liberation = <img width>
// google = Casse-bonbon