package com.Bus.Model.Compte;


import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.DAL.Repository.Exception.InvariantException;


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
	
	//type
	private int compteId;
	
	// Identifiant unique du client associé au compte
	private int clientId;
	
	// Date d'ouverture du compte
	private LocalDate dateOuverture;
	
	// Solde du compte
	protected double solde;
	
	// type du compte
	protected CompteType type;
	
	/**
	 * Constructeur de la classe Compte qui initialise le solde et la date d'ouverture à la date actuelle.
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
	 * @param ResultSet afin de facilement creer des comptes
	 * @throws SQLException 
	 */
	public Compte(ResultSet rs) throws SQLException {
		this.compteId = rs.getInt("CPT_NUMERO");
		this.clientId = rs.getInt("CLI_ID");
		this.solde = rs.getInt("CPT_SOLDE");
		this.dateOuverture = LocalDate.parse(rs.getString("CPT_DATE_OUV").substring(0, 10)) ;
		this.type = CompteType.valueOf(rs.getString("CPT_TYPE"));
	}
	/**
	 * prototype
	 * @param compte
	 * @throws SQLException 
	 */
	public Compte(Compte compte) {
		this.compteId = compte.compteId;
		this.clientId = compte.clientId;
		this.solde = compte.solde;
		this.dateOuverture = compte.dateOuverture;
		this.type = compte.type;
	}
	
	// Retourne l'identifiant du client associé au compte
	public int getCompteId(){
		return this.compteId;
	} 
	
	// Retourne l'identifiant du client associé au compte
	public int getClientId(){
		return this.clientId;
	} 
	
	// Retourne le solde actuel du compte
	public final double getSolde() {
		return this.solde;
	}; 
	
	// Retourne la date d'ouverture du compte
	public LocalDate getDateOuverture() {
		return this.dateOuverture;
	}; 
	
	// Définit le type du compte
	public CompteType getType(){
		return this.type;
	} 
	
	// Retourne l'identifiant du client associé au compte
	public int setCompteId(int compteId){
		return this.compteId=compteId;
	} 
		
	// Définit le solde du compte	
	public void setSolde(double solde) {
		this.solde = solde;
	} 
			
	// Définit la date d'ouverture du compte
	private void setDateOuverture(LocalDate dateOuverture) {
		this.dateOuverture = dateOuverture;
	} 
	
	// MÉTHODES ABSTRAITES
	public abstract void deposer(double montant) throws InvariantException;
	public abstract double retirer(double montant) throws InvariantException;
	
	// Affiche l'état du compte
	@Override
	public String toString() {
		return 	"Client Id: "+clientId+" type de compte: "+type;
	}	
}	

