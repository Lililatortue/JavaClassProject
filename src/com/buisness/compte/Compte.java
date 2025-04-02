package com.buisness.compte;


import java.io.Serializable;
import java.time.LocalDate;

import com.buisness.client.Client;

/*
 * Class abstraite qui encapsule un compte de banque, 
 * Elle set le numero unique du compte 
 * Elle set le solde du compte 
 * Elle set la date de l'ouverture
 * */
public abstract class Compte implements Serializable{	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5552236969165700973L;
	private int clientId;
	private LocalDate dateOuverture;
	protected double solde;
	protected CompteType type;
	//constructor
	/*
	 * set la date du compte a aujourd'hui a etre utiliser pour creer un compte;
	 */
		public Compte(Client clientId, double solde, CompteType type) {
			this.clientId = clientId.getId();
			this.setSolde(solde);
			this.dateOuverture = LocalDate.now();	
			this.type = type;
		}
	
	/*
	 * peux set la date a utiliser quand nous recevons les donner;
	 */
		protected Compte(Client clientId, double solde,LocalDate dateOuverture) {
			this.clientId = clientId.getId();
			this.setSolde(solde);
			this.setDateOuverture(dateOuverture);
		}
		
	//fonction abstraite
		protected abstract void deposer(double montant);
		
		protected abstract double retirer(double montant) throws Exception;
		
		private void setSolde(double solde) {
			this.solde = solde;
		}
			
		private void setDateOuverture(LocalDate dateOuverture) {
			this.dateOuverture = dateOuverture;
		}

	//getters
		public CompteType getType() {
			return this.type;
		};
		
		public final double getSolde() {
			return this.solde;
		};
		
		public LocalDate getDateOuverture() {
			return this.dateOuverture;
		};
		
		public int getClientId(){
			return this.clientId;
		}
	//state
	@Override	
		public String toString() {
		return 	"Compte id: "+this.type;

		}

	
}
