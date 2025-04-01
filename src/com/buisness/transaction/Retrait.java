package com.buisness.transaction;

import com.buisness.compte.Compte;
import java.util.Date;

/*
 * Représente une transaction de type retrait.
 */

public class Retrait extends Transaction {

	private static final long serialVersionUID = 1159037852039274182L;

	/*
	 * Constructeur de la classe Retrait
	 */
	public Retrait(Compte compte, Date date, double montant) {
        super(compte, date, montant);
        this.setType(Transaction_type.retirer); // Spécifie que c'est un retrait
    }
	
	/*
     * Exécute la transaction de retrait, retirant le montant du solde du compte.
     */
    @Override
    public void executer() {
        // Logique spécifique au retrait : retirer le montant du compte
    }
    
    /*
     * Redéfinition de la méthode toString pour afficher la transaction de retrait.
     */
    @Override
    public String toString() {
        return super.toString() + " | Type: Retrait";
    }
}
