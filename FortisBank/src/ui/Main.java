package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import business.*;

public class Main {

	private static final String FICHIER_CLIENTS = "data/clients.dat";
	private static final String FICHIER_GESTIONNAIRES = "data/gestionnaires.dat";
	
	private static List<Client> clients = new ArrayList<>();
	private static List<Gestionnaire> gestionnaires = new ArrayList<>();
	
	private static Scanner scanner = new Scanner(System.in);


	public static void main(String[] args) {
		chargerClients();
		chargerGestionnaires();
		
		
		if (gestionnaires.isEmpty()) {
		    System.out.println("Aucun gestionnaire trouvé | Création d’un gestionnaire par défaut (ID: 1, NIP: 0000)");
		    Gestionnaire admin = new Gestionnaire(1, "Admin", "Root", "HQ", "0000", "Responsable");
		    gestionnaires.add(admin);
		    sauvegarderGestionnaires();
		}
		
		
		

		while (true) {
			System.out.println("\n===== Fortis Bank System =====");
			System.out.println("1. Connexion Gestionnaire");
			System.out.println("2. Connexion Client");
			System.out.println("3. Quitter");
			System.out.print("Choix : ");

			int choix = scanner.nextInt();
			scanner.nextLine();

			switch (choix) {
			case 1:
				menuConnexionGestionnaire();
				break;
			case 2:
				menuConnexionClient();
				break;
			case 3:
				System.out.println("Au revoir !");
				return;
			default:
				System.out.println("Choix invalide");
			}
		}
	}

	// Authentification client
	private static void menuConnexionClient() {
		System.out.print("Entrez votre ID client : ");
		int id = scanner.nextInt();
		scanner.nextLine();
		System.out.print("Entrez votre NIP : ");
		String nip = scanner.nextLine();

		for (Client c : clients) {
			if (c.getId() == id && c.verifierNIP(nip)) {
				menuClient(c);
				return;
			}
		}
		System.out.println("Identifiants invalides");
	}
	
	
	private static void menuConnexionGestionnaire() {
	    System.out.print("ID Gestionnaire : ");
	    int id = scanner.nextInt(); scanner.nextLine();
	    System.out.print("NIP : ");
	    String nip = scanner.nextLine();

	    for (Gestionnaire g : gestionnaires) {
	        if (g.getId() == id && g.verifierNIP(nip)) {
	            System.out.println("Connexion réussie. Bienvenue " + g.getNom());
	            menuGestionnaire(g); // passe le gestionnaire connecté
	            return;
	        }
	    }

	    System.out.println("Identifiants invalides");
	}
	

	private static void menuGestionnaire(Gestionnaire gestionnaire) {
		while (true) {
			System.out.println("\n===== MENU GESTIONNAIRE =====");
			System.out.println("1. Créer un nouveau client");
			System.out.println("2. Ajouter un compte à un client existant");
			System.out.println("3. Supprimer un compte d’un client");
			System.out.println("4. Supprimer un client + compte chèque");
			System.out.println("5. Générer un rapport global");
			System.out.println("6. Consulter l’activité des clients");
			System.out.println("7. Consulter les soldes des comptes");
			System.out.println("8. Relevé mensuel pour tous les clients");
			System.out.println("9. Supervision des comptes critiques");
			System.out.println("10. Sauvegarder les données");
			
			System.out.println("11. Créer un nouveau gestionnaire");
			System.out.println("0. Retour au menu principal");
			System.out.print("Choix : ");

			int choix = scanner.nextInt();
			scanner.nextLine();

			switch (choix) {
			case 1 -> creerNouveauClient(gestionnaire);
			case 2 -> ajouterCompteClient(gestionnaire);
			case 3 -> supprimerCompteClient(gestionnaire);
			case 4 -> supprimerClientEtCompteCheque(gestionnaire);
			case 5 -> gestionnaire.genererRapportGlobal(clients);
			case 6 -> gestionnaire.genererRapportActiviteClients(clients);
			case 7 -> gestionnaire.genererRapportSoldes(clients);
			case 8 -> gestionnaire.envoyerRelevesMensuels(clients);
			case 9 -> gestionnaire.superviserComptesCritiques(clients);
			case 10 -> sauvegarderClients();
			case 11 -> creerNouveauGestionnaire();
			case 0 -> {
				return;
			}
			default -> System.out.println("Choix invalide");
			}
		}
	}

	private static void creerNouveauClient(Gestionnaire gestionnaire) {
		System.out.print("ID client : ");
		int id = scanner.nextInt();
		scanner.nextLine();
		System.out.print("Nom : ");
		String nom = scanner.nextLine();
		System.out.print("Prénom : ");
		String prenom = scanner.nextLine();
		System.out.print("Adresse : ");
		String adresse = scanner.nextLine();
		System.out.print("NIP (4 chiffres) : ");
		String nip = scanner.nextLine();
		System.out.print("Email : ");
		String email = scanner.nextLine();
		System.out.print("Téléphone : ");
		String telephone = scanner.nextLine();

		gestionnaire.creerClientAvecCompteCheque(clients, id, nom, prenom, adresse, nip, email, telephone);
	}

	private static Client trouverClientParId() {
		System.out.print("Entrez l’ID du client : ");
		int id = scanner.nextInt();
		scanner.nextLine();

		for (Client c : clients) {
			if (c.getId() == id)
				return c;
		}
		System.out.println("Client introuvable");
		return null;
	}

	private static void ajouterCompteClient(Gestionnaire gestionnaire) {
		Client client = trouverClientParId();
		if (client == null)
			return;

		System.out.println("Type de compte à ajouter :");
		System.out.println("1. Compte Épargne");
		System.out.println("2. Compte Crédit");
		System.out.println("3. Ligne de Crédit");
		System.out.println("4. Compte en Devise");
		System.out.print("Choix : ");
		int choix = scanner.nextInt();
		scanner.nextLine();

		System.out.print("Numéro de compte : ");
		String numero = scanner.nextLine();
		System.out.print("Solde initial : ");
		double solde = scanner.nextDouble();
		scanner.nextLine();

		Compte nouveauCompte = null;

		switch (choix) {
		case 1 -> {
			System.out.print("Taux d’intérêt annuel (%) : ");
			double taux = scanner.nextDouble();
			scanner.nextLine();
			nouveauCompte = new CompteEpargne(numero, solde, taux);
		}
		case 2 -> {
			System.out.print("Limite de crédit : ");
			double limite = scanner.nextDouble();
			scanner.nextLine();
			System.out.print("Taux d’intérêt : ");
			double taux = scanner.nextDouble();
			scanner.nextLine();
			nouveauCompte = new CompteCredit(numero, solde, limite, taux);
		}
		case 3 -> {
			System.out.print("Limite de crédit : ");
			double limite = scanner.nextDouble();
			scanner.nextLine();
			System.out.print("Taux d’intérêt : ");
			double taux = scanner.nextDouble();
			scanner.nextLine();
			nouveauCompte = new LigneDeCredit(numero, solde, limite, taux);
		}
		case 4 -> {
			System.out.print("Devise (ex : USD, EUR) : ");
			String devise = scanner.nextLine();
			nouveauCompte = new CompteDevise(numero, solde, devise);
		}
		default -> {
			System.out.println("Choix invalide");
			return;
		}
		}

		gestionnaire.ouvrirNouveauCompte(client, nouveauCompte);
	}

	private static void supprimerCompteClient(Gestionnaire gestionnaire) {
		Client client = trouverClientParId();
		if (client == null)
			return;

		System.out.println("Comptes disponibles :");
		List<Compte> comptes = client.getComptes();
		for (int i = 0; i < comptes.size(); i++) {
			System.out.println((i + 1) + ". " + comptes.get(i).getNumero());
		}
		System.out.print("Choisir un compte à supprimer : ");
		int choix = scanner.nextInt();
		scanner.nextLine();

		if (choix >= 1 && choix <= comptes.size()) {
			gestionnaire.fermerCompte(client, comptes.get(choix - 1));
		} else {
			System.out.println("Choix invalide");
		}
	}

	private static void supprimerClientEtCompteCheque(Gestionnaire gestionnaire) {
		Client client = trouverClientParId();
		if (client != null) {
			gestionnaire.supprimerClientAvecCompteCheque(clients, client);
		}
	}

	
	private static void creerNouveauGestionnaire() {
	    System.out.println("\n--- Création d’un nouveau gestionnaire ---");

	    System.out.print("ID du gestionnaire : ");
	    int id = scanner.nextInt();
	    scanner.nextLine();

	    // Vérifie si l’ID existe déjà
	    for (Gestionnaire g : gestionnaires) {
	        if (g.getId() == id) {
	            System.out.println("Un gestionnaire avec cet ID existe déjà");
	            return;
	        }
	    }

	    System.out.print("Nom : ");
	    String nom = scanner.nextLine();

	    System.out.print("Prénom : ");
	    String prenom = scanner.nextLine();

	    System.out.print("Adresse : ");
	    String adresse = scanner.nextLine();

	    System.out.print("NIP (4 chiffres) : ");
	    String nip = scanner.nextLine();

	    System.out.print("Rôle : ");
	    String role = scanner.nextLine();

	    Gestionnaire nouveau = new Gestionnaire(id, nom, prenom, adresse, nip, role);
	    gestionnaires.add(nouveau);
	    sauvegarderGestionnaires();

	    System.out.println("Gestionnaire " + nom + " créé");
	}

	
	
	private static void menuClient(Client client) {
		while (true) {
			System.out.println("\n===== MENU CLIENT =====");
			System.out.println("Bienvenue " + client.getNom());
			System.out.println("1. Consulter solde total");
			System.out.println("2. Effectuer un dépôt");
			System.out.println("3. Effectuer un retrait");
			System.out.println("4. Effectuer un virement");
			System.out.println("5. Voir l’historique des transactions");
			System.out.println("6. Voir le relevé mensuel");
			System.out.println("0. Déconnexion");
			System.out.print("Choix : ");

			int choix = scanner.nextInt();
			scanner.nextLine();

			switch (choix) {
			case 1 -> System.out.println("Solde total : " + client.consulterSolde() + " $");
			case 2 -> effectuerDepot(client);
			case 3 -> effectuerRetrait(client);
			case 4 -> effectuerVirement(client);
			case 5 -> afficherHistoriqueComptes(client);
			case 6 -> Rapport.envoyerReleveMensuel(client);
			case 0 -> {
				System.out.println("Déconnexion...");
				return;
			}
			default -> System.out.println("Choix invalide");
			}
		}
	}

	private static void effectuerDepot(Client client) {
		Compte compte = selectionnerCompte(client);
		if (compte == null)
			return;

		System.out.print("Montant à déposer : ");
		double montant = scanner.nextDouble();
		scanner.nextLine();

		Depot depot = new Depot(genererId(), montant, compte);
		depot.executer();
	}
	
	

	private static void effectuerRetrait(Client client) {
		Compte compte = selectionnerCompte(client);
		if (compte == null)
			return;

		System.out.print("Montant à retirer : ");
		double montant = scanner.nextDouble();
		scanner.nextLine();

		Retrait retrait = new Retrait(genererId(), montant, compte);
		retrait.executer();
	}
	
	

	private static void effectuerVirement(Client client) {
		System.out.println("Compte source :");
		Compte source = selectionnerCompte(client);
		if (source == null)
			return;

		System.out.println("Compte destination :");
		Compte destination = selectionnerCompte(client);
		if (destination == null || source == destination) {
			System.out.println("Virement invalide");
			return;
		}

		System.out.print("Montant à transférer : ");
		double montant = scanner.nextDouble();
		scanner.nextLine();

		Virement virement = new Virement(genererId(), montant, source, destination);
		virement.executer();
	}
	
	

	private static void afficherHistoriqueComptes(Client client) {
		for (Compte c : client.getComptes()) {
			c.afficherHistorique();
		}
	}
	

	private static Compte selectionnerCompte(Client client) {
		List<Compte> comptes = client.getComptes();
		if (comptes.isEmpty()) {
			System.out.println("Aucun compte disponible");
			return null;
		}

		System.out.println("Comptes disponibles :");
		for (int i = 0; i < comptes.size(); i++) {
			System.out.println(
					(i + 1) + ". " + comptes.get(i).getNumero() + " (solde : " + comptes.get(i).getSolde() + " $)");
		}

		System.out.print("Choisissez un compte : ");
		int choix = scanner.nextInt();
		scanner.nextLine();

		if (choix < 1 || choix > comptes.size()) {
			System.out.println("Choix invalide");
			return null;
		}

		return comptes.get(choix - 1);
	}

	private static int genererId() {
		return (int) (Math.random() * 100000);
	}

	
	
	
	// Chargement initial
	private static void chargerClients() {
		List<Client> chargement = FileManager.chargerClients(FICHIER_CLIENTS);
		if (chargement != null) {
			clients = chargement;
		}
	}

	private static void sauvegarderClients() {
		FileManager.sauvegarderClients(clients, FICHIER_CLIENTS);
	}
	
	
	private static void chargerGestionnaires() {
	    List<Gestionnaire> chargement = FileManager.chargerGestionnaires(FICHIER_GESTIONNAIRES);
	    if (chargement != null) {
	        gestionnaires = chargement;
	    }
	}
	
	
	private static void sauvegarderGestionnaires() {
	    FileManager.sauvegarderGestionnaires(gestionnaires, FICHIER_GESTIONNAIRES);
	}
}
