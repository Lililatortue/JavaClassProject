package business;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Rapport implements Serializable {
	
	
	private static final long serialVersionUID = -6035596525818185940L;

	public static void genererRapport(List<Client> clients) {
        System.out.println("\nRapport global des clients :");
        for (Client client : clients) {
            client.afficherDetails();
        }
    }
	
	
	public static String formatClientAvecComptes(Client client) {
	    StringBuilder sb = new StringBuilder();
	    sb.append(client.getNom()).append(" ").append(client.getPrenom()).append("\n");

	    for (Compte compte : client.getComptes()) {
	        sb.append("   • ")
	          .append(compte.getClass().getSimpleName())
	          .append(" - ")
	          .append(compte.getNumero())
	          .append(" | Solde : ")
	          .append(compte.getSolde())
	          .append(" $\n");
	    }

	    sb.append("\n");
	    return sb.toString();
	}
	
	
	
	// Activité des clients
    public static void rapportActiviteClients(List<Client> clients) {
        System.out.println("\nActivité des clients :");
        for (Client client : clients) {
            System.out.println("Client : " + client.getNom() + " " + client.getPrenom());
            for (Compte compte : client.getComptes()) {
                compte.afficherHistorique();
            }
        }
    }
    
    
    // Détail des transactions pour un client
    public static void rapportTransactions(Client client) {
        System.out.println("\nDétails des transactions pour le client : " + client.getNom());
        for (Compte compte : client.getComptes()) {
            compte.afficherHistorique();
        }
    }
    
    
    // Solde de tous les comptes
    public static void rapportSoldes(List<Client> clients) {
        System.out.println("\nRapport des soldes de comptes :");
        for (Client client : clients) {
            System.out.println("Client : " + client.getNom());
            for (Compte compte : client.getComptes()) {
                System.out.println("  Compte " + compte.getNumero() + ", Solde : " + compte.getSolde() + " $");
            }
        }
    }
    
    // Relevé mensuel pour un client (mois en cours)
    public static void envoyerReleveMensuel(Client client) {
        LocalDate today = LocalDate.now();
        int mois = today.getMonthValue();
        int annee = today.getYear();

        System.out.println("\nRelevé mensuel pour : " + client.getNom() + " " + client.getPrenom() + " [" + mois + "/" + annee + "]");

        for (Compte compte : client.getComptes()) {
            System.out.println("Compte : " + compte.getNumero() + " | Solde actuel : " + compte.getSolde() + " $");
            compte.afficherHistoriqueMensuel(mois, annee);
        }

        new Notification("Client", "Votre relevé mensuel de " + mois + "/" + annee + " est disponible").envoyer();
    }
    
    //Relevés pour tous les clients
    public static void envoyerRelevesMensuels(List<Client> clients) {
        for (Client client : clients) {
            envoyerReleveMensuel(client);
        }
    }
}
