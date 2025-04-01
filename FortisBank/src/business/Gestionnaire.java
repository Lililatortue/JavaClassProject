package business;

import java.io.Serializable;
import java.util.List;

public class Gestionnaire extends Utilisateur implements Serializable {

	private static final long serialVersionUID = 263713853896394328L;
	private String role;

	public Gestionnaire(int id, String nom, String prenom, String adresse, String nip, String role) {
		super(id, nom, prenom, adresse, nip);
		this.role = role;
	}

	// GETTERS
	public String getRole() {
		return role;
	}

	// SETTER
	public void setRole(String role) {
		this.role = role;
	}

	// Créer un client
	public void creerClient(List<Client> clients, Client client) {
		clients.add(client);
		System.out.println("Client " + client.getNom() + " créé avec succès");
	}

	// Créer client avec compte chèque obligatoire
	public void creerClientAvecCompteCheque(List<Client> clients, int id, String nom, String prenom, String adresse, String nip, String email, String telephone) {
		Client client = new Client(id, nom, prenom, adresse, nip, email, telephone);

		CompteCheque compteCheque = new CompteCheque("CHQ-" + id, 0.0); // Solde initial 0.0
		client.ajouterCompte(compteCheque);

		clients.add(client);
		System.out.println("Client " + nom + " créé avec compte chèque");
	}

	// Ouvrir un autre type de compte pour un client existant
	public void ouvrirNouveauCompte(Client client, Compte compte) {
		client.ajouterCompte(compte);
		new Notification("Gestionnaire", "Demande d’ouverture de compte...").envoyer();
		System.out.println("Nouveau compte " + compte.getNumero() + " approuvé pour le client " + client.getNom());
	}

	// Modifier un client
	public void modifierClient(Client client, String email, String telephone, String adresse) {
		client.setEmail(email);
		client.setTelephone(telephone);
		client.setAdresse(adresse);
		System.out.println("Informations du client " + client.getNom() + " mises à jour");
	}

	// Supprimer un client
	public void supprimerClient(List<Client> clients, Client client) {
		if (clients.remove(client)) {
			System.out.println("Client " + client.getNom() + " supprimé avec succès");
		} else {
			System.out.println("Client non trouvé");
		}
	}

	public void fermerCompte(Client client, Compte compte) {
		if (compte instanceof CompteCheque) {
			System.out.println("Impossible de fermer le compte chèque directement");
		} else {
			boolean removed = client.getComptes().remove(compte);
			if (removed) {
				System.out.println("Compte " + compte.getNumero() + " fermé avec succès");
			} else {
				System.out.println("Compte introuvable ou déjà fermé");
			}
		}
	}

	public void supprimerClientAvecCompteCheque(List<Client> clients, Client client) {
		Compte cheque = null;

		for (Compte c : client.getComptes()) {
			if (c instanceof CompteCheque) {
				cheque = c;
				break;
			}
		}

		if (cheque != null) {
			client.getComptes().remove(cheque);
			clients.remove(client);
			System.out.println("Le client " + client.getNom() + " et son compte chèque ont été supprimés");
		} else {
			System.out.println("Compte chèque non trouvé | Suppression annulée");
		}
	}

	public void superviserComptesCritiques(List<Client> clients) {
		System.out.println("Supervision des comptes clients...");

		for (Client client : clients) {
			NotificationUtils.verifierSoldeClientEtNotifier(client);
		}

		System.out.println("Supervision terminée");
	}

	
	// Rapport global des clients
	public void genererRapportGlobal(List<Client> clients) {
	    Rapport.genererRapport(clients);
	}

	// Rapport d’activité détaillée (transactions de tous les clients)
	public void genererRapportActiviteClients(List<Client> clients) {
	    Rapport.rapportActiviteClients(clients);
	}

	// Rapport des transactions d’un client spécifique
	public void consulterTransactionsClient(Client client) {
	    Rapport.rapportTransactions(client);
	}

	// Rapport des soldes des comptes
	public void genererRapportSoldes(List<Client> clients) {
	    Rapport.rapportSoldes(clients);
	}

	// Générer le relevé mensuel pour un seul client
	public void envoyerReleveMensuel(Client client) {
	    Rapport.envoyerReleveMensuel(client);
	}

	// Générer les relevés mensuels pour tous les clients
	public void envoyerRelevesMensuels(List<Client> clients) {
	    for (Client client : clients) {
	        Rapport.envoyerReleveMensuel(client);
	    }
	}
	
	@Override
	public void afficherDetails() {
		System.out.println("Gestionnaire : " + nom + " " + prenom + " | ID: " + id + " | Adresse: " + adresse
				+ " | Rôle: " + role);
	}
}
