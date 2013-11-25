package BDD_Users;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UtilisateursAliceTest {
	
	UtilisateursAlice user_test1;
	UtilisateursAlice user_testcurrent;
	
	@Before
	public void initialisation_varTest(){
	user_test1 = new UtilisateursAlice("Louise","123456");
	}
	
	@Test
	public void test_Constructor(){
		System.out.println("Test du constructeur de la classe UtilisateursAlice");
		user_testcurrent=new UtilisateursAlice("Louise","123456");
		assertEquals(user_test1.toString(),user_testcurrent.toString());
		assertTrue(user_test1.user_equals(user_testcurrent));
	}
	@Test
	public void test_ToString() {
		System.out.println("Test de la méthode toString()");
		System.out.println(user_test1.toString());
		assertEquals(user_test1.toString(),"Utilisateur [login=Louise, mdp=123456]");
	}
	
	//Validation du test ssi login = Louise et mdp = 123456 entrés au clavier en mode console
	@Test
	public void test_new_userConsole(){
		System.out.println("Test de la méthode new_userConsole()");
		UtilisateursAlice user = UtilisateursAlice.new_userConsole();
		System.out.println(user.toString());
		assertEquals(user_test1.toString(),user.toString());
	}
	
}
