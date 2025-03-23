package com.buisness.compte;


import java.io.Serializable;
import java.time.LocalDate;

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
	
	private String type;
	private LocalDate dateOuverture;
	protected double solde;
	//constructor
	/*
	 * set la date du compte a aujourd'hui a etre utiliser pour creer un compte;
	 */
		public Compte(String type, double solde) {
			setType(type);
			setSolde(solde);
			this.dateOuverture = LocalDate.now();	
		}
	
	/*
	 * peux set la date a utiliser quand nous recevons les donner;
	 */
		protected Compte(String type, double solde,LocalDate dateOuverture) {
			this.setType(type);
			this.setSolde(solde);
			this.setDateOuverture(dateOuverture);
		}
		
	//fonction abstraite
		protected abstract void deposer(double montant);
		
		protected abstract double retirer(double montant) throws Exception;
	
	//setters
		private void setType(String numero) {
			this.type = numero;
		}
			
		private void setSolde(double solde) {
			this.solde = solde;
		}
			
		private void setDateOuverture(LocalDate dateOuverture) {
			this.dateOuverture = dateOuverture;
		}

	//getters
		public String getType() {
			return this.type;
		};
		
		public final double getSolde() {
			return this.solde;
		};
		
		public LocalDate getDateOuverture() {
			return this.dateOuverture;
		};
	
	//state
	@Override	
		public String toString() {
		return 	"------------------------------------------------"+
				"\n\tType de compte: "+this.type+
			    "\n\tDate ouverture du compte: "+this.dateOuverture+
			    "\n\tSolde: "+this.solde;
		}

	
}
