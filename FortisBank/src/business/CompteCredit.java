package business;

import java.io.Serializable;

public class CompteCredit extends Compte implements Serializable{
	
	
	private static final long serialVersionUID = -8647818052167289497L;
	
	private double limiteCredit;
	private double tauxInteret; 
	
	 public CompteCredit(String numero, double solde, double limiteCredit, double tauxInteret) {
	        super(numero, solde);
	        this.limiteCredit = limiteCredit;
	        this.tauxInteret = tauxInteret;
	    }

	 @Override
	 public boolean retirer(double montant) {
	     if (montant <= 0) return false;

	     // Le client emprunte de l'argent : le solde augmente
	     if (solde + montant <= limiteCredit) {
	         solde += montant;
	         return true;
	     } else {
	         System.out.println("Retrait refusé : dépassement de la limite de crédit");
	         return false;
	     }
	 }

	 @Override
	 public void deposer(double montant) {
	     if (montant > 0) {
	         solde -= montant; // Le client rembourse la banque
	     }
	 }

	    // Applique les intérêts sur le solde emprunté (si négatif)
	 public void calculerInterets() {
		    if (solde > 0) {
		        double interets = solde * (tauxInteret / 100);
		        solde += interets; // La dette augmente
		        System.out.println("Intérêts de " + interets + " appliqués au compte crédit " + numero);
		    } else {
		        System.out.println("Aucun crédit utilisé");
		    }
		}

	 @Override
	 public void afficherDetails() {
	     System.out.println("Compte Crédit [Numéro: " + numero +
	                        ", Solde: " + solde +
	                        ", Limite de crédit: " + limiteCredit +
	                        ", Taux d’intérêt: " + tauxInteret + "%" +
	                        ", Crédit disponible restant: " + (limiteCredit - solde) +
	                        ", Date d’ouverture: " + dateOuverture + "]");
	 }

	    // GETTER
	    public double getLimiteCredit() {
	        return limiteCredit;
	    }
	    
	    public double getTauxInteret() {
	        return tauxInteret;
	    }
	    
	    
	    // SETTER
	    public void setLimiteCredit(double limiteCredit) {
	        this.limiteCredit = limiteCredit;
	    }

	    
	    public void setTauxInteret(double tauxInteret) {
	        this.tauxInteret = tauxInteret;
	    }
}
