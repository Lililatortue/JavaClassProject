package business;

import java.io.Serializable;

public class LigneDeCredit extends Compte implements Serializable {
	
	
	 
	private static final long serialVersionUID = 4004222500099680823L;
	private double limiteCredit;
	private double tauxInteret;
	 
	 
	 public LigneDeCredit(String numero, double solde, double limiteCredit, double tauxInteret) {
	        super(numero, solde);
	        this.limiteCredit = limiteCredit;
	        this.tauxInteret = tauxInteret;
	 }
	 
	 
	 @Override
	    public boolean retirer(double montant) {
	        double soldeDisponible = solde + limiteCredit;

	        if (montant <= soldeDisponible) {
	            solde -= montant;
	            return true;
	        } else {
	            System.out.println("Retrait refusé : dépassement de la limite de la ligne de crédit");
	            return false;
	        }
	 }
	 
	 
	 @Override
	    public void deposer(double montant) {
	        if (montant > 0) {
	            solde += montant; // remboursement automatique si solde < 0
	        }
	 }
	 
	 
	 // Applique les intérêts uniquement si le solde est négatif (crédit utilisé)
	 public void calculerInterets() {
	        if (solde < 0) {
	            double interets = Math.abs(solde) * (tauxInteret / 100);
	            solde -= interets;
	            System.out.println("Intérêts de " + interets + " appliqués à la ligne de crédit " + numero);
	        } else {
	            System.out.println("Aucun crédit utilisé | Aucun intérêt appliqué");
	        }
	 }
	 
	 
	 @Override
	    public void afficherDetails() {
	        System.out.println("Ligne de Crédit [Numéro: " + numero +
	                           ", Solde: " + solde +
	                           ", Limite: " + limiteCredit +
	                           ", Taux d’intérêt: " + tauxInteret + "%" +
	                           ", Date ouverture: " + dateOuverture + "]");
	    }
	 
	 
	 
	 	// Getters
	    public double getLimiteCredit() {
	        return limiteCredit;
	    }

	    public double getTauxInteret() {
	        return tauxInteret;
	    }

	    // Setters
	    public void setLimiteCredit(double limiteCredit) {
	        this.limiteCredit = limiteCredit;
	    }

	    public void setTauxInteret(double tauxInteret) {
	        this.tauxInteret = tauxInteret;
	    }
}
