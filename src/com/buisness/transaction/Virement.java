package com.buisness.transaction;

import com.buisness.compte.Compte;
import java.util.Date;

/*
 * Représente une transaction de type virement entre deux comptes.
 */

public class Virement extends Transaction {

	private static final long serialVersionUID = 7029230432078781532L;
	
	// Compte bénéficiaire du virement
	private Compte compteDestinataire;
	
	/*
	 * Constructeur de la classe Virement
	 */
	public Virement(Compte compteSource, Compte compteDestinataire, Date date, double montant) {
        super(compteSource, date, montant);
        this.compteDestinataire = compteDestinataire;
        this.setType(Transaction_type.virer); // Spécifie que c'est un virement
    }
	
	/*
     * Exécute la transaction de virement, retirant le montant du compte source
     * et l'ajoutant au compte destinataire.
     */
    @Override
    public void executer() {
        // Logique du virement : retirer du compte source et ajouter au compte destinataire
    }
    
    /*
     * Redéfinition de la méthode toString pour afficher la transaction de virement.
     */
    @Override
    public String toString() {
        return super.toString() + " | Type: Virement | Compte destinataire: " + compteDestinataire.getCompteId();
    }
}
