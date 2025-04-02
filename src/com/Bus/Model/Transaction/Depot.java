package com.Bus.Model.Transaction;

import java.util.Date;

import com.Bus.Model.Compte.Compte;

/*
 * Représente une transaction de type dépôt.
 */

public class Depot extends Transaction {

	private static final long serialVersionUID = 7708681526834515575L;
	
	/*
	 * Constructeur de la classe Depot
	 */
	public Depot(Compte compte, Date date, double montant) {
        super(compte, date, montant);
        this.setType(Transaction_type.deposer); // Spécifie que c'est un dépôt
    }
	
	/*
     * Exécute la transaction de dépôt, ajoutant le montant au solde du compte.
     */
    @Override
    public void executer() {
        // Logique spécifique au dépôt : ajouter le montant au compte 
    }
    
    /*
     * Redéfinition de la méthode toString pour afficher la transaction de dépôt.
     */
    @Override
    public String toString() {
        return super.toString() + " | Type: Dépôt";
    }
}
