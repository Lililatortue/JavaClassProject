package business;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CompteDevise extends Compte implements Serializable{
	
	private static final long serialVersionUID = 7393776367109547842L;
	private String devise; // "USD", "EUR", "CAD"
    private LocalDate dateDerniereActivite;
    
    
    public CompteDevise(String numero, double solde, String devise) {
        super(numero, solde);
        this.devise = devise;
        this.dateDerniereActivite = LocalDate.now();
    }
    
    
    @Override
    public boolean retirer(double montant) {
        if (montant <= solde) {
            solde -= montant;
            dateDerniereActivite = LocalDate.now();
            return true;
        } else {
            System.out.println("Retrait refusé : solde insuffisant");
            return false;
        }
    }
    
    @Override
    public void deposer(double montant) {
        if (montant > 0) {
            solde += montant;
            dateDerniereActivite = LocalDate.now();
        }
    }
    
    
    // Vérifie si le compte est inactif depuis plus d’un an
    public boolean estInactifDepuisUnAn() {
        long joursInactifs = ChronoUnit.DAYS.between(dateDerniereActivite, LocalDate.now());
        return joursInactifs >= 365;
    }
    
    @Override
    public void afficherDetails() {
        System.out.println("Compte en Devise [Numéro: " + numero +
                           ", Solde: " + solde +
                           " " + devise +
                           ", Dernière activité: " + dateDerniereActivite +
                           ", Date ouverture: " + dateOuverture + "]");
        if (estInactifDepuisUnAn()) {
            System.out.println("ATTENTION : Ce compte est inactif depuis plus d’un an !");
        }
    }
    
    
    // Getters
    public String getDevise() {
        return devise;
    }

    public LocalDate getDateDerniereActivite() {
        return dateDerniereActivite;
    }
}
