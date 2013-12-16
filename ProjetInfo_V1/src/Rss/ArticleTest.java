package Rss;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;

public class ArticleTest {

	@Test
	public void findPNoun() throws IOException {
		String test="Le president François Hollande est parti de Paris ce matin. Il n'y a pas l'ombre d'un doute sur son identité.";
		String string,result = null;
		String[] strings=test.split(" ");
		ArrayList <String> results = new ArrayList <String>();
		int len, len2, memory=0;
		boolean point=true;
		len=strings.length;
		for (int j=0;j<len;j++) {
			string=strings[j];
			len2=string.length(); // longueur de la string
			char letter, letterend;
			letter = string.charAt(0);
			letterend=string.charAt(len2-1);
			if (letterend=='.') { // Si il y à un . on le signifie
				point=true;
			}
			if (Character.isUpperCase(letter)==true){
				if (memory!=0)
				{
					result=result+"_"+string;
					results.set(results.size()-1, result);
				}
				else {
					if (point==true){
						point = false;
					}
					else {
						results.add(string);
						memory=memory+1; //Pour le cas de nom
						result=string;
					}
				}



			}
			else
				memory=0;
		}
		System.out.println(results);
	}
	
	@Test
	public void wiki() {
		ArrayList <String> results = new ArrayList <String>();
		results.add("François_Hollande");
		results.add("Paris");
		String Urlwiki="http://fr.wikipedia.org/wiki/";
		for (int i=0;i<results.size();i++)
		results.set(i, Urlwiki+results.get(i));
	}
	
}



