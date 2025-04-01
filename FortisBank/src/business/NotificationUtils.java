package business;

import java.util.List;

public class NotificationUtils {

	private static final double SEUIL_FAIBLE = 100;
	private static final double SEUIL_CRITIQUE = 50;

	public static void verifierSoldeEtNotifier(Compte compte) {
		double solde = compte.getSolde();

		if (solde < SEUIL_FAIBLE) {
			new Notification("Client", "Solde faible : " + solde + " $ sur le compte " + compte.getNumero()).envoyer();
		}

		if (solde < SEUIL_CRITIQUE) {
			new Notification("Gestionnaire",
					"Solde critique pour le compte " + compte.getNumero() + " du client (solde : " + solde + " $)")
					.envoyer();
		}
	}

	// Vérifie tous les comptes d’un client et alerte le gestionnaire si l’un est
	// critique
	public static void verifierSoldeClientEtNotifier(Client client) {
		List<Compte> comptes = client.getComptes();

		for (Compte compte : comptes) {
			if (compte.getSolde() < SEUIL_CRITIQUE) {
				new Notification("Gestionnaire", "Alerte : Le client " + client.getNom()
						+ " a un solde critique sur le compte " + compte.getNumero() + " (" + compte.getSolde() + " $)")
						.envoyer();
				break;
			}
		}
	}

}
