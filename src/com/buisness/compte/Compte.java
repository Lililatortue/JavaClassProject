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
	protected String compteId;
	//constructor
	/*
	 * set la date du compte a aujourd'hui a etre utiliser pour creer un compte;
	 */
		public Compte(Client clientId, double solde) {
			this.clientId = clientId.getId();
			this.setSolde(solde);
			this.dateOuverture = LocalDate.now();	
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
	
	//setters
		public abstract void setCompteId(Client clientId);
		
		private void setSolde(double solde) {
			this.solde = solde;
		}
			
		private void setDateOuverture(LocalDate dateOuverture) {
			this.dateOuverture = dateOuverture;
		}

	//getters
		public String getCompteId() {
			return this.compteId;
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
		return 	"------------------------------------------------"+
			    "\n\tDate ouverture du compte: "+this.dateOuverture+
			    "\n\tSolde: "+this.solde;
		}

	
}
