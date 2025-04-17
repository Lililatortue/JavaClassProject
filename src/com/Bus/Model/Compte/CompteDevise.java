package com.Bus.Model.Compte;

/*
 * Représente un compte bancaire en devise étrangère.
 * 
 * Ce type de compte permet de gérer des opérations (dépôts et retraits)
 * dans une devise étrangère, en appliquant un taux de change via une instance Devise.
 * Le solde est exprimé en devise locale (convertie automatiquement à chaque opération).
 */
public class CompteDevise extends Compte {

	private static final long serialVersionUID = 826859753794501933L;
	
	// Devise associée à ce compte, contenant le taux de change
	private Devise devise;
	
	/**
 	 * Constructeur de la classe CompteDevise
 	 * 
 	 * @param clientId
 	 * @param solde
 	 * @param devise
 	 */
	public CompteDevise(int clientId, double solde,Devise devise) {
		super(clientId, solde,CompteType.DEV);
		this.devise = devise; // Associe la devise au compte
	}
	
	/**
 	 * Constructeur de copie
 	 * 
 	 * @param compte
 	 */
	public CompteDevise(CompteDevise compte) {
		super(compte);
		this.devise = compte.devise; // Associe la devise au compte
	}

	/**
 	 * Dépose un montant dans le compte en convertissant la somme selon le taux de change.
 	 *
 	 * @param montant - Montant à déposer (exprimé dans la devise étrangère)
 	 */
	@Override
	public void deposer(double montant) {
		this.solde +=montant * devise.exchangeRate;
	}
	
	/**
 	 * Retire un montant du compte, exprimé dans la devise locale
 	 * Le montant retourné est reconverti en devise étrangère
 	 *
 	 * @param montant - Montant à retirer (en devise locale)
 	 * @return le montant équivalent dans la devise étrangère
 	 */
	@Override
	public double retirer(double montant) throws Exception {
		this.solde -=montant ;
		return montant / devise.exchangeRate;
	}
	
	/**
 	 * 
 	 * @return la devise associée à ce compte
 	 */
	public Devise getDevise() {
		return this.devise;
	}
}

