package com.buisness.transaction;

import java.util.Date;

public class Depot extends Transaction {

	// -- 1 -- 
	// UID pour la sérialisation
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7002909453509148236L;
	
	
	// -- 2 --
	// Constructeurs
	
	public Depot() {
        super();
    }

    public Depot(int id, Date date, double montant, String numeroCompte) {
        super(id, date, montant, numeroCompte);
    }
    
    
    // -- 3 --
    // Implémentation de la méthode abstraite
    @Override
    public void executer() {
    	// Exemple en attendant de rattacher les classes "Transaction" au projet principal 
        System.out.println("Dépôt de " + getMontant() + "$ effectué sur le compte " + getNumeroCompte());
    }
    
    
    // -- 4 --
    // Override pour le formatage
    @Override
    public String toString() {
        return "Dépôt : " + super.toString();
    }
}
