package business;

import java.io.Serializable;

public class Virement extends Transaction implements Serializable {

	private static final long serialVersionUID = 8187022133206340643L;
	private Compte compteDestination;

	public Virement(int id, double montant, Compte compteSource, Compte compteDestination) {
		super(id, montant, compteSource);
		this.compteDestination = compteDestination;
	}

	@Override
	public boolean executer() {
		if (montant > 0) {
			boolean retraitOK = compte.retirer(montant); // compte = compte source
			if (retraitOK) {
				compteDestination.deposer(montant);

				compte.ajouterTransaction(this); // source
				compteDestination.ajouterTransaction(this); // destination

				new Notification("Client", "Virement de " + montant + " effectué depuis le compte " + compte.getNumero()
						+ " vers le compte " + compteDestination.getNumero()).envoyer();
				NotificationUtils.verifierSoldeEtNotifier(compte);

				System.out.println("Virement de " + montant + " effectué du compte " + compte.getNumero() + " vers "
						+ compteDestination.getNumero());
				return true;
			} else {
				System.out.println("Virement refusé : solde insuffisant dans le compte source");
				return false;
			}
		} else {
			System.out.println("Virement refusé : montant invalide");
			return false;
		}
	}

	@Override
	public void afficherDetails() {
		System.out.println("Virement [ID: " + id + ", Montant: " + montant + ", De: " + compte.getNumero() + ", Vers: "
				+ compteDestination.getNumero() + ", Date: " + date + "]");
	}

	// Getter
	public Compte getCompteDestination() {
		return compteDestination;
	}

}
