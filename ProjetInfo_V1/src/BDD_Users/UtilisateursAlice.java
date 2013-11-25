package BDD_Users;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


public class UtilisateursAlice {
	// Pour génération aléatoire
		private final static String[] logins = { "Robert", "Gudule", "Marie-Alix",
				"Godefroy", "Michel", "Alderick", "Hedastine", "Foucault",
				"Leopoldine", "Victoire", "Gaspard" };
		private final static String[] mdps = { "Martin", "Bernard", "Dubois",
				"Thomas", "Robert", "Richard", "Petit", "Durand", "Leroy",
				"Moreau", "Simon", "Laurent", "Lefebvre" };

		// Attributs
		private String login;
		private String mdp;

		public UtilisateursAlice(String login, String mdp) {
			super(); //Appel du constructeur de la classe parente
			
			this.login = login;
			this.mdp = mdp;
		}

		public String getLogin() {
			return login;
		}

		public String getMdp() {
			return mdp;
		}

		/**
		 * Affichage des champs de l'utilisateur
		 */
		public String toString() {
			return "Utilisateur [login=" + login + ", mdp=" + mdp + "]";
		}
		
		/**
		 * Génération aléatoire d'utilisateurs pour les tests
		 */
		public static Set<UtilisateursAlice> usersAleatoire(int combien) {
			Set<UtilisateursAlice> resultat = new HashSet<>(combien);
			for (int i = 0; i < combien; i++) {
				resultat.add(UtilisateursAlice.userAleatoire());
			}
			return resultat;
		}

		private static UtilisateursAlice userAleatoire() {
			return new UtilisateursAlice(logins[((int) (Math.random() * logins.length))],mdps[((int) (Math.random() * mdps.length))]);
		}
		
		/**
		 * Nouvel utilisateur entré en mode console
		 * @return
		 */
		public static UtilisateursAlice new_userConsole(){
			UtilisateursAlice user;
			String log = null;
			String pwd = null;
			@SuppressWarnings("resource")
			Scanner input = new Scanner(System.in);
		
			System.out.println("Entrez login:");
			log = input.nextLine();
			System.out.println("Entrez Mot de passe:");
			pwd = input.nextLine();
			System.out.println("Informations saisies: Login=" + log + " mdp=" + pwd);
			user = new UtilisateursAlice(log,pwd);
			
			return user;
		}
		
		public boolean user_equals(UtilisateursAlice u){
			if(u==null)
				return false;
			if(u.login == this.login && u.mdp==this.mdp)
				return true;
			else
				return false;
		}
}
