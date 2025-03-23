package com.compte;

public class CompteDevise extends Compte {

	private Devise devise;
	public CompteDevise(String n, double s,Devise d) {
		super(n, s);
		this.devise = d;
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

