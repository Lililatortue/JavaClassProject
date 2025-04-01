package business;

import java.io.Serializable;

public class CompteEpargne extends Compte implements Serializable {
	
	
	private static final long serialVersionUID = -1787608012314204482L;
	private double tauxInteretAnnuel;
	
	
	public CompteEpargne(String numero, double solde, double tauxInteretAnnuel) {
        super(numero, solde);
        this.tauxInteretAnnuel = tauxInteretAnnuel;
    }
	
	
	// Applique les intérêts annuels au solde
    public void calculerInteretsAnnuels() {
        double interets = solde * (tauxInteretAnnuel / 100);
        solde += interets;
        System.out.println("Intérêts annuels de " + interets + " ajoutés au compte " + numero);
    }
    
    
    @Override
    public boolean retirer(double montant) {
        if (montant <= solde) {
            solde -= montant;
            return true;
        } else {
            System.out.println("Retrait refusé : solde insuffisant");
            return false;
        }
    }
    
    
    @Override
    public void afficherDetails() {
        System.out.println("Compte Épargne [Numéro: " + numero +
                           ", Solde: " + solde +
                           ", Taux d’intérêt annuel: " + tauxInteretAnnuel + "%" +
                           ", Date d’ouverture: " + dateOuverture + "]");
    }
    
    
    // Getter
    public double getTauxInteretAnnuel() {
        return tauxInteretAnnuel;
    }
}
