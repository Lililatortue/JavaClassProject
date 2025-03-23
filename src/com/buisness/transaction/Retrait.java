package com.buisness.transaction;

import java.util.Date;

public class Retrait extends Transaction {

	// -- 1 -- 
	// UID pour la sérialisation
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7375918968574094175L;
	
	
	// -- 2 --
	// Constructeurs
	
	public Retrait() {
        super();
    }

    public Retrait(int id, Date date, double montant, String numeroCompte) {
        super(id, date, montant, numeroCompte);
    }
    
    
    // -- 3 --
    // Implémentation de la méthode abstraite
    
    @Override
    public void executer() {
    	// Exemple en attendant de rattacher les classes "Transaction" au projet principal 
        System.out.println("Retrait de " + getMontant() + "$ effectué sur le compte " + getNumeroCompte());
    }
    
    
    // -- 4 --
    // Override pour le formatage
    
    public String toString() {
        return "Retrait : " + super.toString();
    }
}
