package com.Bus.Model.Compte;


import java.io.Serializable;
import java.time.LocalDate;

import com.Bus.Model.Client.Client;

 /*
 * Classe abstraite représentant un compte bancaire.
 * Elle contient les informations essentielles liées à un compte bancaire, telles que le solde,
 * la date d'ouverture, et l'identifiant unique du compte.
 * 
 * La classe définit également des méthodes abstraites pour gérer les opérations de dépôt et de retrait sur le compte.
 * 
 * La gestion du solde et de la date d'ouverture est également incluse, avec la possibilité de définir des valeurs personnalisées
 * ou de les initialiser par défaut (date d'ouverture à la date actuelle).
 */
public abstract class Compte implements Serializable{	
	
	private static final long serialVersionUID = -5552236969165700973L;
	
	// Identifiant unique du client associé au compte
	private int clientId;
	
	// Date d'ouverture du compte
	private LocalDate dateOuverture;
	
	// Solde du compte
	protected double solde;
	// type du compte
	protected CompteType type;
	
	 /*
	 * Constructeur de la classe Compte qui initialise le solde et la date d'ouverture à la date actuelle.
	 */
	public Compte(int clientId, double solde,CompteType type) {
		this.clientId = clientId;
		this.setSolde(solde);
		this.dateOuverture = LocalDate.now();	
		this.type=type;
	}

	//prototype
	public Compte(Compte compte) {
		this.clientId = compte.clientId;
		this.solde = compte.solde;
		this.dateOuverture = compte.dateOuverture;
		this.type = compte.type;
	}
	
	
	public final double getSolde() {
		return this.solde;
	}; // Retourne le solde actuel du compte
	
	public LocalDate getDateOuverture() {
		return this.dateOuverture;
	}; // Retourne la date d'ouverture du compte
	
	public int getClientId(){
		return this.clientId;
	} // Retourne l'identifiant du client associé au compte
	
	public CompteType getType(){
		return this.type;
	} 
	
		
	private void setSolde(double solde) {
		this.solde = solde;
	} // Définit le solde du compte
			
	private void setDateOuverture(LocalDate dateOuverture) {
		this.dateOuverture = dateOuverture;
	} // Définit la date d'ouverture du compte
	
	// MÉTHODES ABSTRAITES
	protected abstract void deposer(double montant);
	protected abstract double retirer(double montant) throws Exception;
	
	// Affiche l'état du compte
	@Override
	public String toString() {
		return 	"------------------------------------------------"+
				"\n\tDate ouverture du compte: "+this.dateOuverture+
			    "\n\tSolde: "+this.solde;
	}	
}	

