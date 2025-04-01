package com.buisness.compte;

import com.buisness.client.Client;

/*
 * Représente un compte bancaire en devise étrangère.
 * 
 * Cette classe permet de gérer un compte dont le solde est exprimé dans une devise spécifique.
 * Elle inclut des fonctionnalités de dépôt et de retrait, avec des conversions basées sur le taux de change de la devise.
 * 
 * Le compte de devise utilise une instance de la classe Devise pour déterminer le taux de change applicable
 * lors des opérations financières.
 */

public class CompteDevise extends Compte {

	private static final long serialVersionUID = 826859753794501933L;
	
	// Devise associée à ce compte
	private Devise devise;
	
	/*
	 * Constructeur pour initialiser un compte bancaire en devise étrangère.
	 */
	public CompteDevise(Client clientId, String type, double solde,Devise devise) {
		super(clientId, solde);
		setCompteId(clientId); // Définit l'identifiant du compte
		this.devise = devise; // Associe la devise au compte
	}
	
	// MÉTHODES DE GESTION DU COMPTE
	/*
	 * Définit l'identifiant du compte de devise.
	 * L'identifiant est généré en concaténant "DEV" avec l'identifiant du client.
	 */
	@Override
	public void setCompteId(Client clientId) {
		this.compteId = "DEV"+clientId.getId();
	}

	/*
	 * Dépôt d'une certaine somme sur le compte en devise.
	 * Le montant déposé est converti selon le taux de change de la devise avant d'être ajouté au solde.
	 */
	@Override
	protected void deposer(double montant) {
		this.solde +=montant * devise.exchangeRate;
	}

	/*
	 * Retrait d'une certaine somme du compte en devise.
	 * Le montant retiré est d'abord soustrait du solde, puis converti en devise de retrait en fonction du taux de change.
	 */
	@Override
	protected double retirer(double montant) throws Exception {
		this.solde -=montant ;
		return montant / devise.exchangeRate;
	}
}

