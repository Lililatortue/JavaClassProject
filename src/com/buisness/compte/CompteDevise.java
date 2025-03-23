package com.buisness.compte;

public class CompteDevise extends Compte {

	/**
	 * 
	 */
	private static final long serialVersionUID = 826859753794501933L;
	private Devise devise;
	public CompteDevise(String type, double solde,Devise devise) {
		super(type, solde);
		this.devise = devise;
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

