package com.buisness.client;

import java.io.Serializable;
import java.util.List;

public class Rapport implements Serializable {
	
	
	private static final long serialVersionUID = -6035596525818185940L;

	public static void genererRapport(List<Client> clients) {
        if (clients.isEmpty()) {
            System.out.println("\nAucun client à afficher dans le rapport");
            return;
        }
        System.out.println("\nRapport des Clients :");
        clients.forEach(Client::afficherDetails);
        System.out.println("Rapport généré !");
    }
}
