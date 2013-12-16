package Rss;

public class FluxRSSTest {
	FluxRSS flux = new FluxRSS();
	/*@Test
	public void Htmltest() throws IOException, IllegalArgumentException, FeedException {
		//System.out.println(flux.ReadArticle("http://www.nytimes.com/2013/11/25/world/middleeast/in-iran-mainly-praise-for-nuclear-deal-as-a-good-first-step.html"));
	char c= (char)60;
	System.out.println(c);
	}*/
	
	/*@Test
	public void test() throws ClassNotFoundException, Exception {
		System.out.println("test lesechos");
		int i=0;
		String Date = flux.GetDate("http://emploi.lesechos.fr/rss.php", i);
		System.out.println(Date);	
		//on attend une date du type : Sun, 10 Nov 23:53:00 CET 2013
	}

	@Test // Test Date equipe
	public void DateTest() throws ClassNotFoundException, Exception {
		System.out.println("test equipe");
		int i=0;
		String Date = flux.GetDate("http://www.lequipe.fr/rss/actu_rss_Tennis.xml", i);
		System.out.println(Date);
		//on attend une date du type : Sun, 10 Nov 23:53:00 CET 2013
	}
	
	@Test
	public void StringBuffer() throws SQLException, ClassNotFoundException{
		StringBuffer StrBuff = new StringBuffer("");
		Connection conn;
		JDBC base = new JDBC();
		conn = base.ConnexionDB();
		ResultSet resultat = base.executeQuery("SELECT * FROM lefigaro");
		resultat.next();
		String test=resultat.getString("Description");
		StrBuff.append(test);
		int ind = StrBuff.indexOf("<img width");
		System.out.println(test);
		System.out.println(StrBuff.substring(0,ind));
	}
	
	@Test
	public void Update() throws SQLException, ClassNotFoundException {
		String add="test";
		Connection conn;
		JDBC base = new JDBC();
		conn = base.ConnexionDB();
		Statement statement = conn.createStatement();
		statement.executeUpdate("UPDATE lefigaro SET Description = '"+add+"'");
	}*/
	
	/*@Test
	public void lefigaro() throws SQLException, ClassNotFoundException{
		String Table="lefigaro";
		String Titre = new String();
		JDBC base = new JDBC();
		Connection conn;
		conn= base.ConnexionDB();
		ResultSet resultat = base.executeQuery("SELECT * FROM lefigaro");
		String test=new String();
		while(resultat.next()){
			test=resultat.getString("Description");
			Titre=resultat.getString("Titre");
			//Titre=Titre.replace("\"","");
			System.out.println(Titre);
			int ind = test.indexOf("<img width");
			if (ind>0){
			test=test.substring(0,ind);
			System.out.println(test);
			base.ChangeDesc(test,Table,Titre); }
		}
	}*/
	
	/*@Test
	public void googleDesc() throws SQLException, ClassNotFoundException {	
		String Table="lefigaro";
		String Titre = new String();
		JDBC base = new JDBC();
		Connection conn;
		conn= base.ConnexionDB();
		ResultSet resultat = base.executeQuery("SELECT * FROM google");
		String test=new String();
		while(resultat.next()){
			test=resultat.getString("Description");
			Titre=resultat.getString("Titre");
			//Titre=Titre.replace("\"","");
			System.out.println(" ");
			System.out.println(test);
			while (test.lastIndexOf("<")!=-1)
			{
			int first = test.indexOf("<");
			int end = test.indexOf(">");
			test=test.replace(test.substring(first, end+1),"");
			}
			test=test.replace("&#39;", "'");
			int first = test.indexOf("...");
			test=test.substring(0,first);
			System.out.println(test);
			base.ChangeDesc(test,Table,Titre); 
		}
	}*/
	
}


