package business;

import java.io.Serializable;

public class Retrait extends Transaction implements Serializable {

	private static final long serialVersionUID = -5991534951430770330L;

	public Retrait(int id, double montant, Compte compte) {
		super(id, montant, compte);
	}

	@Override
	public boolean executer() {
		if (montant > 0) {
			boolean success = compte.retirer(montant);
			if (success) {
				compte.ajouterTransaction(this); // Historique
				
				new Notification("Client", "Retrait de " + montant + " effectué depuis le compte " + compte.getNumero()).envoyer();
				NotificationUtils.verifierSoldeEtNotifier(compte);
				
				System.out.println("Retrait de " + montant + " effectué du compte " + compte.getNumero());
				return true;
			} else {
				System.out.println("Retrait refusé : solde ou crédit insuffisant");
				return false;
			}
		} else {
			System.out.println("Retrait refusé : montant invalide");
			return false;
		}
	}

	@Override
	public void afficherDetails() {
		System.out.println("Retrait [ID: " + id + ", Montant: " + montant + ", Compte: " + compte.getNumero()
				+ ", Date: " + date + "]");
	}
}
