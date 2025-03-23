package com.compte;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
/*
 * Class abstraite qui encapsule un compte de banque, 
 * Elle set le numero unique du compte 
 * Elle set le solde du compte 
 * Elle set la date de l'ouverture
 * */
public abstract class Compte {
	private String numero;
	private LocalDate dateOuverture;
	protected double solde;
	//constructor
	/*
	 * set la date du compte a aujourd'hui a etre utiliser pour creer un compte;
	 */
		public Compte(String numero, double solde) {
			setNumero(numero);
			setSolde(solde);
			this.dateOuverture = LocalDate.now();	
		}
	
	/*
	 * peux set la date a utiliser quand nous recevons les donner;
	 */
		protected Compte(String numero, double solde,LocalDate dateOuverture) {
			this.setNumero(numero);
			this.setSolde(solde);
			this.setDateOuverture(dateOuverture);
		}
		
	//fonction abstraite
		protected abstract void deposer(double montant);
		
		protected abstract double retirer(double montant) throws Exception;
	
	//setters
		private void setNumero(String numero) {
			this.numero = numero;
		}
			
		private void setSolde(double solde) {
			this.solde = solde;
		}
			
		private void setDateOuverture(LocalDate dateOuverture) {
			this.dateOuverture = dateOuverture;
		}

	//getters
		public String getNumero() {
			return this.numero;
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
		return "Numero de compte: "+this.numero+
			   "Date ouverture du compte: "+this.dateOuverture+
			   "Solde: "+this.solde;
		}

	
}
