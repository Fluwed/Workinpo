package Rss;

import org.junit.Test;

public class ReaderTest {

	@Test
	public void test() throws ClassNotFoundException, Exception {
		System.out.println("test lesechos");
		Reader rdr = new Reader();
		int i=0;
		String Date = rdr.GetDate("http://emploi.lesechos.fr/rss.php", i);
		System.out.println(Date);	
		//on attend une date du type : Sun, 10 Nov 23:53:00 CET 2013
	}
	
	@Test // Test Date equipe
	public void DateTest() throws ClassNotFoundException, Exception {
		System.out.println("test equipe");
		Reader rdr = new Reader();
		int i=0;
		String Date = rdr.GetDate("http://www.lequipe.fr/rss/actu_rss_Tennis.xml", i);
		System.out.println(Date);
		//on attend une date du type : Sun, 10 Nov 23:53:00 CET 2013
	}
	
	
	@Test // Test Affiche Bdd
	public void AfficheTest() throws ClassNotFoundException, Exception {
		System.out.println("test Affiche");
		FluxRSS base = new FluxRSS();
		base.Affiche("equipe");
	}
}
