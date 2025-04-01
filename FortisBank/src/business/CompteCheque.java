package business;

import java.io.Serializable;

public class CompteCheque extends Compte implements Serializable {
	
	private static final long serialVersionUID = -1106719953603174831L;
	private static final int TRANSACTIONS_GRATUITES_MENSUELLES = 2;
    private static final double FRAIS_TRANSACTION = 5.0;
    
    private int transactionsEffectueesCeMois;
    
    public CompteCheque(String numero, double solde) {
        super(numero, solde);
        this.transactionsEffectueesCeMois = 0;
    }
    
    @Override
    public boolean retirer(double montant) {
        double montantFinal = montant;

        if (transactionsEffectueesCeMois >= TRANSACTIONS_GRATUITES_MENSUELLES) {
            montantFinal += FRAIS_TRANSACTION;
        }

        if (solde >= montantFinal) {
            solde -= montantFinal;
            transactionsEffectueesCeMois++;
            return true;
        } else {
            System.out.println("Solde insuffisant");
            return false;
        }
    }
    
    
    @Override
    public void afficherDetails() {
        System.out.println("Compte Chèque [Numéro: " + numero +
                           ", Solde: " + solde +
                           ", Date d’ouverture: " + dateOuverture +
                           ", Transactions effectuées ce mois: " + transactionsEffectueesCeMois +
                           ", Transactions gratuites restantes: " + Math.max(0, TRANSACTIONS_GRATUITES_MENSUELLES - transactionsEffectueesCeMois) +
                           "]");
    }
    
    
    public void reinitialiserTransactionsMensuelles() {
        this.transactionsEffectueesCeMois = 0;
    }
    
    
    public int getTransactionsEffectueesCeMois() {
        return transactionsEffectueesCeMois;
    }
}
