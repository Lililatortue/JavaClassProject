package business;

import java.io.Serializable;

public class Depot extends Transaction implements Serializable {

	private static final long serialVersionUID = -8272824957441513310L;

	public Depot(int id, double montant, Compte compte) {
		super(id, montant, compte);
	}

	@Override
	public boolean executer() {
		if (montant > 0) {
			compte.deposer(montant);
			compte.ajouterTransaction(this); // Historique
			
			new Notification("Client", "Dépôt de " + montant + " effectué sur le compte " + compte.getNumero()).envoyer();
			NotificationUtils.verifierSoldeEtNotifier(compte);
			
			System.out.println("Dépôt de " + montant + " effectué sur le compte " + compte.getNumero());
			return true;
		} else {
			System.out.println("Dépôt refusé : montant invalide");
			return false;
		}
	}

	@Override
	public void afficherDetails() {
		System.out.println("Dépôt [ID: " + id + ", Montant: " + montant + ", Compte: " + compte.getNumero() + ", Date: "
				+ date + "]");
	}
}
