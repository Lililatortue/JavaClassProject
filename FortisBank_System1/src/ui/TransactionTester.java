package ui;

import bus.*;
import java.util.Date;

public class TransactionTester {

	public static void main(String[] args) {
		
		// Classe Main pour simplement tester la gestion des transactions
		// AVANT D'AVOIR ÉTÉ LIÉ AU PROJET PRINCIPAL !!!
		
		
		// -- 0 --
		// Création des objets Date pour aujourd'hui
        Date now = new Date();

        // -- 1 --
        // Dépôt
        Depot depot = new Depot(1, now, 500.00, "C123");
        depot.executer();
        System.out.println(depot.toString());
        
        System.out.println("---------------------------------------------------");

        // -- 2 --
        // Retrait
        Retrait retrait = new Retrait(2, now, 200.00, "C123");
        retrait.executer();
        System.out.println(retrait.toString());
        
        System.out.println("---------------------------------------------------");

        // -- 3 --
        // Virement
        Virement virement = new Virement(3, now, 300.00, "C123", "C456");
        virement.executer();
        System.out.println(virement.toString());
        
        System.out.println("---------------------------------------------------");
        
        // -- 4 --
        // Fin du test
        
        System.out.println("\nApplication written by Loïs Michelant - Test 1 des classes Transaction"
        + " (dépôt, retrait, virement)");
        System.exit(0);
        
	}

}
