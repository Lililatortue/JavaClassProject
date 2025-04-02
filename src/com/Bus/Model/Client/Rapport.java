package com.Bus.Model.Client;

import java.io.Serializable;
import java.util.List;

 /*
 * La classe Rapport est responsable de la génération de rapports sur les clients.
 * Elle est utilisée pour afficher des informations détaillées sur tous les clients d'une liste.
 */

public class Rapport implements Serializable {
	
	private static final long serialVersionUID = -6035596525818185940L;

	 /*
	 * Génère un rapport sur les clients fournis dans la liste.
	 * Si la liste est vide, un message indiquant l'absence de clients est affiché.
	 * Sinon, les détails de chaque client sont affichés.
	 */
	public static void genererRapport(List<Client> clients) {
		// Si la liste des clients est vide, afficher un message d'avertissement
        if (clients.isEmpty()) {
            System.out.println("\nAucun client à afficher dans le rapport");
            return;
        }
     
        // Afficher les détails de tous les clients dans le rapport
        System.out.println("\nRapport des Clients :");
        clients.forEach(Client::afficherDetails);
        
        // Indiquer que le rapport a été généré
        System.out.println("Rapport généré !");
    }
}
