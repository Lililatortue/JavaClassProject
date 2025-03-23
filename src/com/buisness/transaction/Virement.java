package com.buisness.transaction;

import java.util.Date;

public class Virement extends Transaction {

	// -- 1 -- 
	// UID pour la sérialisation
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8322941129702292050L;
	
	
	// -- 2 --
	// Déclaration de l'attribut privé spécifique à la classe Virement
	
	private String compteDestinataire;
	
	
	// -- 3 --
	// Méthodes publiques getters / setters
	
	public String getCompteDestinataire() { return compteDestinataire; }
    public void setCompteDestinataire(String compteDestinataire) { this.compteDestinataire = compteDestinataire; }
    
    
    // -- 4 --
 	// Constructeurs
    
    public Virement() {
        super();
        this.compteDestinataire = "C000";
    }

    public Virement(int id, Date date, double montant, String numeroCompte, String compteDestinataire) {
        super(id, date, montant, numeroCompte);
        this.compteDestinataire = compteDestinataire;
    }
    
    
    // -- 5 -- 
    // Implémentation de la méthode abstraite
    @Override
    public void executer() {
    	// Exemple en attendant de rattacher les classes "Transaction" au projet principal 
        System.out.println("Virement de " + getMontant() + "$ du compte " + getNumeroCompte() + 
                           " vers le compte " + compteDestinataire);
    }
    
    
    // -- 6 --
    // Override pour le formatage
    
    public String toString() {
    	return "Virement : " + super.toString() + " -> Destinataire: " + compteDestinataire;
    }

}
