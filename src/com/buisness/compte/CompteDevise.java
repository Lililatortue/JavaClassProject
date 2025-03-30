package com.buisness.compte;

import com.buisness.client.Client;

public class CompteDevise extends Compte {

	/**
	 * 
	 */
	private static final long serialVersionUID = 826859753794501933L;
	private Devise devise;
	public CompteDevise(Client clientId, String type, double solde,Devise devise) {
		super(clientId, solde);
		setCompteId(clientId);
		this.devise = devise;
	}
	@Override
	public void setCompteId(Client clientId) {
		this.compteId = "DEV"+clientId.getId();
		
	}

	@Override
	protected void deposer(double montant) {
		this.solde +=montant * devise.exchangeRate;
		
	}

	@Override
	protected double retirer(double montant) throws Exception {
		this.solde -=montant ;
		return montant / devise.exchangeRate;
	}

	

	

}

