package com.Bus.Model.Compte;


import java.io.Serializable;
import java.time.LocalDate;


/*
 * Classe abstraite représentant un compte bancaire.
 * Elle contient les informations essentielles liées à un compte bancaire, telles que le solde,
 * la date d'ouverture, le type de compte et l'identifiant unique du client auquel il est associé.
 * 
 * Elle sert de classe de base pour des types de comptes spécifiques.
 */
public abstract class Compte implements Serializable{	
	
	private static final long serialVersionUID = -5552236969165700973L;
	
	// Identifiant unique du client associé au compte
	private int clientId;
	
	// Date d'ouverture du compte
	private LocalDate dateOuverture;
	
	// Solde actuel du compte
	protected double solde;

	// Type de compte
	protected CompteType type;
	
	/**
 	 * Constructeur de la classe Compte
 	 * Initialise le solde et définit la date d'ouverture à la date actuelle.
 	 * 
 	 * @param clientId
 	 * @param solde
 	 * @param type
 	 */
	public Compte(int clientId, double solde,CompteType type) {
		this.clientId = clientId;
		this.setSolde(solde);
		this.setDateOuverture(LocalDate.now());	
		this.type=type;
	}

	/**
 	 * Constructeur de copie
 	 * 
 	 * @param compte
 	 */
	public Compte(Compte compte) {
		this.clientId = compte.clientId;
		this.solde = compte.solde;
		this.dateOuverture = compte.dateOuverture;
		this.type = compte.type;
	}
	
	/**
 	 * 
 	 * @return le solde actuel du compte
 	 */
	public final double getSolde() {
		return this.solde;
	};
	
	/**
 	 * 
 	 * @return la date d'ouverture du compte
 	 */
	public LocalDate getDateOuverture() {
		return this.dateOuverture;
	};
	
	/**
 	 * 
 	 * @return l'identifiant du client associé à ce compte
 	 */
	public int getClientId(){
		return this.clientId;
	} 
	
	/**
 	 * 
 	 * @return le type de compte
 	 */
	public CompteType getType(){
		return this.type;
	} 
	
	/**
 	 * Modifie le solde du compte
 	 * 	
 	 * @param solde - Nouveau solde
 	 */
	public void setSolde(double solde) {
		this.solde = solde;
	}
		
	/**
 	 * Modifie la date d'ouverture du compte
 	 * 
 	 * @param dateOuverture - Date d'ouverture
 	 */
	private void setDateOuverture(LocalDate dateOuverture) {
		this.dateOuverture = dateOuverture;
	}
	
	// MÉTHODES ABSTRAITES
	
	/**
 	 * Effectue un dépôt sur le compte
 	 * 
 	 * @param montant - Montant à déposer
 	 * @throws Exception - Si le montant est invalide ou si une règle métier est violée
 	 */	
	public abstract void deposer(double montant) throws Exception;
	
	/**
 	 * Effectue un retrait sur le compte
 	 * 
 	 * @param montant - Montant à retirer
 	 * @return Montant réellement retiré
 	 * @throws Exception - Si le montant est invalide ou si le solde est insuffisant
 	 */
	public abstract double retirer(double montant) throws Exception;
	
	/**
 	 * 
 	 * @return une représentation textuelle du compte
 	 */
	@Override
	public String toString() {
		return 	"Client Id: "+clientId+" type de compte: "+type;
	}	
}	

