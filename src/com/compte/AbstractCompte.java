package com.compte;

import java.time.LocalTime;
import java.time.Month;
/*
 * Classe abstraite qui encapsule un compte de banque, 
 * Elle set le numero unique du compte 
 * Elle set le solde du compte 
 * Elle set la date de l'ouverture
 * */
public abstract class AbstractCompte {
	String numero;
	Double solde;
	LocalTime dateOuverture;
	
	/*
	 * set la date du compte a aujourd'hui a etre utiliser pour creer un compte;
	 */
	public AbstractCompte(String numero, Double solde) {
		setNumero(numero);
		setSolde(solde);
		this.dateOuverture = LocalTime.now();
		
	}
	
	/*
	 * peux set la date a utiliser quand nous recevons les donner;
	 */
	public AbstractCompte(String numero, Double solde,LocalTime dateOuverture) {
		setNumero(numero);
		setSolde(solde);
		setDateOuverture(dateOuverture);
	}
	
	public void depose(double montant) throws Exception {
		if(montant>0)
			this.solde+=montant;
		else
			throw new Exception("invalide amount: can't be under or equal to 0.");
	};
	
	public void retirer(double montant) throws Exception {
		if(this.solde-montant>0)
			this.solde-=montant;
		else
			throw new Exception("invalide amount: amount ask is to big\nAmount asked: "+montant);
	};
	
	//getters
	public String getNumero() {
		return this.numero;
	};
	public final Double getSolde() {
		return this.solde;
	};
	
	//setters
	private void setNumero(String numero) {
		this.numero = numero;
	}
	
	private void setSolde(Double solde) {
		this.solde = solde;
	}
	
	private void setDateOuverture(LocalTime dateOuverture) {
		this.dateOuverture = dateOuverture;
	}

	
}
