package main.test;

import com.buisness.client.*;
import com.buisness.compte.*;

public class TestMain {
	
	public static void main(String[] arg) {
		
		
		Client p = new Client(0, "Huque", "Tafsirul", "somewhere", "HT22", "taf@email", "111-1111-1111");
		p.ajouterCompte(new CompteCredit(0.05, 10000.0));
		p.ajouterCompte(new LigneDeCredit(0.05));
		p.ajouterCompte(new CompteEpargne(0.05, 1000.0));
		p.afficherDetails();
		
	}
}
